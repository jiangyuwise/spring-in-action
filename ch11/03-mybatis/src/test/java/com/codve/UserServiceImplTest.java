package com.codve;

import com.codve.model.User;
import com.codve.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/13 16:18
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void findByUserIdTest() {
        User user = userService.findById(1L);
        assertNotNull(user);
    }

    @Test
    public void insertTest() {
        User user = new User("Hello", System.currentTimeMillis());
        userService.insert(user);
        assertTrue(user.getId() > 0);
    }

    @Test
    public void findAllTest() {
        List<User> userList = userService.findAll();
        assertTrue(userList.size() > 0);
    }
}