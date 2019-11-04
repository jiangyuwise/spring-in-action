package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/4 13:27
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Anna");
        user.setBirthday(System.currentTimeMillis());
    }

    @Test
    public void userRepositoryTest() {
        assertNotNull(userRepository);
    }

    // 添加数据
    @Test
    public void addTest() {
        boolean result = userRepository.add(user);
        assertTrue(result);
        System.out.println(user.toString());
    }

    // 添加数据, 返回主键
    @Test
    public void addReturnKeyTest() {
        boolean result = userRepository.addReturnKey(user);
        assertTrue(result);
        assertTrue(user.getId() > 0);
        System.out.println(user.toString());
    }

    // 批量添加
    @Test
    public void batchAddTest() {
        User user2 = new User();
        user2.setName("Anna");
        user2.setBirthday(System.currentTimeMillis());

        List<User> userList = Arrays.asList(user, user2);
        boolean result = userRepository.batchAdd(userList);
        assertTrue(result);
    }

    // 更新
    @Test
    public void updateTest() {
        boolean result = userRepository.addReturnKey(user);
        assertTrue(result);

        user.setName("Scott");
        result = userRepository.update(user);
        assertTrue(result);
    }

    // 批量更新
    @Test
    public void batchUpdateTest() {
        User user2 = new User();
        user2.setName("Anna");
        user2.setBirthday(System.currentTimeMillis());

        boolean result1 = userRepository.addReturnKey(user);
        boolean result2 = userRepository.addReturnKey(user2);

        user.setName("Anna1");
        user2.setName("Anna2");

        List<User> userList = Arrays.asList(user, user2);
        boolean result = userRepository.batchUpdate(userList);
        assertTrue(result);
    }

    // 普通查询
    @Test
    public void queryForMapTest() {
        Map<String, Object> results = userRepository.queryForMap(1);
        assertNotNull(results);
        results.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    // 将查询结果转换成基本类
    @Test
    public void countAllTest() {
        long result = userRepository.countAll();
        assertTrue(result > 0);
        System.out.println(result);
    }

     //将查询结果转换为对象
    @Test
    public void queryForObjectTest() {
        User tmp = userRepository.queryForObject(1);
        assertNotNull(tmp);
        System.out.println(tmp.toString());
    }

    // 使用 lambda 表达式
    @Test
    public void queryForObject2Test() {
        User tmp = userRepository.queryForObject2(2);
        assertNotNull(tmp);
        System.out.println(tmp.toString());
    }

    // 查询所有行, 并将结果转换为对象
    @Test
    public void queryForAllTest() {
        List<User> users = userRepository.queryForAll();
        assertNotNull(users);
        users.forEach(e -> System.out.println(e.toString()));
    }

    // 按条件查询所有行
    @Test
    public void findByUsernameTest() {
        List<User> users = userRepository.findByUsername("J");
        assertNotNull(users);
        users.forEach(e -> System.out.println(e.toString()));
    }

    // 事务
    @Test
    public void transactTest() {
        User user2 = new User();
        user2.setName("Sara");
        user2.setBirthday(System.currentTimeMillis());
        boolean result = userRepository.transact(Arrays.asList(user, user2));
        assertFalse(result);
        assertEquals(3, userRepository.countAll());
    }
}