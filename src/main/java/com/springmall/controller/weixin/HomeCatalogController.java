package com.springmall.controller.weixin;

import com.springmall.bean.*;
import com.springmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fwj on 2019-11-20.
 */

/**
 * 主页和分类页面的显示接口
 */
@RestController
public class HomeCatalogController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    CouponService couponService;
    @Autowired
    GrouponService grouponService;
    @Autowired
    TopicService topicService;
    @Autowired
    AdvertiseService advertiseService;
    @Autowired
    BrandService brandService;
    @Autowired
    GoodsService goodsService;

    // 首页显示数据
    @RequestMapping("/wx/home/index")
    @ResponseBody
    public BaseReqVo homeIndex() {
        HomePageData homePageData = new HomePageData();
        //设置首页分类频道数据
        List<Category> categoryList = categoryService.getChannelCatagory();
        homePageData.setChannel(categoryList);
        // 设置首页优惠券
        HashMap<String,Object> dataMap = couponService.queryCouponListByPage("0","4"); // 限制最多显示4张优惠券信息
        List<Coupon> couponInfoList = (List<Coupon>) dataMap.get("data");
        homePageData.setCouponList(couponInfoList);
        // 设置团购列表
        HashMap<String,Object> grouponDataMap = grouponService.queryGrouponListByPage("0", "5");
        List<Groupon> grouponList = (List<Groupon>) grouponDataMap.get("data");
        homePageData.setGrouponList(grouponList);
        // 设置专题列表
//        homePageData.setTopicList();
        // 设置广告横幅
        List<Ad> adList = advertiseService.getAvailAdvertise(6); // 查询指定数目的广告
        homePageData.setBanner(adList);
        // 设置广告列表
        PageRequest brandPageRequest = new PageRequest();
        brandPageRequest.setLimit(4);
        brandPageRequest.setPage(0);
        brandPageRequest.setSize(4);
        Map map = brandService.queryAllBrandByPage(brandPageRequest);
        List<Brand> brandList = (List<Brand>) map.get("brandList");
        homePageData.setBrandList(brandList);
        // 设置热销商品
        List<Goods> hotGoodsList = goodsService.getHotGoods(6);
        homePageData.setHotGoodsList(hotGoodsList);
        // 设置底部商品列表
        ArrayList floorGoodsList = new ArrayList();
        List<Category> categories = categoryService.getChannelCatagory(); //查询一级类目
        for (Category category : categories) {
            HashMap<String, Object> goodsForCategory = new HashMap<String, Object>();
            goodsForCategory.put("id", category.getId());
            goodsForCategory.put("name", category.getName());
            // 根据一级类目的id查询指定数量的商品
            List<Goods> goodsList = categoryService.getFloorGoodsByCategory(category.getId(), 4);
            goodsForCategory.put("goodsList", goodsList);
            floorGoodsList.add(goodsForCategory);
        }
        homePageData.setFloorGoodsList(floorGoodsList);
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
