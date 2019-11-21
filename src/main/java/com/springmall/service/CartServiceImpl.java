package com.springmall.service;


import com.springmall.bean.*;
import com.springmall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    Goods_productMapper goods_productMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    Coupon_userMapper coupon_userMapper;

    @Override
    public Map<String, Object> cartTotal() {
        CartExample cartExample = new CartExample();
        //要切记，显示的是没有删除的数据，deleted = false
        cartExample.createCriteria().andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        //获取goodsCountTotal
        int goodsCount = goodsCountCart();
    /*    PageInfo<Cart> cartPageInfo = new PageInfo<>(carts);
        long goodsCount = cartPageInfo.getTotal();*/
        //获取goodsAmount //商品总金额
        BigDecimal goodsAmount = new BigDecimal(0.00);
        for (Cart cart : carts) {
            BigDecimal price = cart.getPrice();
            //同一件商品的总价（本质：price * number）
            for (int i = 1; i < cart.getNumber() ; i++) {
                price = price.add(price);
            }
            goodsAmount = goodsAmount.add(price);
        }
        //获取checked = true 的goods
        CartExample checkedCartExample = new CartExample();
        checkedCartExample.createCriteria().andCheckedEqualTo(true);
        List<Cart> checkedCarts = cartMapper.selectByExample(checkedCartExample);
        //获取checkedGoodsCount
        int checkedGoodsCount = checkedGoodsCountCart();
      /*  PageInfo<Cart> checkedCartPageInfo = new PageInfo<>(checkedCarts);
        long checkedGoodsCount = checkedCartPageInfo.getTotal();*/
        //获取checkedGoodsAmount
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
        for (Cart checkedCart : checkedCarts) {
            BigDecimal price = checkedCart.getPrice();
            //同一件商品的总价（本质：price * number）
            for (int i = 1; i < checkedCart.getNumber() ; i++) {
                price = price.add(price);
            }
            checkedGoodsAmount = checkedGoodsAmount.add(price);
        }
        //将所有的total封装成map
        Map<String, Object> totalMap = new HashMap<>();
        totalMap.put("goodsCount", goodsCount);
        totalMap.put("checkedGoodsCount", checkedGoodsCount);
        totalMap.put("goodsAmount", goodsAmount);
        totalMap.put("checkedGoodsAmount", checkedGoodsAmount);
        //将totalMap和carts封装成map
        Map<String, Object> map = new HashMap<>();
        map.put("cartTotal", totalMap);
        map.put("cartList", carts);
        return map;
    }

    /**
     * 删除
     * deleted = true
     * @return
     */
    @Override
    public Map<String, Object> deleteCart(List<Integer> productIds) {
        for (int productId : productIds) {
            int delete = cartMapper.updateByProductIdSelective(productId);
        }
        Map<String, Object> cartTotal = cartTotal();
        return cartTotal;
    }

    /**
     * 选择或者取消选择
     * isChecked = 1(true) ,选择
     * isChecked = 0(false) ,取消选择
     * @param productIds
     * @param isChecked
     * @return 购物车所有数据 cartTotal
     */
    @Override
    public Map<String, Object> checkedCart(List<Integer> productIds, boolean isChecked) {
        for (int productId : productIds) {
            Cart cart = cartMapper.selectByProductId(productId);
            cart.setChecked(isChecked);
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andProductIdEqualTo(productId);
            int checked = cartMapper.updateByExampleSelective(cart, cartExample);
        }
        Map<String, Object> cartTotal = cartTotal();
        return cartTotal;
    }

    /**
     * 更新购物车的商品(只能改变数量)
     * @param cartReq
     * @return
     */
    @Override
    public int updateCart(Cart cartReq) {
        Cart cart = cartMapper.selectByPrimaryKey(cartReq.getId());
        cart.setNumber(cartReq.getNumber());
        Date date = new Date();
        cart.setUpdateTime(date);
        int update = cartMapper.updateByPrimaryKeySelective(cart);
        return update;
    }

    /**
     *  获取购物车商品件数（所有商品的总number）
     * @return
     */
    @Override
    public int goodsCountCart() {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andDeletedEqualTo(false);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        int goodsCount = 0;
        for (Cart cart : carts) {
            Short goodsnumber = cart.getNumber();
            goodsCount += goodsnumber.intValue();
        }
        return goodsCount;
    }

    /**
     * 添加商品到购物车
     * @param cart
     * @return购物车中商品总数
     */
    @Override
    public int addCart(Cart cart) {
        //查询goods表，获取goodsSn, goodsName,
        //查询goods_product表， 获取productId, price, specifications, pic_url
        //默认checked=true
        Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
        Goods_product goods_product = goods_productMapper.selectByPrimaryKey(cart.getProductId());
        cart.setGoodsSn(goods.getGoodsSn());
        cart.setGoodsName(goods.getName());
        cart.setPrice(goods_product.getPrice());
        cart.setSpecifications(goods_product.getSpecifications());
        cart.setPicUrl(goods_product.getUrl());
        cart.setChecked(true);
        Date date = new Date();
        cart.setAddTime(date);
        cart.setUpdateTime(date);
        int insert = cartMapper.insertSelective(cart);
        System.out.println("insert:" + insert);
        int goodsCountCart = goodsCountCart();
        return goodsCountCart;
    }

    /**
     *  立即购买商品
     * @param cart
     * @return 购物车id
     */
    @Override
    public int fastAddCart(Cart cart) {
        Order order = createOrder(cart);
        return 1;
    }

    /**
     * 下单前信息确认
     * @param cartId
     * @param addressId
     * @param couponId
     * @param grouponRulesId
     * @return  一个map。
     * 包括checkedAddress（收货地址），checkedGoodsList(所有商品的cart数据)
     * goodsTotalPrice 无优惠的总价
     * grouponPrice团购价，grouponId = 0时，grouponPrice=0
     * actualPrice 实付价（实付费用 = order_price - integral_price（积分减免））
     * orderTotalPrice 订单总价（订单费用 = goods_price + freight_price - coupon_price）
     * couponPrice 优惠金额
     * 	availableCouponLength 可用优惠券的数量（根据user查询，status=0,计算总量）
     * 	优惠券状态（使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架；）
     * 	couponId 优惠券id
     * freightPrice 运费
     * addressId
     */
    @Override
    public Map<String, Object> checkOutBeforePay(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId) {
        List<Cart> carts = null;
        if(cartId != 0) {
            //立即购买
            CartExample cartExample = new CartExample();
            cartExample.createCriteria().andIdEqualTo(cartId);
            carts = cartMapper.selectByExample(cartExample);
        } else {
            //购物车下单，购买的是所有checked=true的商品
            CartExample cartExamples = new CartExample();
            cartExamples.createCriteria().andCheckedEqualTo(true);
            carts = cartMapper.selectByExample(cartExamples);
        }
        //求goodsTotalPrice
        BigDecimal goodsTotalPrice = new BigDecimal(0);
        for (Cart cart : carts) {
            BigDecimal price = cart.getPrice();
            goodsTotalPrice.add(price);
        }
        //不管哪种方式，都会有一个addressId
        Address address = addressMapper.selectByPrimaryKey(addressId);
        //团购
        BigDecimal grouponPrice = new BigDecimal(0);
        if(grouponRulesId != 0) {
         //   grouponPrice.add();
        }
        //优惠券
        BigDecimal couponPrice = new BigDecimal(0);
        //couponId不为0，根据couponId和userId去coupon_user表搜索优惠券金额，并计算可用优惠券数量
        if(couponId != 0 ) {
            Coupon_userExample coupon_userExample = new Coupon_userExample();
            coupon_userExample.createCriteria().andCouponIdEqualTo(couponId).andUserIdEqualTo(1);
            List<Coupon_user> coupon_users = coupon_userMapper.selectByExample(coupon_userExample);

        }
        //运费
        BigDecimal freightPrice = new BigDecimal(0);

        //orderTotalPrice
        BigDecimal orderTotalPrice = new BigDecimal(0);
        orderTotalPrice.add(freightPrice).add(couponPrice.negate());
        //integralPrice 积分减免
        BigDecimal integralPrice = new BigDecimal(0);
        //actualPrice 实付金额
        BigDecimal actualPrice = orderTotalPrice.add(integralPrice.negate());

        //封装传给前台的数据
        Map<String, Object> map = new HashMap<>();
        map.put("grouponPrice", 0);
        map.put("grouponRulesId", grouponRulesId);
        map.put("checkedAddress", address);
        map.put("checkedGoodsList", carts);
        map.put("goodsTotalPrice", goodsTotalPrice);
        map.put("addressId", addressId);
        map.put("actualPrice", actualPrice);
        map.put("orderTotalPrice", 0);
        map.put("couponId", couponId);
        map.put("couponPrice", couponPrice);
        map.put("availableCouponLength", 0);
        map.put("freightPrice", freightPrice);
        return map;
    }

    /**
     * 获取购物车里选中商品件数（所有选中商品的总number）
     * @return
     */
    public int checkedGoodsCountCart() {
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andDeletedEqualTo(false).andCheckedEqualTo(true);
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        int checkedGoodsCount = 0;
        for (Cart cart : carts) {
            Short checkedGoodsnumber = cart.getNumber();
            checkedGoodsCount += checkedGoodsnumber.intValue();
        }
        return checkedGoodsCount;
    }

    /**
     * 生成一条购物车数据
     * @param cart
     * @return
     */
    public Cart createCart(Cart cart) {
        //已知goodsId, number, productId
        //查询goods表，获取goodsSn, goodsName,
        //查询goods_product表， 获取productId, price, specifications, pic_url
        //默认checked=true
        Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
        Goods_product goods_product = goods_productMapper.selectByPrimaryKey(cart.getProductId());
        cart.setGoodsSn(goods.getGoodsSn());
        cart.setGoodsName(goods.getName());
        cart.setPrice(goods_product.getPrice());
        cart.setSpecifications(goods_product.getSpecifications());
        cart.setPicUrl(goods_product.getUrl());
        cart.setChecked(true);
        Date date = new Date();
        cart.setAddTime(date);
        cart.setUpdateTime(date);
        int insert = cartMapper.insertSelective(cart);
        System.out.println("insert:" + insert);
        return cart;
    }

    /**
     * 生成一条订单
     * @param cart
     * @return
     */
    public Order createOrder(Cart cart) {
        //已知goodsId, number, productId
        //查询goods表，获取goodsSn, goodsName,
        //查询goods_product表， 获取 price, specifications, pic_url
        //默认checked=true
        //计算实付金额
        Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
        Goods_product goods_product = goods_productMapper.selectByPrimaryKey(cart.getProductId());
        Order order = new Order();
        order.setOrderStatus((short) 101);
        order.setGoodsPrice(goods_product.getPrice());
        BigDecimal bigDecimal = new BigDecimal(1);
        order.setFreightPrice(bigDecimal);//运费
        order.setCouponPrice(bigDecimal);//优惠券减免
        order.setIntegralPrice(bigDecimal);//用户积分减免
        order.setGoodsPrice(bigDecimal);//团购优惠价减免
         BigDecimal orderPrice = goods_product.getPrice().add(order.getFreightPrice()).add(order.getCouponPrice().negate());
        order.setOrderPrice(orderPrice);//订单费用 = goods_price + freight_price - coupon_price
        BigDecimal actualPrice = orderPrice.add(order.getIntegralPrice().negate());
        order.setActualPrice(actualPrice);//实付费用 = order_price - integral_price
        return order;
    }

}
