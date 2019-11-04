package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author admin
 * @date 2019/11/4 11:44
 */
@Repository
public class UserRepository {

    private NamedParameterJdbcTemplate template;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public boolean add(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values(:name, :birthday)";
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("birthday", user.getBirthday());
        int result = template.update(sql, params);
        return result > 0;
    }

    public boolean addReturnKey(User user) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values(:name, :birthday)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", user.getName())
                .addValue("birthday", user.getBirthday());
        int result = template.update(sql, params, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return result > 0;
    }

    public boolean batchAdd(List<User> userList) {
        String sql = "insert into `user` (`user_name`, `user_birthday`) values(:name, :birthday)";
        List<MapSqlParameterSource> paramList = new ArrayList<>();
        for (User user : userList) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("name", user.getName());
            params.addValue("birthday", user.getBirthday());
            paramList.add(params);
        }
        int[] result = template.batchUpdate(sql,
                paramList.toArray(new MapSqlParameterSource[paramList.size()]));
        return Arrays.stream(result).allMatch(e -> e > 0);
    }

    public boolean update(User user) {
        String sql = "update `user` set `user_name` = :name where `user_id` = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("name", user.getName());
        params.put("id", user.getId());
        int result = template.update(sql, params);
        return result > 0;
    }

    public boolean batchUpdate(List<User> userList) {
        String sql = "update `user` set `user_name` = :name where `user_id` = :id";
        List<MapSqlParameterSource> paramList = new ArrayList<>();
        for (User user : userList) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("name", user.getName());
            params.addValue("id", user.getId());
            paramList.add(params);
        }
        int[] result = template.batchUpdate(sql,
                paramList.toArray(new MapSqlParameterSource[paramList.size()]));
        return Arrays.stream(result).allMatch(e -> e > 0);
    }

    public Map<String, Object> queryForMap(long id) {
        String sql = "select * from `user` where `user_id` = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.queryForMap(sql, params);
    }

    public long countAll() {
        String sql = "select count(*) from `user`";
        Map<String, Object> params = new HashMap<>();
        return template.queryForObject(sql, params, Long.class);
    }

    public User queryForObject(long id) {
        String sql = "select * from `user` where `user_id` = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.queryForObject(sql, params, new UserRowMapper());
    }

    @SuppressWarnings("Duplicates")
    public User queryForObject2(long id) {
        String sql = "select * from `user` where `user_id` = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return template.queryForObject(sql, params, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setName(rs.getString("user_name"));
                user.setBirthday(rs.getLong("user_birthday"));
                return user;
            }
        });
    }

    public List<User> queryForAll() {
        String sql = "select * from `user`";
        Map<String, Object> params = new HashMap<>();
        return template.query(sql, params, new UserRowMapper());
    }

    public List<User> findByUsername(String username) {
        String sql = "select * from `user` where `user_name` like :name";
        Map<String, Object> params = new HashMap<>();
        params.put("name", "%" + username + "%");
        return template.query(sql, params, new UserRowMapper());
    }

    @SuppressWarnings("Duplicates")
    private static class UserRowMapper implements RowMapper<User> {
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
