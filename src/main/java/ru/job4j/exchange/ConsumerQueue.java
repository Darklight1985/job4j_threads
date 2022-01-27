package ru.job4j.exchange;

import java.util.Queue;

public class ConsumerQueue<T> implements Runnable {
    private SimpleBlockinqQueue<T> queue;
    private T value;

    ConsumerQueue(SimpleBlockinqQueue<T> queue) {
        this.queue = queue;
        new Thread(this, "Потребитель").start();
    }

    @Override
    public void run() {
            try {
                this.value = queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Потребитель забрал " + value);
    }
}
