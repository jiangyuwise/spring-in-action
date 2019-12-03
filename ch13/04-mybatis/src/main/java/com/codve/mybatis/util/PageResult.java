package com.codve.mybatis.util;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/28 19:32
 */
@Data
public class PageResult<T> {

    private List<T> list;

    private Long total;

    public PageResult() {

    }

    public PageResult(List<T> list) {
        this.list = list;
        if (list instanceof Page) {
            Page page = (Page) list;
            this.total = page.getTotal();
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
