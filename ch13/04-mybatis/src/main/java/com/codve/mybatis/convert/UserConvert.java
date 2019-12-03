package com.codve.mybatis.convert;

import com.codve.mybatis.model.business.object.UserBO;
import com.codve.mybatis.model.data.object.UserDO;
import com.codve.mybatis.model.query.UserCreateQuery;
import com.codve.mybatis.model.query.UserUpdateQuery;
import com.codve.mybatis.model.vo.UserVO;
import com.codve.mybatis.util.BeanUtil;
import com.codve.mybatis.util.PageResult;
import org.springframework.beans.BeanUtils;

import java.util.List;

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

    public static UserDO convert(UserUpdateQuery user) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        return userDO;
    }

    public static UserDO convert(UserVO user) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        return userDO;
    }

    public static UserVO convert(UserDO user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    public static PageResult<UserVO> convert(List<UserDO> userDoList) {
        PageResult<UserDO> doResult = new PageResult<>(userDoList);
        PageResult<UserVO> voResult = new PageResult<>();
        voResult.setTotal(doResult.getTotal());
        voResult.setList(BeanUtil.copyList(doResult.getList(), UserVO.class));
        return voResult;
    }

}
