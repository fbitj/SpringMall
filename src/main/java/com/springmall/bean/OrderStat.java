package com.springmall.bean;

import lombok.Data;

/**
 * Created by fwj on 2019-11-16.
 */

//用来保存每天的订单量，下单用户，订单总额，客单价统计数据
@Data
public class OrderStat {

    private String day;

    private Integer orders;

    private Integer customers;

    private Double amount;

    private Double pcr;
}
