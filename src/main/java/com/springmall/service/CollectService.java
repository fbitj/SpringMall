package com.springmall.service;

import com.springmall.bean.Collect;

import java.util.List;

public interface CollectService {
    List<Collect> queryCollectList(Integer userId,Integer valueId);

    boolean queryGoodCollect(Integer userId, Integer id);
}
