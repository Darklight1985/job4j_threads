package ru.job4j.exchange;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.*;

public class SimpleBlockinqQueueTest {

    @Test
    public void queueWork() {
        SimpleBlockinqQueue simpleBlockinqQueue = new SimpleBlockinqQueue(1);
        ProducerQueue<Integer> producer = new ProducerQueue<>(simpleBlockinqQueue, 5);
        ConsumerQueue<Integer> consumer = new ConsumerQueue<>(simpleBlockinqQueue);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(simpleBlockinqQueue.findAll().size(), 0);
    }

    @Test
    public void add() {
        SimpleBlockinqQueue simpleBlockinqQueue = new SimpleBlockinqQueue(1);
        ProducerQueue<Integer> producer = new ProducerQueue<>(simpleBlockinqQueue, 5);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(simpleBlockinqQueue.findAll().poll(), 5);
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockinqQueue<Integer> queue = new SimpleBlockinqQueue<>(15);
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.findAll().isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}