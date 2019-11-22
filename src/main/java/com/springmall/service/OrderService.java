package com.springmall.service;


import com.springmall.bean.Order;
import com.springmall.bean.OrderRespVo;
import com.springmall.bean.Order_goods;

import java.util.ArrayList;
import java.util.Map;


public interface OrderService {
    Map<String, Object> queryOrders(Integer page, Integer limit);
    Map<String, Object> queryOrders(Integer page, Integer limit, Integer userId, String orderSn, Short[] orderStatusArray);
    Map<String, Object> viewOrderDetail(Integer id);
    int shipGoods(Order orgitder);
    int refund(Map<String, Object> map);

    /**
     * 更改指定订单的状态
     * @param orderId 订单id
     * @param status 101:未付款，102:用户取消
     * @return
     */
    int updateOrderStatusById(int orderId, int status);

    /**
     * 查询用户的订单
     * @param userId 用户id
     * @param showType 订单类型
     * @param page 当前页数
     * @param size 每页显示数量
     * @return
     */
    ArrayList<OrderRespVo> queryUserOrders(int userId, int showType, int page, int size);

    /**
     * 通过订单id查询订单信息和订单中的购买商品信息
     * @return
     * @param orderId key：orderInfo、orderGoods
     */
    Map<String, Object> queryOrderInfo(int orderId);

    /**
     * 逻辑删除订单
     * @param orderId
     * @return
     */
    int deleteOrderById(Integer orderId);

    /**
     * 查询订单中指定商品
     * @param orderId 订单id
     * @param goodsId 商品id
     */
    Order_goods queryOrderGoodsByOrderIdAndGoodsId(int orderId, int goodsId);

    /**
     * 下单
     *
     * @param userid
     * @param cartId 购物车id
     * @param addressId 地址id
     * @param couponId 优惠券id
     * @param message 留言信息
     * @param grouponRulesId 团购规则id（团购预留）
     * @param grouponLinkId 团购id（团购预留）
     * @return
     */
    int submitOrder(int userid, int cartId, int addressId, int couponId, String message, int grouponRulesId, int grouponLinkId);

    /**
     * 订单支付
     * @param orderId
     * @return
     */
    int orderPay(int orderId);

    /**
     * 待评价商品数量减一
     * @return
     * @param orderGoodsId
     */
    int CommentSubOne(Integer orderGoodsId);

}
