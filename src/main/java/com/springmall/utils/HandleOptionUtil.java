package com.springmall.utils;

import com.springmall.bean.HandleOption;

public class HandleOptionUtil {
    public static HandleOption handleOption(int orderStatus){
        HandleOption handleOption = new HandleOption();
        switch (orderStatus){

            //未付款：可取消订单、付款
            case 101 :
                handleOption.setCancel(true);
                handleOption.setPay(true);
                break;

            //用户取消：可删除历史订单
            case 102 :
                handleOption.setDelete(true);
                break;

            //系统取消：可删除历史订单
            case 103 :
                handleOption.setDelete(true);
                break;

            //已付款：退款
            case 201 :
                handleOption.setRefund(true);
                break;

            //申请退款：
            case 202 :
                break;

            //已退款：可删除历史订单、再次购买
            case 203 :
                handleOption.setDelete(true);
                handleOption.setRebuy(true);
                break;

            //已发货：可确认收货、退款
            case 301 :
                handleOption.setConfirm(true);
                handleOption.setRefund(true);
                break;

            //用户收货：可删除历史订单、评价、再次购买
            case 401 :

            //系统收货：可删除历史订单、评价、再次购买
            case 402 :
                handleOption.setDelete(true);
                handleOption.setComment(true);
                handleOption.setRebuy(true);
                break;
        }
        return handleOption;
    }
}
