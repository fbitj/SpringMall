package com.springmall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.service.BrandService;
import com.springmall.service.CategoryService;
import com.springmall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.System;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("admin/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    //  /admin/goods/list?page=1&limit=20&sort=add_time&order=desc
    //  /admin/goods/list?page=1&limit=20&goodsSn=727616&name=1&sort=add_time&order=desc

    /**
     * 分页查询所有商品
     *
     * @param page  当前页数
     * @param limit 每页显示商品的数量
     * @param sort  通过那个字段排序
     * @param order 排序规则
     * @return 返回json
     */
    @RequestMapping("list")
    public BaseReqVo showGoodsByPage(String page, String limit, String goodsSn, String name, String sort, String order) {
        BaseReqVo baseReqVo = new BaseReqVo();
        //1、数据库查询（分页查询）
        List<Goods> goods = goodsService.queryGoodsByPage(page, limit, goodsSn, name, sort, order);
//        List<Goods> goods = null;
        PageInfo<Goods> goodsInfo = new PageInfo<>(goods);
        long total = goodsInfo.getTotal();
        //2、结果封装并返回
        HashMap<String, Object> goodsData = new HashMap<>();
        goodsData.put("total", total);
        goodsData.put("items", goods);

        baseReqVo.setData(goodsData);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    /**
     * 逻辑上删除商品==============是否逻辑删除关联表？===============
     * @param goods
     * @return
     */
    @RequestMapping("delete")
    public BaseReqVo deleteGoods(@RequestBody Goods goods) {
        BaseReqVo baseReqVo = new BaseReqVo();
        goodsService.deleteGoods(goods);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    /**
     * 查询商品详情
     * @param id
     */
    @RequestMapping("detail")
    public BaseReqVo goodsDetail(int id){
        BaseReqVo baseReqVo = new BaseReqVo();
        // 根据商品id查询商品详情并返回
        GoodInfoData goodInfoData = goodsService.queryGoodsDetailByGoodsId(id);

        baseReqVo.setData(goodInfoData);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


    @Autowired
    CategoryService categoryService;
    @Autowired
    BrandService brandService;
    /**
     * 所有分类信息和所有的品牌商的简略信息
     * @return
     */
    @RequestMapping("catAndBrand")
    public BaseReqVo AllBrand(){
        // 1.查询商品一级的分类信息
        List<CatAndBrandReqVo> catAndBrandReqVos = categoryService.queryAllCategoryByLevel();

        // 2.查询所有的品牌信息
        List brands = brandService.queryAllBrandSimpleInfo();

        //
        HashMap<String, Object> dateMap = new HashMap<>();
        dateMap.put("categoryList",catAndBrandReqVos);
        dateMap.put("brandList",brands);
        // 封装返回数据
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setData(dateMap);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

    /**
     * 商品上架
     * 问题：怎样阿静前端的json解析为多个bean？？？
     * @return
     */
    @RequestMapping("create")
    public BaseReqVo createGoods(@RequestBody CreateGoods createGoods){
        BaseReqVo baseReqVo = new BaseReqVo();
        int res = goodsService.createGoods(createGoods.getGoods(),createGoods.getSpecifications(),createGoods.getProducts(),createGoods.getAttributes());
        if (res == 1){
            baseReqVo.setErrmsg("成功");
            baseReqVo.setErrno(0);
        }else {
            baseReqVo.setErrmsg("失败");
            baseReqVo.setErrno(605);
        }
        return baseReqVo;
    }

    @RequestMapping("update")
    public BaseReqVo updateGoods(@RequestBody CreateGoods createGoods){
        goodsService.updateGoodsInfo(createGoods.getGoods(),createGoods.getSpecifications(),createGoods.getProducts(),createGoods.getAttributes());
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }

}
