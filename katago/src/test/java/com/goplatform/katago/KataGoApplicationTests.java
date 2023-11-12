package com.goplatform.katago;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

import static com.goplatform.katago.pojo.Constants.*;

@SpringBootTest
class KataGoApplicationTests {


    @Test
    void contextLoads() {

        System.out.println("kata url: " + KATA_URL);
        System.out.println("neural url: " + NEURAL_URL);
        System.out.println("config url: " + CONFIG_URL);

        ProcessBuilder builder = new ProcessBuilder(
                KATA_URL, "gtp", "-model", NEURAL_URL, "-config", CONFIG_URL
        );

        try {
            StringBuilder res = new StringBuilder();
            Process process = builder.start();

            Thread outputThread1 = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        res.append(line);
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
            String finalRes = res.toString();
            System.out.println(finalRes);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            assert false;
        }

    }

}
