package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    CartService cartService;
    //获取购物车的数据
    @RequestMapping("index")
    public BaseReqVo indexCart() {
        Map<String, Object> map = cartService.cartTotal();
         return BaseReqVo.ok(map);
    }
}
