package ru.job4j.forum.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String topic;

    @NotNull
    private String comment;

    @NotNull
    private Calendar created;

    @NotNull
    @Column(name = "order_of_addition")
    private int orderOfAddition;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Post of(String name) {
        Post post = new Post();
        post.topic = name;
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String name) {
        this.topic = name;
    }

    public int getOrderOfAddition() {
        return orderOfAddition;
    }

    public void setOrderOfAddition(int orderOfAddition) {
        this.orderOfAddition = orderOfAddition;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String description) {
        this.comment = description;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id
                && Objects.equals(topic, post.topic)
                && Objects.equals(comment, post.comment)
                && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, comment, created);
    }
}
