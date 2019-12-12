package com.codve.mybatis.filter;

import com.codve.mybatis.annotation.Admin;
import com.codve.mybatis.annotation.User;
import com.codve.mybatis.exception.EX;
import com.codve.mybatis.handler.FilterExceptionHandler;
import com.codve.mybatis.model.auth.UserType;
import com.codve.mybatis.model.data.object.TokenDO;
import com.codve.mybatis.service.AuthService;
import com.codve.mybatis.util.CommonResult;
import com.codve.mybatis.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author admin
 * @date 2019/12/11 19:41
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Autowired
    private FilterExceptionHandler exceptionHandler;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (! (handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        User user = method.getAnnotation(User.class);
        Admin admin = method.getAnnotation(Admin.class);
        TokenDO tokenDO = TokenUtil.getToken(request);
        boolean result = true;
        if (user != null) {
            result = processAuth(user,tokenDO, response);
        }
        if (admin != null) {
            result = processAdmin(admin, tokenDO, response);
        }
        return result;
    }

    @SuppressWarnings("Duplicates")
    private boolean processAuth(User user, TokenDO tokenDO, HttpServletResponse response)
            throws IOException {
        try {
            authService.verify(tokenDO, UserType.USER);
        } catch (Exception e) {
            if (user.required()) {
                log.warn("{} - {}", e.getClass().getName(), e.getMessage());
                String msg = objectMapper.writeValueAsString(CommonResult.error(EX.E_1204.getCode(), e.getMessage()));
                exceptionHandler.handle(response, msg);
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("Duplicates")
    private boolean processAdmin(Admin admin, TokenDO tokenDO, HttpServletResponse response)
            throws IOException {
        try {
            authService.verify(tokenDO, UserType.ADMIN);
        } catch (Exception e) {
            if (admin.required()) {
                log.warn("{} - {}", e.getClass().getName(), e.getMessage());
                String msg = objectMapper.writeValueAsString(CommonResult.error(EX.E_1204.getCode(), e.getMessage()));
                exceptionHandler.handle(response, msg);
                return false;
            }
        }
        return true;
    }
}
