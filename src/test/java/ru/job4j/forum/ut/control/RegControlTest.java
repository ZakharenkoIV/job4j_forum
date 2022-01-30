package ru.job4j.forum.ut.control;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class RegControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService users;

    @MockBean
    private ConstraintViolationException cve;

    @Test
    public void whenUnauthorizedUserRequestingRegPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    public void registerUser() throws Exception {
        RequestBuilder request = post("/register.html")
                .param("username", "testName")
                .param("password", "testPass")
                .with(csrf());
        doNothing().when(users).save(isA(User.class));
        this.mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void registerUserWithExistingName() throws Exception {
        doThrow(cve).when(users).save(isA(User.class));
        this.mockMvc.perform(post("/reg")
                .param("username", "testUser")
                .param("password", "testPass"))
                .andDo(print())
                .andExpect(model().attribute("errorMessage",
                        "Пользователь с таким именем уже существует"))
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
        verify(users, times(1)).save(isA(User.class));
    }
}
