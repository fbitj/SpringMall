package com.springmall.controller.weixin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.*;
import com.springmall.service.CategoryService;
import com.springmall.service.FootPrintService;
import com.springmall.service.GoodsService;
import com.springmall.service.SearchHistoryService;
import com.springmall.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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

    @Autowired
    FootPrintService footPrintService;

    @Autowired
    SearchHistoryService searchHistoryService;

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
        if (request.getCategoryId() == null || (request.getCategoryId() == 0 && request.getIsNew() == null)) {
            //若类目id为0且不是新品或者不存在则根据商品的类目查询
            //为热点商品时也根据商品类目查询
            categoryId = new ArrayList();
            for (Goods good : goods) {
                categoryId.add(good.getCategoryId());
            }

        }

        if (request.getIsNew() != null) {
            categoryId = queryGoodsByParm(request);
        }

        if (request.getIsHot() != null) {
            //查询所有热点商品
            categoryId = queryGoodsByParm(request);
        }

        String keyword = request.getKeyword();
        //若keyword存在则需要为用户添加搜索历史
        if (keyword != null) {
            //判断是否有用户登录
            User principal = (User) SecurityUtils.getSubject().getPrincipal();
            if (principal != null) {
                int i = searchHistoryService.addUserSearchHistory(principal.getId(), keyword);
                if (i == 0) return ResultUtil.fail(402, "服务器繁忙,请登陆后重试");
            }
            //查询关键词搜出来的商品
           categoryId = queryGoodsByParm(request);
        }

        //否则查询所有二级类目
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
        Goods good = goodsService.selectGoodsById(id);
        List<Goods> goods = goodsService.selectGoodsInSameCategory(good.getCategoryId());
        if (goods.size() > 6) {
            goods = goods.subList(0, 6);
        }
        Map map = new HashMap();
        map.put("goodsList", goods);
        return ResultUtil.success(map);
    }

    /**
     * 显示商品详情
     * @param id
     * @return
     */
    @RequestMapping("detail")
    public BaseReqVo goodsDetail(Integer id) throws JsonProcessingException {
        Map result = goodsService.selectGoodsDetailById(id);
        //为用户添加商品足迹
        Subject subject = SecurityUtils.getSubject();
        User principal = (User) subject.getPrincipal();
        if (principal != null) {
            //为用户添加足迹
            footPrintService.addUserFoot(id, principal.getId());
        }
        return ResultUtil.success(result);
    }

    /**
     * 查询符合条件的商品
     * @param request
     * @return
     */
    private List queryGoodsByParm(PageRequest request) {
        request.setCategoryId(0);
        List<Goods> keyGoods = goodsService.goodsListInCurrentCategory(request);
        List categoryId = new ArrayList();
        for (Goods good : keyGoods) {
            categoryId.add(good.getCategoryId());
        }
        return categoryId;
    }
}
