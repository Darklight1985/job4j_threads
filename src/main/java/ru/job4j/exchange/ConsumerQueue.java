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
           this.value = queue.poll();
        System.out.println("Потребитель забрал " + value);
    }
}
