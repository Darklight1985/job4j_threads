package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
     return memory.computeIfPresent(model.getId(), (s, s1) -> {
         Base stored = memory.get(s);
         if (stored.getVersion() != s1.getVersion()) {
             throw new OptimisticException("Version not equal");
         }
         return new Base(s, s1.getVersion() + 1);
     })  != null;
    }

    public void delete(Base model) {
        memory.remove(model);
    }
}
