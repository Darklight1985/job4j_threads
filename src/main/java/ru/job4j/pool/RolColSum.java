package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.Objects.isNull;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < sums.length; i++) {
               sums[i] = new Sums();
        }
        int sum = 0;
     for (int i = 0; i < matrix.length; i++) {
         for (int k = 0; k < matrix.length; k++) {
             sum += matrix[i][k];
             if (isNull(sums[k].colSum)) {
                 sums[k].colSum = 0;
             }
             sums[k].colSum += matrix[i][k];
         }
         sums[i].rowSum = sum;
         sum = 0;
     }
     return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < sums.length; i++) {
            sums[i] = new Sums();
            futures.put(i, getTask(matrix, i));
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTask(int[][] data, int number) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums();
            int sumRow = 0;
            int sumCol = 0;
            for (int i = 0; i < data.length; i++) {
                sumRow += data[number][i];
                sumCol += data[i][number];
            }
            sums.colSum = sumCol;
            sums.rowSum = sumRow;
            return sums;
        });
    }
}