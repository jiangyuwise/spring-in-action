package com.codve.mybatis.service;

import com.codve.mybatis.model.Article;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/19 17:22
 */
public interface ArticleService {

    int save(Article article);

    int deleteById(Long id);

    int update(Article article);

    Article findById(Long id);

    List<Article> find(Article article, Long start, Long end, List<Long> userIds, Integer orderBy,
                       int pageNum, int pageSize);

}
