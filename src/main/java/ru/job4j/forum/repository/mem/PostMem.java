package ru.job4j.forum.repository.mem;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMem {

    private final AtomicInteger idCount;
    private final List<Post> postStore;

    public PostMem() {
        idCount = new AtomicInteger(1);
        postStore = new ArrayList<>();
        Post post = Post.of("Продаю машину ладу 01.");
        post.setDescription("Машина новая");
        post.setCreated(Calendar.getInstance());
        this.save(post);
    }

    public List<Post> getAllPosts() {
        return postStore;
    }

    public Post save(Post post) {
        post.setId(idCount.getAndAdd(1));
        postStore.add(post);
        return post;
    }
}
