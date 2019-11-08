package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/7 11:49
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void saveWithException(Article article) {
        articleRepository.save(article);
        throw new RuntimeException();
    }

    @Override
    public List<Article> findByUserId(Long userId) {
        return articleRepository.findByUserId(userId);
    }

    @Override
    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }
}
