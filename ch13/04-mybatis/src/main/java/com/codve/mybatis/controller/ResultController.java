package com.codve.mybatis.controller;

import com.codve.mybatis.dao.UserMapper;
import com.codve.mybatis.exception.EX;
import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.util.CommonResult;
import com.codve.mybatis.util.PageResult;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/28 15:01
 */
@RestController
@RequestMapping("/result")
public class ResultController {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/success")
    public CommonResult success() {
        return CommonResult.success();
    }

    @GetMapping("/error")
    public CommonResult error() {
        return CommonResult.error(EX.E_302);
    }

    @GetMapping("/single")
    public CommonResult single() {
        return CommonResult.success(userMapper.findById(1L));
    }

    @GetMapping("/list")
    public CommonResult list() {
        PageHelper.startPage(1, 2);
        List<UserDO> userDoList = userMapper.find(null);
        PageResult<UserDO> pageResult = new PageResult<>(userDoList);
        return CommonResult.success(pageResult);
    }

    @GetMapping("/count")
    public CommonResult count() {
        int count = userMapper.count(null);
        return CommonResult.success(count);
    }
}
