package ru.job4j.forum.ut.control;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService posts;

    @MockBean
    private UserService users;

    @Test
    @WithMockUser
    public void whenUserEntersThePostPage() throws Exception {
        List<Post> topicPosts = List.of(Post.of("from user"));
        Mockito.when(posts.getPostsByTopic(anyString())).thenReturn(topicPosts);
        Mockito.when(posts.getPostById(anyString()))
                .thenReturn(Optional.of(topicPosts.get(0)));
        Mockito.when(users.getUserByName(anyString()))
                .thenReturn(User.of(7));
        this.mockMvc.perform(get("/posts/{postId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("authUser", "user"))
                .andExpect(model().attribute("topic", "from user"))
                .andExpect(model().attribute("posts", topicPosts))
                .andExpect(model().attribute("userId", 7))
                .andExpect(model().attribute("isAdmin", false))
                .andExpect(view().name("/posts/post"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void whenAdminEntersThePostPage() throws Exception {
        List<Post> topicPosts = List.of(Post.of("from ADMIN"));
        Mockito.when(posts.getPostsByTopic(anyString())).thenReturn(topicPosts);
        Mockito.when(posts.getPostById(anyString()))
                .thenReturn(Optional.of(topicPosts.get(0)));
        Mockito.when(users.getUserByName(anyString()))
                .thenReturn(User.of(7));
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

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        Mockito.when(users.getUserByName(anyString()))
                .thenReturn(User.of("testName", "testPass"));
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        Post testPost = new Post();
        testPost.setId(1);
        Mockito.when(posts.saveOrUpdate(argument.capture())).thenReturn(testPost);
        this.mockMvc.perform(post("/posts/save")
                .param("id", "1")
                .param("topic", "Куплю хлам. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(posts).saveOrUpdate(argument.capture());
        MatcherAssert.assertThat(argument.getValue().getTopic(), is("Куплю хлам. Дорого."));
    }
}
