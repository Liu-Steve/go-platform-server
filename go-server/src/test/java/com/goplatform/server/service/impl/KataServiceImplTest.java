package com.goplatform.server.service.impl;

import com.goplatform.server.pojo.constant.Constants;
import com.goplatform.server.pojo.domain.ChessDrop;
import com.goplatform.server.pojo.domain.KataCount;
import com.goplatform.server.service.KataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KataServiceImplTest {

    @Resource
    private KataService kataService;
    @Test
    void endCount() {
        KataCount kataCount = kataService.endCount(1L);
        System.out.println(kataCount);
    }

    @Test
    void start() {
        kataService.start(1L);
    }

    @Test
    void play() {
        ChessDrop chessDrop = new ChessDrop();
        chessDrop.setDropPosition(new ArrayList<Integer>(){{add(1); add(2); }});
        kataService.play(1L, chessDrop, Constants.BLACK);
    }

    @Test
    void gen() {
        kataService.gen(1L, Constants.BLACK);
    }

    @Test
    void destroy() {
        kataService.destroy(1L);
    }
}