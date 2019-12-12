package com.codve.mybatis.util;

import com.codve.mybatis.model.data.object.TokenDO;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @date 2019/12/12 09:09
 */
public class TokenUtil {

    public static final String PREFIX = "Bearer ";

    public static TokenDO getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith(PREFIX)) {
            token = token.substring(PREFIX.length());
        }
        TokenDO tokenDO = new TokenDO();
        tokenDO.setToken(token);
        return tokenDO;
    }
}
