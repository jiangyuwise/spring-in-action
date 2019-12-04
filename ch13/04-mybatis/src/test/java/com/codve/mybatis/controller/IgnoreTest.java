package com.codve.mybatis.controller;

import com.codve.mybatis.model.Article;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author admin
 * @date 2019/12/3 22:39
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class IgnoreTest {

    @Autowired
    private ObjectMapper mapper;


    @Test
    void test() throws JsonProcessingException {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("hello, world");
        article.setUserId(1L);
        article.setCreateTime(System.currentTimeMillis());
        System.out.println(mapper.writeValueAsString(article));

    }

}
