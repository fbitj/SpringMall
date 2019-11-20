package com.springmall.service;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.Cart;
import com.springmall.bean.CartExample;
import com.springmall.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartMapper cartMapper;

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
}
