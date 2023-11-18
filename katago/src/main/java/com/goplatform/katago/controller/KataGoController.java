package com.goplatform.katago.controller;

import com.goplatform.katago.pojo.ChessDrop;
import com.goplatform.katago.pojo.KataCount;
import com.goplatform.katago.pojo.Result;
import com.goplatform.katago.service.KataAgentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Set;

import static com.goplatform.katago.pojo.Constants.*;
import static com.goplatform.katago.pojo.Constants.CONFIG_URL;

@RestController
@RequestMapping("/kata")
public class KataGoController {

    @Resource
    KataAgentService service;

    @GetMapping("/test")
    public Result test() {

        System.out.println("kata url: " + KATA_URL);
        System.out.println("neural url: " + NEURAL_URL);
        System.out.println("config url: " + CONFIG_URL);

        ProcessBuilder builder = new ProcessBuilder(
                KATA_URL, "gtp", "-model", NEURAL_URL, "-config", CONFIG_URL
        );
//        ProcessBuilder builder = new ProcessBuilder("pwd");

        try {
            StringBuilder res = new StringBuilder();
            Process process = builder.start();

            Thread outputThread1 = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        res.append(line).append("\n");
                    }
                } catch (IOException ignored) {
                }
            });
            outputThread1.start();

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.write("quit\n");
                writer.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                assert false;
            }

            process.waitFor();
            outputThread1.join();
            String finalRes = res.toString();
            System.out.println(finalRes);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            assert false;
        }

        return Result.ok("OK", null);
    }

    @PutMapping("/start/{roomId}")
    public Result start(@PathVariable long roomId) {
        service.start(roomId);
        return Result.ok();
    }

    @DeleteMapping("/destroy/{roomId}")
    public Result destroy(@PathVariable long roomId) {
        service.destroy(roomId);
        return Result.ok();
    }

    @PostMapping("/play/{roomId}/{color}")
    public Result play(@PathVariable long roomId, @PathVariable String color, @RequestBody ChessDrop drop) {
        service.play(roomId, color, drop);
        return Result.ok();
    }

    @GetMapping("/gen/{roomId}/{color}")
    public Result gen(@PathVariable long roomId, @PathVariable String color) {
        ChessDrop drop = service.gen(roomId, color);
        return Result.ok(drop); // 返回区data为NULL代表下不了
    }

    @GetMapping("/count/{roomId}")
    public Result count(@PathVariable long roomId) {
        KataCount count = service.count(roomId);
        return Result.ok(count); // KataCount
    }

    @GetMapping("/list")
    public Result list() {
        Set<Long> rooms = service.listRoom();
        return Result.ok(rooms);
    }

}
