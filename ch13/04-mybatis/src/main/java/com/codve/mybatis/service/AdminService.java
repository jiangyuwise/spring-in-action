package com.codve.mybatis.service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author admin
 * @date 2019/12/7 14:54
 */
@Slf4j
public class AdminService {

    public int save(int num) {
        log.info("save " + num);
        return num;
    }
}
