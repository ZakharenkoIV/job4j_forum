package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexControl {

    private final PostService posts;
    private final UserService users;

    public IndexControl(PostService posts, UserService users) {
        this.posts = posts;
        this.users = users;
    }

    @GetMapping({"/", "/index", "/posts"})
    public String index(Model model, HttpServletRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("authUser", user.getUsername());
        model.addAttribute("userId", users.getUserByName(user.getUsername()).getId());
        model.addAttribute("isAdmin", request.isUserInRole("ROLE_ADMIN"));
        model.addAttribute("posts", posts.getAllFirstPosts());
        return "index";
    }
}
