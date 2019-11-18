package com.codve.conn.dao;

import com.codve.conn.model.Article;
import com.codve.conn.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
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
    public void saveTest() throws SQLException {
        int result = userDao.save(user);
        assertEquals(1, result);
    }

    @Test
    public void saveWithKeyTest() throws SQLException {
        int result = userDao.saveWithKey(user);
        assertEquals(1, result);
        assertTrue(user.getId() > 0);
    }

    @Test
    public void saveWithArticleTest() throws SQLException {
        userDao.saveWithArticle(user, article);
    }
}