package com.springmall.service;

import com.springmall.bean.OrderConfig;

public interface OrderConfigService {

    OrderConfig queryOrderConfig();

    int updateOrderConfig(OrderConfig orderConfig);
}
