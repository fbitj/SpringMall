package com.springmall.controller.admincontroller;

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
    public BaseRespVo showGoodsByPage(String page, String limit, String goodsSn, String name, String sort, String order) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        //1、数据库查询（分页查询）
        List<Goods> goods = goodsService.queryGoodsByPage(page, limit, goodsSn, name, sort, order);
//        List<Goods> goods = null;
        PageInfo<Goods> goodsInfo = new PageInfo<>(goods);
        long total = goodsInfo.getTotal();
        //2、结果封装并返回
        HashMap<String, Object> goodsData = new HashMap<>();
        goodsData.put("total", total);
        goodsData.put("items", goods);

        BaseRespVo.setData(goodsData);
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setErrno(0);
        return BaseRespVo;
    }

    /**
     * 逻辑上删除商品==============是否逻辑删除关联表？===============
     * @param goods
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo deleteGoods(@RequestBody Goods goods) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        goodsService.deleteGoods(goods);
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setErrno(0);
        return BaseRespVo;
    }

    /**
     * 查询商品详情
     * @param id
     */
    @RequestMapping("detail")
    public BaseRespVo goodsDetail(int id){
        BaseRespVo BaseRespVo = new BaseRespVo();
        // 根据商品id查询商品详情并返回
        GoodInfoData goodInfoData = goodsService.queryGoodsDetailByGoodsId(id);

        BaseRespVo.setData(goodInfoData);
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setErrno(0);
        return BaseRespVo;
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
    public BaseRespVo AllBrand(){
        // 1.查询商品一级的分类信息
        List<CatAndBrandReqVo> catAndBrandReqVos = categoryService.queryAllCategoryByLevel();

        // 2.查询所有的品牌信息
        List brands = brandService.queryAllBrandSimpleInfo();

        //
        HashMap<String, Object> dateMap = new HashMap<>();
        dateMap.put("categoryList",catAndBrandReqVos);
        dateMap.put("brandList",brands);
        // 封装返回数据
        BaseRespVo BaseRespVo = new BaseRespVo();
        BaseRespVo.setData(dateMap);
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setErrno(0);
        return BaseRespVo;
    }

    /**
     * 商品上架
     * 问题：怎样阿静前端的json解析为多个bean？？？
     * @return
     */
    @RequestMapping("create")
    public BaseRespVo createGoods(@RequestBody CreateGoods createGoods){
        BaseRespVo BaseRespVo = new BaseRespVo();
        int res = goodsService.createGoods(createGoods.getGoods(),createGoods.getSpecifications(),createGoods.getProducts(),createGoods.getAttributes());
        if (res == 1){
            BaseRespVo.setErrmsg("成功");
            BaseRespVo.setErrno(0);
        }else {
            BaseRespVo.setErrmsg("失败");
            BaseRespVo.setErrno(605);
        }
        return BaseRespVo;
    }

    @RequestMapping("update")
    public BaseRespVo updateGoods(@RequestBody CreateGoods createGoods){
        goodsService.updateGoodsInfo(createGoods.getGoods(),createGoods.getSpecifications(),createGoods.getProducts(),createGoods.getAttributes());
        BaseRespVo BaseRespVo = new BaseRespVo();
        BaseRespVo.setErrmsg("成功");
        BaseRespVo.setErrno(0);
        return BaseRespVo;
    }

}
