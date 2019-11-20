package com.springmall.controller.weixin;

import com.github.pagehelper.PageInfo;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Category;
import com.springmall.bean.Goods;
import com.springmall.bean.PageRequest;
import com.springmall.service.CategoryService;
import com.springmall.service.GoodsService;
import com.springmall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    CategoryService categoryService;

    /**
     * 获取总的商品数量
     * @return
     */
    @RequestMapping("count")
    public BaseReqVo goodsCount() {
        long count = goodsService.queryTotalGoods();
        Map<String,Long> data = new HashMap<>();
        data.put("goodsCount", count);
        return ResultUtil.success(data);
    }

    /**
     * 根据当前类目id获取当前类，父类和兄弟类的信息
     * @param id
     * @return
     */
    @RequestMapping("category")
    public BaseReqVo categoryMessage(Integer id) {
        Map<String,Object> result = categoryService.queryCategoryById(id);
        return ResultUtil.success(result);
    }

    /**
     * 若categoryId不为0获取当前类目下的商品信息和所有二级类目信息
     * 为0则搜索关键词
     * @param
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo goodsListInCurrentCategory(PageRequest request) {
        /*//查询商品信息
        List<Goods> goods = goodsService.goodsListInCurrentCategory(categoryId, page, size);

        //查询二级类目信息
        List<Category> categories = categoryService.queryCategoryByL2();

        //组装报文
        Map map = new HashMap();
        map.put("count", goods.size());
        map.put("filterCategoryList", categories);
        map.put("goodsList", goods);*/
        //查询商品信息
        List<Goods> goods = goodsService.goodsListInCurrentCategory(request);

        //查询二级类目信息
        List categoryId = null;
        if (request.getCategoryId() == null || request.getCategoryId() == 0) {
            //若类目id为0或者不存在则根据商品的类目查询
            categoryId = new ArrayList();
            for (Goods good : goods) {
                categoryId.add(good.getCategoryId());
            }

        }
        List<Category> categories = categoryService.queryCategoryByL2(categoryId);

        //封装信息
        PageInfo<Goods> info = new PageInfo<>(goods);
        Map map = new HashMap();
        map.put("count",info.getTotal());
        map.put("filterCategoryList", categories);
        map.put("goodsList", goods);
        return ResultUtil.success(map);
    }

    /**
     * 显示相同类目下的商品信息
     * @param id
     * @return
     */
    @RequestMapping("related")
    public BaseReqVo relatedGoods(Integer id){
        List<Goods> goods = goodsService.selectGoodsInSameCategory(id);
        if (goods.size() > 6) {
            goods.subList(0,6);
        }
        return ResultUtil.success(goods);
    }

    /**
     * 显示商品详情
     * @param id
     * @return
     */
    @RequestMapping("detail")
    public BaseReqVo goodsDetail(Integer id) {
        Map result = goodsService.selectGoodsDetailById(id);
        return ResultUtil.success(result);
    }


}
