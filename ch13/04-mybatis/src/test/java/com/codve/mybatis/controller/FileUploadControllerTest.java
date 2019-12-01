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
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author admin
 * @date 2019/12/1 15:03
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FileUploadControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    private Logger log = LoggerFactory.getLogger(FileUploadControllerTest.class);

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void singleUploadTest() throws Exception {
        File file = new FileSystemResource("./upload-test/spring.pdf").getFile();
        MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE, new FileInputStream(file));
        mockMvc.perform(multipart("/file/upload")
                .file(multipartFile))
                .andExpect(status().isOk());
    }

    @Test
    void multiUploadTest() throws Exception {
        File file = new FileSystemResource("./upload-test/spring.pdf").getFile();
        MockMultipartFile multipartFile = new MockMultipartFile("files", file.getName(),
                MediaType.MULTIPART_FORM_DATA_VALUE, new FileInputStream(file));
        MockMultipartFile multipartFile2 = new MockMultipartFile("files", file.getName() + "2",
                MediaType.MULTIPART_FORM_DATA_VALUE, new FileInputStream(file));
        String str = mockMvc.perform(multipart("/file/multi/upload")
                .file(multipartFile).file(multipartFile2))
                .andReturn().getResponse().getContentAsString(UTF_8);
        CommonResult result = objectMapper.readValue(str, CommonResult.class);
        assertNotNull(result);
        log.error(result.toString());
    }


}