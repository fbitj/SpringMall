package com.springmall.service;


import com.springmall.bean.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map<String, Object> queryOrders(Integer page, Integer limit);
    Map<String, Object> queryOrders(Integer page, Integer limit, Integer userId, String orderSn, Short[] orderStatusArray);
    Map<String, Object> viewOrderDetail(Integer id);

    List<Order> queryOrdersByType(int showType, int page, int size);

    /**
     * 更改指定订单的状态
     * @param orderId 订单id
     * @param status 101:未付款，102:用户取消
     * @return
     */
    int updateOrderStatusById(int orderId, int status);

}
