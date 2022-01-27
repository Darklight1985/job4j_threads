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
        boolean rsl = map.containsValue(user);
       if (!rsl) {
           map.putIfAbsent(user.getId(), new User(user.getId(), user.getAmount()));
       }
       return rsl;
    }

    public synchronized boolean update(User user) {
        boolean rsl = false;
      int id = user.getId();
      int amount = user.getAmount();
      if (map.containsKey(id)) {
          rsl = map.replace(user.getId(), user, new User(id, amount));
      }
      return rsl;
    }

    public synchronized boolean delete(User user) {
        return map.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromid, int told, int amount) {
        boolean rsl = false;
        User source = map.get(fromid);
        User target = map.get(told);
        if (nonNull(source) && nonNull(target)) {
            if (amount > source.getAmount()) {
          if (update(new User(source.getId(), source.getAmount() - amount))
                  || update(new User(target.getId(), target.getAmount() + amount))) {
              rsl = true;
          }
            }
        }
        return rsl;
    }
}
