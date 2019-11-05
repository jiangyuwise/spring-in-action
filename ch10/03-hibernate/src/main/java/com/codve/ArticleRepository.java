package com.codve;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/5 11:36
 */
@Repository
@Transactional(readOnly = true)
public class ArticleRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Article> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Article a").list();
    }

    public Article save(Article article) {
        sessionFactory.getCurrentSession().save(article);
        return article;
    }
}
