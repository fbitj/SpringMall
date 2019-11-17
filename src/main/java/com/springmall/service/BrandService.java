package com.springmall.service;


import com.springmall.bean.Brand;

import java.util.Map;

public interface BrandService {
    Map<String, Object> queryBrands(Integer page, Integer limit);
    Brand addBrand(Brand brand);
    Brand updateBrand(Brand brand);
    int deleteBrand(Brand brand);
    //查找
    //根据id精确查找
    //根据name模糊查找
    Map<String, Object> queryBrands(Integer page, Integer limit,Integer id, String name);

}
