package com.codve.mybatis.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codve.mybatis.model.User;
import com.codve.mybatis.util.DataInitializer;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/18 19:05
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataInitializer initializer;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("姜瑜");
        user.setBirthday(System.currentTimeMillis());

        initializer.init();
    }

    @Test
    public void insertTest() {
        int result = userMapper.insert(user);
        assertEquals(1, result);

        assertTrue(user.getId() > 0);
    }

    @Test
    public void deleteTest() {
        int result = userMapper.deleteById(1L);
        assertEquals(1, result);
    }

    @Test
    public void deleteByMapTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("user_name", "Alice");
        int result = userMapper.deleteByMap(params);
        assertEquals(1, result);
    }

    @Test
    @Ignore
    public void deleteByWrapperTest() {

    }

    @Test
    public void listTest() {
        List<User> userList = userMapper.selectList(null);
        assertTrue(userList.size() > 0);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void initTest() {
        assertNotNull(initializer);
    }

    @Test
    public void deleteByIdsTest() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        int result = userMapper.deleteBatchIds(ids);
        assertTrue(result > 0);
        System.out.println(result);
    }

    @Test
    public void updateByIdTest() {
        User tmp = userMapper.selectById(1L);
        tmp.setName("奇怪的喵星人");
        assertTrue(userMapper.updateById(tmp) > 0);
    }

    @Test
    public void selectByMapTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("user_name", "James");
        List<User> userList = userMapper.selectByMap(params);
        assertTrue(userList.size() > 0);
    }

    @Test
    public void pageTest() {
        Page<User> userPage = new Page<>(1, 1);
        QueryWrapper<User> query = new QueryWrapper<>();
        query.select("user_name").like("user_name", "j");
        IPage<User> userIPage = userMapper.selectPage(userPage, query);
        System.out.println(userIPage.getPages());
    }

    @Test
    public void queryWrapperGtTest() {
        Page<User> userPage = new Page<>(1, 1);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name")
                .gt("user_birthday", -1)
                .lt("user_birthday", System.currentTimeMillis());
        IPage<User> userIPage = userMapper.selectPage(userPage, queryWrapper);
        List<User> userList = userIPage.getRecords();
        assertTrue(userList.size() > 0);
    }

    @Test
    public void lambdaQueryWrapperTest() {
        Page<User> userPage = new Page<>(3, 2);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getName)
                .gt(User::getBirthday, -1)
                .lt(User::getBirthday, System.currentTimeMillis());
        IPage<User> userIPage = userMapper.selectPage(userPage, queryWrapper);
        System.out.println(userIPage.getPages());
        List<User> userList = userIPage.getRecords();
        assertTrue(userList.size() == 0);
    }

    @Test
    public void selectComplexTest() {
        Page<User> page = new Page<>(1, 2);
        user.setName("James");
        List<User> userList = userMapper.selectComplex(user,
                0L,
                System.currentTimeMillis(),
                Arrays.asList(1L, 2L, 3L),
                1);
        assertTrue(userList.size() > 0);
    }
}