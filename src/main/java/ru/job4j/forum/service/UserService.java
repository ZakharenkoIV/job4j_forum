package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.data.UserRepository;

@Service
public class UserService {

    private final UserRepository userStore;

    public UserService(UserRepository userStore) {
        this.userStore = userStore;
    }

    @Transactional
    public void save(User user) {
        userStore.save(user);
    }

    public User getUserByName(String name) {
        return userStore.findUserByName(name);
    }
}
