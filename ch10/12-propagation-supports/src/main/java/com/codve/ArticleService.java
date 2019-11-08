package com.codve;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/7 11:48
 */
public interface ArticleService {

    Article save(Article article);

    void saveWithException(Article article);

    List<Article> findByUserId(Long userId);

    List<Article> findByTitle(String title);
}
