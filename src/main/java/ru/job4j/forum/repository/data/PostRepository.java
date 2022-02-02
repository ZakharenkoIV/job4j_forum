package ru.job4j.forum.repository.data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Query("select distinct p FROM Post p left join fetch p.user WHERE p.orderOfAddition = 1")
    List<Post> getAllFirstPosts();

    @Query("select distinct p FROM Post p left join fetch p.user WHERE p.topic =?1")
    List<Post> getPostsByTopic(String topic);

    @Query(value = "WITH count AS (select max(order_of_addition)"
            + "from forum.public.posts "
            + "where topic like ?1)"
            + "insert into forum.public.posts (topic, comment, user_id, order_of_addition)"
            + "values (?1, ?2, ?3, "
            + "CASE WHEN (select * from count) IS NULL THEN 1 ELSE (select * from count) + 1 END) "
            + " returning *",
            nativeQuery = true)
    Post save(String topic, String comment, int userId);

    @Modifying
    @Query("UPDATE Post p SET p.topic =?1, p.comment =?2 WHERE p.id =?3")
    void update(String topic, String comment, int postId);

    @Query(value = "with oldPostTopic as("
            + "select topic from forum.public.posts where id = ?1), "
            + " minTopicId as(select min(id) from forum.public.posts "
            + "where topic like (select * from oldPostTopic)) "
            + "DELETE FROM forum.public.posts as p "
            + "WHERE p.id = ?1 or "
            + "(select * from minTopicId) = ?1 and p.topic like (select * from oldPostTopic) "
            + "returning (select * from minTopicId)",
            nativeQuery = true)
    int deleteByIdWithReturningMinOrderByTopic(int postId);
}
