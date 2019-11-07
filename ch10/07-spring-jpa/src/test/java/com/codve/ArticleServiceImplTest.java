package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/7 11:52
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ArticleServiceImplTest {

    @Autowired
    private ArticleService articleService;

    private Article article;


    @BeforeEach
    void setUp() {
        article = new Article();
        article.setUserId(3);
        article.setTitle("Life is hard");
        article.setCreateTime(System.currentTimeMillis());
    }

    @Test
    public void findByTitle() {
        List<Article> articleList = articleService.findByTitle("Java");
        assertNotNull(articleList);
        articleList.forEach((e) -> System.out.println(e.toString())) ;
    }
}