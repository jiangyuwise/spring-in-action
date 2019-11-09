package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.MANDATORY)
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveWithException(Article article) throws RuntimeException {
        articleRepository.save(article);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void saveWithCatch(Article article) {
        try {
            articleRepository.save(article);
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println(e.getClass());
        }

    }
}
