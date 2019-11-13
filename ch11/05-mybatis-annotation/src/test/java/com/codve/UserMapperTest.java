package com.codve;

import com.codve.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author admin
 * @date 2019/11/13 19:08
 */
class UserMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private UserMapper userMapper;

    @BeforeEach
    void setUp() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
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

}