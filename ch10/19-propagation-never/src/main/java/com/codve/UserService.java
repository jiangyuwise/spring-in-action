package com.codve;

/**
 * @author admin
 * @date 2019/11/6 18:27
 */
public interface UserService {

    User save(User user);

    void saveWithException(User user) throws RuntimeException;

    void save1(Article article) throws RuntimeException;

    void save2(Article article);



}
