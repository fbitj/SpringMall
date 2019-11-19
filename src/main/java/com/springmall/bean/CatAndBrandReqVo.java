package com.springmall.bean;

import lombok.Data;

import java.util.List;

@Data
public class CatAndBrandReqVo{
    private Integer value;
    private String label;
    private List<CatAndBrandReqVo> children;

}
