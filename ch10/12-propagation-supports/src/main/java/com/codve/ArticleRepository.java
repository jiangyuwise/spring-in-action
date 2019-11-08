package com.codve;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/7 11:41
 */
public interface ArticleRepository {

    Article save(Article article);

    List<Article> findByUserId(Long userId);

    List<Article> findByTitle(String title);
}
