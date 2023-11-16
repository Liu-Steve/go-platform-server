package com.goplatform.katago.controller;

import com.goplatform.katago.pojo.BoardVO;
import com.goplatform.katago.pojo.KataCount;
import com.goplatform.katago.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

import static com.goplatform.katago.pojo.Constants.*;
import static com.goplatform.katago.pojo.Constants.CONFIG_URL;

@RestController
@RequestMapping("/kata")
public class KataGoController {

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

    @PostMapping("/count")
    public Result count(BoardVO board) {
//        return new KataCount();
        return Result.ok("OK", new KataCount());
    }

}
