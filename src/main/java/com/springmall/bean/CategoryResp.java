package com.springmall.bean;

import lombok.Data;

import java.util.List;
@Data
public class CategoryResp {
    private Integer id;

    private String name;

    private String keywords;

    private String desc;

    private String iconUrl;

    private String picUrl;

    private String level;

    private List<CategoryChildren> children;
}
