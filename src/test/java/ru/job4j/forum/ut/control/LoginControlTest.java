package ru.job4j.forum.ut.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class LoginControlTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenIncorrectLoginDataWhenErrorMessage() throws Exception {
        this.mockMvc.perform(get("/login").param("error", "true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage",
                        "Неверное имя пользователя или пароль !!"))
                .andExpect(view().name("login"));
    }

    @Test
    public void whenLoggedOffWhenLogoutMessage() throws Exception {
        this.mockMvc.perform(get("/login").param("logout", "true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage",
                        "Вы успешно вышли из системы"))
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser
    public void whenLogoutThenRedirectToLoginWithLogoutMessage() throws Exception {
        this.mockMvc.perform(get("/logout"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?logout=true"));

    }
}
