package com.codve.mybatis.model.data.object;

import lombok.Data;

/**
 * @author admin
 * @date 2019/12/11 12:38
 */
@Data
public class TokenDO {

    private Long id;

    private Long userId;

    private Integer deviceType;

    private String deviceCode;

    private Integer appType;

    private String ip;

    private String token;

    private Long createTime;

    private Long expireTime;
}
