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
    }

    @Test
    
}