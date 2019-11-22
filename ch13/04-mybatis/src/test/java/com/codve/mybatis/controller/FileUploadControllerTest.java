package com.codve.mybatis.controller;

import com.codve.mybatis.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;

/**
 * @author admin
 * @date 2019/11/22 11:24
 */
class FileUploadControllerTest {

    @Test
    public void uploadTest() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void lambdaTest() {
        User user = new User();
        user.setId(1L);
        user.setName("james");
        user.setBirthday(System.currentTimeMillis());

        User user2 = new User();
        BeanUtils.copyProperties(user, user2);
//        System.out.println(user.toString());
    }

}