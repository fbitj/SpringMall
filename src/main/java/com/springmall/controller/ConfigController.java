package com.springmall.controller;

import com.springmall.bean.*;

import com.springmall.service.ExpressConfigService;
import com.springmall.service.MallConfigService;
import com.springmall.service.OrderConfigService;
import com.springmall.service.WxConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理后台系统所有关于config模块下的请求
 * URL：/admin/config/mall
 *      /admin/config/express
 *      /admin/config/order
 *      /admin/config/wx
 */
@RestController
@RequestMapping("admin/config")
public class ConfigController {

    @Autowired
    MallConfigService mallConfigService;
    @Autowired
    ExpressConfigService expressConfigService;
    @Autowired
    OrderConfigService orderConfigService;
    @Autowired
    WxConfigService wxConfigService;

    /**
     * 获取商场配置信息
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"litemall_mall_phone": "10086",
     * 		"litemall_mall_address": "武汉",
     * 		"litemall_mall_name": "光谷店",
     * 		"litemall_mall_qq": "123456789"
     *        },
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "mall", method = RequestMethod.GET)
    public BaseReqVo queryMallConfig(){
        BaseReqVo baseReqVo = new BaseReqVo();
        MallConfig queryMallConfig = mallConfigService.queryMallConfig();
        baseReqVo.setErrno(0);
        baseReqVo.setData(queryMallConfig);
        baseReqVo.setErrmsg("获取商场配置信息成功！");
        return baseReqVo;
    }

    /**
     * 更新商场配置信息
     * @param mallConfig 获取参数：
     * {
     * 	"litemall_mall_phone": "10086",
     * 	"litemall_mall_address": "武汉",
     * 	"litemall_mall_name": "光谷店",
     * 	"litemall_mall_qq": "123456789"
     * }
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "mall", method = RequestMethod.POST)
    public BaseReqVo updateMallConfig(@RequestBody MallConfig mallConfig){
        BaseReqVo baseReqVo = new BaseReqVo();
        int result = mallConfigService.updateMallConfig(mallConfig);
        if(result==1){
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("修改商场配置信息成功！");
            return baseReqVo;
        }
        baseReqVo.setErrno(605);
        if(result==2){
            baseReqVo.setErrmsg("请输入完整的修改信息！");
            return baseReqVo;
        }
        baseReqVo.setErrmsg("修改商场配置信息失败！");
        return baseReqVo;
    }

    /**
     * 获取运费配置信息
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"litemall_express_freight_min": "58",
     * 		"litemall_express_freight_value": "9"
     *        },
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "express", method = RequestMethod.GET)
    public BaseReqVo queryExpressConfig() {
        BaseReqVo baseReqVo = new BaseReqVo();
        ExpressConfig queryExpressConfig = expressConfigService.queryExpressConfig();
        baseReqVo.setErrno(0);
        baseReqVo.setData(queryExpressConfig);
        baseReqVo.setErrmsg("获取运费配置信息成功！");
        return baseReqVo;
    }

    /**
     * 更新运费配置信息
     * @param expressConfig 获取参数：
     * {
     * 	"litemall_mall_phone": "10086",
     * 	"litemall_mall_address": "武汉",
     * 	"litemall_mall_name": "光谷店",
     * 	"litemall_mall_qq": "123456789"
     * }
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "express", method = RequestMethod.POST)
    public BaseReqVo updateExpressConfig(@RequestBody ExpressConfig expressConfig) {
        BaseReqVo baseReqVo = new BaseReqVo();
        int result = expressConfigService.updateExpressConfig(expressConfig);
        if(result==1) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("修改运费配置信息成功！");
            return baseReqVo;
        }
        baseReqVo.setErrno(605);
        if(result==2){
            baseReqVo.setErrmsg("请输入完整的修改信息！");
            return baseReqVo;
        }
        if(result==3){
            baseReqVo.setErrmsg("运费满减所需最低消费需大于0！");
            return baseReqVo;
        }
        if(result==4){
            baseReqVo.setErrmsg("运费满减不足所需运费需大于0！");
            return baseReqVo;
        }
        if(result==5){
            baseReqVo.setErrmsg("参数需输入数字！");
            return baseReqVo;
        }
        baseReqVo.setErrmsg("修改运费配置信息失败！");
        return baseReqVo;
    }

    /**
     * 获取订单配置信息
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"litemall_order_comment": "6",
     * 		"litemall_order_unpaid": "450",
     * 		"litemall_order_unconfirm": "6"
     *        },
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "order", method = RequestMethod.GET)
    public BaseReqVo queryOrderConfig() {
        BaseReqVo baseReqVo = new BaseReqVo();
        OrderConfig queryOrderConfig = orderConfigService.queryOrderConfig();
        baseReqVo.setErrno(0);
        baseReqVo.setData(queryOrderConfig);
        baseReqVo.setErrmsg("获取订单配置信息成功！");
        return baseReqVo;
    }

    /**
     * 更新订单配置信息
     * @param orderConfig 获取参数：
     * {
     * 	"litemall_order_comment": "88888",
     * 	"litemall_order_unpaid": "11",
     * 	"litemall_order_unconfirm": "454545"
     * }
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "order", method = RequestMethod.POST)
    public BaseReqVo updateOrderConfig(@RequestBody OrderConfig orderConfig) {
        BaseReqVo baseReqVo = new BaseReqVo();
        int result = orderConfigService.updateOrderConfig(orderConfig);
        if(result==1) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("修改订单配置信息成功！");
            return baseReqVo;
        }
        baseReqVo.setErrno(605);
        if(result==2){
            baseReqVo.setErrmsg("请输入完整的修改信息！");
            return baseReqVo;
        }
        if(result==3){
            baseReqVo.setErrmsg("确认收货后超期天数需大于0！");
            return baseReqVo;
        }
        if(result==4){
            baseReqVo.setErrmsg("用户下单后超时分钟数需大于0！");
            return baseReqVo;
        }
        if(result==5){
            baseReqVo.setErrmsg("订单发货后超期天数需大于0！");
            return baseReqVo;
        }
        if(result==6){
            baseReqVo.setErrmsg("参数需输入整数！");
            return baseReqVo;
        }
        baseReqVo.setErrmsg("修改订单配置信息失败！");
        return baseReqVo;
    }

    /**
     * 获取微信小程序配置信息
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"data": {
     * 		"litemall_wx_index_new": "10",
     * 		"litemall_wx_catlog_goods": "4",
     * 		"litemall_wx_catlog_list": "4",
     * 		"litemall_wx_share": "true",
     * 		"litemall_wx_index_brand": "4",
     * 		"litemall_wx_index_hot": "4",
     * 		"litemall_wx_index_topic": "4"
     *        },
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "wx", method = RequestMethod.GET)
    public BaseReqVo queryWxConfig() {
        BaseReqVo baseReqVo = new BaseReqVo();
        WxConfig queryWxConfig = wxConfigService.queryWxConfig();
        baseReqVo.setErrno(0);
        baseReqVo.setData(queryWxConfig);
        baseReqVo.setErrmsg("获取微信小程序配置信息成功！");
        return baseReqVo;
    }

    /**
     * 更新微信小程序配置信息
     * @param wxConfig 获取参数：
     * {
     * 	"litemall_wx_index_new": "10",
     * 	"litemall_wx_catlog_goods": "787899",
     * 	"litemall_wx_catlog_list": "6",
     * 	"litemall_wx_share": "false",
     * 	"litemall_wx_index_brand": "4",
     * 	"litemall_wx_index_hot": "51",
     * 	"litemall_wx_index_topic": "6"
     * }
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public BaseReqVo updateWxConfig(@RequestBody WxConfig wxConfig) {
        BaseReqVo baseReqVo = new BaseReqVo();
        int result = wxConfigService.updateWxConfig(wxConfig);
        if(result==1) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("修改微信小程序配置信息成功！");
            return baseReqVo;
        }
        baseReqVo.setErrno(605);
        if(result==2){
            baseReqVo.setErrmsg("请输入完整的修改信息！");
            return baseReqVo;
        }
        if(result==3){
            baseReqVo.setErrmsg("新品首发栏目商品显示数量不得为负数！");
            return baseReqVo;
        }
        if(result==4){
            baseReqVo.setErrmsg("分类栏目商品显示数量不得为负数！");
            return baseReqVo;
        }
        if(result==5){
            baseReqVo.setErrmsg("分类栏目显示数量不得为负数！");
            return baseReqVo;
        }
        if(result==6){
            baseReqVo.setErrmsg("品牌制造商直供栏目品牌商显示数量不得为负数！");
            return baseReqVo;
        }
        if(result==7){
            baseReqVo.setErrmsg("人气推荐栏目商品显示数量不得为负数！");
            return baseReqVo;
        }
        if(result==8){
            baseReqVo.setErrmsg("专题精选栏目显示数量不得为负数！");
            return baseReqVo;
        }
        if(result==9){
            baseReqVo.setErrmsg("参数需输入整数！");
            return baseReqVo;
        }
        baseReqVo.setErrmsg("修改微信小程序配置信息失败！");
        return baseReqVo;
    }
}
