package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

/**
 * @author admin
 * @date 2019/11/5 11:38
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class ArticleRepositoryTest {

    @Autowired
    ArticleRepository articleRepository;

    private Article article;

    @BeforeEach
    public void setUp() {
        article = new Article();
        article.setUserId(2);
        article.setTitle("java cook book");
        article.setCreateTime(System.currentTimeMillis());
    }
    @Test
    public void findAllTest() {
        List<Article> articleList = articleRepository.findAll();
        assertNotNull(articleList);

        articleList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void saveTest() {
        article = articleRepository.save(article);
        System.out.println(article.getId());

        List<Article> articleList = articleRepository.findAll();
        articleList.forEach(e -> System.out.println(e.toString()));
        assertTrue(article.getId() > 0);

    }

}