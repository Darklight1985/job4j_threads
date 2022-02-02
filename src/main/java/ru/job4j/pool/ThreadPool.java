package ru.job4j.pool;

import ru.job4j.exchange.SimpleBlockinqQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockinqQueue<Runnable> tasks = new SimpleBlockinqQueue<>(5);
    private int size;

    public ThreadPool() {
        this.size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                Runnable task = null;
                try {
                   task = tasks.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Потребитель забрал " + task);
            }));
        }
        for (Thread thread: threads) {
            thread.start();
        }

    }

    public void work(Runnable job) {
        try {
            tasks.offer(job);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
for (Thread thread: threads) {
        thread.interrupt();
}
    }
}