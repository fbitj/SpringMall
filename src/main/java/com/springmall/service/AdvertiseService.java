package com.springmall.service;

import com.springmall.bean.Ad;
import com.springmall.bean.AdRequest;

import java.util.Map;

public interface AdvertiseService {
    Map<String,Object> totalAdvertise(AdRequest adRequest);

    Ad update(Ad advertise);

    Ad create(Ad advertise);
}
