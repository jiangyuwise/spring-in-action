package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

/**
 * @author admin
 * @date 2019/11/6 18:47
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Aka");
        user.setBirthday(System.currentTimeMillis());
    }

    @Test
    public void saveTest() {
        userService.save(user);
        assertTrue(user.getId() > 0);
        System.out.println(user.toString());

        user.setName("Angelica");
        userService.save(user);

        User tmp = userService.findById(user.getId());
        assertNotNull(tmp);
        System.out.println(tmp);
    }

    @Test
    public void findAllTest() {
        List<User> userList = userService.findAll();
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void findByNameTest() {
        List<User> userList = userService.findByName("Jimmy");
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void findByIdTest() {
        User user = userService.findById(2L);
        assertNotNull(user);
        System.out.println(user.toString());
    }

    @Test
    public void findByBirthdayTest() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = format.parse("1995-1-1");
        Date end = format.parse("1999-12-31");
        List<User> userList = userService.findByBirthday(start.getTime(), end.getTime());
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }
}