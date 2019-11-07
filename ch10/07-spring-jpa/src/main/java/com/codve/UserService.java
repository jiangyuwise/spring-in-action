package com.codve;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/6 18:27
 */
public interface UserService {

    User save(User user);

    List<User> findAll();

    List<User> findByName(String name);

    User findById(Long id);
}
