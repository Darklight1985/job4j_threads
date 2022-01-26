package ru.job4j.exchange;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockinqQueueTest {

    @Test
    public void queueWork() {
        SimpleBlockinqQueue simpleBlockinqQueue = new SimpleBlockinqQueue();
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
        SimpleBlockinqQueue simpleBlockinqQueue = new SimpleBlockinqQueue();
        ProducerQueue<Integer> producer = new ProducerQueue<>(simpleBlockinqQueue, 5);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(simpleBlockinqQueue.findAll().poll(), 5);
    }
}