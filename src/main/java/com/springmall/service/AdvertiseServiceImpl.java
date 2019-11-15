package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.AdRequest;
import com.springmall.bean.Ad;
import com.springmall.mapper.AdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdvertiseServiceImpl implements AdvertiseService {

    @Autowired
    AdMapper advertiseMapper;

    @Override
    public Map<String,Object> totalAdvertise(AdRequest adRequest) {
        PageHelper.startPage(adRequest.getPage(), adRequest.getLimit());

        //待定
        List<Ad> advertises = advertiseMapper.selectAllWithParm(adRequest);
        PageInfo<Ad> pageInfo = new PageInfo<>(advertises);
        long total = pageInfo.getTotal();

        Map<String,Object> result = new HashMap<>();
        result.put("total", total);
        result.put("items", advertises);
        return result;
    }
}
