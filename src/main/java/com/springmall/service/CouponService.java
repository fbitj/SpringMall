package com.springmall.service;

import com.springmall.bean.PageRequest;
import com.springmall.bean.Coupon;
import com.springmall.bean.Coupon_user;
import com.springmall.bean.DataForPage;

import java.util.List;

public interface CouponService {
    List<Coupon> totalCoupons(PageRequest request);

    Coupon addCoupon(Coupon coupon);

    Coupon showInfo(Integer id);

    DataForPage<Coupon_user> showListUserByPage(PageRequest request);

    Coupon updateListUser(Coupon coupon);

    int deleteCoupon(Coupon coupon);
}
