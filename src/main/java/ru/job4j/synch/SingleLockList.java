package ru.job4j.synch;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = list;
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    public synchronized List copy(List<T> list) {
        List<T> tList = new ArrayList<>(list);
        Collections.copy(tList, list);
        return tList;
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(list).iterator();
    }
}
