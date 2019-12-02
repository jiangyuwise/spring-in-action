package com.codve.mybatis.service.impl;

import com.codve.mybatis.model.Article;
import com.codve.mybatis.model.data.object.ArticleDO;
import com.codve.mybatis.model.query.ArticleQuery;
import com.codve.mybatis.service.ArticleService;
import com.codve.mybatis.util.PageResult;
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

    @BeforeAll
    static void setUpAll() {
        populator.addScript(new ClassPathResource("data/article.sql"));
    }

    @BeforeEach
    void setUp() {
        populator.execute(dataSource);
    }

    @Test
    void save() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setUserId(10L);
        articleDO.setTitle("7 天环游地球");
        articleDO.setCreateTime(System.currentTimeMillis());

        assertEquals(1, articleService.save(articleDO));
    }

    @Test
    void deleteById() {
        assertEquals(1, articleService.deleteById(1L));

        assertThrows(RuntimeException.class, () -> {
            articleService.deleteById(100L);
        });
    }

    @Test
    void update() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setId(1L);
        assertEquals(1, articleService.update(articleDO));

        articleDO.setId(100L);
        assertThrows(RuntimeException.class, () -> {
            articleService.update(articleDO);
        });
    }

    @Test
    void findById() {
        ArticleDO articleDO = articleService.findById(1L);
        assertNotNull(articleDO);
        assertEquals(1L, articleDO.getId());

        assertThrows(RuntimeException.class, () -> {
            articleService.findById(100L);
        });
    }

    @Test
    void find() {
        ArticleQuery query = new ArticleQuery();
        List<ArticleDO> articleDOList = articleService.find(query, 1, 2);
        assertTrue(articleDOList.size() > 0);

        PageResult<ArticleDO> pageResult = new PageResult<>(articleDOList);
        assertTrue(pageResult.getTotal() > 0);

        articleDOList = articleService.find(query);
        assertTrue(articleDOList.size() > 0);

        query.setStart(System.currentTimeMillis());

        assertThrows(RuntimeException.class, () -> {
            articleService.find(query, 1, 2);
        });
    }

    @Test
    void count() {
        ArticleQuery query = new ArticleQuery();
        assertTrue(articleService.count(query) > 0);
    }
}