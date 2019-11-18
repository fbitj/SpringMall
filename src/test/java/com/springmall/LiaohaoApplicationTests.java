package com.springmall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springmall.bean.Ad;
import com.springmall.bean.AdExample;
import com.springmall.bean.Admin;
import com.springmall.bean.Goods;
import com.springmall.mapper.AdMapper;
import com.springmall.mapper.GoodsMapper;
import com.springmall.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class LiaohaoApplicationTests {
    @Autowired
    GoodsMapper goodsMapper;

    @Test
    void contextLoads() throws JsonProcessingException {
        Goods goods = goodsMapper.selectByPrimaryKey(1110016);
        System.out.println(goods);
        ObjectMapper objectMapper = new ObjectMapper();
    }

}
