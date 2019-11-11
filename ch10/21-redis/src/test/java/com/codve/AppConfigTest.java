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
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testKeyValue() {
        String key = "name";
        String value = "jimmy";
        redisTemplate.opsForValue().set(key, value);
        String result = redisTemplate.opsForValue().get(key);
        assertEquals(result, value);
        redisTemplate.delete(key);
    }

    @Test
    public void testKeyValue2() {
        String key = "name";
        String value = "jimmy";
        redisTemplate.opsForValue().set(key, value, 10, TimeUnit.SECONDS);
        String result = redisTemplate.opsForValue().get(key);
        assertEquals(result, value);
        redisTemplate.delete(key);
    }

    @Test
    public void testKeyValue3() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "jimmy");
        map.put("age", "22");
        redisTemplate.opsForValue().multiSet(map);

        List<String> keys = new ArrayList<>();
        keys.add("name");
        keys.add("age");

        List<String> values = redisTemplate.opsForValue().multiGet(keys);
        assertNotNull(values);
        values.forEach(System.out::println);

        redisTemplate.delete(keys);
    }

    @Test
    public void testList() {
        redisTemplate.opsForList().leftPush("users", "jimmy");
        redisTemplate.opsForList().leftPush("users", "Anna");

        Long count = redisTemplate.opsForList().size("users");

        assertEquals(2, count);

        List<String> values = redisTemplate.opsForList().range("users", 0, -1);
        assertNotNull(values);
        values.forEach(System.out::println);

        redisTemplate.delete("users");
    }

    @Test
    public void testHash() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "jimmy");
        map.put("age", "22");
        redisTemplate.opsForHash().putAll("user", map);

        Map<Object, Object> result = redisTemplate.opsForHash().entries("user");
        assertTrue(result.size() > 0);
        result.forEach((k, v) -> System.out.println(k + ": " + v));

        redisTemplate.delete("user");
    }

    @Test
    public void testHash2() {
        redisTemplate.opsForHash().put("user", "name", "jimmy");
        redisTemplate.opsForHash().put("user", "age", "22");

        Map<Object, Object> result = redisTemplate.opsForHash().entries("user");
        assertTrue(result.size() > 0);
        result.forEach((k, v) -> System.out.println(k + ": " + v));

        redisTemplate.delete("user");
    }

    @Test
    public void testSet() {
        redisTemplate.opsForSet().add("user", "jimmy");
        redisTemplate.opsForSet().add("user", "anna");

        Set<String> values = redisTemplate.opsForSet().members("user");
        assertTrue(values.size() > 0);
        values.forEach(System.out::println);

        redisTemplate.delete("user");
    }
}