package com.codve.service;

import com.codve.model.User;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/13 16:15
 */
public interface UserService {

    /**
     * 根据 id 查找用户
     * @param id id
     * @return User
     */
    User findById(Long id);

    /**
     * 插入新用户
     * @param user user
     * @return int
     */
    int insert(User user);

    /**
     * 查找所有用户
     * @return List<User>
     */
    List<User> findAll();
}
