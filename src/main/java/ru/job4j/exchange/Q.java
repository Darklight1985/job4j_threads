package ru.job4j.exchange;

public class Q {
   private boolean valueSet = false;
    private int n;

    synchronized int get() {
        while (!valueSet) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Исключение типа InterruptedException перехвачено");
            }
        }
        System.out.println(" Пoлyчeнo : " + n);
        valueSet = false;
        notify();
        return n;
    }

      synchronized void put(int n) {
          while (valueSet) {
              try {
                  wait();
              } catch (InterruptedException e) {
                  System.out.println("Исключение типа InterruptedException перехвачено");
              }
          }
          this.n = n;
              valueSet = true;
          System.out.println("Отправлено : " + n);
          notify();
      }

}