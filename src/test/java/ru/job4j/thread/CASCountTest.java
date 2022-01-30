package ru.job4j.thread;

import org.junit.Test;

import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void Increment(){
        CASCount count = new CASCount();
        count.increment();
        assertEquals(count.get(), 1);
    }

    @Test
    public void whenIncTwoThread() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(
                () -> count.increment()
        );
        Thread second = new Thread(
                () -> count.increment()
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertEquals(count.get(), 2);
    }
}