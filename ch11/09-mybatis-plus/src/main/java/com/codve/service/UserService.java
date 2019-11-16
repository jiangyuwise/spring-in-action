package com.codve.service;

import com.codve.model.User;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/16 12:15
 */
public interface UserService {

    /**
     * 根据 id 查找用户
     * @param id id
     * @return User
     */
    User selectById(Long id);

    /**
     * 查找所有用户
     * @return List<User>
     */
    List<User> selectAll();
}
