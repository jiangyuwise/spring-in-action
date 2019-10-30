package com.codve.web;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author admin
 * @date 2019/10/29 18:25
 */
public class HomeControllerTest {

    // 使用 mock 模拟 mvc, 执行请求 get("/"), 获取 home 视图
    @Test
    public void homeControllerTest() throws Exception {
        HomeController controller = new HomeController();
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(view().name("home"));
    }

}