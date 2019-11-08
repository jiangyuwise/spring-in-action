package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/8 17:14
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class UserRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("Kathy");
        user.setBirthday(System.currentTimeMillis());
    }

    @Test
    public void saveTest() {
        userRepository.save(user);
        assertTrue(user.getId() > 0);
        System.out.println(user.toString());
    }

    @Test
    public void findByNameTest() {
        List<User> userList = userRepository.findByName("J");
        assertTrue(userList.size() > 0);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void findAllTest() {
        List<User> userList = userRepository.findAll();
        assertTrue(userList.size() > 0);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void findById() {
        User tmp = userRepository.findById(1L);
        assertNotNull(tmp);
        System.out.println(tmp.toString());
    }
}