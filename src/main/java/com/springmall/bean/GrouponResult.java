package com.springmall.bean;

import lombok.Data;

import java.util.List;

@Data
public class GrouponResult {
    private Groupon groupon;
    private Goods goods;
    private Groupon_rules rules;
    private List subGroupons;
}
