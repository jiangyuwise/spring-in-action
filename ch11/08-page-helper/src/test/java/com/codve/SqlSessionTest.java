package com.codve;

import com.codve.model.User;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/13 17:27
 */
public class SqlSessionTest {

    private static SqlSessionFactory sqlSessionFactory;

    private static String namespace = "com.codve.mapper.UserMapper";

    private static DataUtil dataUtil;

    private SqlSession sqlSession;

    private DateFormat dateFormat;

    @BeforeAll
    static void setUpAll() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        dataUtil = new DataUtil();
        dataUtil.addScript("data.sql");
    }

    @BeforeEach
    void setUp() throws IOException {
        sqlSession = sqlSessionFactory.openSession();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dataUtil.setSqlSession(sqlSession);
        dataUtil.init();
    }

    @AfterEach
    void tearDown() {
        sqlSession.close();
    }
    @Test
    public void pageTest1() {
        RowBounds bounds = new RowBounds(2, 1);
        List<User> userList = sqlSession.selectList(namespace + ".findAll", null, bounds);
        assertTrue(userList.size() > 0);
        userList.forEach(e -> System.out.println(e.toString()));
    }
}
