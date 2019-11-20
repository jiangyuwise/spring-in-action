package com.codve.mybatis.service.impl;

import com.codve.mybatis.dao.UserMapper;
import com.codve.mybatis.model.User;
import com.codve.mybatis.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/19 15:48
 */

@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int save(User user) {
        return userMapper.save(user);
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
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
