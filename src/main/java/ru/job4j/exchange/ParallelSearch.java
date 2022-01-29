package ru.job4j.exchange;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockinqQueue<Integer> queue = new SimpleBlockinqQueue<Integer>(5);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                            System.out.println("Нить работает");
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        final Thread producer = new Thread(
                () -> {
                        for (int index = 0; index != 3; index++) {
                            try {
                                queue.offer(index);
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                    }
                    }
        );
        producer.start();
        try {
           producer.join();
           if (queue.findAll().size() == 0) {
               consumer.interrupt();
          }
           consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     }
    }

