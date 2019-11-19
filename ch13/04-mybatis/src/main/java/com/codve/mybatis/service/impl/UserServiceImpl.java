package com.codve.mybatis.service.impl;

import com.codve.mybatis.dao.UserMapper;
import com.codve.mybatis.model.User;
import com.codve.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    @Cacheable(value = "user")
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    @Override
    @CacheEvict(value = "user")
    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    @Cacheable(value = "user")
    public List<User> findComplex(User user, Long start, Long end, List<Long> ids, Integer orderBy) {
        return userMapper.selectComplex(user, start, end, ids, orderBy);
    }
}
