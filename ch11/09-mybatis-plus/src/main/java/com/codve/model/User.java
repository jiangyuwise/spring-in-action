package com.codve.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_EMPTY;
import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_NULL;

/**
 * @author admin
 * @date 2019/11/16 11:44
 */
@Data
@TableName("user")
public class User {

    @TableId(value="user_id", type= IdType.AUTO)
    private Long id;

    @TableField(value = "user_name", insertStrategy = NOT_EMPTY)
    private String name;

    @TableField(value = "user_birthday", insertStrategy = NOT_NULL)
    private Long birthday;

    @Override
    public String toString() {
        Date date = new Date(birthday);
        String dateStr = String.format("%tF", date);
        return  "id: " + id + ", name: " + name + ", birthday: " + dateStr;
    }
}
