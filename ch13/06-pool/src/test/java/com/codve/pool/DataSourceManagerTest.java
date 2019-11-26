package com.codve.pool;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/26 12:57
 */
class DataSourceManagerTest {

    private String url = "jdbc:mysql://localhost:3306/pro_spring?useSSL=true&useUnicode=true&characterEncoding=utf-8";
    private String username = "spring";
    private String password = "qZF08fDcidSRJ2CI";

    @Test
    void test1() throws SQLException, InterruptedException {
        DataSourceManager manager = new DataSourceManager(url, username, password);
        manager.initConnection();

        Connection conn = manager.getConnection();
        query(conn);
        manager.releaseConnection(conn);

        Connection conn2 = manager.getConnection();
        query(conn2);
//        manager.releaseConnection(conn2);

        Connection conn3 = manager.getConnection();
        query(conn3);
        manager.releaseConnection(conn3);

        Connection conn4 = manager.getConnection();
        query(conn4);
        manager.releaseConnection(conn4);
        Thread.sleep(15000);

//        manager.releaseConnection(conn);
    }

    private void query(Connection conn) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select * from user");
        ResultSet resultSet = stmt.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("user_name"));
        }
    }
}