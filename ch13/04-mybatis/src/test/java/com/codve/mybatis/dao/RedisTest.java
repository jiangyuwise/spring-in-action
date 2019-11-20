//package com.codve.mybatis.dao;
//
//import com.codve.mybatis.model.User;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.concurrent.TimeUnit;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
///**
// * @author admin
// * @date 2019/11/19 14:11
// */
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//public class RedisTest {
//
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    @Autowired
//    private RedisTemplate<String, Object> objectRedisTemplate;
//
//    private ObjectMapper objectMapper = new ObjectMapper();;
//
//    private User user;
//
//    @BeforeEach
//    void setUp() {
//        user = new User();
//        user.setName("James");
//        user.setBirthday(System.currentTimeMillis());
//        user.setId(1L);
//    }
//
//    @Test
//    public void redisTest() {
//        assertNotNull(redisTemplate);
//    }
//
//    @Test
//    public void objectRedisTest() {
//        assertNotNull(objectRedisTemplate);
//    }
//
//    @Test
//    public void StringSaveTest() throws JsonProcessingException {
//        String userStr= objectMapper.writeValueAsString(user);
//        redisTemplate.opsForValue().set(user.getName(), userStr, 60, TimeUnit.SECONDS);
//
//        String savedStr = redisTemplate.opsForValue().get(user.getName());
//        User savedUser = objectMapper.readValue(savedStr, User.class);
//        assertTrue(user.getBirthday().equals(savedUser.getBirthday()));
//    }
//
//    @Test
//    public void ObjectSaveTest() {
//        objectRedisTemplate.opsForValue().set(user.getName(), user);
//
//        User savedUser = (User) objectRedisTemplate.opsForValue().get(user.getName());
//        assertTrue(savedUser.getId() > 0);
//    }
//}
