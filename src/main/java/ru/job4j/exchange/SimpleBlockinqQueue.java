package ru.job4j.exchange;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockinqQueue<T> {
   private int valueSet;
   private Queue<T> queue = new LinkedList<>();

    public SimpleBlockinqQueue(int valueSet) {
        this.valueSet = valueSet;
    }

    public synchronized T poll() throws InterruptedException {
        while (valueSet > 0) {
                wait();
        }
        T value = queue.poll();
        System.out.println(" Пoлyчeнo : " + value);
        valueSet++;
        notify();
        return value;
    }

     public synchronized void offer(T value) throws InterruptedException {
          while (valueSet == 0) {
                  wait();
          }
          queue.offer(value);
              valueSet--;
          System.out.println("Отправлено : " + value);
          notify();
      }

      public LinkedList<T> findAll() {
       LinkedList<T> list = new LinkedList<>(queue);
          return list;
      }
}