package com.codve;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/6 18:27
 */
public interface UserService {

    List<User> findAll();

    List<User> findByName(String name);
}
