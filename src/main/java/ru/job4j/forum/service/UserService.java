package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.mem.UserMem;

import java.util.List;

@Service
public class UserService {

    private final UserMem userStore = new UserMem();

    public List<User> getAllPosts() {
        return userStore.getAllUsers();
    }

    public User saveUser(User user) {
        return userStore.save(user);
    }
}
