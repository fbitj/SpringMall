package com.springmall.service;

import com.springmall.bean.Ad;
import com.springmall.bean.PageRequest;
import com.springmall.bean.DataForPage;

public interface AdvertiseService {
    DataForPage<Ad> totalAdvertise(PageRequest adRequest);

    Ad update(Ad advertise);

    Ad create(Ad advertise);

    void delete(Integer id);
}
