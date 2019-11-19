package com.springmall.bean;

import lombok.Data;

import java.util.List;
@Data
public class GoodInfoData {
    private Integer[] categoryIds;
    private Goods goods;
    private List<Attribute> attributes;
    private List<Goods_product> products;
    private List<Goods_specification> specifications;

}
