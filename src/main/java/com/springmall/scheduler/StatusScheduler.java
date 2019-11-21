package com.springmall.scheduler;

import com.springmall.bean.Coupon;
import com.springmall.bean.CouponExample;
import com.springmall.bean.Coupon_user;
import com.springmall.bean.Coupon_userExample;
import com.springmall.mapper.CouponMapper;
import com.springmall.mapper.Coupon_userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StatusScheduler {

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    Coupon_userMapper couponUserMapper;

    @Scheduled(cron = "0 * * * * ?")
    public void coupon() {
        Short s0 = 0;
        Short s1 = 1;
        Short s2 = 2;

        Date currentTime = new Date();
        //更新基于绝对时间优惠券状态
        Coupon coupon = new Coupon();
        coupon.setStatus(s1);
        CouponExample example = new CouponExample();
        example.createCriteria().andTimeTypeEqualTo(s1).andEndTimeLessThanOrEqualTo(currentTime).andStatusEqualTo(s0).andDeletedEqualTo(false);
        couponMapper.updateByExampleSelective(coupon, example);

        //更新用户领取的优惠券状态
        Coupon_user couponUser = new Coupon_user();
        couponUser.setStatus(s2);
        Coupon_userExample couponUserExample = new Coupon_userExample();
        couponUserExample.createCriteria().andStatusEqualTo(s0).andEndTimeLessThanOrEqualTo(currentTime).andDeletedEqualTo(false);
        couponUserMapper.updateByExampleSelective(couponUser, couponUserExample);

    }
}
