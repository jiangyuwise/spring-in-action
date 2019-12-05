package com.codve.mybatis.controller;

import com.codve.mybatis.model.query.ArticleUpdateQuery;
import com.codve.mybatis.model.query.UserCreateQuery;
import com.codve.mybatis.model.query.UserQuery;
import com.codve.mybatis.model.vo.ArticleVO;
import com.codve.mybatis.model.vo.UserVO;
import com.codve.mybatis.util.CommonResult;
import com.codve.mybatis.util.PageResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author admin
 * @date 2019/12/5 13:58
 */
public class RestTemplateTest {

    private static ObjectMapper mapper;

    private static RestTemplate template;

    private Logger logger = LoggerFactory.getLogger(RestTemplateTest.class);

    @BeforeAll
    static void setUpAll() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        template = new RestTemplate();
        template.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    @Test
    void test1() throws JsonProcessingException {
        String url = "http://localhost:8080/user/save";

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserCreateQuery query = new UserCreateQuery();
        query.setName("中国➔😄😇😌😙");
        query.setBirthday(System.currentTimeMillis());
        String queryStr = mapper.writeValueAsString(query);

        // 设置请求体
        HttpEntity<String> entity = new HttpEntity<>(queryStr, headers);

        ResponseEntity<String> responseEntity =
                template.exchange(url, HttpMethod.POST, entity, String.class);

        assertNotNull(responseEntity);
        CommonResult commonResult = mapper.readValue(responseEntity.getBody(), CommonResult.class);

        logger.error(commonResult.toString());
    }

    @Test
    void test2() {
        String url = "http://localhost:8080/str/hello";
        ResponseEntity<String> responseEntity =
                template.exchange(url, HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        logger.error(responseEntity.getBody());
    }

    @Test
    void test3() {
        String url = "http://localhost:8080/article/1";
        ResponseEntity<CommonResult<ArticleVO>> responseEntity =
                template.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<CommonResult<ArticleVO>>() {});
        CommonResult<ArticleVO> result = responseEntity.getBody();
        assertNotNull(result);
        assertEquals(0, result.getCode());

        ArticleVO articleVO = result.getData();
        logger.error(articleVO.toString());
    }

    @Test
    void test4() throws JsonProcessingException {
        String url = "http://localhost:8080/article/update";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ArticleUpdateQuery query = new ArticleUpdateQuery();
        query.setId(1L);
        query.setTitle("测试 HTTP client");
        String queryStr = mapper.writeValueAsString(query);
        HttpEntity<String> entity = new HttpEntity<>(queryStr, headers);

        ResponseEntity<String> responseEntity =
                template.exchange(url, HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        CommonResult result = mapper.readValue(responseEntity.getBody(), CommonResult.class);
        logger.error(result.toString());
    }

    @Test
    void test5() {
        String url = "http://localhost:8080/article/delete/1";
        ResponseEntity<CommonResult> responseEntity =
                template.getForEntity(url, CommonResult.class);
        CommonResult result = responseEntity.getBody();

        assertNotNull(result);
        logger.error(result.toString());
    }

    @Test
    void test6() {
        String url = "http://localhost:8080/article/delete/1";
        CommonResult result = template.getForObject(url, CommonResult.class);

        assertNotNull(result);
        logger.error(result.toString());
    }

    @Test
    void test7() throws JsonProcessingException {
        String url = "http://localhost:8080/user/find";

        UserQuery query = new UserQuery();
        String queryStr = mapper.writeValueAsString(query);

        HttpEntity<String> entity = new HttpEntity<>(queryStr);

        ResponseEntity<CommonResult<PageResult<UserVO>>> responseEntity = template.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<CommonResult<PageResult<UserVO>>>() {
                });

        CommonResult<PageResult<UserVO>> result = responseEntity.getBody();
        assertEquals(0, result.getCode());

        result.getData().getList().stream().forEach(e -> logger.error(e.toString()));

    }
}
