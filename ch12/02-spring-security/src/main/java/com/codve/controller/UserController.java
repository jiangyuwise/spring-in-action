package com.codve.controller;

import com.codve.model.User;
import com.codve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/17 18:21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<String> userList() {
        List<User> userList = userService.listAll();
        List<String> strings = new ArrayList<>();
        userList.forEach(e -> strings.add(e.getName()));
        return strings;
    }
}
