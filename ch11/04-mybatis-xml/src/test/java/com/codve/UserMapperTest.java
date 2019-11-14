package com.codve;

import com.codve.mapper.UserMapper;
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
import java.util.Date;
import java.util.List;

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
    public void insertTest() {
        User user = new User("Robot", new Date().getTime());
        userMapper.insert(user);
    }

    @Test
    public void findByIdTest() {
        User user = userMapper.findById(1L);
        assertNotNull(user);
    }

    @Test
    public void deleteTest() {
        int result = userMapper.delete(1L);
        assertEquals(1, result);
    }

    @Test
    public void findByParamsTest() throws ParseException {
        Date date = dateFormat.parse("1995-1-1");
        String name = "%" + "j" + "%";
        List<User> userList = userMapper.findByParams(name, date.getTime());
        assertTrue(userList.size() > 0);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    /**
     * 动态 SQL, 使用 if 拼接
     */
    @Test
    public void selectIf1() {
        List<User> userList = userMapper.selectIf(null, null);
        assertTrue(userList.size() > 0);
    }

    @Test
    public void selectIf2() {
        List<User> userList = userMapper.selectIf("j", null);
        assertTrue(userList.size() > 0);
//        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void selectIf3() throws ParseException {
        Date date = dateFormat.parse("1996-1-1");
        List<User> userList = userMapper.selectIf("j", date.getTime());
        assertTrue(userList.size() > 0);
        userList.forEach(e -> System.out.println(e.toString()));
    }

}