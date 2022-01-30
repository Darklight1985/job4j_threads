package ru.job4j.pool;

import ru.job4j.exchange.SimpleBlockinqQueue;

public class ConsumerTasks<T> extends Thread {
    private SimpleBlockinqQueue<T> queue;
    private T value;

    ConsumerTasks(SimpleBlockinqQueue<T> queue) {
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
