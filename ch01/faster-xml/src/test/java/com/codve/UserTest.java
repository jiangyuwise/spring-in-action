package com.codve;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/12 12:18
 */
class UserTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    /**
     * 输出到文件
     * @throws IOException IOException
     */
    @Test
    public void test1() throws IOException {
        User user = new User("Jiangyu", 24);
        objectMapper.writeValue(new File("user.json"), user);
    }

    /**
     * 输出为字符串
     * @throws JsonProcessingException
     */
    @Test
    public void test2() throws JsonProcessingException {
        User user = new User("James", 24);
        String jsonStr = objectMapper.writeValueAsString(user);
        System.out.println(jsonStr);
    }

    /**
     * 将 json 字符串反序列化为对象
     */
    @Test
    public void test3() throws JsonProcessingException {
        String jsonStr = "{\"name\":\"Jiangyu\",\"age\":24}";
        User user = objectMapper.readValue(jsonStr, User.class);
        System.out.println(user.toString());
    }

    /**
     * 将集合序列化
     * @throws JsonProcessingException
     */
    @Test
    public void test4() throws JsonProcessingException {
        User user1 = new User("James", 24);
        User user2 = new User("Alice", 22);
        List<User> userList = Arrays.asList(user1, user2);

        String jsonStr = objectMapper.writeValueAsString(userList);
        System.out.println(jsonStr);
    }

    /**
     * 将 json 串反序列化为 List
     * @throws JsonProcessingException
     */
    @Test
    public void test5() throws JsonProcessingException {
        String jsonStr = "[{\"name\":\"James\",\"age\":24},{\"name\":\"Alice\",\"age\":22}]";
        List<User> userList = objectMapper.readValue(jsonStr,
                new TypeReference<List<User>>(){});
        assertTrue(userList.size() > 0);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    /**
     * 将 set JSON 化
     * @throws JsonProcessingException
     */
    @Test
    public void test6() throws JsonProcessingException {
        Set<User> userSet = new HashSet<>();
        userSet.add(new User("James", 20));
        userSet.add(new User("Helen", 30));
        String jsonStr = objectMapper.writeValueAsString(userSet);
        System.out.println(jsonStr);
    }

    /**
     * 将 json 字符串反序列化为 Set
     * @throws JsonProcessingException
     */
    @Test
    public void test7() throws JsonProcessingException {
        String jsonStr = "[{\"name\":\"Helen\",\"age\":30},{\"name\":\"James\",\"age\":20}]";
        Set<User> userSet = objectMapper.readValue(jsonStr,
                new TypeReference<Set<User>>() {});
        assertTrue(userSet.size() > 0);
        userSet.forEach(e -> System.out.println(e.toString()));
    }

    /**
     * 将 Map 序列化为 JSON 串
     * @throws JsonProcessingException
     */
    @Test
    public void test8() throws JsonProcessingException {
        Map<Integer, User> userSet = new HashMap<>();
        userSet.put(1, new User("James", 20));
        userSet.put(2, new User("Helen", 30));
        String jsonStr = objectMapper.writeValueAsString(userSet);
        System.out.println(jsonStr);
    }

    /**
     * 将 json 串反序列化为 Map
     */
    @Test
    public void test9() throws JsonProcessingException {
        String jsonStr = "{\"1\":{\"name\":\"James\",\"age\":20},\"2\":{\"name\":\"Helen\",\"age\":30}}";
        Map<Integer, User> userMap = objectMapper.readValue(jsonStr,
                new TypeReference<Map<Integer, User>>() {});
        assertTrue(userMap.size() > 0);
        userMap.forEach((k, v) -> System.out.println(k + ": " + v.toString()));
    }
    


}