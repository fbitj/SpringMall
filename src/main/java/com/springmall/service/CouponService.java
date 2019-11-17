package com.springmall.service;

import com.springmall.bean.AdRequest;
import com.springmall.bean.Coupon;
import com.springmall.bean.Coupon_user;
import com.springmall.bean.DataForPage;

import java.util.List;
import java.util.Map;

public interface CouponService {
    List<Coupon> totalCoupons(AdRequest request);

    Coupon addCoupon(Coupon coupon);

    Coupon showInfo(Integer id);

    DataForPage<Coupon_user> showListUserByPage(AdRequest request);

    Coupon updateListUser(Coupon coupon);
}
