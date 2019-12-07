package com.codve.mybatis.service.impl;

import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.UserQuery;
import com.codve.mybatis.model.vo.UserVO;
import com.codve.mybatis.service.UserService;
import com.codve.mybatis.util.BeanUtil;
import com.codve.mybatis.util.PageResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/19 15:50
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

    private Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    private DataSource dataSource;

    private static ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

    @Autowired
    private UserService userService;

    @BeforeAll
    static void setUpAll() {
        populator.addScript(new ClassPathResource("data/user.sql"));
    }

    @BeforeEach
    void setUp() {
        populator.execute(dataSource);
    }

    @Test
    void save() {
        assertEquals(1, userService.save(new UserDO()));
    }


    @Test
    void saveEncodeTest() {
        UserDO userDO = new UserDO();
        userDO.setName("詹姆斯");
        userDO.setPassword("123456");
        userDO.setBirthday(System.currentTimeMillis());
        assertEquals(1, userService.save(userDO));
    }

    @Test
    void deleteById() {
        assertEquals(1, userService.deleteById(1L));
        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            userService.deleteById(100L);
        });
    }

    @Test
    void update() {
        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setName("hello");
        assertEquals(1, userService.update(userDO));

        userDO.setId(100L);
        assertThrows(RuntimeException.class, () -> userService.update(userDO));
    }
//
    @Test
    void findById() {

        assertThrows(RuntimeException.class, () -> {
            userService.findById(0L);
        });

        UserDO userDO = userService.findById(1L);
        assertNotNull(userDO);
        assertEquals(1L, userDO.getId());
    }

    @Test
    void find() {
        UserQuery userQuery = new UserQuery();
        userQuery.setName("j");

        List<UserDO> userDOList = userService.find(userQuery);
        assertTrue(userDOList.size() > 0);

        PageResult<UserDO> pageResult = new PageResult<>(userDOList);
        assertTrue(pageResult.getTotal() > 0);

        userQuery.setName("xxx");
        userDOList = userService.find(userQuery);
        assertEquals(0, userDOList.size());

    }

    @Test
    void count() {
        UserQuery userQuery = new UserQuery();
        userQuery.setName("j");
        assertTrue(userService.count(userQuery) > 0);

        userQuery.setStart(System.currentTimeMillis());
        assertEquals(0, userService.count(userQuery));
    }

    @Test
    void convert() {
        List<UserDO> userDOList = userService.find(new UserQuery());
        assertTrue(userDOList.size() > 0);
        List<UserVO> userVOList = BeanUtil.copyList(userDOList, UserVO.class);

        assertTrue(userVOList.size() > 0);

        userVOList.forEach(System.out::println);
    }

    @Test
    void convert2() {

        UserDO userDO = new UserDO();
        userDO.setId(1L);
        userDO.setName("james");
        userDO.setBirthday(System.currentTimeMillis());

        UserVO userVO = new UserVO();
        BeanCopier copier = BeanCopier.create(UserDO.class, UserVO.class, false);
        copier.copy(userDO, userVO, null);

        log.warn(userVO.toString());

        assertNotNull(userVO.getName());
    }

    @Test
    void unionSaveTest() {
        assertThrows(RuntimeException.class, () -> userService.unionSave());
    }

    @Test
    void transactionTest() {
        userService.save(new UserDO());
        userService.deleteById(1L);
    }

}