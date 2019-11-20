package com.codve.mybatis.service.impl;

import com.codve.mybatis.dao.ArticleMapper;
import com.codve.mybatis.model.Article;
import com.codve.mybatis.service.ArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/20 10:40
 */
@Service
@CacheConfig(cacheNames = "ArticleServiceImpl")
public class ArticleServiceImpl implements ArticleService {

    private ArticleMapper articleMapper;

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    @CacheEvict(allEntries = true)
    public int save(Article article) {
        return articleMapper.save(article);
    }

    @Override
    @CacheEvict(allEntries = true)
    public int deleteById(Long id) {
        return articleMapper.deleteById(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public int update(Article article) {
        return articleMapper.update(article);
    }

    @Override
    @Cacheable(unless = "#result == null")
    public Article findById(Long id) {
        return articleMapper.findById(id);
    }

    @Override
    @Cacheable(unless = "#result.size() == 0")
    public List<Article> find(Article article, Long start, Long end, List<Long> userIds, Integer orderBy,
                              int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return articleMapper.find(article, start, end, userIds, orderBy);
    }
}
