package com.springmall.controller.admin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Category;
import com.springmall.bean.Order;
import com.springmall.service.CommentService;
import com.springmall.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CommentService commentService;

    //显示订单和条件查询
    @RequestMapping("list")
    @RequiresPermissions(value = {"admin:order:list"})
    public BaseReqVo orderList(Integer page, Integer limit, Integer userId, String orderSn, Short[] orderStatusArray) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Map<String, Object> map = null;
        if(userId != null || orderSn != null || orderStatusArray != null) {
            //条件查询
          map = orderService.queryOrders(page, limit, userId, orderSn, orderStatusArray);
        } else {
            //显示订单
            map = orderService.queryOrders(page, limit);
        }
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    //详情
    @RequestMapping("detail")
    @RequiresPermissions(value = {"admin:order:read"})
    public BaseReqVo orderDetail(Integer id) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Map<String, Object> map = orderService.viewOrderDetail(id);
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }


    @RequestMapping("reply")
    @RequiresPermissions(value = {"admin:order:reply"})
    public BaseReqVo replay(@RequestBody Map value){
        int commentId = (int) value.get("commentId");
        String content = (String) value.get("content");
        int isReplay = commentService.replay(commentId, content);
        BaseReqVo baseReqVo = new BaseReqVo();
        if (isReplay == 1) {
            //{"errno":622,"errmsg":"订单商品已回复！"}
            baseReqVo.setErrmsg("订单商品回复成功！");
            baseReqVo.setErrno(0);
        }else {
            baseReqVo.setErrmsg("订单商品已回复！");
            baseReqVo.setErrno(622);
        }
        return baseReqVo;
    }

    //发货
    @RequestMapping("ship")
    @RequiresPermissions(value = {"admin:order:ship"})
    public BaseReqVo ship(@RequestBody Order order) {
        int shiped = orderService.shipGoods(order);
        if (shiped == 1) {
            return BaseReqVo.ok();
        }
        return BaseReqVo.error(555, "发货失败");
    }

    //退款
    @RequestMapping("refund")
    @RequiresPermissions(value = {"admin:order:refund"})
    public BaseReqVo refund(@RequestBody Map<String, Object> map) {
        int refunded = orderService.refund(map);
        if(refunded == 1) {
            return BaseReqVo.ok();
        }
        return BaseReqVo.error(621, "订单退款失败");
    }
}
