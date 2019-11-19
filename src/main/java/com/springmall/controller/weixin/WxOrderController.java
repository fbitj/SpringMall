package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/order")
public class WxOrderController {
    @Autowired
    OrderService orderService;


    /**
     * 用户查看订单
     * @param showType 订单类型
     * @param page 当前页
     * @param size 每页订单数量
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo orderList(int showType,int page, int size){
        BaseReqVo baseReqVo = new BaseReqVo();
        /*baseReqVo.setData();
        baseReqVo.setErrmsg();
        baseReqVo.setErrno();*/
        return baseReqVo;
    }

    /**
     * 订单支付
     * {"errno":724,"errmsg":"订单不能支付"}
     * @param orderId
     * @return
     */
    @RequestMapping("prepay")
    public BaseReqVo prepay(@RequestBody int orderId){
        BaseReqVo baseReqVo = new BaseReqVo();
        int res = orderService.updateOrderStatusById(orderId,101);
        if (res == 1){
            baseReqVo.setErrmsg("支付成功！");
            baseReqVo.setErrno(0);
        }else {
            baseReqVo.setErrmsg("订单不能支付");
            baseReqVo.setErrno(724);
        }
        return baseReqVo;
    }
}
