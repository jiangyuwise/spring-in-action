package com.codve.mybatis.service.impl;

import com.codve.mybatis.model.User;
import com.codve.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author admin
 * @date 2019/11/19 15:50
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

//    @Autowired
//    private DataInitializer initializer;
//
//    @BeforeEach
//    void setUp() {
//        initializer.init();
//    }

    @Test
    public void userServiceTest() {
        assertNotNull(userService);
    }

    @Test
    public void findByIdTest() {
        User tmp = userService.findById(1L);
        assertNotNull(tmp);

        tmp = userService.findById(2L);
        assertNotNull(tmp);
    }

    @Test
    public void findByIdCacheTest() {
        User tmp = userService.findById(4L);
        System.out.println(tmp.toString());
    }

    @Test
    public void deleteTest() {
        User tmp = userService.findById(1L);
        userService.deleteById(1L);
    }

    @Test
    public void findComplexTest() {
        List<User> userList = userService.findComplex(null, 0L, null, null, null);
        assertTrue(userList.size() > 0);

    }
}