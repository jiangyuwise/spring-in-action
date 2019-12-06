package com.codve.mybatis.controller;

import com.codve.mybatis.model.query.UserQuery;
import com.codve.mybatis.model.query.UserUpdateQuery;
import com.codve.mybatis.model.vo.UserVO;
import com.codve.mybatis.util.CommonResult;
import com.codve.mybatis.util.PageResult;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

/**
 * @author admin
 * @date 2019/12/6 09:56
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private DataSource dataSource;

    private static ResourceDatabasePopulator populator;

    private static ObjectMapper mapper = new ObjectMapper();

    private Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    private RestTemplate template = new RestTemplate();
    private String url = "http://localhost:8080";
    @BeforeAll
    static void setUpAll() {
        populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data/article.sql"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @BeforeEach
    void setUp() {
        populator.execute(dataSource);
    }

    @Test
    void saveTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "黄渤是戏😜");
        params.add("birthday", String.valueOf(System.currentTimeMillis()));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        ResponseEntity<CommonResult> response = template
                .exchange(url + "/user/save", POST, request, CommonResult.class);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        CommonResult result = response.getBody();
        logger.error(request.toString());
        logger.error(result.toString());
    }

    @Test
    void updateTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        UserUpdateQuery query = new UserUpdateQuery();
        query.setId(1L);
        query.setName("戏精😜");
        query.setBirthday(System.currentTimeMillis());

        Map<String, String> map = mapper.convertValue(query, new TypeReference<Map<String, String>>() {});

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        map.forEach(params::add);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<CommonResult> response = template
                .exchange(url + "/user/update", POST, request, CommonResult.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        CommonResult result = response.getBody();
        logger.error(result.toString());
    }

    @Test
    void deleteTest() {
        ResponseEntity<CommonResult> response = template
                .exchange(url + "/user/delete/1", GET, null, CommonResult.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        CommonResult result = response.getBody();
        logger.error(result.toString());
    }

    @Test
    void findTest() {
        ResponseEntity<CommonResult<UserVO>> response = template
                .exchange(url + "/user/2", GET, null, new ParameterizedTypeReference<CommonResult<UserVO>>() {
                });
        CommonResult<UserVO> result = response.getBody();
        logger.error(result.getData().toString());
    }

    @Test
    @SuppressWarnings("deprecation")
    void findTest1() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        UserQuery query = new UserQuery();
        query.setName("J");

        String queStr = mapper.writeValueAsString(query);

        HttpEntity<String> request = new HttpEntity<>(queStr, headers);

        ResponseEntity<CommonResult<PageResult<UserVO>>> response = template
                .exchange(url + "/user/find", POST, request, new ParameterizedTypeReference<CommonResult<PageResult<UserVO>>>() {
                });

        PageResult<UserVO> result = response.getBody().getData();
        result.getList().forEach(e -> logger.error(e.toString()));
    }

}
