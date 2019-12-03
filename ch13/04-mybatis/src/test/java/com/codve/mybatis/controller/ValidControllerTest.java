package com.codve.mybatis.controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author admin
 * @date 2019/12/2 10:03
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ValidControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    private Logger log = LoggerFactory.getLogger(ValidControllerTest.class);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void intValidTest() throws Exception {
        String result = mockMvc.perform(get("/valid/int/0"))
                .andReturn().getResponse().getContentAsString(UTF_8);
        log.error(result);

    }


}