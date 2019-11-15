package com.codve.mapper;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author admin
 * @date 2019/11/15 10:14
 */
public class UserProvider {

    public String selectById(final Long id) {
        return new SQL(){
            {
                SELECT("`user_id`, `user_name`, `user_birthday`");
                FROM("`user`");
                WHERE("`user_id` = #{id}");
            }
        }.toString();
    }
}
