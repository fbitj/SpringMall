package com.springmall.controller;

import com.springmall.bean.BaseReqVo;
import com.springmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    //显示订单
    @RequestMapping("list")
    public BaseReqVo orderList(Integer page, Integer limit, Integer userId, String orderSn, Short[] orderStatusArray) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Map<String, Object> map = null;
        if(userId != null || orderSn != null || orderStatusArray != null) {
          map = orderService.queryOrders(page, limit, userId, orderSn, orderStatusArray);
        } else {
            map = orderService.queryOrders(page, limit);
        }
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    //详情
    @RequestMapping("detail")
    public BaseReqVo orderDetail(Integer id) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Map<String, Object> map = orderService.viewOrderDetail(id);
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
