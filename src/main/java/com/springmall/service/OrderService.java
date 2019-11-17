package com.springmall.service;


import java.util.Map;

public interface OrderService {
    Map<String, Object> queryOrders(Integer page, Integer limit);
    Map<String, Object> queryOrders(Integer page, Integer limit, Integer userId, String orderSn, Short[] orderStatusArray);
    Map<String, Object> viewOrderDetail(Integer id);

}
