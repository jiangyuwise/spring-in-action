package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/8 17:09
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User save(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getName());
                stmt.setLong(2, user.getBirthday());
                return stmt;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public List<User> findByName(String name) {
        String sql = "select * from `user` where `user_name` like ?";
        return jdbcTemplate.query(sql, new UserRowMapper(), "%" + name + "%");
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from `user`";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public User findById(Long userId) {
        String sql = "select * from `user` where `user_id` = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
    }

    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setName(rs.getString("user_name"));
            user.setBirthday(rs.getLong("user_birthday"));
            return user;
        }
    }

}
