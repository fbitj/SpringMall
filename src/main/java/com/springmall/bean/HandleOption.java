package com.springmall.bean;

import lombok.Data;

/**
 * 用户微信小程序端可执行操作
 */
@Data
public class HandleOption {

    //取消订单
    private Boolean cancel = false;

    //删除历史订单
    private Boolean delete = false;

    //付款
    private Boolean pay = false;

    //评价
    private Boolean comment = false;

    //确认收货
    private Boolean confirm = false;

    //退款
    private Boolean refund = false;

    //再次购买
    private Boolean rebuy = false;
}
