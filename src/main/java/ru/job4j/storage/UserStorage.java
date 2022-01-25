package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.List;

import static java.util.Objects.nonNull;

@ThreadSafe
public final class UserStorage {
    @GuardedBy("this")
    private final List<User> list;

    public UserStorage(List<User> list) {
        this.list = list;
    }

    public synchronized boolean add(User user) {
       return list.add(new User(user.getId(), user.getAmount()));
    }

    public synchronized boolean update(User user) {
        boolean rsl = false;
      int id = user.getId();
      int amount = user.getAmount();
      if (delete(user)) {
          rsl = add(new User(id, amount));
      }
      return rsl;
    }

    public synchronized boolean delete(User user) {
        return list.remove(user);
    }

    public synchronized boolean transfer(int fromid, int told, int amount) {
        boolean rsl = false;
        User source = list.get(fromid);
        User target = list.get(told);
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
