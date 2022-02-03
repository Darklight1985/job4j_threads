package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void sumOne() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] sum = RolColSum.sum(matrix);
       int rsl = sum[1].getColSum();
       assertEquals(rsl, 15);
    }

    @Test
    public void sumTwo() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] sum = RolColSum.sum(matrix);
        int rsl = sum[2].getRowSum();
        assertEquals(rsl, 24);
    }

    @Test
    public void sumAsyncOne() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] sum = RolColSum.asyncSum(matrix);
        int rsl = sum[2].getRowSum();
        assertEquals(rsl, 24);
    }

    @Test
    public void sumAsyncTwo() {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] sum = RolColSum.sum(matrix);
        int rsl = sum[0].getColSum();
        assertEquals(rsl, 12);
    }
}