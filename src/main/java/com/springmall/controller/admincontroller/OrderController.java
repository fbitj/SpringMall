package com.springmall.controller.admincontroller;

import com.springmall.bean.BaseRespVo;
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
    public BaseRespVo orderList(Integer page, Integer limit, Integer userId, String orderSn, Short[] orderStatusArray) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Map<String, Object> map = null;
        if(userId != null || orderSn != null || orderStatusArray != null) {
          map = orderService.queryOrders(page, limit, userId, orderSn, orderStatusArray);
        } else {
            map = orderService.queryOrders(page, limit);
        }
        BaseRespVo.setData(map);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }

    //详情
    @RequestMapping("detail")
    public BaseRespVo orderDetail(Integer id) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Map<String, Object> map = orderService.viewOrderDetail(id);
        BaseRespVo.setData(map);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }


    @RequestMapping("reply")
    public BaseRespVo replay(@RequestBody Map value){
        int commentId = (int) value.get("commentId");
        String content = (String) value.get("content");
        int isReplay = commentService.replay(commentId, content);
        BaseRespVo BaseRespVo = new BaseRespVo();
        if (isReplay == 1) {
            //{"errno":622,"errmsg":"订单商品已回复！"}
            BaseRespVo.setErrmsg("订单商品回复成功！");
            BaseRespVo.setErrno(0);
        }else {
            BaseRespVo.setErrmsg("订单商品已回复！");
            BaseRespVo.setErrno(622);
        }
        return BaseRespVo;
    }
}
