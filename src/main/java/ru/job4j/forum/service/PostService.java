package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.data.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postStore;

    public PostService(PostRepository postStore) {
        this.postStore = postStore;
    }

    public List<Post> getAllPosts() {
        return postStore.getAllPosts();
    }

    public Optional<Post> getPostById(String postId) {
        return postStore.findById(Long.getLong(postId));
    }

    public void savePost(Post post) {
        postStore.save(post);
    }
}
