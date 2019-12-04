package com.codve;

/**
 * @author admin
 * @date 2019/11/7 11:48
 */
public interface ArticleService {

    Article save(Article article);

    void saveWithException(Article article);

    void saveWithCatch(Article article);
}
