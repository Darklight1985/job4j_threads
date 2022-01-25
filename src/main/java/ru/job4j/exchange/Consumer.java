package ru.job4j.exchange;

public class Consumer implements Runnable {
    private Q q;

    Consumer(Q q) {
        this.q = q;
        new Thread(this, "Потребитель").start();
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            q.get();
        }
    }
}
