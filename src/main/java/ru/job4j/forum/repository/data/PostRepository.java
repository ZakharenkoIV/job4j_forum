package ru.job4j.forum.repository.data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query("select distinct p FROM Post p left join fetch p.user")
    List<Post> getAllPosts();

    @Modifying
    @Query("UPDATE Post p SET p.name =:name, p.description =:desc WHERE p.id =:id")
    void update(@Param("name") String name,
                @Param("desc") String description,
                @Param("id") Integer id);
}