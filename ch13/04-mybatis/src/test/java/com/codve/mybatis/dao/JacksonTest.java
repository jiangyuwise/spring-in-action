package com.codve.mybatis.dao;

import com.codve.mybatis.model.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/20 13:00
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JacksonTest {

    @Autowired
    private RedisConnectionFactory factory;

    @Test
    void stringTest(){
        StringRedisTemplate template = new StringRedisTemplate();
        StringRedisSerializer serializer = new StringRedisSerializer();

        template.setConnectionFactory(factory);
        template.setKeySerializer(serializer);
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(serializer);
        template.setHashValueSerializer(serializer);
        template.afterPropertiesSet();

        template.opsForValue().set("key", "'");
        String str = template.opsForValue().get("key");
        System.out.println(str);
    }

    @Test
    void stringStringTest() throws JsonProcessingException {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();

        template.setConnectionFactory(factory);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(stringSerializer);
        template.afterPropertiesSet();

        User user = new User();
        user.setId(1L);
        user.setName("James");
        user.setBirthday(System.currentTimeMillis());

        ObjectMapper om = new ObjectMapper();
        String jsonStr = om.writeValueAsString(user);

        template.opsForValue().set("key", jsonStr);

        String savedStr = template.opsForValue().get("key");
        User savedUser = om.readValue(savedStr, User.class);
        System.out.println(savedUser);
        template.delete("key");
    }

    @Test
    @SuppressWarnings("unchecked")
    void jsonTest() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> objectSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        PolymorphicTypeValidator validator = BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Object.class)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.activateDefaultTyping(validator, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        objectSerializer.setObjectMapper(objectMapper);

        template.setConnectionFactory(factory);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(objectSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(objectSerializer);
        template.afterPropertiesSet();

        User user = new User();
        user.setId(1L);
        user.setName("james");
        user.setBirthday(10L);

        User user1 = new User();
        BeanUtils.copyProperties(user, user1);
        template.opsForList().rightPushAll("key", user, user1);

//        User saved = (User) template.opsForList().leftPop("key");

        List<User> userList = (List) template.opsForList().range("key", 0, -1);
        template.delete("key");
    }

    @Test
    public void jsonEasyTest() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer objectSerializer = new GenericJackson2JsonRedisSerializer();

        template.setConnectionFactory(factory);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(objectSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(objectSerializer);
        template.afterPropertiesSet();

        User user = new User();
        user.setId(1L);
        user.setName("James");
        user.setBirthday(10L);

        template.opsForValue().set("key", user);
        User saved = (User) template.opsForValue().get("key");
        template.delete("key");

    }

    @Test
    public void jsonStrTest() throws JsonProcessingException {
        String json = "{\"@class\":\"com.codve.mybatis.model.User\",\"id\":1,\"name\":\"james\",\"birthday\":10}";
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(json, User.class);
        System.out.println(user.toString());
    }

}
