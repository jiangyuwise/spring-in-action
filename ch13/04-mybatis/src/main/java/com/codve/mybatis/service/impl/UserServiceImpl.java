package com.codve.mybatis.service.impl;

import com.codve.mybatis.dao.UserMapper;
import com.codve.mybatis.exception.EX;
import com.codve.mybatis.model.auth.AuthUser;
import com.codve.mybatis.model.data.object.ArticleDO;
import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.UserQuery;
import com.codve.mybatis.service.ArticleService;
import com.codve.mybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codve.mybatis.util.ExceptionUtil.exception;

/**
 * @author admin
 * @date 2019/11/19 15:48
 */
@Service
@CacheConfig(cacheNames = "UserServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserMapper userMapper;

    private ArticleService articleService;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(allEntries = true)
    public int save(UserDO userDO) {
        int result = userMapper.save(userDO);
        if (result != 1) {
            exception(EX.E_1101);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(cacheNames = {"ArticleServiceImpl", "UserServiceImpl"}, allEntries = true)
    public int deleteById(Long id) {
        int result = userMapper.deleteById(id);
        if (result != 1) {
            exception(EX.E_1102);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict
    public int update(UserDO userDO) {
        int result = userMapper.update(userDO);
        if (result != 1) {
            exception(EX.E_1103);
        }
        return result;
    }

    @Override
    @Cacheable
    public UserDO findById(Long id) {
        UserDO userDO = userMapper.findById(id);
        if (userDO == null) {
            exception(EX.E_1104);
        }
        return userDO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userMapper.findByName(username);
        if (userDO == null) {
            throw new UsernameNotFoundException(EX.E_1201.getMessage());
        }
        return AuthUser.newInstance(userDO);
    }

    @Override
    @Cacheable(unless = "#result.size() == 0")
    public List<UserDO> find(UserQuery userQuery) {
        PageHelper.startPage(userQuery.getPageNum(), userQuery.getPageSize());
        return userMapper.find(userQuery);
    }

    @Override
    public int count(UserQuery userQuery) {
        return userMapper.count(userQuery);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int unionSave() {
        UserDO userDO = new UserDO();
        userDO.setName("James");
        userDO.setBirthday(System.currentTimeMillis());
        ArticleDO articleDO = new ArticleDO();
        articleDO.setUserId(1L);
        articleDO.setTitle("测试事务");
        articleDO.setCreateTime(System.currentTimeMillis());
        userMapper.save(userDO);
        articleService.save(articleDO);
        throw new RuntimeException("手动触发错误");
    }
}
