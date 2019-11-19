package com.springmall.service;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.Cart;
import com.springmall.bean.CartExample;
import com.springmall.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        //获取goodsCountTotal
        PageInfo<Cart> cartPageInfo = new PageInfo<>(carts);
        long goodsCount = cartPageInfo.getTotal();
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
        cartExample.createCriteria().andCheckedEqualTo(true);
        List<Cart> checkedCarts = cartMapper.selectByExample(cartExample);
        //获取checkedGoodsCount
        PageInfo<Cart> checkedCartPageInfo = new PageInfo<>(checkedCarts);
        long checkedGoodsCount = checkedCartPageInfo.getTotal();
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
}
