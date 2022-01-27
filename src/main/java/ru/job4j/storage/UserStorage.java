package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;

import static java.util.Objects.nonNull;

@ThreadSafe
public final class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> map;

    public UserStorage(Map<Integer, User> map) {
        this.map = map;
    }

    public synchronized boolean add(User user) {
        return map.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
          return map.replace(user.getId(), user) == user;
    }

    public synchronized boolean delete(User user) {
        return map.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromid, int told, int amount) {
        boolean rsl = false;
        User source = map.get(fromid);
        User target = map.get(told);
        if (nonNull(source) && nonNull(target)) {
            if (amount >= source.getAmount()) {
          source.setAmount(source.getAmount() - amount);
                  target.setAmount(target.getAmount() + amount);
              rsl = true;
          }
            }
        return rsl;
    }
}
