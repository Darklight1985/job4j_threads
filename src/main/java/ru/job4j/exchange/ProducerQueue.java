package ru.job4j.exchange;

public class ProducerQueue<T> implements Runnable {
    private SimpleBlockinqQueue<T> queue;
    private T value;

    ProducerQueue(SimpleBlockinqQueue<T> queue, T value) {
        this.queue = queue;
        this.value = value;
        new Thread(this, "Поставщик").start();
    }

    @Override
    public void run() {
            try {
                queue.offer(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Поставщик отдал " + value);
    }
}
