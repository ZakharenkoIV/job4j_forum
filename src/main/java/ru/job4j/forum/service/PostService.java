package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.data.PostRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postStore;

    public PostService(PostRepository postStore) {
        this.postStore = postStore;
    }

    public List<Post> getAllFirstPosts() {
        return postStore.getAllFirstPosts();
    }

    public List<Post> getPostsByTopic(String topic) {
        return postStore.getPostsByTopic(topic);
    }

    public Optional<Post> getPostById(String postId) {
        return postStore.findById(Integer.parseInt(postId));
    }

    @Transactional
    public Post saveOrUpdate(Post post) {
        Post saved;
        if (post.getId() == 0) {
            post.setCreated(Calendar.getInstance());
            saved = postStore.save(post.getTopic(), post.getComment(), post.getUser().getId());
        } else {
            postStore.update(post.getTopic(), post.getComment(), post.getId());
            saved = post;
        }
        return saved;
    }

    public int deleteById(String postId) {
        return postStore.deleteByIdWithReturningMinOrderByTopic(Integer.parseInt(postId));
    }
}
