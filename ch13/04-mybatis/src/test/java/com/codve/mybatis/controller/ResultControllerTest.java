package com.codve.mybatis.controller;

import com.codve.mybatis.util.CommonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author admin
 * @date 2019/11/30 21:31
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ResultControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private Logger log = LoggerFactory.getLogger(ResultControllerTest.class);

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testSuccess() throws Exception {
        mockMvc.perform(get("/result/success"))
                .andExpect(status().isOk());
    }

    @Test
    void testSuccessFailed() throws Exception {
        String result = mockMvc.perform(post("/result/success"))
                .andExpect(jsonPath("$.code").value("1001"))
                .andExpect(jsonPath("$.msg").value("请求方式错误"))
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        CommonResult result1 = mapper.readValue(result, CommonResult.class);
        assertEquals(1001, result1.getCode());
        log.warn(result1.toString());
    }

}