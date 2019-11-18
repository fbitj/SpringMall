package com.springmall.bean;

import lombok.Data;

/**
 * Created by fwj on 2019-11-16.
 */

//用来保存每天的订单量、下单货品数量、下单货品总额的统计数据
@Data
public class GoodsStat {

    private String day;

    private Integer orders;

    private Integer products;

    private Double amount;
}
