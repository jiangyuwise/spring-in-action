package com.codve.mybatis.controller;

import com.codve.mybatis.model.query.UserLoginQuery;
import com.codve.mybatis.service.AuthService;
import com.codve.mybatis.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2019/12/11 09:07
 */
@RestController
public class AuthController {

    private AuthService authService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public CommonResult<String> auth(@Validated UserLoginQuery query) {
        String jwt = authService.passwordAuth(query.getName(), query.getPassword());
        return CommonResult.success(jwt);
    }
}
