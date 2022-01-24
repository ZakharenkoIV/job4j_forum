package ru.job4j.forum.repository.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("FROM Post")
    List<Post> getAllPosts();
}
