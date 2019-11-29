package com.codve.mybatis.controller;

import com.codve.mybatis.convert.UserConvert;
import com.codve.mybatis.model.vo.UserVO;
import com.codve.mybatis.service.UserService;
import com.codve.mybatis.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CommonResult save(@RequestBody @Validated UserVO user) {
        userService.save(UserConvert.convert(user));
        return CommonResult.success();
    }
//
//    @GetMapping("/delete/{id}")
//    public R delete(@PathVariable Long id) {
//        userService.deleteById(id);
//        return R.success();
//    }
//
//    @PostMapping("/update")
//    public R update(@RequestBody User user) {
//        userService.update(user);
//        return R.success();
//    }
//
//    @GetMapping("/{id}")
//    @Valid
//    public R findById(@PathVariable("id") Long id) {
//        User user = userService.findById(id);
//        if (user == null) {
//            return R.error(EX.USER_NOT_FOUND);
//        }
//        return R.success(Collections.singletonList(user));
//    }
//
//    @GetMapping("/list")
//    public R list(@RequestParam(value = "pageNum", defaultValue = "1")  int pageNum,
//                  @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
//        List<User> userList = userService.find(null, null, null, null, 1,
//                pageNum, pageSize);
//        if (userList.size() == 0) {
//            return R.error(EX.FIND_FAILED);
//        }
//        return R.success(userList);
//    }
//
//    @GetMapping("/find")
//    public User find(@RequestBody User user) {
//        return user;
//    }
}
