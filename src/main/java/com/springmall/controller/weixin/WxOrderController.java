package com.springmall.controller.weixin;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.service.CommentService;
import com.springmall.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        // 获取用户信息
        /*Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();*/
        int userId = 1;
        ArrayList<OrderRespVo> orderRespVo = orderService.queryUserOrders(userId,showType,page,size);
       if (orderRespVo.size() == 0){
           return BaseReqVo.ok();
       }else {
           HashMap<String, Object> map = new HashMap<>();
           PageInfo<OrderRespVo> goodsInfo = new PageInfo<>(orderRespVo);
           long total = goodsInfo.getTotal();
           int pages = goodsInfo.getPages();
           map.put("data",orderRespVo);
           map.put("count",total);
           map.put("totalPages",pages);
           return BaseReqVo.ok(map);
       }

    }



    /**
     * 通过订单id查询订单详情
     * @param orderId
     * @return
     */
    @RequestMapping("detail")
    public BaseReqVo orderDetail(int orderId){
        Map<String, Object> res = orderService.queryOrderInfo(orderId);
        return BaseReqVo.ok(res);
    }

    /**
     * 取消订单
     * 考虑：取消定单后要将商品的库存还原
     * @param order
     * @return
     */
    @RequestMapping("cancel")
    public BaseReqVo cancelOrder(@RequestBody Map order){
        // 商品库存归还到product表=================
        orderService.updateOrderStatusById((Integer) order.get("orderId"),102);
        return BaseReqVo.ok();
    }
    /**
     * 退款取消订单
     * 考虑：退款取消定单后要将商品的库存还原
     * @param order
     * @return
     */
    @RequestMapping("refund")
    public BaseReqVo orderRefund(@RequestBody Map order){
        // 商品库存归还到product表=================
        //1.获取订单中的各个商品的数量，
        orderService.updateOrderStatusById((Integer) order.get("orderId"),202);
        return BaseReqVo.ok();
    }
    /**
     * 逻辑删除订单
     * 考虑：如果订单用户未支付，要不要真实删除？
     * 这里使用一刀切，都是用逻辑删除
     * @param order
     * @return
     */
    @RequestMapping("delete")
    public BaseReqVo orderDelete(@RequestBody Map order){
        int res = orderService.deleteOrderById((Integer) order.get("orderId"));
        if (res == 1){
            return BaseReqVo.ok();
        } else {
            return BaseReqVo.error(500,"删除失败！");
        }
    }

    /**
     * 确认收货
     * @param order
     * @return
     */
    @RequestMapping("confirm")
    public BaseReqVo orderConfirm(@RequestBody Map order){
        // 商品库存归还到product表=================
        //1.获取订单中的各个商品的数量，
        orderService.updateOrderStatusById((Integer) order.get("orderId"),401);
        return BaseReqVo.ok();
    }


    /**
     * 查询订单中指定商品
     * @param orderId 订单id
     * @param goodsId 商品id
     * @return 订单商品信息
     */
    @RequestMapping("goods")
    public BaseReqVo orderGoods(int orderId,int goodsId){
        Order_goods orderGoods = orderService.queryOrderGoodsByOrderIdAndGoodsId(orderId,goodsId);
        return BaseReqVo.ok(orderGoods);
    }

    @Autowired
    CommentService commentService;

    /**
     * 评论订单商品
     * @param orderGoodsCommentReqVo
     * @return
     */
    @RequestMapping("comment")
    public BaseReqVo OrderComment(@RequestBody OrderGoodsCommentReqVo orderGoodsCommentReqVo){
        int userId = 1;
        int res = commentService.commentSubmit(userId,
                orderGoodsCommentReqVo.getOrderGoodsId(),
                orderGoodsCommentReqVo.getContent(),
                orderGoodsCommentReqVo.isHasPicture(),
                orderGoodsCommentReqVo.getStar(),
                orderGoodsCommentReqVo.getPicUrls());
        if (res == 1){
            return BaseReqVo.error(577,"订单已评价！");
        } else if (res == 0){
            return BaseReqVo.ok();
        } else {
            return BaseReqVo.error(577,"超期不能评论！");
        }
    }

    /**
     * 提交订单
     * @return
     */
    @RequestMapping("submit")
    public BaseReqVo orderSubmit(@RequestBody Map map){
        int cartId = (int) map.get("cartId");
        int addressId = (int) map.get("addressId");
        int couponId = (int) map.get("couponId");// 优惠id
        String message = (String) map.get("message");
        // 团购规则预留
        int grouponRulesId = (int) map.get("grouponRulesId");
        int grouponLinkId = (int) map.get("grouponLinkId");
        // 固定的userid
        int userid = 1;
        int res = orderService.submitOrder(userid,cartId,addressId,couponId,message,grouponRulesId,grouponLinkId);

        if (res == 1){
            return BaseReqVo.ok();
        } else {
            return BaseReqVo.error(500, "服务器繁忙，请稍后再试!");
        }

    }

    /**
     * 订单支付
     * @param map
     * @return
     */
    @RequestMapping("prepay")
    public BaseReqVo orderPrepay(@RequestBody Map<String,Integer> map){
        Integer orderId = map.get("orderId");
        orderService.orderPay(orderId);
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("timeStamp","timeStamp");
        map2.put("nonceStr","nonceStr");
        map2.put("packageValue","packageValue");
        map2.put("signType","signType");
        map2.put("paySign","paySign");
        map2.put("sus","paySign");
        map2.put("success","true");
        return BaseReqVo.ok(map2);
    }
    /**
     * 用户订单支付
     * {"errno":724,"errmsg":"订单不能支付"}
     * @param orderId
     * @return
     */
    /*@RequestMapping("prepay")
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
    }*/
}
