package com.codve.mybatis.model.query;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

/**
 * @author admin
 * @date 2019/12/2 19:44
 */
public class PageQuery {

    @Min(value = 1)
    private Integer pageNum = 1;

    @Range(min = 1, max = 100)
    private Integer pageSize = 20;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
