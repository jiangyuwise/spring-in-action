package com.codve.mybatis.convert;

import com.codve.mybatis.model.business.object.UserBO;
import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.UserCreateQuery;
import com.codve.mybatis.model.vo.UserVO;
import org.springframework.beans.BeanUtils;

/**
 * @author admin
 * @date 2019/11/26 17:19
 */
public class UserConvert {


    public static UserDO convert(UserCreateQuery user) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        return userDO;
    }

    public static UserDO convert(UserVO user) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        return userDO;
    }
}
