package com.codve;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/4 16:37
 */
public interface UserRepository {

    User save(User user);

    List<User> findByName(String name);

    List<User> findAll();

    User findById(Long userId);
}
