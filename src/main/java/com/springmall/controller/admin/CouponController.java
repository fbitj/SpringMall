package com.springmall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.service.CouponService;
import com.springmall.utils.ResultUtil;
import com.springmall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
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
    public BaseReqVo<Map<String,Object>> couponlistByPage(PageRequest request) {
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
        String illegal = isIllegal(coupon);
        if (illegal != null) {
            return ResultUtil.fail(402, illegal);
        }
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
    public BaseReqVo showListUserByPage(PageRequest request) {
        DataForPage<Coupon_user> couponUsers = couponService.showListUserByPage(request);
        return ResultUtil.success(couponUsers);
    }

    /**
     * 更新优惠券信息
     * @param coupon
     * @return
     */
    @RequestMapping("update")
    public BaseReqVo updateListUser(@RequestBody Coupon coupon) {
        String illegal = isIllegal(coupon);
        if (illegal != null) {
            return ResultUtil.fail(402, illegal);
        }
        Coupon result = couponService.updateListUser(coupon);
        return ResultUtil.success(result);
    }

    /**
     * 删除优惠券信息
     * @param coupon
     * @return
     */
    @RequestMapping("delete")
    public BaseReqVo deleteCoupon(@RequestBody Coupon coupon) {
        couponService.deleteCoupon(coupon);
        return ResultUtil.success(null);
    }

    /**
     * 限制传入参数
     * @param coupon
     * @return
     */
    private String isIllegal(Coupon coupon) {
        if (StringUtils.isEmpty(coupon.getName())) {
            return "优惠券名称不能为空";
        }
        if (coupon.getMin() == null || coupon.getMin().compareTo(BigDecimal.ZERO) == -1) {
            return "最低消费金额必须大于等于0";
        }
        if (coupon.getDiscount() == null || coupon.getDiscount().compareTo(BigDecimal.ZERO) != 1) {
            return "满减金额必须大于0";
        }
        if (coupon.getLimit() == null || coupon.getLimit() < 0) {
            return "限领数量必须大于等于0";
        }
        if (coupon.getTotal() == null || coupon.getTotal() <= 0) {
            return "优惠券数量必须大于0";
        }
        if (coupon.getTimeType() == 0 && (coupon.getDays() == null || coupon.getDays() <= 0)) {
            return "相对天数必须大于0";
        }
        if (coupon.getTimeType() == 1 && (coupon.getEndTime().before(coupon.getStartTime()))) {
            return "日期选择有误";
        }
        if (coupon.getTimeType() == 1 && (coupon.getEndTime().getTime() - new Date().getTime() < 1000*60*60)) {
            return "有效期最后时间最少要大于当前1小时";
        }
        return null;
    }
}
