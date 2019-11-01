package com.codve;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/1 11:16
 */
public interface UserRepository {

    long count();

    User save(User user);

    User findOne(User user);

    List<User> findByUsername(String username);

    List<User> findAll();
}
