package com.codve.mybatis.service;

import com.codve.mybatis.model.Article;

/**
 * @author admin
 * @date 2019/11/19 17:22
 */
public interface ArticleService {

    void save(Article article);

    void getById(Long id);


}
