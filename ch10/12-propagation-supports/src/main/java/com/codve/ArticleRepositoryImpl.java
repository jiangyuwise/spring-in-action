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
 * @date 2019/11/8 16:43
 */
@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Article save(Article article) {
        String sql = "insert into `article` (`user_id`, `article_title`, `create_time`) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setLong(1, article.getUserId());
                stmt.setString(2, article.getTitle());
                stmt.setLong(3, article.getCreateTime());
                return stmt;
            }
        }, keyHolder);
        article.setId(keyHolder.getKey().longValue());
        return article;
    }

    @Override
    public List<Article> findByUserId(Long userId) {
        String sql = "select * from `article` where `user_id` = ?";
        return jdbcTemplate.query(sql, new ArticleRowMapper(), userId);
    }

    @Override
    public List<Article> findByTitle(String title) {
        String sql = "select * from `article` where `article_title` like ?";
        return jdbcTemplate.query(sql, new ArticleRowMapper(), "%" + title + "%");
    }

    private static final class ArticleRowMapper implements RowMapper<Article> {
        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article article = new Article();
            article.setId(rs.getLong("article_id"));
            article.setUserId(rs.getLong("user_id"));
            article.setTitle(rs.getString("article_title"));
            article.setCreateTime(rs.getLong("create_time"));
            return article;
        }
    }
}
