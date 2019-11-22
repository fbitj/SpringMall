package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Coupon;
import com.springmall.bean.User;
import com.springmall.service.CouponService;
import com.springmall.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理前台系统所有关于coupon模块下的请求
 * URL：/wx/coupon/list
 *      /wx/coupon/mylist
 *      /wx/coupon/selectlist
 *      /wx/coupon/receive
 *      /wx/coupon/exchange
 */
@RestController
@RequestMapping("wx/coupon")
public class WxCouponController {

    @Autowired
    CouponService couponService;

    /**
     * 分页显示可用的通用券信息
     * @param page 当前分页页码
     * @param size 每页显示条目数
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo queryCouponListByPage(String page, String size) {
        HashMap<String,Object> dataMap = couponService.queryCouponListByPage(page,size);
        return ResultUtil.success(dataMap);
    }

    /**
     * 领取优惠券
     * @param map Json优惠券id
     * @return
     */
    @RequestMapping("receive")
    public BaseReqVo receiveCouponByCouponId(@RequestBody HashMap<String,String> map) {
        int couponId = Integer.parseInt(map.get("couponId"));
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            return ResultUtil.fail(740,"请先登录账号！");
        }
        int result = couponService.receiveCouponByCouponId(couponId, user.getId());
        if(result==0){
            return ResultUtil.fail(740,"领取失败！");
        }
        if(result==1){
            return ResultUtil.success(null);
        }
        if (result==2){
            return ResultUtil.fail(740,"您领取该券数满！");
        }
        if (result==3){
            return ResultUtil.fail(740,"该券已被领完！");
        }
        return ResultUtil.fail(740,"服务器异常！");
    }

    /**
     * 兑换优惠券
     * @param code 优惠券兑换码
     * @return
     */
    @RequestMapping("exchange")
    public BaseReqVo exchangeCoupon(@RequestBody Map<String,String> code) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        int result = couponService.exchangeCoupon(code.get("code"), user.getId());
        if(result==0){
            return ResultUtil.fail(742,"兑换码不正确！");
        }
        if(result==1){
            return ResultUtil.success(null);
        }
        if (result==2){
            return ResultUtil.fail(742,"您兑换该券已满");
        }
        if (result==3){
            return ResultUtil.fail(742,"该券已兑换完");
        }
        if (result==4){
            return ResultUtil.fail(742,"该券已失效");
        }
        return ResultUtil.fail(742,"服务器异常");
    }

    /**
     * 分页显示当前登录用户指定状态的优惠券列表
     * @param status 优惠券状态
     * @param page 当前分页页码
     * @param size 每页显示条目数
     * @return
     */
    @RequestMapping("mylist")
    public BaseReqVo queryMyCouponListByStatusAndPage(String status, String page, String size) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        HashMap<String,Object> dataMap = couponService.queryMyCouponListByStatusAndPage(status,page,size,user.getId());
        return ResultUtil.success(dataMap);
    }

    /**
     * 获取当前订单全部可用优惠券列表
     * @param cartId 购物车id
     * @param grouponRulesId 团购规则id
     * @return
     */
    @RequestMapping("selectlist")
    public BaseReqVo queryOrderCouponList(String cartId, String grouponRulesId) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        List<Coupon> dataList = couponService.queryOrderCouponList(Integer.parseInt(cartId),Integer.parseInt(grouponRulesId),user.getId());
        return ResultUtil.success(dataList);
    }
}
