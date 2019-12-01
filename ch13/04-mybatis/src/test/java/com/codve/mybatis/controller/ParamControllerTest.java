package com.codve.mybatis.controller;

import com.codve.mybatis.model.vo.UserVO;
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

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author admin
 * @date 2019/12/1 10:00
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ParamControllerTest {

    private Logger log = LoggerFactory.getLogger(ParamControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void intTest() throws Exception {
        String result = mockMvc.perform(get("/int/1 "))
                .andExpect(content().string("1"))
                .andReturn()
                .getResponse()
                .getContentAsString(UTF_8);
        log.error(result);
    }

    @Test
    void intTest2() {

    }

    @Test
    void floatTest() throws Exception {
        String result = mockMvc.perform(get("/float/1.5"))
                .andExpect(content().string("1.5"))
                .andReturn()
                .getResponse()
                .getContentAsString(UTF_8);
        log.error(result);
    }

    @Test
    void stringTest() throws Exception {
        String result = mockMvc.perform(get("/str/hello"))
                .andExpect(content().string("hello"))
                .andReturn().getResponse().getContentAsString(UTF_8);
        log.error(result);
    }

    @Test
    void stringTest2() throws Exception {
        String result = mockMvc.perform(get("/str/ hello"))
                .andExpect(content().string("hello"))
                .andReturn().getResponse().getContentAsString(UTF_8);
        log.error(result);
    }

    @Test
    void trueTest() throws Exception {
        String result = mockMvc.perform(get("/true/true"))
                .andExpect(content().string("true"))
                .andReturn().getResponse().getContentAsString(UTF_8);
        log.error(result);

    }

    @Test
    void paramTest() throws Exception {
        String result = mockMvc.perform(get("/param/str")
                .param("name", "中国"))
                .andExpect(content().string("中国"))
                .andReturn().getResponse().getContentAsString(UTF_8);
        log.error(result);
    }

    @Test
    void paramTest2() throws Exception {
        String result = mockMvc.perform(get("/param/user")
                .param("name", "james")
                .param("birthday", Long.toString(System.currentTimeMillis())))
                .andReturn().getResponse().getContentAsString(UTF_8);
        UserVO userVO = mapper.readValue(result, UserVO.class);
        assertNotNull(userVO);
        log.error(userVO.toString());
    }
}