package com.springmall.service;


import com.springmall.bean.Order;

import java.util.Map;

public interface OrderService {
    Map<String, Object> queryOrders(Integer page, Integer limit);
    Map<String, Object> queryOrders(Integer page, Integer limit, Integer userId, String orderSn, Short[] orderStatusArray);
    Map<String, Object> viewOrderDetail(Integer id);
    int shipGoods(Order order);
    int refund(Map<String, Object> map);

}
