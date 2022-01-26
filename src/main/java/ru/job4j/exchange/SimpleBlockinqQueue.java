package ru.job4j.exchange;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SimpleBlockinqQueue<T> {
   private boolean valueSet = true;
   private Queue<T> queue = new LinkedList<>();

   public synchronized T poll() {
        while (valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Исключение типа InterruptedException перехвачено");
            }
        }
        T value = queue.poll();
        System.out.println(" Пoлyчeнo : " + value);
        valueSet = true;
        notify();
        return value;
    }

     public synchronized void offer(T value) {
          while (!valueSet) {
              try {
                  wait();
              } catch (InterruptedException e) {
                  System.out.println("Исключение типа InterruptedException перехвачено");
              }
          }
          queue.offer(value);
              valueSet = false;
          System.out.println("Отправлено : " + value);
          notify();
      }

      public LinkedList<T> findAll() {
       LinkedList<T> list = new LinkedList<>(queue);
          return list;
      }
}