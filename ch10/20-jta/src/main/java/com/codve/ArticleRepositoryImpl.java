package com.codve;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * @author admin
 * @date 2019/11/8 16:43
 */
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

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
    public Article save(Article article) {
        String sql = "insert into `article` (`user_id`, `article_title`, `create_time`) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate1.update(con -> {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, article.getUserId());
            stmt.setString(2, article.getTitle());
            stmt.setLong(3, article.getCreateTime());
            return stmt;
        }, keyHolder);
        article.setId(keyHolder.getKey().longValue());

        String sql2 = "insert into `article` (`article_id`, `user_id`, `article_title`, `create_time`) values (?, ?, ?, ?)";
        jdbcTemplate2.update(sql2, article.getId(), article.getUserId(), article.getTitle(), article.getCreateTime());

        return article;
    }
}
