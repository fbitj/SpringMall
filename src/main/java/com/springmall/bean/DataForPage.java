package com.springmall.bean;

import lombok.Data;

import java.util.List;

/**
 * 分页
 */
@Data
public class DataForPage<T> {
    private long total;
    private List<T> items;

    public DataForPage(long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public DataForPage() {
    }
}
