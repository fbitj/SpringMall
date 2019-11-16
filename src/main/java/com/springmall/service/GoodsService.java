package com.springmall.service;

import com.springmall.bean.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> queryGoodsByPage(String page, String limit,String goodsSn,String name, String sortField, String order);
}
