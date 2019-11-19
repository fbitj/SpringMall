package com.springmall.controller.admin;

import com.springmall.bean.BaseReqVo;
import com.springmall.service.CommentService;
import com.springmall.service.OrderService;
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


    @RequestMapping("reply")
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
}
