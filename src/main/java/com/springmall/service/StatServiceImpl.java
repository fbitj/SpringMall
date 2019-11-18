package com.springmall.service;

import com.springmall.bean.GoodsStat;
import com.springmall.bean.OrderStat;
import com.springmall.bean.UserStat;
import com.springmall.mapper.OrderMapper;
import com.springmall.mapper.Order_goodsMapper;
import com.springmall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fwj on 2019-11-16.
 */
@Service
public class StatServiceImpl implements StatService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    Order_goodsMapper order_goodsMapper;
    @Autowired
    OrderMapper orderMapper;

    /**
     * 返回用户增长的统计数据
     * @return
     */
    @Override
    public List<UserStat> userStat() {
        return userMapper.selectUserCountOfDay();
    }

    /**
     * 返回订单与用户的统计数据
     * @return
     */
    @Override
    public List<OrderStat> OrderStat() {
        return orderMapper.selectOrderStat();
    }

    /**
     * 返回订单与商品的统计数据
     * @return
     */
    @Override
    public List<GoodsStat> GoodsStat() {
        return order_goodsMapper.selectGoodsStat();
    }
}
