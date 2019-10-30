package com.codve;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author admin
 * @date 2019/10/30 20:14
 */
public class JdbcUserRepository implements UserRepository{

    private DataSource dataSource;

    @Override
    public long count() {
        return 0;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User findOne(long userId) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
