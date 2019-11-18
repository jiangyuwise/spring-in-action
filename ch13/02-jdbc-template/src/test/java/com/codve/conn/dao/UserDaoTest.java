package com.codve.conn.dao;

import com.codve.conn.model.Article;
import com.codve.conn.model.User;
import com.codve.conn.util.DataInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author admin
 * @date 2019/11/18 11:26
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DataInitializer initializer;

    private User user;

    private Article article;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Kate");
        user.setBirthday(System.currentTimeMillis());

        article = new Article();
        article.setTitle("go go go");
        article.setCreateTime(System.currentTimeMillis());

        initializer.init();
    }

    @Test
    public void findAllTest() throws SQLException {
        List<User> userList = userDao.findAll();
        assertTrue(userList.size() > 0);
    }

    @Test
    public void findTest() throws SQLException {
        List<User> userList = userDao.find("j");
        assertTrue(userList.size() > 0);
    }

    @Test
    public void findByIdTest() {
        User tmp = userDao.findById(1L);
        assertTrue(tmp.getId() > 0);
    }

    @Test
    public void saveTest() {
        int result = userDao.save(user);
        assertEquals(1, result);
    }

    @Test
    public void saveWithKeyTest() {
        int result = userDao.saveWithKey(user);
        assertEquals(1, result);
        assertTrue(user.getId() > 0);
    }

    @Test
    public void batchSaveTest() {
        User user1 = new User("Goslin", System.currentTimeMillis());
        User user2 = new User("Brown", System.currentTimeMillis());
        List<User> userList = Arrays.asList(user1, user2);
        assertTrue(userDao.batchSave(userList));
    }

    @Test
    public void saveWithArticleTest() {
        userDao.saveWithArticle(user, article);
    }

    @Test
    public void saveWithArticle2Test() {
        userDao.saveWithArticle2(user, article);
    }
}