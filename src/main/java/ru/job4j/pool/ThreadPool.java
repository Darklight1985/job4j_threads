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
            threads.add(new MyRunnable(tasks));
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

    public class MyRunnable extends Thread {
      private   SimpleBlockinqQueue<Runnable> queue;
      private Runnable task;

        public MyRunnable(SimpleBlockinqQueue queue) {
            this.queue = queue;
            new Thread(this, "Потребитель").start();
        }

        @Override
        public void run() {
            try {
                this.task = queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Потребитель забрал " + task);
        }
    }
}