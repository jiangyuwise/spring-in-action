package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

/**
 * @author admin
 * @date 2019/11/10 18:59
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Aka");
        user.setBirthday(System.currentTimeMillis());
    }

    @Test
    public void saveTest() {
        userService.save(user);
        assertTrue(user.getId() > 0);
        System.out.println(user.toString());
    }

    @Test
    public void findAllTest() {
        List<User> userList = userService.findAll();
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void findByNameTest() {
        List<User> userList = userService.findByName("j");
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }
}