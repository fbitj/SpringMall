package com.springmall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmall.bean.*;
import com.springmall.mapper.AdMapper;
import com.springmall.mapper.CouponMapper;
import com.springmall.mapper.GoodsMapper;
import com.springmall.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.System;
import java.util.Date;
import java.util.List;

@SpringBootTest
class LiaohaoApplicationTests {
    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CouponMapper couponMapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        Goods goods = goodsMapper.selectByPrimaryKey(1110016);
        System.out.println(goods);
        ObjectMapper objectMapper = new ObjectMapper();
    }

    @Test
    public void test() {
        CouponExample example = new CouponExample();
        Short s = 1;
        example.createCriteria().andEndTimeLessThanOrEqualTo(new Date()).andTimeTypeEqualTo(s);
        List<Coupon> coupons = couponMapper.selectByExample(example);
        System.out.println(coupons);
    }

    @Test
    public void test1() {
        double asdf = Double.parseDouble("10");
        System.out.println(asdf);
    }

}
