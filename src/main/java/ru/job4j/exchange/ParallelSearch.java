package ru.job4j.exchange;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockinqQueue<Integer> queue = new SimpleBlockinqQueue<Integer>(5);
        final Thread consumer = new Thread(
                () -> {
                    while (true) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                        for (int index = 0; index != 3; index++) {
                            System.out.println(consumer.getState());
                            try {
                                queue.offer(index);
                                if (consumer.getState() == Thread.State.WAITING && queue.findAll().size() == index) {
                                    Thread.currentThread().interrupt();
                                }
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
        ).start();
    }
}
