package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelSearchTest {

    @Test
    public void searchOne() {
        int[] masssiv = new int[]{2, 5, 7, 12, 29, 75, 78, 90, 4, 128, 50,
                0, 32, 22, 19, 78, 21, 14, 16, 77};
        int rsl = ParallelSearch.search(masssiv, 6);
        assertEquals(rsl, 78);
    }

    @Test
    public void searchTwo() {
        int[] masssiv = new int[]{2, 5, 7, 12, 29, 75, 78, 90, 4, 128, 50,
                0, 32, 22, 19, 78, 21, 14, 16, 77};
        int rsl = ParallelSearch.search(masssiv, 13);
        assertEquals(rsl, 22);
    }

    @Test
    public void searchNonContain() {
        int[] masssiv = new int[]{2, 5, 7, 12, 29, 75, 78, 90, 4, 128, 50,
                0, 32, 22, 19, 78, 21, 14, 16, 77};
        int rsl = ParallelSearch.search(masssiv, 25);
        assertEquals(rsl, -1);
    }
}