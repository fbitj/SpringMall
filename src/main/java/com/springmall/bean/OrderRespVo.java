package com.springmall.bean;

import lombok.Data;

import java.util.List;

@Data
public class OrderRespVo {
    private Integer id;

    private String orderStatusText;

    private boolean visGroupin;

    private String orderSn;

    private List<Order_goods> goodsList;

    private HandleOption handleOption;

    /**
     * 订单用户允许的操作
     */
    private class HandleOption {
        private boolean cancel;

        private boolean delete;

        private boolean pay;

        private boolean comment;

        private boolean confirm;

        private boolean refund;

        private boolean rebuy;

        public HandleOption(int orderStatus) {
            if (orderStatus == 1){ // 未付款
                cancel = true;
                pay = true;
            } else if (orderStatus ==2){// 代发货
                refund = true;
            }else if (orderStatus ==3){// 待收货
                confirm = true;
                refund = true;
            }else if (orderStatus == 4){// 待评价
                delete = true;
                rebuy = true;
                comment = true;
            }
        }
    }
}
