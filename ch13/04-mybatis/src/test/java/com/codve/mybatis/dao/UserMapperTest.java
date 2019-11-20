package com.codve.mybatis.dao;

import com.codve.mybatis.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/19 13:07
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserMapperTest {

    private static ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    private User user;

    @BeforeAll
    static void setUpAll() {
        populator.addScript(new ClassPathResource("data/user.sql"));
    }

    @BeforeEach
    void setUp() {
        populator.execute(dataSource);
        user = new User();
        user.setName("James");
        user.setBirthday(System.currentTimeMillis());
    }

    @Test
    void saveNonParamTest() {
        user = new User();
        assertEquals(1, userMapper.save(user));
    }

    @Test
    void saveOneParamTest() {
        user = new User();
        user.setId(1L);
        assertEquals(1, userMapper.save(user));

        user = new User();
        user.setName("喜洋洋");
        assertEquals(1, userMapper.save(user));

        user = new User();
        user.setBirthday(1L);
        assertEquals(1, userMapper.save(user));
    }

    @Test
    void saveTest() {
        assertEquals(1, userMapper.save(user));

        user.setId(1L);
        assertEquals(1, userMapper.save(user));
    }

    @Test
    void deleteByIdTest() {
        assertEquals(1, userMapper.deleteById(1L));
        assertEquals(0, userMapper.deleteById(1L));
    }

    @Test
    void updateNoParam() {
        user = new User();
        assertEquals(0, userMapper.update(user));
    }

    @Test
    void updateOneParam() {
        user = new User();
        user.setId(1L);
        assertEquals(1, userMapper.update(user));

        user = new User();
        user.setName("小苹果");
        assertEquals(0, userMapper.update(user));

        user = new User();
        user.setBirthday(1L);
        assertEquals(0, userMapper.update(user));
    }

    @Test
    void updateTwoParam() {
        user = new User();
        user.setId(1L);
        user.setName("你好");
        assertEquals(1, userMapper.update(user));

        user = new User();
        user.setId(1L);
        user.setBirthday(0L);
        assertEquals(1, userMapper.update(user));

        user = new User();
        user.setName("你好");
        user.setBirthday(0L);
        assertEquals(0, userMapper.update(user));
    }

    @Test
    void updateTest() {
        user = new User();
        user.setId(10L);
        user.setName("哈哈");
        user.setBirthday(0L);
        assertEquals(0, userMapper.update(user));

        user.setId(1L);
        assertEquals(1, userMapper.update(user));
    }

    @Test
    void findByIdTest() {
        user = userMapper.findById(10L);
        assertNull(user);

        user = userMapper.findById(1L);
        assertNotNull(user);
        assertEquals(1L, user.getId());
    }

    @Test
    void findNoParam() {
        PageHelper.startPage(1, 2);
        List<User> userList = userMapper.find(null, null, null, null, null);
        assertTrue(userList.size() > 0);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        assertTrue(pageInfo.getSize() > 0);
    }

    @Test
    void findUserParam() {
        user = new User();
        user.setId(0L);
        user.setBirthday(0L);
        PageHelper.startPage(1, 2);
        List<User> userList = userMapper.find(user, null, null, null, null);
        assertTrue(userList.size() > 0);

        PageHelper.startPage(1, 2);
        user.setName("j");
        userList = userMapper.find(user, null, null, null, null);
        assertTrue(userList.size() > 0);
    }

    @Test
    void findTwoParam() {
        user = new User();
        user.setName("j");
        PageHelper.startPage(1, 2);
        List<User> userList = userMapper.find(user, 0L, null, null, 1);
        assertTrue(userList.size() > 0);

        userList = userMapper.find(user, null, -1L, null, null);
        assertEquals(0, userList.size());

        PageHelper.startPage(1, 2);
        userList = userMapper.find(user, null, null, Arrays.asList(1L, 2L), 2);
        assertTrue(userList.size() > 0);

        PageHelper.startPage(1, 2);
        userList = userMapper.find(null, 0L, System.currentTimeMillis(), null, 3);
        assertTrue(userList.size() > 0);

        PageHelper.startPage(1, 2);
        userList = userMapper.find(null, null, System.currentTimeMillis(), Arrays.asList(1L, 2L), 4);
        assertTrue(userList.size() > 0);
    }

    @Test
    void findThreeParam() {
        user = new User();
        user.setName("j");
        PageHelper.startPage(1, 2);
        List<User> userList = userMapper.find(user, 0L, System.currentTimeMillis(), null, 2);
        assertTrue(userList.size() > 0);
    }

    @Test
    void findTest() {
        user.setName("j");
        List<User> userList = userMapper.find(user, 0L, System.currentTimeMillis(),
                Arrays.asList(1L, 2L, 3L, 4L), 3);
        assertTrue(userList.size() > 0);
    }

}