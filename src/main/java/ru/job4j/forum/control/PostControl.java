package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class PostControl {

    private final PostService posts;
    private final UserService users;

    public PostControl(PostService posts, UserService users) {
        this.posts = posts;
        this.users = users;
    }

    @PostMapping("/posts/save")
    public String postSave(@ModelAttribute Post post) {
        if (post.getUser() == null) {
            post.setUser(ru.job4j.forum.model.User.of(
                    users.getUserByName(this.getAuthorizedUserName()).getId())
            );
        }
        post = posts.saveOrUpdate(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/posts/{postId}")
    public String getPostPageByPostId(@PathVariable(value = "postId") String postId,
                                      Model model,
                                      HttpServletRequest request) {
        model.addAttribute("authUser", this.getAuthorizedUserName());
        Post post = posts.getPostById(postId).orElse(new Post());
        List<Post> topicPosts = posts.getPostsByTopic(post.getTopic());
        model.addAttribute("topic", post.getTopic());
        model.addAttribute("posts", topicPosts);
        model.addAttribute("userId", users.getUserByName(getAuthorizedUserName()).getId());
        model.addAttribute("isAdmin", request.isUserInRole("ROLE_ADMIN"));
        return "/posts/post";
    }

    @GetMapping("/posts/create")
    public String postCreate(Model model) {
        model.addAttribute("authUser", this.getAuthorizedUserName());
        return "/posts/create";
    }

    @GetMapping("/posts/{postId}/edit")
    public String postEdit(Model model, @PathVariable String postId) {
        model.addAttribute("authUser", this.getAuthorizedUserName());
        Optional<Post> post = posts.getPostById(postId);
        model.addAttribute("post", post.orElse(new Post()));
        return "/posts/edit";
    }

    @GetMapping("/posts/{postId}/delete")
    public String postDelete(Model model, @PathVariable String postId, HttpServletRequest request) {
        model.addAttribute("authUser", this.getAuthorizedUserName());
        int i = posts.deleteById(postId);
        if (postId.equals(String.valueOf(i))) {
            return "redirect:/posts/";
        } else {
            return "redirect:/posts/" + i;
        }
    }

    private String getAuthorizedUserName() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUsername();
    }
}
