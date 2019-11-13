package com.codve.mapper;

import com.codve.User;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/13 19:04
 */
public interface UserMapper {

    int insert(User user);

    User findById(Long userId);

    List<User> findAll();

    int update(User user);

    int delete(Long userId);
}
