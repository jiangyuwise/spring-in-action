package com.codve.mybatis.service.impl;

import com.codve.mybatis.convert.ArticleConvert;
import com.codve.mybatis.convert.UserConvert;
import com.codve.mybatis.dao.ArticleMapper;
import com.codve.mybatis.dao.UserMapper;
import com.codve.mybatis.exception.EX;
import com.codve.mybatis.model.business.object.ArticleBO;
import com.codve.mybatis.model.data.object.ArticleDO;
import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.ArticleQuery;
import com.codve.mybatis.model.query.UserQuery;
import com.codve.mybatis.model.vo.ArticleVO;
import com.codve.mybatis.service.ArticleService;
import com.codve.mybatis.util.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.codve.mybatis.util.ExceptionUtil.exception;

/**
 * @author admin
 * @date 2019/11/20 10:40
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleMapper articleMapper;

    private UserMapper userMapper;

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = RuntimeException.class)
    public int save(ArticleDO articleDO) {
        UserDO userDO = userMapper.findById(articleDO.getUserId());
        if (userDO == null) {
            exception(EX.E_1201);
        }
        int result = articleMapper.save(articleDO);
        if (result != 1) {
            exception(EX.E_1101);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteById(Long id) {
        int result = articleMapper.deleteById(id);
        if (result != 1) {
            exception(EX.E_1102);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int update(ArticleDO articleDO) {
        if (articleMapper.findById(articleDO.getId()) == null) {
            exception(EX.E_1401);
        }

        if (userMapper.findById(articleDO.getUserId()) == null) {
            exception(EX.E_1201);
        }

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
    public List<ArticleDO> find(ArticleQuery articleQuery) {
        PageHelper.startPage(articleQuery.getPageNum(), articleQuery.getPageSize());
        return articleMapper.find(articleQuery);
    }

    @Override
    public int count(ArticleQuery articleQuery) {
        return articleMapper.count(articleQuery);
    }

    @Override
    public PageResult<ArticleBO> unionFind(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        List<UserDO> userDoList = userMapper.find(userQuery);
        PageInfo<UserDO> pageInfo = new PageInfo<>(userDoList);
        if (userDoList.size() == 0) {
            exception(EX.E_1104);
        }
        List<Long> userIdList = userDoList.stream().map(UserDO::getId)
                .collect(Collectors.toList());

        // 查找文章总数
        ArticleQuery articleQuery = new ArticleQuery();
        articleQuery.setUserIds(userIdList);
        int articleCount = articleMapper.count(articleQuery);

        // 查出所有文章
        PageHelper.startPage(1, articleCount);
        List<ArticleDO> articleDoList = articleMapper.find(articleQuery);

        List<ArticleBO> articleBoList = userDoList.stream()
                .map(e -> {
                    ArticleBO articleBo = new ArticleBO();
                    articleBo.setUserVO(UserConvert.convert(e));
                    List<ArticleVO> articleList = articleDoList.stream()
                            .filter(a -> a.getUserId().equals(e.getId()))
                            .map(ArticleConvert::convert)
                            .collect(Collectors.toList());
                    articleBo.setArticleVoList(articleList);
                    return articleBo;
                }).collect(Collectors.toList());
        PageResult<ArticleBO> result = new PageResult<>();
        result.setList(articleBoList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
