package com.springmall.service;

import com.springmall.bean.Cart;
import com.springmall.bean.Order;

import java.util.List;
import java.util.Map;

public interface CartService {
    //获取购物车的数据
    Map<String, Object> cartTotal();
    //删除购物车商品
    Map<String, Object> deleteCart(List<Integer> productIds);
    //选择或者取消选择
    Map<String, Object> checkedCart(List<Integer> productIds, boolean isChecked);
    //更新购物车的商品(只能改变数量)
    int updateCart(Cart cart);
    //获取购物车商品件数（所有商品的总number）
    int goodsCountCart();
    //添加商品到购物车
    int addCart(Cart cart);
    // 立即购买商品
    int fastAddCart(Cart cart);
    //下单前信息确认
    Map<String, Object> checkOutBeforePay(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId);

}
