package com.springmall.service;

import com.springmall.bean.Footprint;

import java.util.List;

public interface FootPrintService {
    List<Footprint> queryFootPrintList(Integer userId,Integer goodsId);

    List queryUserFootPrintByPage(Integer userId, Integer page, Integer size);

    int addUserFoot(Integer goodsId, Integer userId);

    int deleteFootPrintById(Footprint footprint);
}
