package com.codve;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/4 13:04
 */
class UserTest {

    @Test
    public void toStringTest() {
        User user = new User();
        user.setName("James");
        user.setBirthday(System.currentTimeMillis());
        assertEquals("James", user.getName());
        System.out.println(user.toString());
    }
}