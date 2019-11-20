package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.Brand;
import com.springmall.bean.BrandExample;
import com.springmall.bean.PageRequest;
import com.springmall.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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

    /**
     * 显示全部品牌，将page数目和brandList封装成map返回
     * @param page
     * @param limit
     * @return
     */
    @Override
    public Map<String, Object> queryBrands(Integer page, Integer limit) {
        //完成分页查询
        PageHelper.startPage(page, limit);
        //查询
        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andDeletedEqualTo(false);
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        //获取总页数
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        long total = brandPageInfo.getTotal();
        //封装成map
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", brands);
        return map;
    }

    /**
     * 添加品牌
     * @param brand
     * @return
     */
    @Override
    public Brand addBrand(Brand brand) {
        Date date = new Date();
        brand.setAddTime(date);
        brand.setUpdateTime(date);
        int i = brandMapper.insertSelective(brand);
        if(i == 1) {
            Brand newBrand = brandMapper.selectByName(brand.getName());
            return newBrand;
        }
        return null;
    }

    /**
     * 编辑
     * @param brand
     * @return
     */
    @Override
    public Brand updateBrand(Brand brand) {
        Date date = new Date();
        brand.setUpdateTime(date);
        int i = brandMapper.updateByPrimaryKeySelective(brand);
        if (i == 1) {
            Brand updateBrand = brandMapper.selectByPrimaryKey(brand.getId());
            return updateBrand;
        }
        return null;
    }

    /**
     * 删除
     * @param brand
     * @return
     */
    @Override
    public int deleteBrand(Brand brand) {
        //逻辑删除
        brand.setDeleted(true);
        Date date = new Date();
        brand.setUpdateTime(date);
        int delete = brandMapper.updateByPrimaryKeySelective(brand);
       // int delete = brandMapper.deleteByPrimaryKey(brand.getId());
        return delete;
    }

    /**
     * 查找
     * 根据id精确查找
     * 根据name模糊查找
     * @param page
     * @param limit
     * @param id
     * @param name
     * @return
     */
    @Override
    public Map<String, Object> queryBrands(Integer page, Integer limit, Integer id, String name) {
        //完成分页
        PageHelper.startPage(page, limit);
        //查询
        BrandExample brandExample = new BrandExample();
        if (id != null && name == null) {//id不为空，name为空
            brandExample.createCriteria().andIdEqualTo(id);
        }else if(id != null && name != null) {//id不为空，name不为空
            brandExample.createCriteria().andIdEqualTo(id).andNameLike("%" + name + "%");
        }else {//id为空，name不为空
            brandExample.createCriteria().andNameLike( "%" + name + "%");
        }

        List<Brand> brands = brandMapper.selectByExample(brandExample);
        //获取总页数
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        long total = brandPageInfo.getTotal();
        //封装成map
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", brands);
        return map;
    }

    /**
     * 根据id查询品牌信息
     * @param id
     * @return
     */
    @Override
    public Brand queryBrandById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 显示所有品牌信息并分页
     * @param pageRequest
     * @return
     */
    @Override
    public Map queryAllBrandByPage(PageRequest pageRequest) {
        PageHelper.startPage(pageRequest.getPage(), pageRequest.getSize());

        BrandExample example = new BrandExample();
        example.createCriteria().andDeletedEqualTo(false);
        List<Brand> brands = brandMapper.selectByExample(example);
        PageInfo<Brand> info = new PageInfo<>(brands);
        int pages = info.getPages();

        Map map = new HashMap();
        map.put("brandList", brands);
        map.put("totalPages", pages);
        return map;
    }


    public Brand selectBrandByName(String name) {
        Brand brand = brandMapper.selectByName(name);
        return brand;
    }
}
