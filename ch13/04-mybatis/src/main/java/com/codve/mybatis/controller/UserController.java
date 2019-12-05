package com.codve.mybatis.controller;

import com.codve.mybatis.convert.UserConvert;
import com.codve.mybatis.exception.EX;
import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.UserQuery;
import com.codve.mybatis.model.query.UserUpdateQuery;
import com.codve.mybatis.model.vo.UserVO;
import com.codve.mybatis.service.UserService;
import com.codve.mybatis.util.CommonResult;
import com.codve.mybatis.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static com.codve.mybatis.util.ExceptionUtil.exception;

/**
 * @author admin
 * @date 2019/11/21 12:19
 */
@RestController
@RequestMapping(value = "/user")
@Validated
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

    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable @Valid @Min(value = 1) Long id) {
        userService.deleteById(id);
        return CommonResult.success();
    }

    @PostMapping("/update")
    public CommonResult update(@RequestBody @Validated UserUpdateQuery updateQuery) {
        userService.update(UserConvert.convert(updateQuery));
        return CommonResult.success();
    }

    @GetMapping("/{id}")
    public CommonResult<UserVO> findById(@PathVariable("id") @Valid @Min(value = 1) Long id) {
        UserDO user = userService.findById(id);
        return CommonResult.success(UserConvert.convert(user));
    }

    @GetMapping("/find")
    public CommonResult<PageResult<UserVO>> find(@Validated UserQuery query) {
        List<UserDO> userDoList = userService.find(query);
        if (userDoList.size() == 0) {
            exception(EX.E_1104);
        }
        PageResult<UserVO> pageResult = UserConvert.convert(userDoList);
        return CommonResult.success(pageResult);
    }
}
