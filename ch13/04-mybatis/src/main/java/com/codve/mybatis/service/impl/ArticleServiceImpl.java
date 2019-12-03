package com.codve.mybatis.service.impl;

import com.codve.mybatis.dao.ArticleMapper;
import com.codve.mybatis.exception.EX;
import com.codve.mybatis.model.data.object.ArticleDO;
import com.codve.mybatis.model.query.ArticleQuery;
import com.codve.mybatis.service.ArticleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codve.mybatis.util.ExceptionUtil.exception;

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
    public int save(ArticleDO articleDO) {
        int result = articleMapper.save(articleDO);
        if (result != 1) {
            exception(EX.E_1101);
        }
        return result;
    }

    @Override
    public int deleteById(Long id) {
        int result = articleMapper.deleteById(id);
        if (result != 1) {
            exception(EX.E_1102);
        }
        return result;
    }

    @Override
    public int update(ArticleDO articleDO) {
        int result = articleMapper.update(articleDO);
        if (result != 1) {
            exception(EX.E_1103);
        }
        return result;
    }

    @Override
    public ArticleDO findById(Long id) {
        ArticleDO articleDO = articleMapper.findById(id);
        if (articleDO == null) {
            exception(EX.E_1104);
        }
        return articleDO;
    }

    @Override
    public List<ArticleDO> find(ArticleQuery articleQuery, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleDO> articleDoList = articleMapper.find(articleQuery);
        if (articleDoList.size() == 0) {
            exception(EX.E_1104);
        }
        return articleDoList;
    }

    @Override
    public List<ArticleDO> find(ArticleQuery articleQuery) {
        return find(articleQuery, 1, 20);
    }

    @Override
    public int count(ArticleQuery articleQuery) {
        return articleMapper.count(articleQuery);
    }
}
