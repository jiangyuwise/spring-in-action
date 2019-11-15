package com.codve;

import com.codve.mapper.UserMapper;
import com.codve.model.User;
import org.apache.ibatis.io.Resources;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/13 19:08
 */
class UserMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private UserMapper userMapper;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static DataUtil dataUtil;

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
        userMapper = sqlSession.getMapper(UserMapper.class);
        dataUtil.setSqlSession(sqlSession);
        dataUtil.init();
    }

    @AfterEach
    public void teardown() {
        sqlSession.close();
    }

    @Test
    public void cacheTest() {
        User user1 = userMapper.findById(1L);
        User user2 = userMapper.findById(1L);
        user1.setName("Alice");
        System.out.println(user1.toString());
        System.out.println(user2.toString());
    }

    @Test
    public void cacheTest2() {
        User user1 = userMapper.findById(1L);
        user1.setName("Alice");
        userMapper.update(user1);

        User user2 = userMapper.findById(1L);
        System.out.println(user1.toString());
        System.out.println(user2.toString());
    }

    @Test
    public void noCacheTest() {
        User user1 = userMapper.findNoCache(1L);
        User user2 = userMapper.findNoCache(1L);
        user1.setName("Alice");
        System.out.println(user1.toString());
        System.out.println(user2.toString());
    }

    @Test
    public void HitTest() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        User user1 = userMapper1.findById(1L);
        User user2 = userMapper1.findById(1L);
        user1.setName("Alice");
        System.out.println(user1.toString());
        System.out.println(user2.toString());
        sqlSession1.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user3 = userMapper2.findById(1L);
        User user4 = userMapper2.findById(1L);
        System.out.println(user3);
        System.out.println(user4);
    }

}