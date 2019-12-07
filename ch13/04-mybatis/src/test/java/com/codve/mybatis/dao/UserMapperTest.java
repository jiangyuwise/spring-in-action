package com.codve.mybatis.dao;

import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.UserQuery;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/19 13:07
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserMapperTest {

    private static ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder encoder;

    @BeforeAll
    static void setUpAll() {
        populator.addScript(new ClassPathResource("data/user.sql"));
    }

    @BeforeEach
    void setUp() {
        populator.execute(dataSource);
    }

    @Test
    void saveNonParamTest() {
        UserDO userDO = new UserDO();
        assertEquals(1, userMapper.save(userDO));
    }

    @Test
    void saveOneParamTest() {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        assertEquals(1, userMapper.save(userDO));

        userDO = new UserDO();
        userDO.setName("喜洋洋");
        assertEquals(1, userMapper.save(userDO));

        userDO = new UserDO();
        userDO.setPassword("password");
        assertEquals(1, userMapper.save(userDO));

        userDO = new UserDO();
        userDO.setBirthday(1L);
        assertEquals(1, userMapper.save(userDO));
    }

    @Test
    void saveTwoParamTest() {
        UserDO userDO = new UserDO();
        userDO.setName("牛顿");
        userDO.setPassword("password");
        assertEquals(1, userMapper.save(userDO));

        userDO = new UserDO();
        userDO.setName("牛顿");
        userDO.setBirthday(System.currentTimeMillis());
        assertEquals(1, userMapper.save(userDO));

        userDO = new UserDO();
        userDO.setPassword("password");
        userDO.setBirthday(System.currentTimeMillis());
        assertEquals(1, userMapper.save(userDO));
    }

    @Test
    void saveTest() {
        UserDO userDO = new UserDO();
        userDO.setName("喜洋洋");
        userDO.setPassword("password");
        userDO.setBirthday(System.currentTimeMillis());
        assertEquals(1, userMapper.save(userDO));

        userDO.setId(1L);
        assertEquals(1, userMapper.save(userDO));
    }

    @Test
    void deleteByIdTest() {
        assertEquals(1, userMapper.deleteById(1L));
        assertEquals(0, userMapper.deleteById(1L));
    }

    @Test
    void updateNoParam() {
        UserDO userDO = new UserDO();
        assertEquals(0, userMapper.update(userDO));
    }

    @Test
    void updateOneParam() {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        assertEquals(1, userMapper.update(userDO));

        userDO = new UserDO();
        userDO.setName("小苹果");
        assertEquals(0, userMapper.update(userDO));

        userDO = new UserDO();
        userDO.setBirthday(1L);
        assertEquals(0, userMapper.update(userDO));
    }

    @Test
    void updateTwoParam() {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setName("你好");
        assertEquals(1, userMapper.update(userDO));

        userDO = new UserDO();
        userDO.setId(1L);
        userDO.setBirthday(0L);
        assertEquals(1, userMapper.update(userDO));

        userDO = new UserDO();
        userDO.setName("你好");
        userDO.setBirthday(0L);
        assertEquals(0, userMapper.update(userDO));
    }

    @Test
    void updateTest() {
        UserDO userDO = new UserDO();
        userDO.setId(10L);
        userDO.setName("哈哈");
        userDO.setBirthday(0L);
        assertEquals(0, userMapper.update(userDO));

        userDO.setId(1L);
        assertEquals(1, userMapper.update(userDO));
    }

    @Test
    void findByIdTest() {
        UserDO userDO = userMapper.findById(10L);
        assertNull(userDO);

        userDO = userMapper.findById(1L);
        assertNotNull(userDO);
        assertEquals(1L, userDO.getId());
    }

    @Test
    void findByNameTest() {
        UserDO userDO = userMapper.findByName("James");
        assertNotNull(userDO);
    }

    @Test
    void findNoParam() {
        PageHelper.startPage(1, 2);
        List<UserDO> userDOList = userMapper.find(new UserQuery());
        assertTrue(userDOList.size() > 0);
        PageInfo<UserDO> pageInfo = new PageInfo<>(userDOList);
        assertTrue(pageInfo.getSize() > 0);
    }

    @Test
    void findUserParam() {
        UserQuery query = new UserQuery();
        query.setStart(0L);
        PageHelper.startPage(1, 2);
        List<UserDO> userDOList = userMapper.find(query);
        assertTrue(userDOList.size() > 0);

        PageHelper.startPage(1, 2);
        query.setName("j");
        userDOList = userMapper.find(query);
        assertTrue(userDOList.size() > 0);
    }

    @Test
    void findTwoParam() {
        UserQuery userQuery = new UserQuery();
        userQuery.setName("j");
        PageHelper.startPage(1, 2);
        List<UserDO> userDOList = userMapper.find(userQuery);
        assertTrue(userDOList.size() > 0);

        userQuery = new UserQuery();
        userQuery.setName("j");
        userQuery.setEnd(-1L);
        userDOList = userMapper.find(userQuery);
        assertEquals(0, userDOList.size());

        userQuery = new UserQuery();
        userQuery.setName("j");
        userQuery.setUserIds(Arrays.asList(1L, 2L, 3L));
        userQuery.setOrderBy(2);
        PageHelper.startPage(1, 2);
        userDOList = userMapper.find(userQuery);
        assertTrue(userDOList.size() > 0);

        userQuery = new UserQuery();
        userQuery.setName("j");
        userQuery.setStart(0L);
        userQuery.setEnd(System.currentTimeMillis());
        userQuery.setOrderBy(3);
        PageHelper.startPage(1, 2);
        userDOList = userMapper.find(userQuery);
        assertTrue(userDOList.size() > 0);

        userQuery = new UserQuery();
        userQuery.setEnd(System.currentTimeMillis());
        userQuery.setUserIds(Arrays.asList(1L, 2L));
        userQuery.setOrderBy(4);
        PageHelper.startPage(1, 2);
        userDOList = userMapper.find(userQuery);
        assertTrue(userDOList.size() > 0);
    }

    @Test
    void findThreeParam() {
        UserQuery userQuery = new UserQuery();
        userQuery.setName("j");
        userQuery.setStart(0L);
        userQuery.setEnd(System.currentTimeMillis());
        userQuery.setOrderBy(2);
        PageHelper.startPage(1, 2);
        List<UserDO> userDOList = userMapper.find(userQuery);
        assertTrue(userDOList.size() > 0);
    }

    @Test
    void findTest() {
        UserQuery userQuery = new UserQuery();
        userQuery.setName("j");
        userQuery.setStart(0L);
        userQuery.setEnd(System.currentTimeMillis());
        userQuery.setUserIds(Arrays.asList(1L, 2L, 3L, 4L));
        userQuery.setOrderBy(3);
        List<UserDO> userDOList = userMapper.find(userQuery);
        assertTrue(userDOList.size() > 0);
    }

    @Test
    void countTest() {
        UserQuery userQuery = new UserQuery();
        userQuery.setName("j");
        userQuery.setStart(0L);
        userQuery.setEnd(System.currentTimeMillis());
        userQuery.setUserIds(Arrays.asList(1L, 2L, 3L, 4L));
        userQuery.setOrderBy(3);
        int count = userMapper.count(userQuery);
        assertTrue(count > 0);
    }

    @Test
    void pageTest() throws JsonProcessingException {
        PageHelper.startPage(1, 2);
        List<UserDO> userDOList = userMapper.find(null);
        System.out.println(userDOList.getClass().getName());

        ObjectMapper mapper = new ObjectMapper();
        Page<UserDO> userDOPage = (Page<UserDO>) userDOList;
        System.out.println(mapper.writeValueAsString(userDOPage));
    }

}