package com.springmall.service;
import java.util.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.mapper.OrderMapper;
import com.springmall.mapper.Order_goodsMapper;
import com.springmall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    Order_goodsMapper order_goodsMapper;

    /**
     * 显示订单
     * 根据userId、orderSn、OrderStatusArray查找订单
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
        List<OrderResp> orderResps=new ArrayList<>();
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
        if( orderStatusArray != null) {
             shorts = orderStatusArrayToList(orderStatusArray);
        }
        if(userId != null && orderSn != null && orderStatusArray !=null) {
            orderExample.createCriteria().andUserIdEqualTo(userId).andOrderSnEqualTo(orderSn).andOrderStatusIn(shorts);
        }
        if(userId != null && orderSn != null) {
            orderExample.createCriteria().andUserIdEqualTo(userId).andOrderSnEqualTo(orderSn);
        }
        if(userId != null && orderStatusArray != null) {
            orderExample.createCriteria().andUserIdEqualTo(userId).andOrderStatusIn(shorts);
        }
        if(orderSn != null && orderStatusArray != null) {
            orderExample.createCriteria().andOrderSnEqualTo(orderSn).andOrderStatusIn(shorts);
        }
        if (userId != null) {
            orderExample.createCriteria().andUserIdEqualTo(userId);
        }
        if(orderSn != null) {
            orderExample.createCriteria().andOrderSnEqualTo(orderSn);
        }
        if(orderStatusArray != null) {
            orderExample.createCriteria().andOrderStatusIn(shorts);
        }
        List<Order> orders = orderMapper.selectByExample(orderExample);
        List<OrderResp> orderResps=new ArrayList<>();
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
        map.put("order",orderResp);
        return map;
    }

    /**
     * 将order封装到orderResp
     * 刨除了几个前端不需要接收的成员变量
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
        return orderResp;
    }

    public List<Short> orderStatusArrayToList(Short[] orderStatusArray) {
        ArrayList<Short> shorts = new ArrayList<>();
        for (int i = 0; i < orderStatusArray.length ; i++) {
            shorts.add(orderStatusArray[i]);
        }
        return shorts;
    }

    @Override
    public List<Order> queryOrdersByType(int showType, int page, int size) {

        return null;
    }

    @Override
    public int updateOrderStatusById(int orderId, int status) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short) status);
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }
}
