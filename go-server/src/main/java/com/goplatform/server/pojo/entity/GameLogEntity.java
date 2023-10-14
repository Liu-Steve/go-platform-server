package com.goplatform.server.pojo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 对应game log表，用于记录对局信息
 */
@Data
@Entity
@Table(name = "GO_GAME_LOG")
public class GameLogEntity {
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "OPPONENT_ID")
    private Long opponentId;

    @Column(name = "OPPONENT_NAME")
    private String opponentName;

    /**
     * 执棋颜色
     * 0：白色
     * 1：黑色
     */
    @Column(name = "COLOR")
    private Long color;

    @Column(name = "CHESSBOARD")
    private String chessBoard;

    /**
     * 对局结果
     * 0：胜利
     * 1：失败
     * 2：平局
     */
    @Column(name = "RESULT")
    private Long result;

    @Column(name = "BEGIN_DATE")
    private Date beginDate;
}
