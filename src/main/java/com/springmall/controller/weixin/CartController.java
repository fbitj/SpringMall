package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Cart;
import com.springmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/cart")
public class CartController {

    @Autowired
    CartService cartService;
    //获取购物车的数据
    @RequestMapping("index")
    public BaseReqVo indexCart() {
        Map<String, Object> map = cartService.cartTotal();
        if (map != null) {
            return BaseReqVo.ok(map);
    }
    return BaseReqVo.error(404,"请先登录");
    }

    // 添加商品到购物车
    @RequestMapping("add")
    public BaseReqVo addCart(@RequestBody Cart cart) {
        int goodsCount = cartService.addCart(cart);
        if(goodsCount != 0) {
            return BaseReqVo.ok(goodsCount);
        }
        return BaseReqVo.error(404,"请先登录");
    }
    // 立即购买商品
    @RequestMapping("fastadd")
    public BaseReqVo fastAddCart(@RequestBody Cart cart) {
        int cartId = cartService.fastAddCart(cart);
        if(cartId != 0) {
            return BaseReqVo.ok(cartId);
        }
        return BaseReqVo.error(404,"请先登录");
    }

    // 删除购物车的商品
    @RequestMapping("delete")
    public BaseReqVo deleteCart(@RequestBody Map<String,List<Integer>> mapPid) {
        List<Integer> productIds = mapPid.get("productIds");
        Map<String, Object> map = cartService.deleteCart(productIds);
        if(map != null) {
            return BaseReqVo.ok(map);
        }
        return BaseReqVo.error(404,"请先登录");
    }

    //选择或取消选择
    @RequestMapping("checked")
    public BaseReqVo checkedCart(@RequestBody Map<String,Object> mapReq) {
        int isChecked = (int) mapReq.get("isChecked");
        List<Integer> productIds = (List<Integer>) mapReq.get("productIds");
        boolean checked = false;
        if(isChecked == 1) {
            checked = true;
        }
        Map<String, Object> map = cartService.checkedCart(productIds, checked);
        if(map != null) {
            return BaseReqVo.ok(map);
        }
       return BaseReqVo.error(404, "请先登录");
    }

     //更新购物车的商品(只能改变数量)
    @RequestMapping("update")
    public BaseReqVo checkedCart(@RequestBody Cart cart) {
        int updateCart = cartService.updateCart(cart);
        if(updateCart == 1) {
            return BaseReqVo.ok();
        }
        return BaseReqVo.error(500,"更新失败");
    }

    //获取购物车商品件数（所有商品的总number）
    @RequestMapping("goodscount")
    public BaseReqVo goodsCountCart() {
        int goodsCount = cartService.goodsCountCart();
        return BaseReqVo.ok(goodsCount);
    }

    // 下单前信息确认
    @RequestMapping("checkout")
    public BaseReqVo checkoutCart(Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId) {
        Map<String, Object> map = cartService.checkOutBeforePay(cartId, addressId, couponId, grouponRulesId);
        if(map != null) {
            return BaseReqVo.ok(map);
        }
        return BaseReqVo.error(404, "请先登录");
    }
}
