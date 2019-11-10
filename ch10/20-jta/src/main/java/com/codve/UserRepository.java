package com.codve;

/**
 * @author admin
 * @date 2019/11/4 16:37
 */
public interface UserRepository {

    User save1(User user);

    void save2(User user);

    void save2WithException(User user) throws RuntimeException;
}
