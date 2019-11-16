package com.codve.service.impl;

import com.codve.config.AppConfig;
import com.codve.model.User;
import com.codve.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/16 12:30
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void selectByIdTest() {
        User user = userService.selectById(1L);
        assertNotNull(user);
        System.out.println(user.toString());
    }

    @Test
    public void SelectTest() {
        List<User> userList = userService.selectAll();
        assertTrue(userList.size() > 0);
    }
}