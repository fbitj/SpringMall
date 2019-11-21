package com.springmall.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GrouponDetail {

    private String orderStatusText;

    private Object creator;

    private Groupon groupon;

    private Integer orderId;

    private String orderSn;

    private BigDecimal actualPrice;

    private List<User> joiners;

    private Integer joinerCount;

    private Order orderInfo;

    private List<Order_goods> orderGoods;

    private List<Order_goods> goodsList;

    private Groupon_rules rules;

    private Integer id;

    private Integer linkGrouponId;

    private Boolean isCreator;

    private HandleOption handleOption;
}
