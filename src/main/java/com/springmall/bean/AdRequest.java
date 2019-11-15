package com.springmall.bean;

import lombok.Data;

@Data
public class AdRequest {
    private Integer page;
    private Integer limit;
    private String name;
    private String content;
    //类型待定
    private String sort;
    private String desc;
}
