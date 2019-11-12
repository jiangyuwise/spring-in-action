package com.codve;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/11 18:26
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class AppConfigTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void keyValueTest() {
        String key = "name";
        String value = "jimmy";
        redisTemplate.opsForValue().set(key, value);
        String result = (String) redisTemplate.opsForValue().get(key);
        assertEquals(result, value);
        System.out.println(result);
        redisTemplate.delete(key);
    }

    @Test
    public void keyValueTest2() {
        User user = new User();
        user.setName("Anna");
        user.setAge(24);
        redisTemplate.opsForValue().set("user", user);

        User value = (User) redisTemplate.opsForValue().get("user");
        assertNotNull(value);
        System.out.println(value.toString());
        redisTemplate.delete("user");
    }

    @Test
    public void keyValueTest3() {
        int num = 12;
        redisTemplate.opsForValue().set("num", num);

        int result = (int) redisTemplate.opsForValue().get("num");
        assertEquals(num, result);

        redisTemplate.opsForValue().increment("num");
        result = (int) redisTemplate.opsForValue().get("num");
        assertEquals(num + 1, result);
    }

    @Test
    public void listTest() {
        User user1 = new User("james", 24);
        User user2 = new User("kate", 22);
        redisTemplate.opsForList().rightPushAll("userList", user1, user2);

        List<Object> userList = redisTemplate.opsForList().range("userList", 0, -1);
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
        redisTemplate.delete("userList");
    }

}