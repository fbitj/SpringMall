package com.springmall.controller;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.service.CouponService;
import com.springmall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    /**
     * 根据条件显示优惠券信息
     * @param request
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo<Map<String,Object>> couponlistByPage(AdRequest request) {
        List<Coupon> coupons = couponService.totalCoupons(request);
        PageInfo<Coupon> pageInfo = new PageInfo<>(coupons);
        long total = pageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("items", coupons);
        map.put("total", total);
        BaseReqVo success = ResultUtil.success(map);
        return success;
    }

    /**
     * 新增优惠券
     * @param coupon
     * @return
     */
    @RequestMapping("create")
    public BaseReqVo<Coupon> addCoupon(@RequestBody Coupon coupon) {
        Coupon result = couponService.addCoupon(coupon);
        return ResultUtil.success(result);
    }

    /**
     * 显示单个优惠券信息
     * @param id
     * @return
     */
    @RequestMapping("read")
    public BaseReqVo<Coupon> showInfo(Integer id) {
        Coupon coupon = couponService.showInfo(id);
        return ResultUtil.success(coupon);
    }

    /**
     * 获取用户优惠券信息
     * @param request
     * @return
     */
    @RequestMapping("listuser")
    public BaseReqVo showListUserByPage(AdRequest request) {
        DataForPage<Coupon_user> couponUsers = couponService.showListUserByPage(request);
        return ResultUtil.success(couponUsers);
    }

    /**
     * 更新优惠券信息
     * @param couponUser
     * @return
     */
    @RequestMapping("update")
    public BaseReqVo updateListUser(@RequestBody Coupon couponUser) {
        Coupon result = couponService.updateListUser(couponUser);
        return ResultUtil.success(result);
    }
}
