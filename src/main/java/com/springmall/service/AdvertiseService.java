package com.springmall.service;

import com.springmall.bean.AdRequest;

import java.util.Map;

public interface AdvertiseService {
    Map<String,Object> totalAdvertise(AdRequest adRequest);
}
