package com.springmall.service;

import com.springmall.bean.Footprint;

import java.util.List;

public interface FootPrintService {
    List<Footprint> queryFootPrintList(Integer userId,Integer goodsId);
}
