package ru.job4j.forum.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserMem {

    private final AtomicInteger idCount;
    private final List<User> userStore;

    public UserMem() {
        idCount = new AtomicInteger(1);
        userStore = new ArrayList<>();
        userStore.add(User.of("q", "q"));
    }

    public List<User> getAllUsers() {
        return userStore;
    }

    public User save(User user) {
        user.setId(idCount.getAndAdd(1));
        userStore.add(user);
        return user;
    }
}
