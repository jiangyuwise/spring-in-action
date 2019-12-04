package com.codve;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

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
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public long countByUsername(String username) {
        String sql = "select count(*) as count from `user` where `user_name` = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, username);
    }

    @Override
    public User save(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update((conn) -> {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getBirthday().getTime());
            return stmt;
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    public boolean save2(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        int result = jdbcTemplate.update(sql, user.getName(), user.getBirthday().getTime());
        return result > 0;
    }

    public boolean save3(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        int result = jdbcTemplate.update(sql, (ps) -> {
            ps.setString(1, user.getName());
            ps.setLong(2, user.getBirthday().getTime());
        });
        return result > 0;
    }

    public boolean save4(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        int result = jdbcTemplate.update((conn) -> {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getBirthday().getTime());
            return stmt;
        });
        return result > 0;
    }


    public boolean batchSave(List<User> userList) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values (?, ?)";
        List<Object[]> args = userList.stream()
                .map(e -> new Object[]{e.getName(), e.getBirthday().getTime()})
                .collect(Collectors.toList());
        int[] results = jdbcTemplate.batchUpdate(sql, args);
        return Arrays.stream(results).allMatch(e -> e > 0);
    }

    public boolean update(User user) {
        String sql = "update `user` set `user_name` = ? where `user_id` = ?";
        int result = jdbcTemplate.update(sql, user.getName(), user.getId());
        return result > 0;
    }

    public boolean update2(User user) {
        String sql = "update `user` set `user_name` = ? where `user_id` = ?";
        int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                con.setAutoCommit(true);
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, user.getName());
                stmt.setLong(2, user.getId());
                return stmt;
            }
        });
        return result > 0;
    }

    public boolean batchUpdate(List<User> userList) {
        String sql = "update `user` set `user_name` = ? where `user_id` = ?";
        List<Object[]> args = userList.stream()
                .map(e -> new Object[]{e.getName(), e.getId()})
                .collect(Collectors.toList());
        int[] results = jdbcTemplate.batchUpdate(sql, args);
        return Arrays.stream(results).allMatch(e -> e > 0);
    }

    @Override
    public User findOne(long userId) {
        String sql = "select * from `user` where `user_id` = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
    }

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

    public Map<String, Object> findOne3(long userId) {
        String sql = "select * from `user` where `user_id` = ?";
        return jdbcTemplate.queryForMap(sql, userId);
    }

    @Override
    public List<User> findByUsername(String username) {
        String sql = "select * from `user` where `user_name` like ?";
        username = "%" + username + "%";
        return jdbcTemplate.query(sql, new Object[]{username}, new UserRowMapper());
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from `user`";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    public List<Map<String, Object>> findAllUserArticle() {
        String sql = "select `u`.`user_id`, `u`.`user_name`, `u`.`user_birthday`, " +
                "`a`.`article_id`, `a`.`article_title`, `a`.`create_time` " +
                "from `user` as `u` left join `article` as `a` " +
                "on `u`.`user_id` = `a`.`user_id`";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> findAllUserArticleByUserId(long userId) {
        String sql = "select `u`.`user_id`, `u`.`user_name`, `u`.`user_birthday`, " +
                "`a`.`article_id`, `a`.`article_title`, `a`.`create_time` " +
                "from `user` as `u` left join `article` as `a` " +
                "on `u`.`user_id` = `a`.`user_id` " +
                "where `u`.`user_id` = ?";
        return jdbcTemplate.queryForList(sql, userId);
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
