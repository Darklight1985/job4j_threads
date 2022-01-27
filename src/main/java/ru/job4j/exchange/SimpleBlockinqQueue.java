package ru.job4j.exchange;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockinqQueue<T> {
   private final int valueSet;
   private Queue<T> queue = new LinkedList<>();

    public SimpleBlockinqQueue(int valueSet) {
        this.valueSet = valueSet;
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
                wait();
        }
        T value = queue.poll();
        System.out.println(" Пoлyчeнo : " + value);
        notify();
        return value;
    }

     public synchronized void offer(T value) {
          while (queue.size() == valueSet) {
              try {
                  wait();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          queue.offer(value);
          System.out.println("Отправлено : " + value);
          notify();
      }

      public LinkedList<T> findAll() {
       LinkedList<T> list = new LinkedList<>(queue);
          return list;
      }
}