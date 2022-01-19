package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        int i = 0;
        String str;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
            i++;
            if (i == 4) {
                i = 1;
            }
            str = symbol(i);
            System.out.print("\r load: " + "..." + str);
        }
    }

    public String symbol(int i) {
        String str = null;
        switch (i) {
            case 1 : str = "\\";
            break;
            case 2: str = "|";
            break;
            case 3: str = "/";
            break;
            default: break;
        }
        return str;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(6000);
        progress.interrupt();
    }
}
