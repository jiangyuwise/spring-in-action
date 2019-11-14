package com.codve;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @AfterEach
    void tearDown() {
        sqlSession.close();
    }

    @Test
    public void insertTest() throws ParseException {
        Date date = dateFormat.parse("1995-08-07");
        User user = new User("James", date.getTime());
        String statement = namespace + ".insert";
        int result = sqlSession.insert(statement, user);
        assertEquals(1, result);

        assertTrue(user.getId() > 0);
        System.out.println(user.toString());
    }

    @Test
    public void insertAfter() throws ParseException {
        Date date = dateFormat.parse("1995-08-07");
        User user = new User("James", date.getTime());
        String statement = namespace + ".insertAfter";
        int result = sqlSession.insert(statement, user);
        assertEquals(1, result);

        assertTrue(user.getId() > 0);
        System.out.println(user.toString());
    }

    @Test
    public void findByIdTest() {
        User user = sqlSession.selectOne(namespace + ".findById", 2L);
        assertNotNull(user);
        System.out.println(user.toString());
    }

    @Test
    public void updateTest() {
        User user = sqlSession.selectOne(namespace + ".findById", 2L);
        user.setBirthday(System.currentTimeMillis());
        sqlSession.update(namespace + ".update", user);
    }

    @Test
    public void deleteTest() {
        User user = sqlSession.selectOne(namespace + ".findById", 22L);
        sqlSession.delete(namespace + ".delete", user.getId());
    }

    @Test
    public void findAllTest() {
        List<User> userList = sqlSession.selectList(namespace + ".findAll");
        assertNotNull(userList);
        userList.forEach(e-> System.out.println(e.toString()));
    }

    @Test
    public void countTest() {
        long count = sqlSession.selectOne(namespace + ".count");
        assertTrue(count > 0);
        System.out.println(count);
    }
}
