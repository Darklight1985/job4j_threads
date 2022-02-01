package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch extends RecursiveTask<Integer> {
    private final int[] array;
    private int min;
    private int max;
    private final int index;

    public ParallelSearch(int[] array, int min, int max, int index) {
        this.array = array;
        this.min = min;
        this.max = max;
        this.index = index;
    }

    @Override
    protected Integer compute() {

        if (max - min <= 10) {
            for (int i = min; i <= max; i++) {
                if (i == index) {
                    return array[i];
                }
            }
            return -1;
        }

        int mid = (min + max) / 2;
        if (mid == index) {
            return array[mid];
        } else {
            if (mid < index) {
                min = mid;
            } else {
                max = mid;
            }
        }
        ParallelSearch leftHalf = new ParallelSearch(array, min, mid,  index);
        ParallelSearch rightHalf = new ParallelSearch(array, mid + 1, max, index);
leftHalf.fork();
rightHalf.fork();

        int left = leftHalf.join();
        int right = rightHalf.join();

        return Math.max(left, right);
    }

    public static void main(String[] args) {
        int[] masssiv = new int[]{2, 5, 7, 12, 29, 75, 78, 90, 4, 128, 50,
                0, 32, 22, 19, 78, 21, 14, 16, 77};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool
                .invoke(new ParallelSearch(masssiv, 0, masssiv.length - 1, 6)));
    }
}
