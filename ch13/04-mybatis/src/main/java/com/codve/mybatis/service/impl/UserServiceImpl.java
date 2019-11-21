package com.codve.mybatis.service.impl;

import com.codve.mybatis.dao.UserMapper;
import com.codve.mybatis.model.User;
import com.codve.mybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/19 15:48
 */

@Service
@CacheConfig(cacheNames = "UserServiceImpl")
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @CacheEvict(allEntries = true)
    public int save(User user) {
        return userMapper.save(user);
    }

    @Override
    @CacheEvict(allEntries = true)
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    @Cacheable(unless = "#result == null ")
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    @Cacheable(unless = "#result.size() == 0")
    public List<User> find(User user,
                           Long start,
                           Long end,
                           List<Long> userIds,
                           Integer orderBy,
                           int pageNum,
                           int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.find(user, start, end, userIds, orderBy);
    }
}
