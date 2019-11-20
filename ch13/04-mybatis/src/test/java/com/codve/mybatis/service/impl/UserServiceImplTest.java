package com.codve.mybatis.service.impl;

import com.codve.mybatis.model.User;
import com.codve.mybatis.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/19 15:50
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private DataSource dataSource;

    private static ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

    @Autowired
    private UserService userService;

    @BeforeAll
    static void setUpAll() {
        populator.addScript(new ClassPathResource("data/user.sql"));
    }

    @BeforeEach
    void setUp() {
        populator.execute(dataSource);
    }

    @Test
    void save() {
        assertEquals(1, userService.save(new User()));
    }

    @Test
    void deleteById() {
        assertEquals(1, userService.deleteById(1L));
    }

    @Test
    void update() {
        User user = new User();
        user.setId(1L);
        user.setName("hello");
        assertEquals(1, userService.update(user));
    }

    @Test
    public void findById() {
        User user = userService.findById(0L);
        assertNull(user);

        user = userService.findById(1L);
        assertNotNull(user);
        assertEquals(1L, user.getId());
    }

    @Test
    public void find() {
        User user = new User();
        user.setName("j");
        List<User> userList = userService.find(user, 0L, null, null, 4, 1, 1);
        assertTrue(userList.size() > 0);

    }
}