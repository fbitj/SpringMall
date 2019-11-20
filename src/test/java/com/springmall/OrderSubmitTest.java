package com.springmall;


import com.springmall.controller.weixin.WxOrderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class OrderSubmitTest {
   /* @Autowired
    WxOrderController wxOrderController;
    @Test
    void test1(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("cartId",0);
        map.put("addressId",1);
        map.put("couponId",1);
        map.put("message","这是测试");
        map.put("grouponRulesId",0);
        map.put("grouponLinkId",0);

        wxOrderController.orderSubmit(map);
    }*/
}
