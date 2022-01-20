package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        int i = 0;
        char[] symbol = new char[] {'\\', '|', '/'};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            if (i == 3) {
                i = 0;
            }
            System.out.print("\r load: " + "..." + symbol[i]);
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(6000);
        progress.interrupt();
    }
}
