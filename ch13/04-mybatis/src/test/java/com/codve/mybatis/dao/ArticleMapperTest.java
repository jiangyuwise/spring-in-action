package com.codve.mybatis.dao;

import com.codve.mybatis.model.Article;
import com.codve.mybatis.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/19 17:51
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private DataSource dataSource;

    private static ResourceDatabasePopulator populator;

    private Article article;

    @BeforeAll
    static void setUpAll() {
        populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data/article.sql"));
    }

    @BeforeEach
    void setUp() {
        populator.execute(dataSource);
        article = new Article();
        article.setUserId(4L);
        article.setTitle("环游世界 100 天");
        article.setCreateTime(System.currentTimeMillis());

    }

    @Test
    void save() {
        int result= articleMapper.save(article);
        assertTrue(result > 0);
        assertTrue(article.getId() > 0);
    }

    @Test
    void saveNoParam() {
        article = new Article();
        assertEquals(1, articleMapper.save(article));
    }

    @Test
    void saveOneParam() {
        article = new Article();
        article.setId(1L);
        assertEquals(1, articleMapper.save(article));

        article = new Article();
        article.setUserId(1L);
        assertEquals(1, articleMapper.save(article));

        article = new Article();
        article.setTitle("嘻嘻嘻");
        assertEquals(1, articleMapper.save(article));

        article = new Article();
        article.setCreateTime(System.currentTimeMillis());
        assertEquals(1, articleMapper.save(article));
    }

    @Test
    void saveNegativeParam() {
        article = new Article();
        article.setUserId(-1L);
        assertThrows(RuntimeException.class, () -> {
            articleMapper.save(article);
        });
    }

    @Test
    void saveTwoParam() {
        article = new Article();
        article.setUserId(1L);
        article.setTitle("哈哈哈");
        assertEquals(1, articleMapper.save(article));

        article = new Article();
        article.setUserId(1L);
        article.setCreateTime(0L);
        assertEquals(1, articleMapper.save(article));

        article = new Article();
        article.setCreateTime(0L);
        article.setTitle("哈哈哈");
        assertEquals(1, articleMapper.save(article));
    }

    @Test
    void deleteById() {
        int result = articleMapper.deleteById(1L);
        assertEquals(1, result);
    }

    @Test
    void deleteByIdInMax() {
        int result = articleMapper.deleteById(1024L);
        assertEquals(0, result);
    }

    @Test
    void deleteByIdNegative() {
        int result = articleMapper.deleteById(-1L);
        assertEquals(0, result);
    }

    @Test
    void updateNoParam() {
        Article article = new Article();
        assertEquals(0, articleMapper.update(article));
    }

    @Test
    void updateOneParam() {
        article = new Article();
        article.setId(1L);
        assertEquals(1, articleMapper.update(article));

        article = new Article();
        article.setUserId(1L);
        assertEquals(0, articleMapper.update(article));

        article = new Article();
        article.setTitle("");
        assertEquals(0, articleMapper.update(article));

        article = new Article();
        article.setCreateTime(0L);
        assertEquals(0, articleMapper.update(article));
    }

    @Test
    public void updateTwoParam() {
        article = new Article();
        article.setId(1L);
        article.setUserId(10L);
        assertEquals(1, articleMapper.update(article));

        article = new Article();
        article.setId(1L);
        article.setTitle("哈哈哈");
        assertEquals(1, articleMapper.update(article));

        article = new Article();
        article.setId(1L);
        article.setCreateTime(1L);
        assertEquals(1, articleMapper.update(article));

        article = new Article();
        article.setUserId(1L);
        article.setTitle("");
        assertEquals(0, articleMapper.update(article));

        article = new Article();
        article.setUserId(1L);
        article.setCreateTime(1L);
        assertEquals(0, articleMapper.update(article));

        article = new Article();
        article.setTitle("");
        article.setCreateTime(1L);
        assertEquals(0, articleMapper.update(article));
    }

    @Test
    void update() {
        article.setId(1L);
        int result = articleMapper.update(article);
        assertEquals(1, result);
    }

    @Test
    void findById() {
        Article tmp = articleMapper.findById(1L);
        assertNotNull(tmp);
        assertTrue(tmp.getId() > 0);
    }

    @Test
    void findByIdNone() {
        Article tmp = articleMapper.findById(8L);
        assertNull(tmp);
    }

    @Test
    void findNoParam() {
        PageHelper.startPage(1, 1);
        List<Article> userList = articleMapper.find(null, null, null, null, null);
        assertTrue(userList.size() > 0);
    }

    @Test
    void findByArticleNoParam() {
        PageHelper.startPage(1, 1);
        List<Article> userList = articleMapper.find(new Article(), null, null, null, null);
        assertTrue(userList.size() > 0);
    }

    @Test
    void findByArticleOneParam() {
        PageHelper.startPage(1, 1);
        article = new Article();
        article.setId(1L);
        List<Article> userList = articleMapper.find(article, null, null, null, null);
        assertTrue(userList.size() > 0);

        PageHelper.startPage(1, 1);
        article = new Article();
        article.setUserId(1L);
        userList = articleMapper.find(article, null, null, null, null);
        assertTrue(userList.size() > 0);

        PageHelper.startPage(1, 1);
        article = new Article();
        article.setTitle("C");
        userList = articleMapper.find(article, null, null, null, null);
        assertTrue(userList.size() > 0);
    }

    @Test
    void findByArticleTwoParam() {
        PageHelper.startPage(1, 1);
        article = new Article();
        article.setId(1L);
        article.setUserId(1L);
        List<Article> articles = articleMapper.find(article, null, null, null, null);
        assertTrue(articles.size() > 0);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        assertTrue(pageInfo.getSize() > 0);

        PageHelper.startPage(1, 1);
        article = new Article();
        article.setId(1L);
        article.setTitle("C");
        articles = articleMapper.find(article, null, null, null, null);
        assertTrue(articles.size() > 0);

        PageHelper.startPage(1, 1);
        article = new Article();
        article.setUserId(1L);
        article.setTitle("C");
        articles = articleMapper.find(article, null, null, null, null);
        assertTrue(articles.size() > 0);
    }

    @Test
    void findByOneParam() {
        PageHelper.startPage(1, 1);
        List<Article> articles = articleMapper.find(null, 0L, null, null, null);
        assertTrue(articles.size() > 0);

        PageHelper.startPage(1, 1);
        articles = articleMapper.find(null, null, System.currentTimeMillis(), null, null);
        assertTrue(articles.size() > 0);

        PageHelper.startPage(1, 1);
        articles = articleMapper.find(null, null, null, Arrays.asList(1L, 2L), null);
        assertTrue(articles.size() > 0);
    }

    @Test
    void findByTwoParam() {
        article = new Article();
        article.setUserId(1L);
        article.setTitle("c");
        article.setCreateTime(System.currentTimeMillis());
        PageHelper.startPage(1, 1);
        List<Article> articles = articleMapper.find(article, 0L, null, null, 1);
        assertTrue(articles.size() > 0);

        PageHelper.startPage(1, 1);
        articles = articleMapper.find(article, null, System.currentTimeMillis(), null, 2);
        assertTrue(articles.size() > 0);

        PageHelper.startPage(1, 1);
        articles = articleMapper.find(article, null, null, Arrays.asList(1L, 2L), 3);
        assertTrue(articles.size() > 0);

        PageHelper.startPage(1, 1);
        articles = articleMapper.find(article, 0L, null, Arrays.asList(1L, 2L), 4);
        assertTrue(articles.size() > 0);
    }
}