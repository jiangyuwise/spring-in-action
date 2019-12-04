package com.codve;

/**
 * @author admin
 * @date 2019/11/6 18:27
 */
public interface UserService {

    User save(User user);

    void saveWithException(User user);

    void save1(User user, Article article);

    void save2(User user, Article article);

    void save3(User user, Article article);

    void save4(User user, Article article);

    void save5(User user, Article article);

    void save6(User user, Article article);

    void save7(User user) throws Exception;

    void save8(User user) throws Exception;

    void save9(User user);

    void save10(User user);


}
