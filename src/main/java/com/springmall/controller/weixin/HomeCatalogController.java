package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Category;
import com.springmall.bean.HomePageData;
import com.springmall.service.CategoryService;
import com.springmall.service.CouponService;
import com.springmall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by fwj on 2019-11-20.
 */

@RestController
public class HomeCatalogController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CouponService couponService;
    // 首页显示数据
    @RequestMapping("/wx/home/index")
    @ResponseBody
    public BaseReqVo homeIndex() {
        HomePageData homePageData = new HomePageData();
        List<Category> categoryList = categoryService.getChannelCatagory();
        homePageData.setChannel(categoryList);
        
        return BaseReqVo.ok(homePageData);
    }

    /**
     * 分类首页显示，返回所有分类和当前子分类的数据
     * @return
     */
    @RequestMapping("/wx/catalog/index")
    @ResponseBody
    public BaseReqVo catalogIndex() {
        HashMap<String, Object> dataMap = new HashMap<>();
        List<Category> categoryList = categoryService.getCategoryByLevel("L1"); // 获取所有一级分类
        dataMap.put("categoryList", categoryList);
        Category currentCategory = categoryList.get(0); // 第一个分类为当前分类
        dataMap.put("currentCategory", currentCategory);
        List<Category> subCategoryList = categoryService.getSubCategoryByPid(currentCategory.getId()); // 获取当前分类的子分类
        dataMap.put("currentSubCategory", subCategoryList);
        return BaseReqVo.ok(dataMap);
    }

    /**
     * 返回指定一级分类的子分类数据
     * @param id
     * @return
     */
    @RequestMapping("/wx/catalog/current")
    @ResponseBody
    public BaseReqVo catalogCurrent(Integer id) {
        HashMap<String, Object> dataMap = new HashMap<>();
        Category currentCategory = categoryService.getCategoryById(id);
        dataMap.put("currentCategory", currentCategory);
        List<Category> subCategoryList = categoryService.getSubCategoryByPid(id); // 获取当前分类的子分类
        dataMap.put("currentSubCategory", subCategoryList);
        return BaseReqVo.ok(dataMap);
    }


}
