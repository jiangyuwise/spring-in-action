package com.codve;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author admin
 * @date 2019/11/13 17:27
 */
public class SqlSessionTest {

    private static SqlSessionFactory sqlSessionFactory;

    private static String namespace = "com.codve.mapper.UserMapper";

    private SqlSession sqlSession;

    private DateFormat dateFormat;

    @BeforeEach
    void setUp() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = sqlSessionFactory.openSession();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Test
    public void insertTest() throws ParseException {
        Date date = dateFormat.parse("1995-08-07");
        User user = new User("James", date.getTime());
        String statement = namespace + ".insert";
        sqlSession.insert("com.code.mapper.UserMapper.insert", user);
    }

    @Test
    public void findByIdTest() {
        User user = sqlSession.selectOne(namespace + ".findById", 1L);
        assertNotNull(user);
        System.out.println(user.toString());
    }

    @Test
    public void findAllTest() {
        List<User> userList = sqlSession.selectList(namespace + ".findAll");
//        System.out.println(conn.toString());
        assertNotNull(userList);
//        System.out.println(userList.size());
    }
}
