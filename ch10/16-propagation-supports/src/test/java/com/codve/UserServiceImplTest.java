package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/9 10:06
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class UserServiceImplTest {

    private User user;

    private Article article;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Aka");
        user.setBirthday(System.currentTimeMillis());

        article = new Article();
        article.setUserId(3L);
        article.setTitle("Life is hard");
        article.setCreateTime(System.currentTimeMillis());
    }

    @Test
    public void test1() {
        assertThrows(RuntimeException.class, () -> {
            userService.save1(user, article);
        });
    }

    @Test
    public void test2() {
        userService.save2(user, article);
    }

    @Test
    public void test3() {
        assertThrows(RuntimeException.class, () -> {
            userService.save3(user, article);
        });
    }
}