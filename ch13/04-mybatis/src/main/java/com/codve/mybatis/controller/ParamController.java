package com.codve.mybatis.controller;

import com.codve.mybatis.model.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2019/12/1 09:55
 */
@RestController
public class ParamController {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/int/{num}")
    public int intController(@PathVariable int num) {
        return num;
    }

    @GetMapping("/float/{num:.+}")
    public float floatController(@PathVariable float num) {
        return num;
    }

    @GetMapping("/str/{str}")
    public String strController(@PathVariable String str) {
        return str;
    }

    @GetMapping("/true/{par}")
    public String trueController(@PathVariable boolean par) {
        return Boolean.toString(par);
    }

    @GetMapping("/param/str")
    public String paramStr(@RequestParam("name") String name) {
        return name;
    }

    @GetMapping("/param/user")
    public String paramUser(UserVO userVO) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userVO);
    }
}
