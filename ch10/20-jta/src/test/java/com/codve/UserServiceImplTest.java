package com.codve;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.IllegalTransactionStateException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/9 10:06
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class UserServiceImplTest {

    private User user;

    @Autowired
    private UserService userService;

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
    }

    @Test
    public void saveWithExceptionTest() {
        assertThrows(RuntimeException.class, () -> {
            userService.saveWithException(user);
        });
    }

    @Test
    public void save2WithExceptionTest() {
        assertThrows(RuntimeException.class, () -> {
            userService.save2WithException(user);
        });
    }
}