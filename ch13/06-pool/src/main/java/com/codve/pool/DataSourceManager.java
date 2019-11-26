package com.codve.pool;

import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * @author admin
 * @date 2019/11/26 12:15
 */

@Data
public class DataSourceManager {

    private static final Logger logger = Logger.getLogger("DataSourceManager");

    private String url;

    private String username;

    private String password;

    // 初始化时的连接数
    private int initialSize = 1;

    // 最大连接数
    private int maxSize = 3;

    // 当前已有连接数
    private int currentSize = 0;

    // 连接池, 连接从右边存入, 从左边取出
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public DataSourceManager() {

    }

    public DataSourceManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void initConnection() {
        for (int i = 0; i < initialSize; i++) {
            Connection conn = openConnection();
            pool.addLast(conn);
            currentSize++;
        }
    }

    public Connection getConnection() {
        if (pool.size() > 0) {
            return pool.removeFirst();
        }
        if (currentSize < maxSize) {
            currentSize++;
            return openConnection();
        }
        throw new RuntimeException("已达到最大连接数");
    }

    public void releaseConnection(Connection conn) {
        if (pool.size() < initialSize) {
            pool.addLast(conn);
            currentSize--;
        } else {
            closeConnection(conn);
        }
    }

    private Connection openConnection(){
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            logger.info("open connection. " + conn.toString());
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeConnection(Connection conn) {
        try {
            conn.close();
            logger.info("close connection. " + conn.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
