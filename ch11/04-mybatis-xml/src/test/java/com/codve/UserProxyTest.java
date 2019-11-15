package com.codve;

import com.codve.mapper.UserMapper;
import com.codve.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author admin
 * @date 2019/11/14 13:00
 */
class UserProxyTest {

    private static SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    @BeforeAll
    static void setUpAll() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    @BeforeEach
    void setUpEach() {
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void proxyTest() {
        UserProxy userProxy = new UserProxy<>(UserMapper.class, sqlSession);
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{UserMapper.class},
                userProxy
        );
        List<User> userList = userMapper.findAll();
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }

}