package com.goplatform.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.goplatform.server.exception.ExceptionEnum;
import com.goplatform.server.exception.GoServerException;
import com.goplatform.server.manager.Scheduler;
import com.goplatform.server.pojo.constant.ChessBoardStatus;
import com.goplatform.server.pojo.constant.Constants;
import com.goplatform.server.pojo.constant.Player;
import com.goplatform.server.pojo.domain.*;
import com.goplatform.server.repository.UserRepository;
import com.goplatform.server.service.ChessBoardService;
import com.goplatform.server.service.KataService;
import com.goplatform.server.utils.PublicUtil;
import com.goplatform.server.websocket.ChessWebSocketHandler;
import com.goplatform.server.websocket.WebSocketResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ChessBoardServiceImpl implements ChessBoardService {

    public static final int OVER_STOP_ONCE_MODE = 0;

    public static final int OVER_RESIGN_MODE = 1;

    public static final String RESULT_MODE = "mode";

    public static final String RESULT_WINNER = "winner";


    private static final Logger logger = LoggerFactory.getLogger(ChessBoardServiceImpl.class);

    @Resource
    private UserRepository userRepository;
    @Resource
    private Scheduler scheduler;

    @Resource
    private KataService kataService;

    @Override
    public Room createChessBoard(Long userId, Long roomId, ChessBoardConfig chessBoardConfig) {

        logger.debug("user: {} begin to create ChessBoard", roomId);
        // 1、判断用户是否有权限来创建棋盘
        PublicUtil.checkUserIdValid(userId, userRepository);
        Room room = scheduler.getRoom(roomId);
        if (!room.getCreateUserId().equals(userId)) {
            throw new GoServerException(ExceptionEnum.CHESS_USER_PERMISSION_DENY);
        }
        if (room.getPersonCount() != 2) {
            throw new GoServerException(ExceptionEnum.CHESS_ROOM_NOT_FILL);
        }

        // 2、补全棋盘配置，并放到room里面
        ChessBoardConfig config = new ChessBoardConfig();
        config.init(chessBoardConfig, roomId);
        room.setChessBoardConfig(config);

        // 3、初始化棋盘
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.init(config);
        room.setChessBoard(chessBoard);
        // 首先初始化AI
        kataService.start(roomId);
        // 4、如果是PVP，通知黑方下棋，白方等待
        if (!scheduler.isKataRoom(roomId)) {
            ChessWebSocketHandler.sendResult(config.getBlackPlayerId(), chessBoard, WebSocketResult.CHESS_START);
            ChessWebSocketHandler.sendResult(config.getWhitePlayerId(), chessBoard, WebSocketResult.CHESS_WAIT);
            logger.info("user: {} create ChessBoard success", roomId);
            return room;
        }
        // 黑方是用户则通知用户下棋
        if (Objects.equals(room.getChessBoardConfig().getBlackPlayerId(), userId)) {
            ChessWebSocketHandler.sendResult(userId, chessBoard, WebSocketResult.CHESS_START);
        } else {
            // 如果用户是白方则通知用户等待，并且AI下棋
            ChessWebSocketHandler.sendResult(userId, chessBoard, WebSocketResult.CHESS_WAIT);
            // 然后AI模仿用户下棋，ws通知前端
            ChessDrop chessDrop = kataService.gen(roomId, Constants.BLACK);
            kataDropChess(userId, roomId, chessDrop, Constants.BLACK);
        }
        logger.info("user: {} create ChessBoard success", roomId);
        return room;
    }

    /**
     * 下棋操作
     *
     * @param userId    下棋用户
     * @param roomId    下棋房间
     * @param chessDrop 落子信息
     * @return
     */
    @Override
    public ChessBoard dropChess(Long userId, Long roomId, ChessDrop chessDrop) {
        /*
         * 1、校验用户是否有权限下棋（在房间中，轮到自己）
         * 2、具体下棋逻辑
         * 3、WS通知自己停止，通知对手下棋
         */
        // 1、校验用户是否有权限下棋（在房间中，轮到自己）
        ChessWebSocketHandler.checkWebSocketConnection(userId);
        Room room = scheduler.getRoom(roomId);
        int type = checkDropPermission(userId, room, true);
        room.getChessBoard().setStatus(ChessBoardStatus.Going);
        // 2、具体下棋逻辑
        int r = chessDrop.getDropPosition().get(0);
        int c = chessDrop.getDropPosition().get(1);
        logger.debug("user {} begin to drop ({}, {})", userId, r, c);
        if (!doOneMove(r, c, type, room.getChessBoard())) {
            logger.debug("user {} drop ({}, {}) failed", userId, r, c);
            throw new GoServerException(ExceptionEnum.CHESS_DROP_FAILED);
        }
        // 3、WS通知自己停止，通知对手下棋
        logger.info("user {} drop ({}, {}) success!", userId, r, c);
        if (!scheduler.isKataRoom(roomId)) {
            String userColor = type == ChessBoard.BLACK ? Constants.BLACK : Constants.WHITE;
            kataService.play(roomId, chessDrop, userColor);
            switchPlayer(type, room);
            logger.info("user {} drop ({}, {}) success!", userId, r, c);
        } else {
            String userColor = type == ChessBoard.BLACK ? Constants.BLACK : Constants.WHITE;
            String kataColor = type == ChessBoard.BLACK ? Constants.WHITE : Constants.BLACK;
            // 如果是人机对战，则AI开始下棋
            // 首先通知用户等待
            ChessWebSocketHandler.sendResult(userId, room.getChessBoard(), WebSocketResult.CHESS_WAIT);
            // 然后将用户下棋位置输入到AI
            kataService.play(roomId, chessDrop, userColor);
            // 随后AI下棋
            ChessDrop kataChessDrop = kataService.gen(roomId, kataColor);
            // AI停一手
            if (kataChessDrop == null) {
                kataStopOnce(userId, roomId);
            } else {
                kataDropChess(userId, roomId, kataChessDrop, kataColor);
            }

        }

        return room.getChessBoard();
    }

    /**
     * AI 下棋
     *
     * @return
     */
    public Boolean kataDropChess(Long userId, Long roomId, ChessDrop chessDrop, String color) {
        Room room = scheduler.getRoom(roomId);
        room.getChessBoard().setStatus(ChessBoardStatus.Going);
        int type = color.equals(Constants.BLACK) ? ChessBoard.BLACK : ChessBoard.WHITE;

        int r = chessDrop.getDropPosition().get(0);
        int c = chessDrop.getDropPosition().get(1);
        logger.debug("AI begin to drop ({}, {}) color: {}", r, c, color);
        if (!doOneMove(r, c, type, room.getChessBoard())) {
            // 如果AI无法下棋，则AI直接认输，通知用户即可
            logger.info("AI drop ({}, {}) color: {} failed human win!", r, c, color);
            ChessWebSocketHandler.sendResult(userId, null, WebSocketResult.CHESS_READY_STOP);
            String winnerColor = type == ChessBoard.BLACK ? Constants.BLACK : Constants.WHITE;
            Object res = buildRes(roomId, OVER_RESIGN_MODE, winnerColor);
            ChessWebSocketHandler.sendResult(userId, res, WebSocketResult.CHESS_STOP);
            return false;
        }
        // 下棋成功，通知用户下棋
        ChessWebSocketHandler.sendResult(userId, room.getChessBoard(), WebSocketResult.CHESS_START);
        logger.info("AI drop ({}, {}) color: {} success!", r, c, color);
        return true;
    }

    /**
     * 停一手操作
     * 只更新操作用户，并更新棋盘装填
     * @param userId 操作用户
     * @param roomId 操作房间
     * @return 棋盘装填
     */
    @Override
    public ChessBoard stopOnce(Long userId, Long roomId) {
        logger.debug("user {} begin to stop once", roomId);
        ChessWebSocketHandler.checkWebSocketConnection(userId);
        Room room = scheduler.getRoom(roomId);
        int type = checkDropPermission(userId, room, true);
        room.getChessBoard().getRecord().add(new OneMove(type, new int[]{-1, -1}));
        // 如果人机对战中用户停了一手
        if (scheduler.isKataRoom(roomId)) {
            room.getChessBoard().setStatus(ChessBoardStatus.StopOnce);
            if (type == ChessBoard.BLACK) {
                room.getChessBoard().setNowPlayer(Player.WHITE_PLAYER);
            } else if (type == ChessBoard.WHITE) {
                room.getChessBoard().setNowPlayer(Player.BLACK_PLAYER);
            }
            ChessWebSocketHandler.sendResult(userId, room.getChessBoard(), WebSocketResult.CHESS_WAIT);
            String kataColor = type == ChessBoard.BLACK ? Constants.WHITE : Constants.BLACK;
            ChessDrop kataChessDrop = kataService.gen(roomId, kataColor);
            if (kataChessDrop == null) {
                kataStopOnce(userId, roomId);
            } else {
                kataDropChess(userId, roomId, kataChessDrop, kataColor);
            }
            logger.info("user {} stop once success and AI drop over", userId);
            return room.getChessBoard();
        }
        // 如果对方已经停了一手，则判断两人是否要结束棋局
        if (room.getChessBoard().getStatus().equals(ChessBoardStatus.StopOnce)) {
            if (type == ChessBoard.BLACK) {
                room.getChessBoard().setNowPlayer(Player.WHITE_PLAYER);
            } else if (type == ChessBoard.WHITE) {
                room.getChessBoard().setNowPlayer(Player.BLACK_PLAYER);
            }
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getBlackPlayerId(), null, WebSocketResult.CHESS_READY_STOP);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getWhitePlayerId(), null, WebSocketResult.CHESS_READY_STOP);
            Object res = buildRes(roomId, OVER_STOP_ONCE_MODE, null);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getBlackPlayerId(), res, WebSocketResult.CHESS_STOP);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getWhitePlayerId(), res, WebSocketResult.CHESS_STOP);
            logger.info("user {} stop once success and over the game!", userId);
            return room.getChessBoard();
        }
        // 通知对手自己停一手，并也要告知自己，自己停了一手
        if (type == ChessBoard.BLACK) {
            room.getChessBoard().setNowPlayer(Player.WHITE_PLAYER);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getBlackPlayerId(), room.getChessBoard(), WebSocketResult.CHESS_STOP_ONCE);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getWhitePlayerId(), room.getChessBoard(), WebSocketResult.CHESS_STOP_ONCE_ANOTHER);
        } else if (type == ChessBoard.WHITE) {
            room.getChessBoard().setNowPlayer(Player.BLACK_PLAYER);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getBlackPlayerId(), room.getChessBoard(), WebSocketResult.CHESS_STOP_ONCE_ANOTHER);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getWhitePlayerId(), room.getChessBoard(), WebSocketResult.CHESS_STOP_ONCE);
        }
        // 并且设置房间状态为停一手
        room.getChessBoard().setStatus(ChessBoardStatus.StopOnce);
        // 并清除掉打劫信息
        room.getChessBoard().flushKo();
        logger.info("user {} stop once success!", userId);
        return room.getChessBoard();
    }

    /**
     * AI 停一手
     *
     * @param userId 与AI对战用户
     * @param roomId 房间ID
     */
    private void kataStopOnce(Long userId, Long roomId) {
        logger.info("AI begin to stop once and over the game!");
        Room room = scheduler.getRoom(roomId);
        int kataType = Objects.equals(userId, room.getChessBoardConfig().getWhitePlayerId()) ? ChessBoard.BLACK : ChessBoard.WHITE;
        room.getChessBoard().getRecord().add(new OneMove(kataType, new int[]{-1, -1}));
        if (room.getChessBoard().getStatus() == ChessBoardStatus.StopOnce) {
            ChessWebSocketHandler.sendResult(userId, null, WebSocketResult.CHESS_READY_STOP);
            Object res = buildRes(roomId, OVER_STOP_ONCE_MODE, null);
            ChessWebSocketHandler.sendResult(userId, res, WebSocketResult.CHESS_STOP);
            logger.info("AI  stop once success and over the game!");
            return;
        }
        room.getChessBoard().setStatus(ChessBoardStatus.StopOnce);
        ChessWebSocketHandler.sendResult(userId, room.getChessBoard(), WebSocketResult.CHESS_STOP_ONCE_ANOTHER);
    }

    private Object buildRes(Long roomId, int mode, String winner) {
        JSONObject object = new JSONObject();
        object.put(RESULT_MODE, mode); // 0表示双方停一手结束对局，1表示认输结束对局
        if (mode == OVER_STOP_ONCE_MODE) {
            KataCount res = kataService.endCount(roomId);
            object.put(Constants.WHITE, res.getWhite());
            object.put(Constants.BLACK, res.getBlack());
            if (res.getWhite() > res.getBlack() - 7.5) {
                object.put(RESULT_WINNER, Constants.WHITE);
            } else if (res.getWhite() < res.getBlack() - 7.5) {
                object.put(RESULT_WINNER, Constants.BLACK);
            } else {
                object.put(RESULT_WINNER, Constants.EQUAL);
            }
        } else {
            object.put(RESULT_WINNER, winner);
        }
        return object;
    }

    /**
     * 谁收到ws，并且mode为5，message.mode为1，就表示对面投降, 不用通知投降用户
     * 投降不需要轮到自己回合
     *
     * @param userId 投降用户
     * @param roomId 用户所在房间
     * @return 棋盘信息
     */
    @Override
    public ChessBoard overGameRequest(Long userId, Long roomId) {
        ChessWebSocketHandler.checkWebSocketConnection(userId);
        Room room = scheduler.getRoom(roomId);
        int type = checkDropPermission(userId, room, false);
        // 请求认输，使用websocket通知对面，更新棋盘状态，但不更新棋盘当前用户
        room.getChessBoard().setStatus(ChessBoardStatus.End);
        String winnerColor = type == ChessBoard.WHITE ? Constants.BLACK : Constants.WHITE;
        Object res = buildRes(roomId, OVER_RESIGN_MODE, winnerColor);
        // 如果不是人机对战，那就通知对方
        if (!scheduler.isKataRoom(roomId)) {
            if (type == ChessBoard.BLACK) {
                ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getWhitePlayerId(), res, WebSocketResult.CHESS_STOP);
            } else {
                ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getBlackPlayerId(), res, WebSocketResult.CHESS_STOP);
            }
        }
        logger.info("user {} resigned!", userId);
        return null;
    }

    /** 确认结束对局功能
     *
     * @param userId 确认用户
     * @param roomId 房间号
     * @return
     */
    @Override
    public ChessBoard overGameConfirm(Long userId, Long roomId) {
        ChessWebSocketHandler.checkWebSocketConnection(userId);
        Room room = scheduler.getRoom(roomId);
        int type = checkDropPermission(userId, room, false);
        // 请求认输，使用websocket通知对面，更新棋盘状态，但不更新棋盘当前用户
        room.getChessBoard().setStatus(ChessBoardStatus.OverRequest);

        return null;
    }

    /**
     * 获取当前棋盘的得分
     *
     * @param userId 用户Id
     * @param roomId 房间号
     * @return 结果
     */
    @Override
    public Object getChessBoardResult(Long userId, Long roomId) {
        ChessWebSocketHandler.checkWebSocketConnection(userId);
        Room room = scheduler.getRoom(roomId);
        checkDropPermission(userId, room, false);
        ChessWebSocketHandler.sendResult(userId, null, WebSocketResult.CHESS_READY_STOP);
        KataCount res = kataService.endCount(room.getRoomId());
        JSONObject object = new JSONObject();
        object.put(Constants.WHITE, res.getWhite());
        object.put(Constants.BLACK, res.getBlack());
        return object;
    }

    private void switchPlayer(int type, Room room) {
        if (type == ChessBoard.BLACK) {
            room.getChessBoard().setNowPlayer(Player.WHITE_PLAYER);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getBlackPlayerId(), room.getChessBoard(), WebSocketResult.CHESS_WAIT);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getWhitePlayerId(), room.getChessBoard(), WebSocketResult.CHESS_START);
        } else if (type == ChessBoard.WHITE) {
            room.getChessBoard().setNowPlayer(Player.BLACK_PLAYER);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getBlackPlayerId(), room.getChessBoard(), WebSocketResult.CHESS_START);
            ChessWebSocketHandler.sendResult(room.getChessBoardConfig().getWhitePlayerId(), room.getChessBoard(), WebSocketResult.CHESS_WAIT);
        }
    }

    private int checkDropPermission(Long userId, Room room, Boolean isNeedNow) {
        logger.debug("begin to check user {} permission in room {}", userId, room.getRoomId());
        PublicUtil.checkUserIdValid(userId, userRepository);
        if (room.getChessBoardConfig() == null || room.getChessBoard() == null) {
            logger.error("check user {} permission in room {} error chessboard not init", userId, room.getRoomId());
            throw new GoServerException(ExceptionEnum.CHESS_ROOM_NOT_INIT);
        }

        if (!Objects.equals(room.getCreateUserId(), userId) && !Objects.equals(room.getSecondUserId(), userId)) {
            logger.error("check user {} permission in room {} error user not inside room", userId, room.getRoomId());
            throw new GoServerException(ExceptionEnum.CHESS_USER_PERMISSION_DENY);
        }
        // 判断当前用户是黑棋还是白棋

        if (isNeedNow) {
            if (room.getChessBoardConfig().getWhitePlayerId().equals(userId)
                    && room.getChessBoard().getNowPlayer().equals(Player.WHITE_PLAYER)) {
                return ChessBoard.WHITE;
            }
            if (room.getChessBoardConfig().getBlackPlayerId().equals(userId)
                    && room.getChessBoard().getNowPlayer().equals(Player.BLACK_PLAYER)) {
                return ChessBoard.BLACK;
            }
        } else {
            return room.getChessBoardConfig().getWhitePlayerId().equals(userId) ? ChessBoard.WHITE : ChessBoard.BLACK;
        }

        throw new GoServerException(ExceptionEnum.CHESS_USER_PERMISSION_DENY);
    }


    private static final int[] dr = {-1, 0, 0, 1};
    private static final int[] dc = {0, -1, 1, 0};

    private int turnPlayerIntoChessboardColor(Player player) {
        if (player == Player.BLACK_PLAYER) {
            return ChessBoard.BLACK;
        } else {
            return ChessBoard.WHITE;
        }
    }

    private Player turnChessboardColorIntoPlayer(int player) {
        if (player == ChessBoard.WHITE) {
            return Player.WHITE_PLAYER;
        } else {
            return Player.BLACK_PLAYER;
        }
    }

    public boolean testKo(int r, int c, ChessBoard board) {
        // test if taking only one stone
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if (nr < 0 || nr >= board.getBoardSize() ||
                nc < 0 || nc >= board.getBoardSize() ||
                board.getBoard()[r][c] != board.getBoard()[nr][nc]) {
                continue;
            } else {
                return false;
            }
        }

        // compare with ko record
        if (!board.isOnKo()) {
            return false;
        }

        return board.getKoPos()[0] == r && board.getKoPos()[1] == c;
    }

    public int tryTake(int r, int c, ChessBoard board) { // if killed successfully, modify the board directly
        int numOfTaken = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if (nr < 0 || nr >= board.getBoardSize() ||
                    nc < 0 || nc >= board.getBoardSize() ||
                board.getBoard()[nr][nc] == board.getBoard()[r][c] ||
                board.getBoard()[nr][nc] == -1) {
                continue;
            }
            if (isNoLiberty(nr, nc, board) && !testKo(nr, nc, board)) {
                numOfTaken += take(nr, nc, board);
            }
        }

        return numOfTaken;
    }

    public boolean isNoLiberty(int r, int c, ChessBoard board) {
        board.flushFlag();

        boolean ret = true;
        int type = board.getBoard()[r][c];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});

        bfs:
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int tmp_r = tmp[0], tmp_c = tmp[1];
            if (posIsNotValid(tmp_r, tmp_c, board)) {
                continue;
            }
            board.getBoardFlag()[tmp_r][tmp_c] = true;
            for (int i = 0; i < 4; i++) {
                int nr = tmp_r + dr[i], nc = tmp_c + dc[i];
                if (posIsNotValid(nr, nc, board)) {
                    continue;
                }
                if (board.getBoard()[nr][nc] == type && !posIsNotValid(nr, nc, board)) {
                    q.offer(new int[]{nr, nc});
                } else if (board.getBoard()[nr][nc] == ChessBoard.EMPTY) {
                    ret = false;
                    break bfs;
                }
            }
        }
        return ret;
    }

    public int take(int r, int c, ChessBoard board) {
        board.flushFlag();

        int type = board.getBoard()[r][c];
        int ret = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int tmp_r = tmp[0], tmp_c = tmp[1];
            if (posIsNotValid(tmp_r, tmp_c, board)) {
                continue;
            }
            board.getBoardFlag()[tmp_r][tmp_c] = true;
            board.getBoard()[tmp_r][tmp_c] = ChessBoard.EMPTY;
            ret++;
            for (int i = 0; i < 4; i++) {
                int nr = tmp_r + dr[i], nc = tmp_c + dc[i];
                if (posIsNotValid(nr, nc, board)) {
                    continue;
                }
                if (board.getBoard()[nr][nc] == type) {
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return ret;
    }

    public boolean posIsNotValid(int r, int c, ChessBoard board) {
        if (r < 0 || r >= board.getBoardSize() ||
                c < 0 || c >= board.getBoardSize()) {
            return true;
        }
        return board.getBoardFlag()[r][c];
    }

    public boolean doOneMove(int r, int c, int type, ChessBoard board) {
        int[][] boardSave = new int[board.getBoardSize()][board.getBoardSize()];
        for (int i = 0; i < board.getBoardSize(); i++) {
            boardSave[i] = Arrays.copyOf(board.getBoard()[i], board.getBoardSize());
        }
        boolean isValidMove = false;
        int numOfTaken = 0;
        try {
            if (type != turnPlayerIntoChessboardColor(board.getNowPlayer())) {
                // not this player's turn to move
                return false;
            }
            if (board.getBoard()[r][c] != ChessBoard.EMPTY) {
                // this pos has been taken
                return false;
            }
            board.getBoard()[r][c] = type;
            numOfTaken = tryTake(r, c, board);
            if (numOfTaken != 0) {
                isValidMove = true;
                if (numOfTaken == 1) {
                    // activate ko
                    board.setOnKo(true);
                    board.setKoPos(new int[] {r, c});
                }
            }
            if (!isNoLiberty(r, c, board)) {
                isValidMove = true;
            }
        } catch (Exception e) {
            board.setBoard(boardSave);
        }
        if (isValidMove) {
            // flush ko
            if (numOfTaken != 1) {
                board.flushKo();
            }
            // update last move
            board.setLastPos(new int[] {r, c});
            // update records
            board.getRecord().add(new OneMove(type, new int[] {r, c}));
            // switch player
            board.setNowPlayer(
                    turnChessboardColorIntoPlayer(1 -
                            turnPlayerIntoChessboardColor(board.getNowPlayer())
                    ));
        } else {
            board.getBoard()[r][c] = ChessBoard.EMPTY;
        }
        board.setBoardFlag(null);
        return isValidMove;
    }



    //计算目数
    public Result countPoints(ChessBoard chessBoard){
        int[][] board=chessBoard.getBoard();
        /*
        int countWhite = 0;
        int countBlack= 0;

        // 遍历棋盘的每一行和每一列
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ChessBoard.WHITE) {
                    countWhite++;
                } else if (board[i][j] == ChessBoard.BLACK) {
                    countBlack++;
                }
            }
        }

        // 返回一个数组，其中第一个元素是0的数量，第二个元素是1的数量
        return Result.ok(new int[]{countWhite, countBlack});
         */


        //返回的是一个int[2]，记录{白方分数，黑方分数}
        //共同区域平分
        //return Result.ok(GoScoring.score2(board));
        //共同区域考虑包围关系不平分
        return Result.ok(GoScoring.score1(board));
    }


}



