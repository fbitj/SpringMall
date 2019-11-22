package com.springmall.controller.admin;

import com.springmall.bean.*;
import com.springmall.service.GoodsService;
import com.springmall.service.GrouponService;
import com.springmall.utils.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("admin/groupon")
public class GrouponController {

    @Autowired
    GrouponService grouponService;

    @Autowired
    GoodsService goodsService;


    /**
     * 获取团购规则并分页
     * @param request
     * @return
     */
    @RequestMapping("list")
    @RequiresPermissions(value = {"admin:groupon:list"})
    public BaseReqVo showWholesaleRulesByPage(PageRequest request) {
        DataForPage<Groupon_rules> result = grouponService.showWholesaleByPage(request);
        return ResultUtil.success(result);
    }

    /**
     * 删除团购
     * @param rules
     * @return
     */
    @RequestMapping("delete")
    @RequiresPermissions(value = {"admin:groupon:delete"})
    public BaseReqVo deleteWholesaleRulesById(@RequestBody Groupon_rules rules) {
        grouponService.deleteRulesById(rules);
        return ResultUtil.success(null);
    }

    /**
     * 新增团购规则
     * @param rules
     * @return
     */
    @RequestMapping("create")
    @RequiresPermissions(value = {"admin:groupon:create"})
    public BaseReqVo addWholesaleRules(@RequestBody Groupon_rules rules) {
        //查看商品id是否存在
        Goods goods = goodsService.selectGoodsById(rules.getGoodsId());
        if (goods == null) {
            return ResultUtil.fail(402, "商品id不存在");
        }

        //判断参数是否合法
        String illegal = isIllegal(rules);
        if (illegal != null) {
            return ResultUtil.fail(402, illegal);
        }
        //团购折扣不能超过商品价格
        BigDecimal discount = rules.getDiscount();
        BigDecimal retailPrice = goods.getRetailPrice();
        if (discount.compareTo(retailPrice) == 1) {
            return ResultUtil.fail(402, "团购折扣价格不能高于商品本身价格");
        }

        //封装商品相关属性
        rules.setGoodsName(goods.getName());
        rules.setPicUrl(goods.getPicUrl());
        Groupon_rules result = grouponService.create(rules);
        return ResultUtil.success(result);
    }

    /**
     * 更新商品信息
     * @param rules
     * @return
     */
    @RequestMapping("update")
    @RequiresPermissions(value = {"admin:groupon:update"})
    public BaseReqVo update(@RequestBody Groupon_rules rules) {
        //判断参数是否合法
        String illegal = isIllegal(rules);
        if (illegal != null) {
            return ResultUtil.fail(402, illegal);
        }

        //查看商品id是否存在
        Goods goods = goodsService.selectGoodsById(rules.getGoodsId());
        if (goods == null) {
            return ResultUtil.fail(402, "商品id不存在");
        }

        grouponService.update(rules);
        return ResultUtil.success(null);
    }

    /**
     * 显示团购活动信息并分页
     * @param request
     * @return
     */
    @RequestMapping("listRecord")
    @RequiresPermissions(value = {"admin:groupon:read"})
    public BaseReqVo showWholesaleByPage(PageRequest request) {
        DataForPage result = grouponService.showWholesale(request);
        return ResultUtil.success(result);
    }

    /**
     * 判断参数是否合法
     * @param rules
     * @return
     */
    private String isIllegal(Groupon_rules rules) {
        BigDecimal discount = rules.getDiscount();
        Integer discountMember = rules.getDiscountMember();
        Date expireTime = rules.getExpireTime();
        if (rules.getGoodsId() == null) {
            return "请输入合法的商品id";
        }
        if (discount == null || discount.compareTo(BigDecimal.ZERO) != 1) {
            return "团购折扣必须大于0";
        }
        if (discountMember == null || discountMember < 1) {
            return "参团人数最低为2人";
        }
        if (expireTime == null || expireTime.before(new Date())) {
            return "过期时间必须在当前之后";
        }
        return null;
    }
}
