package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.data.UserRepository;

@Service
public class UserService {

    private final UserRepository userStore;

    public UserService(UserRepository userStore) {
        this.userStore = userStore;
    }

    public User saveUser(User user) {
        return userStore.save(user);
    }
}
