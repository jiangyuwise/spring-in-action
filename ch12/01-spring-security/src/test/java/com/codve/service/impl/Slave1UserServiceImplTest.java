package com.codve.service.impl;

import com.codve.model.User;
import com.codve.service.Slave1UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author admin
 * @date 2019/11/17 16:52
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class Slave1UserServiceImplTest {

    @Autowired
    private Slave1UserService userService;

    @Test
    public void selectByIdTest() {
        User user = userService.getById(1L);
        assertNotNull(user);
    }

}