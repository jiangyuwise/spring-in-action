package com.codve;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author admin
 * @date 2019/10/31 18:14
 */
public class UserDao {
    private String url = "jdbc:mysql://localhost:3306/pro_spring?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    private String user = "spring";
    private String password = "qZF08fDcidSRJ2CI";

    public List<User> findAll() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement stmt = conn.prepareStatement("select * from `user`");
        ResultSet resultSet = stmt.executeQuery();
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setName(resultSet.getString("user_name"));
            user.setBirthday(new Date(resultSet.getLong("user_birthday")));
            userList.add(user);
        }
        conn.close();
        return userList;
    }

    public boolean update() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "update `user` set `user_name` = ? where `user_id` = ?";
        conn.setAutoCommit(true);
        conn.setNetworkTimeout(Executors.newSingleThreadExecutor(), 100);

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "Jackson");
        stmt.setLong(2, 16);
        int result = stmt.executeUpdate();
        return result > 0;
    }


    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        try {
            boolean result = userDao.update();
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
