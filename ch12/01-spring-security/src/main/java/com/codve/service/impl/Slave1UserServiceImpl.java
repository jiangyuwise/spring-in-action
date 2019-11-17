package com.codve.service.impl;

import com.codve.dao.slave1.Slave1UserMapper;
import com.codve.dao.spring.UserMapper;
import com.codve.model.User;
import com.codve.service.Slave1UserService;
import com.codve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/17 16:46
 */
@Service
public class Slave1UserServiceImpl implements Slave1UserService {

    private Slave1UserMapper userMapper;

    @Autowired
    public void setUserMapper(Slave1UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> listAll() {
        return userMapper.selectList(null);
    }
}
