package com.springmall;

import com.springmall.bean.GoodsStat;
import com.springmall.bean.OrderStat;
import com.springmall.bean.Order_goods;
import com.springmall.bean.UserStat;
import com.springmall.mapper.OrderMapper;
import com.springmall.mapper.Order_goodsMapper;
import com.springmall.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

/**
 * Created by fwj on 2019-11-16.
 */
@SpringBootTest
public class StatTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    Order_goodsMapper order_goodsMapper;
    @Autowired
    OrderMapper orderMapper;

    /**
     * 用户统计数据的测试
     */
    @Test
    public void userStatTest() {
        List<UserStat> userStats = userMapper.selectUserCountOfDay();
        for (UserStat userStat : userStats) {
            System.out.println(userStat);
        }
    }

    /**
     * 订单与用户的数据统计测试
     */
    @Test
    public void orderStatTest() {
        List<OrderStat> orderStats = orderMapper.selectOrderStat();
        for (OrderStat orderStat : orderStats) {
            System.out.println(orderStat);
        }
    }

    /**
     * 订单与商品的统计数据测试
     */
    @Test
    public void goodsStatTest() {
        List<GoodsStat> goodsStats = order_goodsMapper.selectGoodsStat();
        for (GoodsStat goodsStat : goodsStats) {
            System.out.println(goodsStat);
        }
    }

//    @Test
//    public void pathTest() {
//        File file = new File("src/main/resources/static/file.txt");
//        System.out.println(file.getAbsolutePath());
//    }
}
