package com.codve.mybatis.dao;

import com.codve.mybatis.util.CommonResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author admin
 * @date 2019/11/28 18:29
 */
public class JsonIgnoreNullTest {
    @Test
    void test() throws JsonProcessingException {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(1);
        commonResult.setMsg("success");

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = mapper.writeValueAsString(commonResult);
    }

    @Test
    void logTest() {
        Logger logger = LoggerFactory.getLogger(JsonIgnoreNullTest.class);
        logger.info("hello, world");
    }
}
