package com.springmall.scheduler;

import com.springmall.bean.Coupon;
import com.springmall.bean.CouponExample;
import com.springmall.mapper.CouponMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StatusScheduler {

    @Autowired
    CouponMapper couponMapper;

    @Scheduled(cron = "0 * * * * ?")
    public void coupon() {
        Short s1 = 0;
        Short s2 = 1;

        Date currentTime = new Date();
        //更新过期优惠券状态
        Coupon coupon = new Coupon();
        coupon.setStatus(s2);
        CouponExample example = new CouponExample();
        example.createCriteria().andTimeTypeEqualTo(s2).andEndTimeLessThanOrEqualTo(currentTime).andStatusEqualTo(s1).andDeletedEqualTo(false);
        couponMapper.updateByExampleSelective(coupon, example);

        //更新基于领取时间过期的优惠券
    }
}
