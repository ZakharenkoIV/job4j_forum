package ru.job4j.forum.control;

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
import ru.job4j.forum.service.PostService;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService posts;

    @Test
    @WithMockUser(username = "Joker")
    public void whenUserEntersThePostPage() throws Exception {
        List<Post> topicPosts = List.of(Post.of("from Joker"));
        Mockito.when(posts.getPostsByTopic(anyString())).thenReturn(topicPosts);
        Mockito.when(posts.getPostById(anyString()))
                .thenReturn(Optional.of(topicPosts.get(0)));
        this.mockMvc.perform(get("/posts/{postId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("authUser", "Joker"))
                .andExpect(model().attribute("topic", "from Joker"))
                .andExpect(model().attribute("posts", topicPosts))
                .andExpect(model().attribute("userId", 6))
                .andExpect(model().attribute("isAdmin", false))
                .andExpect(view().name("/posts/post"));
    }

    @Test
    @WithMockUser(username = "root", roles = {"ADMIN"})
    public void whenAdminEntersThePostPage() throws Exception {
        this.mockMvc.perform(get("/posts/{postId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("isAdmin", true))
                .andExpect(view().name("/posts/post"));
    }

    @Test
    public void whenUnauthorizedUserRequestingPost() throws Exception {
        this.mockMvc.perform(get("/posts/{postId}", 1))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

}