package com.codve.web;

import com.codve.User;
import com.codve.data.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author admin
 * @date 2019/10/30 12:02
 */
public class UserControllerTest {

    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
    }

    @Test
    public void showRegisterForm() throws Exception {
        UserController controller = new UserController(userRepository);
        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(get("/user/register"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void register() throws Exception {
        User user = new User("jimmy", "123456");
        User savedUser = new User(1L, "jimmy", "123456");

        when(userRepository.save(user)).thenReturn(savedUser);

        UserController controller = new UserController(userRepository);

        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/user/register")
                .param("name", "jimmy")
                .param("password", "123456"))
                .andExpect(redirectedUrl("/user/jimmy"));
        verify(userRepository, times(1)).save(user);
    }

    // 检验参数
    @Test
    public void validate() throws Exception {
        UserController controller = new UserController(userRepository);

        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(post("/user/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerForm"))
                .andExpect(model().errorCount(2))
                .andExpect(model().attributeHasFieldErrors("user", "name", "password"));
    }

}