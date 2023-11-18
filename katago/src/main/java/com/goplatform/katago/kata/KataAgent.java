package com.goplatform.katago.kata;

import com.goplatform.katago.exception.ExceptionEnum;
import com.goplatform.katago.exception.KataGoException;
import com.goplatform.katago.pojo.ChessDrop;
import com.goplatform.katago.pojo.KataCount;
import com.goplatform.katago.pojo.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.goplatform.katago.pojo.Constants.*;

public class KataAgent {
    //kata-process
    ProcessBuilder builder;
    Process process;
    BufferedReader inputReader;

    public void init() {
        try {
            builder = new ProcessBuilder(KATA_URL, "gtp", "-model", NEURAL_URL, "-config", CONFIG_URL);
            process = builder.start();
            inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // 初始设置
            cmd("boardsize " + BOARD_SIZE);
            cmd("clear_board");
            cmd("komi 0");
        } catch (IOException e) {
            throw new KataGoException(ExceptionEnum.KATA_EXE_FAIL, e.getMessage());
        }
    }

    public String cmd(String command) {
        try {
            process.getOutputStream().write((command + "\n").getBytes());
            process.getOutputStream().flush();
            String output = inputReader.readLine();
            String ignore = inputReader.readLine();
            return output;
        } catch (IOException e) {
            throw new KataGoException(ExceptionEnum.KATA_EXE_FAIL, e.getMessage());
        }
    }

    public void quit() {
        try {
            process.getOutputStream().write("quit\n".getBytes());
            process.getOutputStream().flush();
            process.waitFor();
            inputReader.close();
        } catch (IOException | InterruptedException e) {
            throw new KataGoException(ExceptionEnum.KATA_EXE_FAIL, e.getMessage());
        }
    }

    protected void colorValid(String color) {
        if ("black".equals(color) || "white".equals(color)) {
            return;
        }
        throw new KataGoException(ExceptionEnum.COLOR_ERROR, color);
    }

    /**
     * 用户通知 KataGo 落子
     *
     * @param color 用户颜色
     * @param drop 用户落子
     */
    public void play(String color, ChessDrop drop) {
        colorValid(color);
        cmd("play " + color + " " + ChessDrop.dropToString(drop));
    }

    /**
     * KataGo 生成新落子
     *
     * @param color KataGo 执子颜色
     * @return KataGo 落子
     */
    public ChessDrop gen(String color) {
        colorValid(color);
        String ret = cmd("genmove " + color);
        if (!ret.startsWith("= ")) {
            throw new KataGoException(ExceptionEnum.KATA_EXE_FAIL, "返回值为" + ret);
        }
        return ChessDrop.stringToDrop(ret.substring(2));
    }

    /**
     * 形势判断
     *
     * @return 黑白方各有多少目
     */
    public KataCount count() {
        String ret = cmd("final_score");
        if (!ret.startsWith("= ")) {
            throw new KataGoException(ExceptionEnum.KATA_EXE_FAIL, "返回值为" + ret);
        }
        return new KataCount(); // DOING
    }

}
