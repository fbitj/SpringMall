package com.springmall.service;

import com.springmall.bean.Brand;
import com.springmall.bean.BrandExample;
import com.springmall.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandMapper brandMapper;
    @Override
    public List queryAllBrandSimpleInfo() {
        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andDeletedEqualTo(false);
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        ArrayList<Object> simpleBrands = new ArrayList<>();
        for (Brand brand : brands) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("value", brand.getId());
            map.put("label", brand.getName());
            simpleBrands.add(map);
        }
        return simpleBrands;
    }
}
