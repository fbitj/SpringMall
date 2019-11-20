package com.springmall.bean;

import lombok.Data;

import java.util.List;

@Data
public class OrderRespVo extends Order {

    private List<Order_goods> goodsList;

    private boolean isGroupin;

    private String orderStatusText;

    private HandleOption handleOption;


    public void setHandleOption(int orderStatus) {
        this.handleOption = new HandleOption(orderStatus);
    }

    /**
     * 订单用户允许的操作
     */
    @Data
    private class HandleOption {
        private boolean cancel;

        private boolean delete;

        private boolean pay;

        private boolean comment;

        private boolean confirm;

        private boolean refund;

        private boolean rebuy;

        public HandleOption(int orderStatus) {
            switch (orderStatus){
                case 101://待付款
                    cancel = true;
                    pay = true;
                    break;
                case 102://用户取消
                    delete = true;
                    break;
                case 103://系统取消
                    delete = true;
                    break;
                case 201://已付款
                    confirm = true;
                    refund = true;
                    break;
                case 202://申请退款
                    break;
                case 203://已发货
                    confirm = true;
                    refund = true;
                    break;
                case 301:
                    confirm = true;
                    refund = true;
                    break;
                case 401://用户收货
                case 402://系统收货
                delete = true;
                rebuy = true;
                comment = true;
                    break;
            }
        }
    }
}
