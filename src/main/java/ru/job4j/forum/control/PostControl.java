package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Optional;

@Controller
public class PostControl {

    private final PostService posts;

    public PostControl(PostService posts) {
        this.posts = posts;
    }

    @PostMapping("/posts/{postId}")
    public String postSave(@ModelAttribute Post post, @PathVariable String postId) {
        posts.savePost(post);
        return "redirect:/";
    }

    @GetMapping("/posts/{postId}")
    public String getPostPageByPostId(@PathVariable String postId) {
        Optional<Post> post = posts.getPostById(postId);
        return "reg";
    }
}
