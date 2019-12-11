package com.codve.mybatis.controller;

import com.codve.mybatis.util.CommonResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author admin
 * @date 2019/12/11 15:42
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
class AuthControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain).build();
    }

    @Test
    void testAuth2() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "Alice");
        params.add("password", "123456");

        String result = mockMvc.perform(post("/auth2")
                .params(params))
                .andReturn()
                .getResponse().getContentAsString(StandardCharsets.UTF_8);
        TypeReference<CommonResult<String>> type = new TypeReference<CommonResult<String>>() {};
        CommonResult<String> commonResult = mapper.readValue(result, type);
        log.warn(commonResult.toString());
    }

    @Test
    void testUpdate() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "9");
        params.add("name", "Alice");
        params.add("password", "123456");

        String token = "fbcc55c369f8074b242b600d2a86c8ae651ce59920bbb25ba55465cb97d1b116";
        String result = mockMvc.perform(post("/user/update")
                .params(params)
                .header("Authorization", "Bearer " + token))
                .andReturn()
                .getResponse().getContentAsString(StandardCharsets.UTF_8);
        TypeReference<CommonResult<String>> type = new TypeReference<CommonResult<String>>() {};
        CommonResult<String> commonResult = mapper.readValue(result, type);
        log.warn(commonResult.toString());
    }

    @Test
    void testDetele() throws Exception {

        String token = "5dd983d1362f9574d41bb8b3452574129d2eba25a8c9db349f8d6af74ffee170";
        String result = mockMvc.perform(get("/user/delete/10")
                .header("Authorization", "Bearer " + token)
                )
                .andReturn()
                .getResponse().getContentAsString(StandardCharsets.UTF_8);
        TypeReference<CommonResult<String>> type = new TypeReference<CommonResult<String>>() {};
        CommonResult<String> commonResult = mapper.readValue(result, type);
        log.warn(commonResult.toString());
    }
}