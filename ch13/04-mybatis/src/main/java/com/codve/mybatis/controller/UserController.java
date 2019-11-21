package com.codve.mybatis.controller;

import com.codve.mybatis.exception.EX;
import com.codve.mybatis.model.User;
import com.codve.mybatis.service.UserService;
import com.codve.mybatis.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/21 12:19
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public R save(@RequestBody User user) {
        userService.save(user);
        return R.success();
    }

    @GetMapping("/delete/{id}")
    public R delete(@PathVariable Long id) {
        userService.deleteById(id);
        return R.success();
    }

    @PostMapping("/update")
    public R update(@RequestBody User user) {
        userService.update(user);
        return R.success();
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return R.error(EX.USER_NOT_FOUND);
        }
        return R.success(Collections.singletonList(user));
    }

    @GetMapping("/list")
    public R list(@RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
                  @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        List<User> userList = userService.find(null, null, null, null, 1,
                pageNum, pageSize);
        if (userList.size() == 0) {
            return R.error(EX.FIND_FAILED);
        }
        return R.success(userList);
    }

    @GetMapping("/find")
    public User find(@RequestBody User user) {
        return user;
    }
}
