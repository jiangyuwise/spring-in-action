package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/6 13:49
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
        user.setName("Aka");
        user.setBirthday(System.currentTimeMillis());
    }

    @Test
    public void saveTest() {
        userRepository.save(user);
        assertTrue(user.getId() > 0);
        System.out.println(user.toString());
    }

    @Test
    public void findAllTest() {
        List<User> userList = userRepository.findAll();
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void delTest() {
        userRepository.save(user);
        List<User> userList = userRepository.findAll();
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));

        userRepository.del(user);
        userList = userRepository.findAll();
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void findByIdTest() {
        User tmp = userRepository.findById(1L);
        assertNotNull(tmp);
        System.out.println(tmp.toString());
    }

    @Test
    public void countTest() {
        long count = userRepository.count();
        assertTrue(count > 0);
        System.out.println(count);
    }

    @Test
    public void findAllNativeTest() {
        List<User> userList = userRepository.findAllNative();
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }

}