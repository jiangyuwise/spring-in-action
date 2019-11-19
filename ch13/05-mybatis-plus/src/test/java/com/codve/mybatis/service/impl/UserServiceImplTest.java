package com.codve.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codve.mybatis.model.User;
import com.codve.mybatis.service.UserService;
import com.codve.mybatis.util.DataInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/19 09:42
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DataInitializer initializer;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("安吉拉");
        user.setBirthday(System.currentTimeMillis());
        initializer.init();
    }

    @Test
    public void saveTest() {
        userService.save(user);
        assertTrue(user.getId() > 0);
    }

    @Test
    public void saveBatchTest() {
        User user1 = new User();
        user1.setName("诸葛亮");
        user1.setBirthday(System.currentTimeMillis());
        List<User> userList = Arrays.asList(user, user1);
        assertTrue(userService.saveBatch(userList));
    }

    @Test
    public void saveBatchTest2() {
        User user1 = new User();
        user1.setName("诸葛亮");
        user1.setBirthday(System.currentTimeMillis());
        List<User> userList = Arrays.asList(user, user1);
        assertTrue(userService.saveBatch(userList, 1));
    }

    @Test
    public void removeByIdTest() {
        assertTrue(userService.removeById(1L));
    }

    @Test
    public void lambdaQueryTest() {
        List<User> userList = userService.lambdaQuery().eq(User::getName, "James").list();
        assertTrue(userList.size() > 0);
    }

    @Test
    public void lambdaQueryLikeTest() {
        List<User> userList = userService.lambdaQuery().like(User::getName, "j").list();
        assertTrue(userList.size() > 0);
    }

    @Test
    public void lambdaQueryInTest() {
        List<User> userList = userService.lambdaQuery().in(User::getId, 1, 2, 3).list();
        assertTrue(userList.size() > 0);
    }

    @Test
    public void lambdaQuerySelectTest() {
        List<User> userList = userService.lambdaQuery().select(User::getName).list();
        assertTrue(userList.size() > 0);
    }

    @Test
    public void lambdaUpdateTest() {
        boolean result = userService.lambdaUpdate().eq(User::getId, 1).set(User::getName, "Bob").update();
        assertTrue(result);
    }

    @Test
    public void lambdaRemoveTest() {
        boolean result = userService.lambdaUpdate().eq(User::getId, 1).remove();
        assertTrue(result);
    }
}