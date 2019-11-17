package com.springmall.service;

import com.springmall.bean.GoodsStat;
import com.springmall.bean.OrderStat;
import com.springmall.bean.UserStat;

import java.util.List;

/**
 * Created by fwj on 2019-11-16.
 */
public interface StatService {

    List<UserStat> userStat();

    List<OrderStat> OrderStat();

    List<GoodsStat> GoodsStat();
}
