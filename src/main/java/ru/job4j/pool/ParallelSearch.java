package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch <T> extends RecursiveTask<Integer> {
    private final T[] array;
    private int min;
    private int max;
    private final T elem;

    public ParallelSearch(T[] array, int min, int max, T elem) {
        this.array = array;
        this.min = min;
        this.max = max;
        this.elem = elem;
    }

    @Override
    protected Integer compute() {
        if (max - min <= 10) {
            for (int i = min; i <= max; i++) {
                if (array[i].equals(elem)) {
                    return i;
                }
            }
            return -1;
        }

        int mid = (min + max) / 2;
        if (array[mid].equals(elem)) {
            return mid;
        }

        ParallelSearch leftHalf = new ParallelSearch(array, min, mid, elem);
        ParallelSearch rightHalf = new ParallelSearch(array, mid + 1, max, elem);
        leftHalf.fork();
        rightHalf.fork();

        int left = (int) leftHalf.join();
        int right = (int) rightHalf.join();

        return Math.max(left, right);

    }

       public static <T> int search(T [] array, T elem) {
           ForkJoinPool forkJoinPool = new ForkJoinPool();
           return (int) forkJoinPool.invoke(new ParallelSearch(array, 0, array.length - 1, elem));
        }
    }

