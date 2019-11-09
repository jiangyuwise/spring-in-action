package com.codve;

/**
 * @author admin
 * @date 2019/11/6 18:27
 */
public interface UserService {

    User save(User user);

    void saveWithException(User user) throws RuntimeException;

    void save1(User user, Article article) throws RuntimeException;

    void save2(User user, Article article) throws RuntimeException;

    void save3(User user, Article article) throws RuntimeException;

    void save4(User user, Article article) throws RuntimeException;

    void save5(User user, Article article) throws RuntimeException;

    void save6(User user, Article article) throws RuntimeException;

    void save7(User user) throws Exception;

    void save8(User user) throws Exception;

    void save9(User user) throws RuntimeException;

    void save10(User user) throws RuntimeException;


}
