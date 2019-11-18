package com.springmall.service;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.mapper.GoodsMapper;
import com.springmall.mapper.OrderMapper;
import com.springmall.mapper.ProductMapper;
import com.springmall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomePageServiceImpl implements HomePageService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    OrderMapper orderMapper;
    @Override
    public Map<String, Long> dataTotal() {
        //goodsTotal
        GoodsExample goodsExample = new GoodsExample();
        List<Goods> goods = goodsMapper.selectByExample(goodsExample);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goods);
        long goodsTotal = goodsPageInfo.getTotal();
        //userTotal
        UserExample userExample = new UserExample();
        List<User> users = userMapper.selectByExample(userExample);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long userTotal = userPageInfo.getTotal();
        //productTotal
        ProductExample productExample = new ProductExample();
        List<Product> products = productMapper.selectByExample(productExample);
        PageInfo<Product> productPageInfo = new PageInfo<>(products);
        long productTotal = productPageInfo.getTotal();
        //orderTotal
        OrderExample orderExample = new OrderExample();
        List<Order> orders = orderMapper.selectByExample(orderExample);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        long orderTotal = orderPageInfo.getTotal();
        //封装
        Map<String, Long> map = new HashMap<>();
        map.put("goodsTotal", goodsTotal);
        map.put("userTotal", userTotal);
        map.put("productTotal", productTotal);
        map.put("orderTotal", orderTotal);
        return map;
    }
}
