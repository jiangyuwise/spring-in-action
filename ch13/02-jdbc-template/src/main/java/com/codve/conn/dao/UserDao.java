package com.codve.conn.dao;

import com.codve.conn.model.Article;
import com.codve.conn.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/18 11:21
 */
@Slf4j
@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    private PlatformTransactionManager transactionManager;

    private TransactionTemplate transactionTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Autowired
    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @SuppressWarnings("Duplicates")
    List<User> findAll() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @SuppressWarnings("Duplicates")
    List<User> find(String name) {
        String sql = "select * from user where user_name like ?";
        return jdbcTemplate.query(sql, new UserMapper(), "%" + name + "%");
    }

    @SuppressWarnings("Duplicates")
    User findById(Long id) {
        String sql = "select * from user where user_id = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    @SuppressWarnings("Duplicates")
    int save(User user) {
        String sql = "insert into user (user_name, user_birthday) values(?, ?)";
        return jdbcTemplate.update(sql, user.getName(), user.getBirthday());
    }

    int saveWithKey(User user) {
        String sql = "insert into user (user_name, user_birthday) values(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update((con) -> {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getBirthday());
            return stmt;
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return result;
    }

    boolean batchSave(List<User> userList) {
        String sql = "insert into user (user_name, user_birthday) values(?, ?)";
        List<Object[]> args = new ArrayList<>();
        userList.forEach(e -> args.add(new Object[]{e.getName(), e.getBirthday()}));
        int[] results = jdbcTemplate.batchUpdate(sql, args);
        return Arrays.stream(results).allMatch(e -> e > 0);
    }

    @SuppressWarnings("Duplicates")
    void saveWithArticle(User user, Article article) {
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);

        try {
            String sql = "insert into user (user_name, user_birthday) values(?, ?)";
            jdbcTemplate.update(sql, user.getName(), user.getBirthday());

            sql = "insert into article(article_title, article_time) values(?, ?)";
            jdbcTemplate.update(sql, article.getTitle(), article.getCreateTime());
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
            log.error(e.getMessage());
        }
    }

    @SuppressWarnings("Duplicates")
    void saveWithArticle2(User user, Article article) {
        transactionTemplate.executeWithoutResult((status) -> {
            try {
                String sql = "insert into user (user_name, user_birthday) values(?, ?)";
                jdbcTemplate.update(sql, user.getName(), user.getBirthday());

                sql = "insert into article(article_title, article_time) values(?, ?)";
                jdbcTemplate.update(sql, article.getTitle(), article.getCreateTime());
            } catch (DataAccessException e) {
                status.setRollbackOnly();
            }
        });
    }

    private static final class UserMapper implements RowMapper<User> {
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
