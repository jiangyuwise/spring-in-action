package com.codve;

import com.codve.mapper.UserMapper;
import com.codve.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public void pageTest() {
        User user = new User("J", null);
        List<User> userList = userMapper.find(user);
        assertTrue(userList.size() > 0);
    }

    @Test
    public void pageTest2() {
        PageHelper.startPage(2, 1);
        List<User> userList = userMapper.findAll();

        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println(pageInfo.toString());
        assertEquals(1, userList.size());
    }

    @Test
    public void pageTest3() {
        PageHelper.startPage(2, 1);
        List<User> userList = userMapper.find(new User());
        assertEquals(1, userList.size());
        System.out.println(((Page) userList).getTotal());
    }

    @Test
    public void pageTest4() {
        List<User> userList = userMapper.findAll();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println(pageInfo.toString());
    }



}