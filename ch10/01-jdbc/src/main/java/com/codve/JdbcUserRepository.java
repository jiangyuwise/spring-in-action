package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @date 2019/10/30 20:14
 */
@Repository
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() {
        String sql = "select count(*) as count from `user`";
        int result = jdbcTemplate.queryForObject(sql, Integer.class);
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public User save(User user) {
        return null;
    }

    public boolean save2(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        int result = jdbcTemplate.update(sql, user.getName(), user.getBirthday().getTime());
        return result > 0;
    }

    public boolean save3(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, user.getName());
                ps.setLong(2, user.getBirthday().getTime());
            }
        });
        return result > 0;
    }

    public boolean save4(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, user.getName());
                stmt.setLong(2, user.getBirthday().getTime());
                return stmt;
            }
        });
        return result > 0;
    }

    public boolean save5(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getName());
                stmt.setLong(2, user.getBirthday().getTime());
                return stmt;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return result > 0;
    }

    public boolean batchSave(List<User> userList) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        List<Object[]> args = new ArrayList<>();
        userList.stream().forEach(e ->
                args.add(new Object[]{e.getName(), e.getBirthday().getTime()})
        );
        int[] results = jdbcTemplate.batchUpdate(sql, args);
        return Arrays.stream(results).allMatch(e -> e > 0);
    }

    @Override
    public User findOne(long userId) {
        String sql = "select * from `user` where `user_id` = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
    }

    // 使用 lambda 表达式
    @SuppressWarnings("Duplicates")
    public User findOne2(long userId) {
        String sql = "select * from `user` where `user_id` = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setName(rs.getString("user_name"));
            user.setBirthday(new Date(rs.getLong("user_birthday")));
            return user;
        }, userId);
    }

    @Override
    public List<User> findByUsername(String username) {
//        String sql = "select * from `user` where `user_name` like ?";
//        Object[] args = {"%" + username + "%"};
//        int[] argsType = {Types.VARCHAR};
//
//        return jdbcTemplate.queryForList(sql,);
        return null;
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from `user`";
        return jdbcTemplate.queryForList(sql, User.class);
    }

    private static final class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("user_id"));
            user.setName(rs.getString("user_name"));
            user.setBirthday(new Date(rs.getLong("user_birthday")));
            return user;
        }
    }
}
