package com.codve;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/10 18:35
 */
public interface UserService {

    User save(User user);

    List<User> findAll();

    List<User> findByName(String name);
}
