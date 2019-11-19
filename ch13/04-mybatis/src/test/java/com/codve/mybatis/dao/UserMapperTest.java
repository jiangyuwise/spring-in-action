package com.codve.mybatis.dao;

import com.codve.mybatis.model.User;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author admin
 * @date 2019/11/19 13:07
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("James");
        user.setBirthday(System.currentTimeMillis());
    }

    @Test
    public void userMapperTest() {
        assertNotNull(userMapper);
    }

    @Test
    public void redisTest() {
        assertNotNull(redisTemplate);
    }

    @Test
    public void selectComplexTest() {
        PageHelper.startPage(2, 1);
        user.setName("j");
        List<User> userList = userMapper.selectComplex(user, null, null, null, null);
        assertTrue(userList.size() > 0);
    }

    @Test
    public void redisSaveTest() {
        String key = "hello";
        String value = "world";
        redisTemplate.opsForValue().set(key, value, 60, TimeUnit.SECONDS);
        String savedValue = redisTemplate.opsForValue().get(key);
        assertEquals(value, savedValue);
    }

    @Test
    public void redisObjectSaveTest() {
        User user = userMapper.findById(1L);
    }

}