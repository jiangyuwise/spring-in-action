package com.codve.mybatis.handler;

import com.codve.mybatis.exception.CommonException;
import com.codve.mybatis.exception.EX;
import com.codve.mybatis.exception.UserNotFoundException;
import com.codve.mybatis.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author admin
 * @date 2019/11/29 10:02
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 通用异常处理
     * @param e 异常
     * @return CommonResult
     */
    @ExceptionHandler(value = {
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotWritableException.class,
            MissingServletRequestPartException.class,
            BindException.class,
            NoHandlerFoundException.class,
            AsyncRequestTimeoutException.class} )
    public CommonResult defaultHandler(Exception e) {
        log.error(e.getClass().getName());
        log.error(e.getMessage());
        return CommonResult.error(1000, e.getMessage());
    }

    /**
     * 请求方式错误
     * @return commonResult
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult methodNotSupport() {
        return CommonResult.error(EX.E_1001);
    }

    /**
     * 缺少请求体
     * @return commonResult
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public CommonResult messageNotReadable() {
        return CommonResult.error(EX.E_1002);
    }

    /**
     * 参数验证失败
     * @param e 异常
     * @return commonResult
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult invalid(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        ObjectError objectError = result.getAllErrors().get(0);
        return CommonResult.error(1003, objectError.getDefaultMessage());
    }

    @ExceptionHandler(CommonException.class)
    public CommonResult commonException(CommonException e) {
        return CommonResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public CommonResult userNotFound(UserNotFoundException e) {
        return CommonResult.error(e.getCode(), e.getMessage());
    }

}
