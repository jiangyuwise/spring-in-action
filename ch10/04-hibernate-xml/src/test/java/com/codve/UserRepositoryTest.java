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
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

/**
 * @author admin
 * @date 2019/11/4 18:18
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:app.xml")
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setName("Alice");
        user.setBirthday(System.currentTimeMillis());
    }

    @Test
    public void findAllTest() {
        List<User> userList = userRepository.findAll();
        assertNotNull(userList);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void countTest() {
//        long result = userRepository.count();
//        assertTrue(result > 4);
    }

    @Test
    public void saveTest() {
        user = userRepository.save(user);
        assertTrue(user.getId() > 0);
        System.out.println(user.toString());
    }

    @Test
    public void updateTest() {
        userRepository.save(user);
        user.setName("updated");
        userRepository.save(user);

        List<User> userList = userRepository.findAll();
        assertEquals(4, userList.size());
        userList.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void delTest() {
        userRepository.save(user);
        userRepository.del(user);
    }

}