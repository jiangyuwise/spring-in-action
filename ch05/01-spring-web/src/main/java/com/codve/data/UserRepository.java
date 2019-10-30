package com.codve.data;

import com.codve.User;

/**
 * @author admin
 * @date 2019/10/30 12:20
 */
public interface UserRepository {
    User save(User user);

    User findByUsername(String username);
}
