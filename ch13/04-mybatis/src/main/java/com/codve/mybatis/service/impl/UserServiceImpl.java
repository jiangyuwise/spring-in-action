package com.codve.mybatis.service.impl;

import com.codve.mybatis.dao.ArticleMapper;
import com.codve.mybatis.dao.UserMapper;
import com.codve.mybatis.exception.EX;
import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.UserQuery;
import com.codve.mybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codve.mybatis.util.ExceptionUtil.exception;

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
            exception(EX.E_1101);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int deleteById(Long id) {
        int result = userMapper.deleteById(id);
        if (result != 1) {
            exception(EX.E_1102);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int update(UserDO userDO) {
        int result = userMapper.update(userDO);
        if (result != 1) {
            exception(EX.E_1103);
        }
        return result;
    }

    @Override
    public UserDO findById(Long id) {
        UserDO userDO = userMapper.findById(id);
        if (userDO == null) {
            exception(EX.E_1104);
        }
        return userDO;
    }

    @Override
    public List<UserDO> find(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        return userMapper.find(userQuery);
    }

    @Override
    public int count(UserQuery userQuery) {
        return userMapper.count(userQuery);
    }
}
