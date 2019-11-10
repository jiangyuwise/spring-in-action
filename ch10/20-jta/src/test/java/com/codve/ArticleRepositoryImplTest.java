package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/9 22:42
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
        article.setTitle("Harry Potter");
        article.setCreateTime(System.currentTimeMillis());
    }

    @Test
    @Transactional
    public void saveTest() {
        articleRepository.save(article);
        assertTrue(article.getId() > 0);
        System.out.println(article.toString());
    }
}