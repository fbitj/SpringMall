package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.exception.OrderException;
import com.springmall.mapper.*;
import com.springmall.utils.GetUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.System;
import java.math.BigDecimal;
import java.util.*;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    Order_goodsMapper order_goodsMapper;
    @Autowired
    OrderStatusMapper orderStatusMapper;

    /**
     * 显示订单
     * 根据userId、orderSn、OrderStatusArray查找订单
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Map<String, Object> queryOrders(Integer page, Integer limit) {
        //完成分页
        PageHelper.startPage(page, limit);
        //查询
        OrderExample orderExample = new OrderExample();
        List<Order> orders = orderMapper.selectByExample(orderExample);
        List<OrderResp> orderResps = new ArrayList<>();
        for (Order order : orders) {
            OrderResp orderResp = packageOrderToOrderResp(order);
            orderResps.add(orderResp);
        }
        Map<String, Object> map = new HashMap<>();
        PageInfo<OrderResp> orderRespsPageInfo = new PageInfo<>(orderResps);
        long total = orderRespsPageInfo.getTotal();
        map.put("total", total);
        map.put("items", orderResps);
        return map;
    }

    /**
     * 条件查询
     * @param page
     * @param limit
     * @param userId
     * @param orderSn
     * @param orderStatusArray
     * @return
     */
    @Override
    public Map<String, Object> queryOrders(Integer page, Integer limit, Integer userId, String orderSn, Short[] orderStatusArray) {
        //完成分页
        PageHelper.startPage(page, limit);
        //查询
        OrderExample orderExample = new OrderExample();
        List<Short> shorts = null;
        if (orderStatusArray != null) {
            shorts = orderStatusArrayToList(orderStatusArray);
        }
        if (userId != null && orderSn != null && orderStatusArray != null) {
            orderExample.createCriteria().andUserIdEqualTo(userId).andOrderSnEqualTo(orderSn).andOrderStatusIn(shorts);
        }
        if (userId != null && orderSn != null) {
            orderExample.createCriteria().andUserIdEqualTo(userId).andOrderSnEqualTo(orderSn);
        }
        if (userId != null && orderStatusArray != null) {
            orderExample.createCriteria().andUserIdEqualTo(userId).andOrderStatusIn(shorts);
        }
        if (orderSn != null && orderStatusArray != null) {
            orderExample.createCriteria().andOrderSnEqualTo(orderSn).andOrderStatusIn(shorts);
        }
        if (userId != null) {
            orderExample.createCriteria().andUserIdEqualTo(userId);
        }
        if (orderSn != null) {
            orderExample.createCriteria().andOrderSnEqualTo(orderSn);
        }
        if (orderStatusArray != null) {
            orderExample.createCriteria().andOrderStatusIn(shorts);
        }
        List<Order> orders = orderMapper.selectByExample(orderExample);
        List<OrderResp> orderResps = new ArrayList<>();
        for (Order order : orders) {
            OrderResp orderResp = packageOrderToOrderResp(order);
            orderResps.add(orderResp);
        }
        PageInfo<OrderResp> orderPageInfo = new PageInfo(orderResps);
        long total = orderPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", orders);
        return map;
    }

    @Override
    public Map<String, Object> viewOrderDetail(Integer id) {
        //查找订单
        Order order = orderMapper.selectByPrimaryKey(id);
        OrderResp orderResp = packageOrderToOrderResp(order);
        //根据user_id查找用户
        User user = userMapper.selectByPrimaryKey(order.getUserId());
        Map<String, String> userMap = new HashMap<>();
        userMap.put("nickname", user.getNickname());
        userMap.put("avatar", user.getAvatar());
        //根据orderid查找order_goodsList
        Order_goodsExample order_goodsExample = new Order_goodsExample();
        order_goodsExample.createCriteria().andOrderIdEqualTo(order.getId());
        List<Order_goods> order_goods = order_goodsMapper.selectByExample(order_goodsExample);
        Map<String, Object> map = new HashMap<>();
        map.put("orderGoods", order_goods);
        map.put("user", userMap);
        map.put("order", orderResp);
        return map;
    }

    /**
     * 发货：改变订单状态。order_status = 301
     *
     * @param order
     * @return
     */
    @Override
    public int shipGoods(Order order) {
        Order toShipOrder = orderMapper.selectByPrimaryKey(order.getOrderId());
        toShipOrder.setShipSn(order.getShipSn());
        toShipOrder.setShipChannel(order.getShipChannel());
        toShipOrder.setOrderStatus((short) 301);
        Date date = new Date();
        toShipOrder.setUpdateTime(date);
        int shiped = orderMapper.updateByPrimaryKeySelective(toShipOrder);
        return shiped;
    }

    /**
     * 退款，order_status = 203
     *
     * @param map
     * @return
     */
    @Override
    public int refund(Map<String, Object> map) {
        int orderId = (int) map.get("orderId");
        Order order = orderMapper.selectByPrimaryKey(orderId);
        Object refundMoney =map.get("refundMoney");
        BigDecimal actualPrice = order.getActualPrice();
//        if (order.getActualPrice().compareTo(refundMoney)) {
            order.setOrderStatus((short) 203);
            Date date = new Date();
            order.setUpdateTime(date);
            int refunded = orderMapper.updateByPrimaryKeySelective(order);
            return refunded;
//        return 0;
    }

    /**
     * 将order封装到orderResp
     * 刨除了几个前端不需要接收的成员变量
     *
     * @param order
     * @return
     */
    public OrderResp packageOrderToOrderResp(Order order) {
        OrderResp orderResp = new OrderResp();
        orderResp.setId(order.getId());
        orderResp.setUserId(order.getUserId());
        orderResp.setOrderSn(order.getOrderSn());
        orderResp.setOrderStatus(order.getOrderStatus());
        orderResp.setConsignee(order.getConsignee());
        orderResp.setMobile(order.getMobile());
        orderResp.setAddress(order.getAddress());
        orderResp.setMessage(order.getMessage());
        orderResp.setGoodsPrice(order.getGoodsPrice());
        orderResp.setFreightPrice(order.getFreightPrice());
        orderResp.setCouponPrice(order.getCouponPrice());
        orderResp.setIntegralPrice(order.getIntegralPrice());
        orderResp.setGrouponPrice(order.getGrouponPrice());
        orderResp.setOrderPrice(order.getOrderPrice());
        orderResp.setActualPrice(order.getActualPrice());
        orderResp.setComments(order.getComments());
        orderResp.setEndTime(order.getEndTime());
        orderResp.setAddTime(order.getAddTime());
        orderResp.setUpdateTime(order.getUpdateTime());
        orderResp.setDeleted(order.getDeleted());
        orderResp.setPayTime(order.getPayTime());
        orderResp.setShipSn(order.getShipSn());
        orderResp.setShipChannel(order.getShipChannel());
        return orderResp;
    }

    public List<Short> orderStatusArrayToList(Short[] orderStatusArray) {
        ArrayList<Short> shorts = new ArrayList<>();
        for (int i = 0; i < orderStatusArray.length; i++) {
            shorts.add(orderStatusArray[i]);
        }
        return shorts;
    }


    @Override
    public int updateOrderStatusById(int orderId, int status) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short) status);
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }


    @Override
    public ArrayList<OrderRespVo> queryUserOrders(int userId, int showType, int page, int size) {
        PageHelper.startPage(page, size);//分页查询
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause("id desc");// id倒序
        OrderExample.Criteria criteria = orderExample.createCriteria();
//        criteria.andOrderStatusGreaterThanOrEqualTo((short) (showType * 100)).andOrderStatusLessThan((short) ((showType + 1) * 100));
        switch (showType){
            case 1:
                criteria.andOrderStatusEqualTo((short) 101);
                break;
            case 2:
                criteria.andOrderStatusBetween((short)201,(short)202);
                break;
            case 3:
                criteria.andOrderStatusEqualTo((short) 301);
                break;
            case 4:
                criteria.andOrderStatusBetween((short)401,(short)402);
                break;
        }
        // 查询未删除订单
        criteria.andUserIdEqualTo(userId).andDeletedEqualTo(false);
        List<Order> orders = orderMapper.selectByExample(orderExample);
        ArrayList<OrderRespVo> orderRespVos = new ArrayList<>();
        for (Order order : orders) {
            // 通过订单查询订单包含的商品信息
            Order_goodsExample order_goodsExample = new Order_goodsExample();
            order_goodsExample.createCriteria().andOrderIdEqualTo(order.getId()).andDeletedEqualTo(false);
            List<Order_goods> order_goods = order_goodsMapper.selectByExample(order_goodsExample);
            OrderRespVo orderRespVo = new OrderRespVo();
            orderRespVo.setId(order.getId());
            // 通过订单状态查询订单的状态描述
            String statusText = orderStatusMapper.queryStatusTextByStatusId(order.getOrderStatus());
            orderRespVo.setOrderStatusText(statusText);
            orderRespVo.setGroupin(order.getGrouponPrice().doubleValue() > 0);// 如果有团购优惠代表参加了团购
            orderRespVo.setOrderSn(order.getOrderSn());
            orderRespVo.setActualPrice(order.getActualPrice());
            orderRespVo.setGoodsList(order_goods);
            orderRespVo.setHandleOption(order.getOrderStatus());
            orderRespVos.add(orderRespVo);
        }

        // 通过订单编号查询订单包含商品

        return orderRespVos;
    }

    @Override
    public Map<String, Object> queryOrderInfo(int orderId) {
        // 查询订单详情
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andIdEqualTo(orderId).andDeletedEqualTo(false);
        OrderRespVo orderRespVo = orderMapper.queryOrderById(orderId);

        // 查询订单中的商品
        Order_goodsExample order_goodsExample = new Order_goodsExample();
        order_goodsExample.createCriteria().andOrderIdEqualTo(orderRespVo.getId()).andDeletedEqualTo(false);
        List<Order_goods> order_goods = order_goodsMapper.selectByExample(order_goodsExample);

        // 数据封装
        Short orderStatus = orderRespVo.getOrderStatus();
        orderRespVo.setHandleOption(orderStatus);
        orderRespVo.setGoodsList(order_goods);
        String statusText = orderStatusMapper.queryStatusTextByStatusId(orderStatus);
        orderRespVo.setOrderStatusText(statusText);
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> expressInfo = new HashMap<>();
        expressInfo.put("shipperName",orderRespVo.getShipChannel());
        expressInfo.put("logisticCode",orderRespVo.getShipSn());
        map.put("orderInfo", orderRespVo);
        map.put("orderGoods", order_goods);
        map.put("expressInfo", expressInfo);
        return map;
    }

    /**
     * 逻辑删除订单，并更新修改时间
     *
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public int deleteOrderById(Integer orderId) {
        orderMapper.updateOrderDeletedAndTimeById(orderId);
        order_goodsMapper.updateDeleteAndTimeByOrderId(orderId);
        return 1;
    }

    @Autowired
    Order_goodsMapper orderGoodsMapper;

    @Override
    public Order_goods queryOrderGoodsByOrderIdAndGoodsId(int orderId, int goodsId) {
        Order_goodsExample orderGoodsExample = new Order_goodsExample();
        orderGoodsExample.createCriteria().andOrderIdEqualTo(orderId).andGoodsIdEqualTo(goodsId);
        List<Order_goods> order_goods = orderGoodsMapper.selectByExample(orderGoodsExample);
        return order_goods.get(0);
    }

    @Autowired
    CartMapper cartMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    SystemMapper systemMapper;
    @Autowired
    Goods_productMapper goodsProductMapper;
    @Autowired
    Coupon_userMapper couponUserMapper;
    /**
     * @param userid
     * @param cartId         购物车id(没有用到)
     * @param addressId      地址id
     * @param couponId       优惠券id
     * @param message        留言信息
     * @param grouponRulesId 团购规则id（团购预留）
     * @param grouponLinkId  团购id（团购预留）
     * @return
     */
    @Override
    @Transactional
    public int submitOrder(int userid, int cartId, int addressId, int couponId, String message, int grouponRulesId, int grouponLinkId) {

        // 根据addressId查询地址信息
        Address address = addressMapper.selectByPrimaryKey(addressId);
        // 根据couponId查询优惠券信息
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
       // 根据couponId修改用户的优惠卷使用状态
        couponUserMapper.updateUserCouponStatusByCouponId(couponId,userid,1);
        // 购物车中查询商品信息
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andUserIdEqualTo(userid).andCheckedEqualTo(true).andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        if (carts.size() == 0) {
            throw new OrderException("服务器异常");
        }
        // 生成订单编号
        String orderSn = UUID.randomUUID().toString().replaceAll("-", "");

        // 生成订单信息
        Order order = new Order();
        order.setUserId(userid);
        order.setOrderSn(orderSn);
        order.setOrderStatus((short) 101);// 订单状态
        order.setConsignee(address.getName());// 收件人
        order.setMobile(address.getMobile());
        order.setAddress(address.getAddress());
        order.setMessage(message);// 订单留言
        BigDecimal goodPrice = new BigDecimal("0");
        for (Cart cart : carts) {
            BigDecimal price = cart.getPrice();
            Short number = cart.getNumber();
            BigDecimal multiplicand = BigDecimal.valueOf(number);
            BigDecimal multiply = price.multiply(multiplicand);
            goodPrice = goodPrice.add(multiply);
        }
        order.setGoodsPrice(goodPrice);// 商品总费用
        // 查询配送规则
        BigDecimal freightMin = new BigDecimal(systemMapper.selectFreightMin());
        BigDecimal freightPrice = new BigDecimal(systemMapper.selectFreightPrice());
        if (goodPrice.compareTo(freightMin) == -1) {
            order.setFreightPrice(freightPrice);// 配送费用
        } else {
            order.setFreightPrice(new BigDecimal("0"));// 配送费用
        }
        BigDecimal discount = new BigDecimal("0");
        if (coupon != null) {
            discount = coupon.getDiscount();
        }
        order.setCouponPrice(discount);// 优惠券减免
        order.setIntegralPrice(new BigDecimal("0"));// 用户积分减免============没有不考虑！！！！！！！！！！！
        order.setGrouponPrice(new BigDecimal("0"));// 团购优惠减免============不考虑！！！！！！！！！！！
        order.setOrderPrice(order.getGoodsPrice().add(order.getFreightPrice()).subtract(discount));// 订单费用 goods_price + freight_price - coupon_price
        order.setActualPrice(order.getOrderPrice().subtract(order.getIntegralPrice()));// 实付费用 order_price - integral_price
        order.setPayTime(new Date());//微信支付时间
        order.setAddTime(new Date());
        order.setDeleted(false);
        // 将订单插入数据库
        orderMapper.insertSelective(order);

        Integer id = order.getId();
        // 生成订单商品信息
        for (Cart cart : carts) {
            // 查询订单中商品的以及商品的数量
            Integer productId = cart.getProductId();
            Short number = cart.getNumber();
            // 判断商品库存是否足够，不足返回 -1
            Goods_product goods_product = goodsProductMapper.selectByPrimaryKey(productId);
            if (goods_product.getNumber() < number) {
                throw new OrderException("商品库存不足");
            }
            Order_goods orderGoods = new Order_goods();
            orderGoods.setOrderId(id);
            orderGoods.setGoodsId(cart.getGoodsId());
            orderGoods.setGoodsName(cart.getGoodsName());
            orderGoods.setGoodsSn(cart.getGoodsSn());
            orderGoods.setProductId(cart.getProductId());
            orderGoods.setNumber(cart.getNumber());
            orderGoods.setPrice(cart.getPrice());
            orderGoods.setSpecifications(cart.getSpecifications());
            orderGoods.setPicUrl(cart.getPicUrl());
            orderGoods.setComment(0);
            orderGoods.setAddTime(new Date());
            orderGoods.setUpdateTime(new Date());
            orderGoods.setDeleted(false);
            orderGoodsMapper.insertSelective(orderGoods);
            // 商品库存good_product的number减少
            orderGoodsMapper.updateNumberById(cart.getProductId(), number);
            // 购物车中逻辑删除
            cartMapper.logicDeleteCartById(cart.getId());

        }
        return order.getId();
    }


    @Override
    public int orderPay(int orderId) {
        short status = 201;
        String payId = UUID.randomUUID().toString().replaceAll("-", "");
        return orderMapper.OrderPayById(orderId, status, payId);
    }
}
