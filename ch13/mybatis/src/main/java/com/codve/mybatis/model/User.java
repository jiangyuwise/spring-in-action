package com.codve.mybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @author admin
 * @date 2019/11/18 18:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_name")
    private String name;

    @TableField(value = "user_birthday")
    private Long birthday;
}
