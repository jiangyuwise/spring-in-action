package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/8 16:53
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class ArticleRepositoryImplTest {

    @Autowired
    private ArticleRepository articleRepository;

    private Article article;

    @BeforeEach
    void setUp() {
        article = new Article();
        article.setUserId(3L);
        article.setTitle("to be or not to be.");
        article.setCreateTime(System.currentTimeMillis());
    }

    @Test
    public void saveTest() {
        articleRepository.save(article);
        assertTrue(article.getId() > 0);
        System.out.println(article);
    }

    @Test
    public void findByUserIdTest() {
        List<Article> articleList = articleRepository.findByUserId(1L);
        assertNotNull(articleList);
        articleList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void findByTitleTest() {
        List<Article> articleList = articleRepository.findByTitle("Spring");
        assertNotNull(articleList);
        articleList.forEach(e -> System.out.println(e.toString()));
    }
}