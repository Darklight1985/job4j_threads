package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private final String dest;

    public Wget(String url, int speed, String dest) {
        this.url = url;
        this.speed = speed;
        this.dest = dest;
    }

    @Override
    public void run() {

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(dest)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            Instant start = Instant.now();
            long bytesWrited = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                bytesWrited += bytesRead;
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (bytesWrited >= speed) {
                    long elapsed = Duration.between(start, Instant.now()).toMillis();
                    System.out.println("Скачано " + bytesWrited
                            + " байт за " + elapsed + " миллисекунд");
                    start = Instant.now();
                    bytesWrited = 0;
                    if (elapsed < 1000) {
                        System.out.println("Need pause in " + (1000 - elapsed) + " ms");
                        Thread.sleep(1000 - elapsed);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void parse(String[] args) {
            if (args.length < 3) {
                throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        parse(args);
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String dest = args[2];
        Thread wget = new Thread(new Wget(url, speed, dest));
        wget.start();
        wget.join();
    }
}