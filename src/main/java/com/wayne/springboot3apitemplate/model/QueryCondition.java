package com.wayne.springboot3apitemplate.model;

import lombok.Data;

@Data
public class QueryCondition {

    /**
     * 分页当前页,默认值为第一页
     */
    private Long pageNum = 1L;

    /**
     * 分页每页显示分页数
     */
    private Long pageSize = 10L;
}