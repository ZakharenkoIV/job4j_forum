package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postStore;

    public PostService(PostRepository postStore) {
        this.postStore = postStore;
    }

    public List<Post> getAllPosts() {
        return postStore.getAllPosts();
    }
}
