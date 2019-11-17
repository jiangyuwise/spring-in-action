package com.codve.service;


import com.codve.model.User;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/16 23:10
 */
public interface Slave1UserService {

    /**
     * 根据 id 查找用户
     * @param id id
     * @return user
     */
    User getById(Long id);

    /**
     * 查找所有用户
     * @return List<user>
     */
    List<User> listAll();
}
