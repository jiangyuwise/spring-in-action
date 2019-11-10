package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author admin
 * @date 2019/11/8 17:09
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate1;

    private JdbcTemplate jdbcTemplate2;

    @Autowired
    @Qualifier("jdbcTemplate1")
    public void setJdbcTemplate1(JdbcTemplate jdbcTemplate1) {
        this.jdbcTemplate1 = jdbcTemplate1;
    }

    @Autowired
    @Qualifier("jdbcTemplate2")
    public void setJdbcTemplate2(JdbcTemplate jdbcTemplate2) {
        this.jdbcTemplate2 = jdbcTemplate2;
    }

    @Override
    public User save(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate1.update(con -> {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getBirthday());
            return stmt;
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());

        String sql2 = "insert into `user` (`user_id`, `user_name`, `user_birthday`) values (?, ?, ?)";
        jdbcTemplate2.update(sql2, user.getId(), user.getName(), user.getBirthday());
        return user;
    }

}
