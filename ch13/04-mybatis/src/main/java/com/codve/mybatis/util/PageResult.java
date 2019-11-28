package com.codve.mybatis.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/28 19:32
 */
@Data
public class PageResult<T> implements Serializable {

    private List<T> list;

    private Integer total;

    public PageResult(List<T> list, Integer total) {
        this.list = list;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
