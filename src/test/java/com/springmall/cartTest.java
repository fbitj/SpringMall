package com.springmall;

import com.springmall.bean.Cart;
import com.springmall.bean.Order;
import com.springmall.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class cartTest {
    @Autowired
    CartService cartService;
    @Test
    public void mytest() {
        Cart cart = new Cart();
        cart.setGoodsId(1181000);
        cart.setProductId(6);
        cart.setNumber((short) 10);
        int i = cartService.addCart(cart);
        System.out.println(i);
    }

    @Test
    public void mytest2() {
        Cart cart = new Cart();
        cart.setGoodsId(1181000);
        cart.setProductId(6);
        cart.setNumber((short) 10);
        int fastAddCart = cartService.fastAddCart(cart);
        System.out.println(fastAddCart);
    }
}
