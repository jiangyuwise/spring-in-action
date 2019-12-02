package com.codve.mybatis.dao;

import com.codve.mybatis.model.data.object.ArticleDO;
import com.codve.mybatis.model.query.ArticleQuery;
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

    @BeforeAll
    static void setUpAll() {
        populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data/article.sql"));
    }

    @BeforeEach
    void setUp() {
        populator.execute(dataSource);

    }

    @Test
    void saveNoParam() {
        ArticleDO articleDO = new ArticleDO();
        assertEquals(1, articleMapper.save(articleDO));
    }

    @Test
    void saveOneParam() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setId(1L);
        assertEquals(1, articleMapper.save(articleDO));

        articleDO = new ArticleDO();
        articleDO.setUserId(1L);
        assertEquals(1, articleMapper.save(articleDO));

        articleDO = new ArticleDO();
        articleDO.setTitle("嘻嘻嘻");
        assertEquals(1, articleMapper.save(articleDO));

        articleDO = new ArticleDO();
        articleDO.setCreateTime(System.currentTimeMillis());
        assertEquals(1, articleMapper.save(articleDO));
    }

    @Test
    void saveNegativeParam() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setUserId(-1L);
        assertThrows(RuntimeException.class, () -> {
            articleMapper.save(articleDO);
        });
    }

    @Test
    void saveTwoParam() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setUserId(1L);
        articleDO.setTitle("哈哈哈");
        assertEquals(1, articleMapper.save(articleDO));

        articleDO = new ArticleDO();
        articleDO.setUserId(1L);
        articleDO.setCreateTime(0L);
        assertEquals(1, articleMapper.save(articleDO));

        articleDO = new ArticleDO();
        articleDO.setCreateTime(0L);
        articleDO.setTitle("哈哈哈");
        assertEquals(1, articleMapper.save(articleDO));
    }

    @Test
    void save() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setUserId(1L);
        articleDO.setTitle("哈哈哈");
        articleDO.setCreateTime(System.currentTimeMillis());
        int result= articleMapper.save(articleDO);
        assertTrue(result > 0);
        assertTrue(articleDO.getId() > 0);
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
        ArticleDO articleDO = new ArticleDO();
        assertEquals(0, articleMapper.update(articleDO));
    }

    @Test
    void updateOneParam() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setId(1L);
        assertEquals(1, articleMapper.update(articleDO));

        articleDO = new ArticleDO();
        articleDO.setUserId(1L);
        assertEquals(0, articleMapper.update(articleDO));

        articleDO = new ArticleDO();
        articleDO.setTitle("");
        assertEquals(0, articleMapper.update(articleDO));

        articleDO = new ArticleDO();
        articleDO.setCreateTime(0L);
        assertEquals(0, articleMapper.update(articleDO));

    }

    @Test
    public void updateTwoParam() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setId(1L);
        articleDO.setUserId(10L);
        assertEquals(1, articleMapper.update(articleDO));

        articleDO = new ArticleDO();
        articleDO.setId(1L);
        articleDO.setTitle("哈哈哈");
        assertEquals(1, articleMapper.update(articleDO));

        articleDO = new ArticleDO();
        articleDO.setId(1L);
        articleDO.setCreateTime(1L);
        assertEquals(1, articleMapper.update(articleDO));

        articleDO = new ArticleDO();
        articleDO.setUserId(1L);
        articleDO.setTitle("");
        assertEquals(0, articleMapper.update(articleDO));

        articleDO = new ArticleDO();
        articleDO.setUserId(1L);
        articleDO.setCreateTime(1L);
        assertEquals(0, articleMapper.update(articleDO));

        articleDO = new ArticleDO();
        articleDO.setTitle("");
        articleDO.setCreateTime(1L);
        assertEquals(0, articleMapper.update(articleDO));
    }

    @Test
    void update() {
        ArticleDO articleDO = new ArticleDO();
        articleDO.setId(1L);
        articleDO.setUserId(1L);
        articleDO.setTitle("哈哈");
        articleDO.setCreateTime(System.currentTimeMillis());
        assertEquals(1, articleMapper.update(articleDO));
    }

    @Test
    void findById() {
        ArticleDO articleDO = articleMapper.findById(1L);
        assertNotNull(articleDO);
        assertTrue(articleDO.getId() > 0);
    }

    @Test
    void findByIdNone() {
        ArticleDO articleDO = articleMapper.findById(8L);
        assertNull(articleDO);
    }

    @Test
    void findNoParam() {
        PageHelper.startPage(1, 1);
        List<ArticleDO> articleDOList = articleMapper.find(null);
        assertTrue(articleDOList.size() > 0);
    }

    @Test
    void findByArticleNoParam() {
        PageHelper.startPage(1, 1);
        List<ArticleDO> articleDOList = articleMapper.find(new ArticleQuery());
        assertTrue(articleDOList.size() > 0);
    }

    @Test
    void findByArticleOneParam() {
        PageHelper.startPage(1, 1);
        ArticleQuery articleQuery = new ArticleQuery();
        articleQuery.setId(1L);
        List<ArticleDO> articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);

        PageHelper.startPage(1, 1);
        articleQuery = new ArticleQuery();
        articleQuery.setUserId(1L);
        articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);

        PageHelper.startPage(1, 1);
        articleQuery = new ArticleQuery();
        articleQuery.setTitle("C");
        articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);
    }

    @Test
    void findByArticleTwoParam() {
        ArticleQuery articleQuery = new ArticleQuery();
        articleQuery.setId(1L);
        articleQuery.setUserId(1L);
        PageHelper.startPage(1, 1);
        List<ArticleDO> articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);

        PageInfo<ArticleDO> pageInfo = new PageInfo<>(articleDOList);
        assertTrue(pageInfo.getSize() > 0);

        articleQuery = new ArticleQuery();
        articleQuery.setId(1L);
        articleQuery.setTitle("C");
        PageHelper.startPage(1, 1);
        articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);

        articleQuery = new ArticleQuery();
        articleQuery.setUserId(1L);
        articleQuery.setTitle("C");
        PageHelper.startPage(1, 1);
        articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);
    }

    @Test
    void findByOneParam() {
        ArticleQuery articleQuery = new ArticleQuery();
        articleQuery.setStart(0L);
        PageHelper.startPage(1, 1);
        List<ArticleDO> articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);

        articleQuery = new ArticleQuery();
        articleQuery.setEnd(System.currentTimeMillis());
        PageHelper.startPage(1, 1);
        articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);

        articleQuery = new ArticleQuery();
        articleQuery.setUserIds(Arrays.asList(1L, 2L));
        PageHelper.startPage(1, 1);
        articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);
    }

    @Test
    void findByTwoParam() {
        ArticleQuery articleQuery = new ArticleQuery();
        articleQuery.setUserId(1L);
        articleQuery.setTitle("c");
        PageHelper.startPage(1, 1);
        List<ArticleDO> articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);

        articleQuery = new ArticleQuery();
        articleQuery.setUserId(1L);
        articleQuery.setTitle("c");
        articleQuery.setEnd(System.currentTimeMillis());
        articleQuery.setOrderBy(2);
        PageHelper.startPage(1, 1);
        articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);

        articleQuery = new ArticleQuery();
        articleQuery.setTitle("c");
        articleQuery.setUserIds(Arrays.asList(1L, 2L));
        articleQuery.setOrderBy(3);
        PageHelper.startPage(1, 1);
        articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);

        articleQuery = new ArticleQuery();
        articleQuery.setTitle("c");
        articleQuery.setStart(0L);
        articleQuery.setUserIds(Arrays.asList(1L, 2L));
        articleQuery.setOrderBy(4);
        PageHelper.startPage(1, 1);
        articleDOList = articleMapper.find(articleQuery);
        assertTrue(articleDOList.size() > 0);
    }

    @Test
    void countTest() {
        ArticleQuery articleQuery = new ArticleQuery();
        articleQuery.setTitle("c");
        articleQuery.setStart(0L);
        articleQuery.setUserIds(Arrays.asList(1L, 2L));
        articleQuery.setOrderBy(4);
        int count = articleMapper.count(articleQuery);
        assertTrue(count > 0);
    }
}