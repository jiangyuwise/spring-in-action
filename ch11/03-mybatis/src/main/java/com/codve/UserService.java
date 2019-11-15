package com.codve;

import com.codve.model.User;

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
}
