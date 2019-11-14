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
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author admin
 * @date 2019/11/13 19:08
 */
class UserMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private UserMapper userMapper;

    @BeforeAll
    static void setUpAll() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    @BeforeEach
    void setUp(){
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }
    
    @AfterEach
    public void teardown() {
        sqlSession.close();
    }

    @Test
    public void insertTest() {
        User user = new User("Robot", new Date().getTime());
        userMapper.insert(user);
        assertTrue(user.getId() > 0);
        System.out.println(user.toString());
    }

    @Test
    public void insertAfterTest() {
        User user = new User("Robot", new Date().getTime());
        userMapper.insertAfter(user);
        assertTrue(user.getId() > 0);
        System.out.println(user.toString());
    }

    @Test
    public void findByIdTest() {
        User user = userMapper.findById(2L);
        assertNotNull(user);
        System.out.println(user.toString());
    }

    @Test
    public void findAllTest() {
        List<User> userList = userMapper.findAll();
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void updateTest() {
        User user = userMapper.findById(2L);
        user.setName("Robot");
        int result = userMapper.update(user);
        assertEquals(1, result);
    }

    @Test
    public void deleteTest() {
        int result = userMapper.delete(1L);
        assertEquals(1, result);
    }

}