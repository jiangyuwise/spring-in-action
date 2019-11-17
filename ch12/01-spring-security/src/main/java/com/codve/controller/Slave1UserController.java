package com.codve.controller;

import com.codve.model.User;
import com.codve.service.Slave1UserService;
import com.codve.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/17 18:21
 */
@RestController
@RequestMapping("/slave1/user")
public class Slave1UserController {

    private Slave1UserService userService;

    @Autowired
    public void setUserService(Slave1UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public List<User> userList() {
        return userService.listAll();
    }
}
