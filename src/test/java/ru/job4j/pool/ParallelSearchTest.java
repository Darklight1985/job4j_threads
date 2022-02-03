package ru.job4j.pool;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParallelSearchTest {

    @Test
    public void searchInt() {
        Integer[] masssiv = new Integer[]{2, 5, 7, 12, 29, 75, 78, 90, 4, 128, 50,
                0, 32, 22, 19, 78, 21, 14, 16, 77};
        int rsl = ParallelSearch.search(masssiv, 78);
        assertEquals(rsl, 15);
    }

    @Test
    public void searchString() {
        String[] masssiv = new String[]{"Abc", "Petr", "Dima", "Vasya"};
        int rsl = ParallelSearch.search(masssiv, "Dima");
        assertEquals(rsl, 2);
    }

    @Test
    public void searchNonContain() {
        Integer[] masssiv = new Integer[]{2, 5, 7, 12, 29, 75, 78, 90, 4, 128, 50,
                0, 32, 22, 19, 78, 21, 14, 16, 77};
        int rsl = ParallelSearch.search(masssiv, 25);
        assertEquals(rsl, -1);
    }

}