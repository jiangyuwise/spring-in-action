package com.codve;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    void setStr() {
        String key = "key";

        // 存储英文字符, redis 中显示不变
        redisTemplate.opsForValue().set(key, "hello");

        // 存储中文字符, redis 中存储的是转义过后的字符
        redisTemplate.opsForValue().set(key, "中国");

        // 但取出来时, 客户端会自动进行转义
        assertEquals("中国", redisTemplate.opsForValue().get(key));

        redisTemplate.delete(key);
    }

    @Test
    void setNum() {
        String key = "key";

        // 存储数字
        redisTemplate.opsForValue().set(key, "1");

        redisTemplate.opsForValue().increment(key, 1);
        assertEquals("2", redisTemplate.opsForValue().get(key));
        redisTemplate.delete(key);
        redisTemplate.delete(key);
    }

    @Test
    void setFloat() {
        String key = "key";
        redisTemplate.opsForValue().set(key, "1.5");
        redisTemplate.opsForValue().increment(key, 1.0);
        assertEquals("2.5", redisTemplate.opsForValue().get(key));
        redisTemplate.delete(key);
    }

    @Test
    void testKeyValue() {
        String key = "name";
        String value = "jimmy";
        redisTemplate.opsForValue().set(key, value);
        String result = redisTemplate.opsForValue().get(key);
        assertEquals(result, value);
        System.out.println(result);
        redisTemplate.delete(key);
    }

    @Test
    void testKeyValue2() {
        String key = "name";
        String value = "jimmy";
        redisTemplate.opsForValue().set(key, value, 10, TimeUnit.SECONDS);
        String result = redisTemplate.opsForValue().get(key);
        assertEquals(result, value);
        redisTemplate.delete(key);
    }

    @Test
    void testKeyValue3() {
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
    void testKeyValue4() {
        int num = 23;
        redisTemplate.opsForValue().set("num", String.valueOf(num));

        String result =  redisTemplate.opsForValue().get("num");
        int intResult = Integer.parseInt(result);
        assertEquals(23, intResult);

        redisTemplate.opsForValue().increment("num");
        result = redisTemplate.opsForValue().get("num");
        intResult = Integer.parseInt(result);
        assertEquals(24, intResult);

        redisTemplate.opsForValue().increment("num", 2);
        result = redisTemplate.opsForValue().get("num");
        intResult = Integer.parseInt(result);
        assertEquals(26, intResult);

        redisTemplate.delete("num");
    }

    @Test
    void testKeyValue5() throws JsonProcessingException {
        User user = new User("Alice", 24);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(user);

        redisTemplate.opsForValue().set("user", jsonStr);

        String savedStr = redisTemplate.opsForValue().get("user");
        assertNotNull(savedStr);
        System.out.println(savedStr);
        User savedUser = objectMapper.readValue(savedStr, User.class);
        System.out.println(savedUser.toString());
        redisTemplate.delete("user");
    }

    @Test
    void testList() {
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
    void testHash() {
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
    void testHash2() {
        redisTemplate.opsForHash().put("user", "name", "jimmy");
        redisTemplate.opsForHash().put("user", "age", "22");

        Map<Object, Object> result = redisTemplate.opsForHash().entries("user");
        assertTrue(result.size() > 0);
        result.forEach((k, v) -> System.out.println(k + ": " + v));

        redisTemplate.delete("user");
    }

    @Test
    void testSet() {
        redisTemplate.opsForSet().add("user", "jimmy");
        redisTemplate.opsForSet().add("user", "anna");

        Set<String> values = redisTemplate.opsForSet().members("user");
        assertTrue(values.size() > 0);
        values.forEach(System.out::println);

        redisTemplate.delete("user");
    }
}