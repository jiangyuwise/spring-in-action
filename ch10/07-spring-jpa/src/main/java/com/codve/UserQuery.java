package com.codve;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/11 15:26
 */
public interface UserQuery {

    List<User> findByBirthday(Long start, Long end);
}
