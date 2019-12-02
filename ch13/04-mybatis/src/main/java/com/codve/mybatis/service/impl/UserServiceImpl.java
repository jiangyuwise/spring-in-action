package com.codve.mybatis.service.impl;

import com.codve.mybatis.dao.ArticleMapper;
import com.codve.mybatis.dao.UserMapper;
import com.codve.mybatis.exception.EX;
import com.codve.mybatis.model.business.object.UserArticleBO;
import com.codve.mybatis.model.data.object.ArticleDO;
import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.ArticleQuery;
import com.codve.mybatis.model.query.UserQuery;
import com.codve.mybatis.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.codve.mybatis.util.ExceptionUtil.exception;
import static java.util.stream.Collectors.toList;

/**
 * @author admin
 * @date 2019/11/19 15:48
 */

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    private ArticleMapper articleMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int save(UserDO userDO) {
        int result = userMapper.save(userDO);
        if (result != 1) {
            exception(EX.E_301);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteById(Long id) {
        int result = userMapper.deleteById(id);
        if (result != 1) {
            exception(EX.E_302);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int update(UserDO userDO) {
        int result = userMapper.update(userDO);
        if (result != 1) {
            exception(EX.E_303);
        }
        return result;
    }

    @Override
    public UserDO findById(Long id) {
        UserDO userDO = userMapper.findById(id);
        if (userDO == null) {
            exception(EX.E_304);
        }
        return userDO;
    }

    @Override
    public List<UserDO> find(UserQuery userQuery, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDO> userDoList = userMapper.find(userQuery);
        if (userDoList.size() == 0) {
            exception(EX.E_304);
        }
        return userDoList;
    }

    @Override
    public List<UserDO> find(UserQuery userQuery) {
        return find(userQuery, 1, 20);
    }

    @Override
    public int count(UserQuery userQuery) {
        return userMapper.count(userQuery);
    }

    @Override
    public Page<UserArticleBO> findWithArticle(UserQuery userQuery, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDO> userDoList = userMapper.find(userQuery);
        if (userDoList.size() == 0) {
            exception(EX.E_304);
        }
        List<Long> userIds = userDoList.stream().map(UserDO::getId).collect(toList());

        ArticleQuery articleQuery = new ArticleQuery();
        articleQuery.setUserIds(userIds);
        int articleNum = articleMapper.count(articleQuery);

        PageHelper.startPage(1, articleNum);
        List<ArticleDO> articleDoList = articleMapper.find(articleQuery);

        Page<UserArticleBO> boList = new Page<>();
        for (UserDO userDO : userDoList) {
            UserArticleBO bo = new UserArticleBO();
            bo.setUserDO(userDO);
            List<ArticleDO> articleList = new ArrayList<>();
            bo.setArticleDoList(new ArrayList<>());
            for (ArticleDO articleDO : articleDoList) {
                if (articleDO.getUserId().equals(userDO.getId())) {
                    articleList.add(articleDO);
                }
            }
            bo.setArticleDoList(articleList);
            boList.add(bo);
        }
        Page userDoPage = (Page) userDoList;
        boList.setTotal(userDoPage.getTotal());
        return boList;

    }
}
