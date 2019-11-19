package com.codve.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codve.mybatis.dao.UserMapper;
import com.codve.mybatis.model.User;
import com.codve.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @date 2019/11/19 09:41
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
