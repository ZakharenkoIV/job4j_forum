package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.mem.PostMem;

import java.util.List;

@Service
public class PostService {

    private final PostMem postStore = new PostMem();

    public List<Post> getAllPosts() {
        return postStore.getAllPosts();
    }
}
