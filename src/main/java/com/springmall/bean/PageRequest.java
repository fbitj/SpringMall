package com.springmall.bean;

import lombok.Data;

@Data
public class PageRequest {
    private Integer page;
    private Integer limit;
    private String name;
    private String content;
    //类型待定
    private String sort;
    private String desc;

    //优惠券请求新增
    private Short type;
    private Short status;
    private String order;

    //listuser请求新增
    private String couponId;
    private String userId;

    //topic请求新增
    private String title;
    private String subtitle;

    //groupon请求新增
    private Integer goodsId;

    //wx/goods/list新增
    private Integer categoryId;
    private String keyword;
    private Integer size;
    private Integer brandId;
    private Boolean isHot;
}
