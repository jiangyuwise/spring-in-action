package com.codve;

import java.util.List;

/**
 * @author admin
 * @date 2019/10/30 20:11
 */
public interface UserRepository {

    long count();

    User save(User user);

    User findOne(long userId);

    User findByUsername(String username);

    List<User> findAll();
}
