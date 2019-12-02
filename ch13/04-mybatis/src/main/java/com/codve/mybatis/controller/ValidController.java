package com.codve.mybatis.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author admin
 * @date 2019/12/2 10:01
 */
@RestController
@RequestMapping("/valid")
@Validated
public class ValidController {

    @GetMapping("/int/{num:.+}")
    public int intValid(@PathVariable @Valid @Min(value = 1) int num) {
        return num;
    }

}
