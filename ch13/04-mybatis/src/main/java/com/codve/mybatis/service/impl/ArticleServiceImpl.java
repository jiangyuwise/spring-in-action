package com.codve.mybatis.service.impl;

import com.codve.mybatis.dao.ArticleMapper;
import com.codve.mybatis.model.Article;
import com.codve.mybatis.service.ArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/20 10:40
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleMapper articleMapper;

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public int save(Article article) {
        return articleMapper.save(article);
    }

    @Override
    public int deleteById(Long id) {
        return articleMapper.deleteById(id);
    }

    @Override
    public int update(Article article) {
        return articleMapper.update(article);
    }

    @Override
    public Article findById(Long id) {
        return articleMapper.findById(id);
    }

    @Override
    public List<Article> find(Article article, Long start, Long end, List<Long> userIds, Integer orderBy,
                              int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return articleMapper.find(article, start, end, userIds, orderBy);
    }
}
