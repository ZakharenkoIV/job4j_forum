package ru.job4j.forum.ut.control;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class IndexControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService posts;

    @MockBean
    private UserService users;

    @Test
    @WithMockUser
    public void whenUserRequestingRootPage() throws Exception {
        List<Post> topicPosts = List.of(Post.of("from Joker"));
        Mockito.when(posts.getAllFirstPosts()).thenReturn(topicPosts);
        Mockito.when(users.getUserByName(anyString()))
                .thenReturn(User.of(7));
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("authUser", "user"))
                .andExpect(model().attribute("userId", 7))
                .andExpect(model().attribute("isAdmin", false))
                .andExpect(model().attribute("posts", topicPosts))
                .andExpect(view().name("index"));
        verify(posts).getAllFirstPosts();
    }

    @Test
    public void whenUnauthorizedUserRequestingRootPage() throws Exception {
        Mockito.when(posts.getPostById(anyString()))
                .thenReturn(Optional.of(new Post()));
        Mockito.when(users.getUserByName(anyString()))
                .thenReturn(new User());
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser
    public void whenUserRequestingIndexPage() throws Exception {
        Mockito.when(posts.getPostById(anyString()))
                .thenReturn(Optional.of(new Post()));
        Mockito.when(users.getUserByName(anyString()))
                .thenReturn(new User());
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void whenUnauthorizedUserRequestingIndexPage() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser
    public void whenUserRequestingPostsPage() throws Exception {
        Mockito.when(posts.getPostById(anyString()))
                .thenReturn(Optional.of(new Post()));
        Mockito.when(users.getUserByName(anyString()))
                .thenReturn(new User());
        this.mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void whenUnauthorizedUserRequestingPostsPage() throws Exception {
        this.mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void whenAdminEntersTheRootPage() throws Exception {
        Mockito.when(posts.getPostById(anyString()))
                .thenReturn(Optional.of(new Post()));
        Mockito.when(users.getUserByName(anyString()))
                .thenReturn(new User());
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("isAdmin", true))
                .andExpect(view().name("index"));
    }
}
