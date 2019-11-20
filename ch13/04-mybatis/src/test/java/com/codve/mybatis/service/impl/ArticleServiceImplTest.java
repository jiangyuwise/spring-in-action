package com.codve.mybatis.service.impl;

import com.codve.mybatis.model.Article;
import com.codve.mybatis.model.User;
import com.codve.mybatis.service.ArticleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/20 10:43
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ArticleServiceImplTest {

    private static ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ArticleService articleService;

    private Article article;

    @BeforeAll
    static void setUpAll() {
        populator.addScript(new ClassPathResource("data/article.sql"));
    }

    @BeforeEach
    void setUp() {
        populator.execute(dataSource);
        article = new Article();
        article.setUserId(10L);
        article.setTitle("Hello, world");
        article.setCreateTime(System.currentTimeMillis());
    }

    @Test
    void save() {
        assertEquals(1, articleService.save(article));
    }

    @Test
    void deleteById() {
        assertEquals(1, articleService.deleteById(1L));
    }

    @Test
    void update() {
        article.setId(1L);
        assertEquals(1, articleService.update(article));
    }

    @Test
    void findById() {
        article = articleService.findById(1L);
        assertNotNull(article);
        assertEquals(1L, article.getId());

        article = articleService.findById(10L);
        assertNull(article);
    }

    @Test
    void find() {
        articleService.find(article, 0L, null, Arrays.asList(1L), 1,
                1, 1);
    }
}