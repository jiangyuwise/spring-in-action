package com.codve.conn.dao;

import com.codve.conn.model.Article;
import com.codve.conn.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/18 11:21
 */
@Slf4j
@Repository
public class UserDao {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SuppressWarnings("Duplicates")
    List<User> findAll() throws SQLException {
        String sql = "select * from user";
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();
        List<User> userList = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setBirthday(resultSet.getLong("user_birthday"));
            userList.add(user);
        }
        return userList;
    }

    @SuppressWarnings("Duplicates")
    List<User> find(String name) throws SQLException {
        String sql = "select * from user where user_name like ?";
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + name + "%");
        ResultSet resultSet = stmt.executeQuery();
        List<User> userList = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setBirthday(resultSet.getLong("user_birthday"));
            userList.add(user);
        }
        return userList;
    }

    @SuppressWarnings("Duplicates")
    int save(User user) throws SQLException {
        String sql = "insert into user (user_name, user_birthday) values(?, ?)";
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getName());
        stmt.setLong(2, user.getBirthday());
        return stmt.executeUpdate();
    }

    int saveWithKey(User user) throws SQLException {
        String sql = "insert into user (user_name, user_birthday) values(?, ?)";
        Connection conn = dataSource.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, user.getName());
        stmt.setLong(2, user.getBirthday());
        int result = stmt.executeUpdate();
        ResultSet resultSet = stmt.getGeneratedKeys();
        if (resultSet.next()) {
            user.setId(resultSet.getLong(1));
        }
        return result;
    }

    @SuppressWarnings("Duplicates")
    void saveWithArticle(User user, Article article) throws SQLException {
        Connection conn = dataSource.getConnection();
        conn.setAutoCommit(false);
        try {
            String sql = "insert into user (user_name, user_birthday) values(?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getBirthday());
            stmt.executeUpdate();

            sql = "insert into article (article_title, article_time) values (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, article.getTitle());
            stmt.setLong(2, article.getCreateTime());
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            log.error(e.getMessage());
            conn.rollback();
        }
    }


}
