package com.springmall.controller;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Goods;
import com.springmall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        goodsData.put("total", 50);
        goodsData.put("items", goods);

        baseReqVo.setData(goodsData);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }


    @RequestMapping("delete")
    public BaseReqVo deleteGoods(@RequestBody Goods goods) {
        BaseReqVo baseReqVo = new BaseReqVo();
        goodsService.deleteGoods(goods);
        baseReqVo.setErrmsg("成功");
        baseReqVo.setErrno(0);
        return baseReqVo;
    }
}
