package com.goplatform.katago.kata;

import com.goplatform.katago.exception.ExceptionEnum;
import com.goplatform.katago.exception.KataGoException;
import com.goplatform.katago.pojo.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.goplatform.katago.pojo.Constants.*;

public class KataAgent {

    //executable package
    String kataUrl = KATA_URL;
    //neural package
    String neuralUrl = NEURAL_URL;
    //config package
    String configUrl = CONFIG_URL;
    //search-param
    int countPointWidth = COUNT_POINT_WIDTH;
    int countPointDepth = COUNT_POINT_DEPTH;

    //kata-process
    ProcessBuilder builder;

    public List<String> dialog(Consumer<Writer> func) {
        List<String> res = new ArrayList<>();
        try {
            Process process = builder.start();
            List<IOException> exceptions = new ArrayList<>();

            Thread outputThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        res.add(line);
                    }
                } catch (IOException e) {
                    exceptions.add(e);
                }
            });
            outputThread.start();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            func.accept(writer);

            outputThread.join();
            process.waitFor();
            if (!exceptions.isEmpty()) {
                throw exceptions.get(0);
            }
        } catch (IOException | InterruptedException e) {
            throw new KataGoException(ExceptionEnum.KATA_EXE_FAIL, e.getMessage());
        }
        return res;
    }

    //init
    public String kataInit() {

        builder = new ProcessBuilder(kataUrl, "gtp", "-model", neuralUrl, "-config", configUrl);

        StringBuilder res = new StringBuilder();


        try {
            Process process = builder.start();

            // 处理进程的输出

            Thread outputThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        res.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            outputThread.start();


            // 向进程发送命令

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                //default 19*19
                writer.write("boardsize 19\n");
                writer.flush();

                writer.write("clear_board\n");
                writer.flush();


            } catch (IOException e) {
                e.printStackTrace();
            }


            // 等待输出线程结束,cai neng jin xing zhuxiangcheng
            //
            outputThread.join();

            // 等待进程结束
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        String finalRes = res.toString();
        String result;

        if (finalRes.equals("= = ")) {
            result = "init yes";
        } else {
            result = "init no";
        }

        return result;
    }


    //clear
    public String kataClear(Process process) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write("clear_board\n");
            writer.flush();
            //writer can't close here
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //setboard
    public String kataSetBoard(int boardsize, int[][] board, Player player, Process process) {
        try {
            // 向进程发送命令
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write("boardsize " + Integer.toString(boardsize) + "\n");
            writer.flush();
            //writer can't close here

            //drop history play
            for (int row = 0; row < boardsize; row++) {
                for (int col = 0; col < boardsize; col++) {
                    switch (board[row][col]) {
                        case -1:
                            break;
                        case 0:
                            writer.write("play white " + (char) ('A' + row) + col + "\n");
                            writer.flush();
                            break;
                        case 1:
                            writer.write("play black " + (char) ('A' + row) + col + "\n");
                            writer.flush();
                            break;
                        default:
                            break;
                    }
                }
            }
            if (Player.BLACK_PLAYER == player) {
                writer.write("play white pass\n");
                writer.flush();
            } else {
                writer.write("play black pass\n");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    //drop-predict
    public String kataDropPoint(int boardsize, int[][] board, Player player, int maxmoves, int visits) {
        ArrayList<String> res = new ArrayList<>();
        String nextMove = null;


        try {
            Process process = builder.start();
            // 处理进程的输出
            // thread is temp

            Thread outputThread1 = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        res.add(line);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            outputThread1.start();

            // 向进程发送命令
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                kataClear(process);
                kataSetBoard(boardsize, board, player, process);

                //predict
                boolean notFound = true;
                //re for the number
                while (notFound) {
                    //predict
                    writer.write("kata-analyze interval 200 maxmoves " + maxmoves + "\n");
                    writer.flush();
                    //jian ge tai xiao, res hui bei zang du ying xiang xiu gai
                    Thread.sleep(10 * 200);
                    writer.write("\n");
                    writer.flush();
                    Thread.sleep(500);

                    //find aprop visits
                    ArrayList<String> tempRes = new ArrayList<>(res);
                    int lastInfosIndex = Math.max(tempRes.size() - 2, 0);
                    if (tempRes.isEmpty()) {
                        Thread.sleep(1000);
                    } else {
                        int visitIndex = tempRes.get(lastInfosIndex).indexOf("visits ");
                        if (visitIndex == -1) {
                            continue;
                        }
                        int numberEndIndex = tempRes.get(lastInfosIndex).indexOf(" ", visitIndex + "visits ".length());
                        int maxVists = Integer.parseInt(tempRes.get(lastInfosIndex).substring(visitIndex + "visits ".length(), numberEndIndex));
                        if (maxVists > visits) {
                            //the first order is max winrate
                            int moveIndex = tempRes.get(lastInfosIndex).indexOf("move ");
                            int moveEndIndex = tempRes.get(lastInfosIndex).indexOf(" ", moveIndex + "move ".length());
                            nextMove = tempRes.get(lastInfosIndex).substring(moveIndex + "move ".length(), moveEndIndex);
                            notFound = false;
                        } else {
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 等待输出线程结束
            outputThread1.join();

            // 等待进程结束
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        String result = null;
        if (nextMove.matches("[A-Z]\\d+")) {
            result = nextMove;
        } else {
            result = "drop no";
        }

        return result;
    }


    //count-point
    public int[] kataCountPoint(int boardsize, int[][] board, Player player) {
        ArrayList<String> res = new ArrayList<>();
        int pointScore = 0;
        int antiPointScore = 0;


        try {
            Process process = builder.start();
            // 处理进程的输出
            // thread is temp

            Thread outputThread1 = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;

                    while ((line = reader.readLine()) != null) {
                        //one line for maxmoves moves in the order
                        //tongbu fangcun
                        synchronized (res) {
                            res.add(line);
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            outputThread1.start();


            // 向进程发送命令
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                //writer.write("kata-analyze "+" interval "+Integer.toString(200)+ " maxmoves "+Integer.toString(maxmoves)+" ownership true"+"\n");


                kataClear(process);
                kataSetBoard(boardsize, board, player, process);

                //predict
                boolean notFound = true;
                //System.out.println("find drop");
                //re for the number
                while (notFound) {
                    //System.out.println("finding\n");
                    writer.write("kata-analyze interval 200 maxmoves " + Integer.toString(countPointWidth) + " ownership true" + "\n");
                    //writer.write("kata-analyze interval 200 maxmoves "+Integer.toString(countPointWidth)+"\n");
                    writer.flush();
                    //jian ge tai xiao, res hui bei zang du ying xiang xiu gai
                    Thread.sleep(15 * 200);
                    writer.write("\n");
                    writer.flush();
                    Thread.sleep(500);

                    //System.out.println( countSubstringOccurrences(res.get(res.size()-1),"visit"));
                    //find aprop visits
                    ArrayList<String> tempRes = new ArrayList<>();
                    tempRes.addAll(res);
                    int lastInfosIndex = Math.max(tempRes.size() - 2, 0);
                    //System.out.println(res.size());
                    if (tempRes.isEmpty()) {
                        Thread.sleep(1000);
                    } else {
                        int visitIndex = tempRes.get(lastInfosIndex).indexOf("visits ");
                        //System.out.println("visitIndex:"+Integer.toString(visitIndex));
                        if (visitIndex == -1) {
                            //System.out.println("continue");
                            continue;
                        }
                        int numberEndIndex = tempRes.get(lastInfosIndex).indexOf(" ", visitIndex + "visits ".length());
                        int maxVists = Integer.parseInt(tempRes.get(lastInfosIndex).substring(visitIndex + "visits ".length(), numberEndIndex));
                        //System.out.println("visits:"+Integer.toString(maxVists));
                        if (maxVists > countPointDepth) {
                            //the first order is max winrate
                            //String[] lastNumberInfo=tempRes.get(lastInfosIndex).split("info");
                            //int ownerIndex=lastInfo[lastNumberInfo].indexOf("ownership ");
                            int ownerIndex = tempRes.get(lastInfosIndex).indexOf("ownership ");
                            //int ownerEndIndex=tempRes.get(lastInfosIndex).indexOf(" ",ownerIndex+"owner ".length());
                            String[] numberArray = tempRes.get(lastInfosIndex).substring(ownerIndex + "ownership ".length()).split(" ");
                            for (int i = 0; i < numberArray.length; i++) {
                                double p = Double.parseDouble(numberArray[i]);
                                if (p > 0.1) {
                                    pointScore += 1;
                                } else if (p < -0.1) {
                                    antiPointScore += 1;
                                }
                            }
                            notFound = false;
                        } else {
                            //res.clear();
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 等待输出线程结束
            outputThread1.join();

            // 等待进程结束
            //process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        int[] result = new int[2];
        result[player.ordinal()] = pointScore;
        result[1 - player.ordinal()] = antiPointScore;

        return result;
    }

    //destory-katago
    public String kataQuit() {

        StringBuilder res = new StringBuilder();

        try {
            Process process = builder.start();
            // 处理进程的输出
            // thread is temp

            Thread outputThread1 = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        res.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            outputThread1.start();

            // 向进程发送命令
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.write("quit\n");
                writer.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

            // 等待输出线程结束
            outputThread1.join();

            // 等待进程结束
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        String finalRes = res.toString();
        String result;

        if (finalRes.equals("= ")) {
            result = "quit yes";
        } else {
            result = "quit no";
        }

        return result;
    }

    public static int countSubstringOccurrences(String text, String substring) {
        int count = 0;
        int lastIndex = 0;

        while (lastIndex != -1) {
            lastIndex = text.indexOf(substring, lastIndex);

            if (lastIndex != -1) {
                count++;
                lastIndex += substring.length();
            }
        }

        return count;
    }

}
