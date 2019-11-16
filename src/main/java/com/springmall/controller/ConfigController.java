package com.springmall.controller;

import com.springmall.bean.*;

import com.springmall.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
    SystemService systemService;

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
        MallConfig queryMallConfig = systemService.queryMallConfig();
        baseReqVo.setErrno(0);
        baseReqVo.setData(queryMallConfig);
        baseReqVo.setErrmsg("获取商场配置信息成功！");
        return baseReqVo;
    }

    /**
     * 更新商场配置信息
     * @param litemall_mall_phone 商场联系电话
     * @param litemall_mall_address 商场地址
     * @param litemall_mall_name 商场名称
     * @param litemall_mall_qq 商场联系QQ
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "mall", method = RequestMethod.POST)
    public BaseReqVo updateMallConfig(
            @RequestBody String litemall_mall_phone,
            @RequestBody String litemall_mall_address,
            @RequestBody String litemall_mall_name,
            @RequestBody String litemall_mall_qq
    ){
        Map<String,String> map1 = new HashMap<>();
        Map<String,String> map2 = new HashMap<>();
        Map<String,String> map3 = new HashMap<>();
        Map<String,String> map4 = new HashMap<>();
        map1.put("paramName","cskaoyan_mall_mall_name");
        map1.put("paramValue",litemall_mall_name);
        map2.put("paramName","cskaoyan_mall_mall_address");
        map2.put("paramValue",litemall_mall_address);
        map3.put("paramName","cskaoyan_mall_mall_phone");
        map3.put("paramValue",litemall_mall_phone);
        map4.put("paramName","cskaoyan_mall_mall_qq");
        map4.put("paramValue",litemall_mall_qq);
        List<Map<String,String>> paramMapList = new ArrayList<>();
        paramMapList.add(map1);
        paramMapList.add(map2);
        paramMapList.add(map3);
        paramMapList.add(map4);
        BaseReqVo baseReqVo = new BaseReqVo();
        int result = systemService.updateMallConfig(paramMapList);
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
        ExpressConfig queryExpressConfig = systemService.queryExpressConfig();
        baseReqVo.setErrno(0);
        baseReqVo.setData(queryExpressConfig);
        baseReqVo.setErrmsg("获取运费配置信息成功！");
        return baseReqVo;
    }

    /**
     * 更新运费配置信息
     * @param litemall_express_freight_min 运费满减所需最低消费
     * @param litemall_express_freight_value 运费满减不足所需运费
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "express", method = RequestMethod.POST)
    public BaseReqVo updateExpressConfig(
            @RequestBody String litemall_express_freight_min,
            @RequestBody String litemall_express_freight_value
    ) {
        Map<String,String> map1 = new HashMap<>();
        Map<String,String> map2 = new HashMap<>();
        map1.put("paramName","cskaoyan_mall_express_freight_min");
        map1.put("paramValue",litemall_express_freight_min);
        map2.put("paramName","cskaoyan_mall_express_freight_value");
        map2.put("paramValue",litemall_express_freight_value);
        List<Map<String,String>> paramMapList = new ArrayList<>();
        paramMapList.add(map1);
        paramMapList.add(map2);
        BaseReqVo baseReqVo = new BaseReqVo();
        int result = systemService.updateExpressConfig(paramMapList);
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
            baseReqVo.setErrmsg("参数需大于0！");
            return baseReqVo;
        }
        if(result==4){
            baseReqVo.setErrmsg("参数需输入数值！");
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
        OrderConfig queryOrderConfig = systemService.queryOrderConfig();
        baseReqVo.setErrno(0);
        baseReqVo.setData(queryOrderConfig);
        baseReqVo.setErrmsg("获取订单配置信息成功！");
        return baseReqVo;
    }

    /**
     * 更新订单配置信息
     * @param litemall_order_comment 确认收货后超期天数
     * @param litemall_order_unpaid 用户下单后超时分钟数
     * @param litemall_order_unconfirm 订单发货后超期天数
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "order", method = RequestMethod.POST)
    public BaseReqVo updateOrderConfig(
            @RequestBody String litemall_order_comment,
            @RequestBody String litemall_order_unpaid,
            @RequestBody String litemall_order_unconfirm
    ) {
        Map<String,String> map1 = new HashMap<>();
        Map<String,String> map2 = new HashMap<>();
        Map<String,String> map3 = new HashMap<>();
        map1.put("paramName","cskaoyan_mall_order_comment");
        map1.put("paramValue",litemall_order_comment);
        map2.put("paramName","cskaoyan_mall_order_unpaid");
        map2.put("paramValue",litemall_order_unpaid);
        map3.put("paramName","cskaoyan_mall_order_unconfirm");
        map3.put("paramValue",litemall_order_unconfirm);
        List<Map<String,String>> paramMapList = new ArrayList<>();
        paramMapList.add(map1);
        paramMapList.add(map2);
        paramMapList.add(map3);
        BaseReqVo baseReqVo = new BaseReqVo();
        int result = systemService.updateOrderConfig(paramMapList);
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
            baseReqVo.setErrmsg("参数需大于0！");
            return baseReqVo;
        }
        if(result==4){
            baseReqVo.setErrmsg("参数需输入正整数！");
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
        WxConfig queryWxConfig = systemService.queryWxConfig();
        baseReqVo.setErrno(0);
        baseReqVo.setData(queryWxConfig);
        baseReqVo.setErrmsg("获取微信小程序配置信息成功！");
        return baseReqVo;
    }

    /**
     * 更新微信小程序配置信息
     * @param litemall_wx_index_new 新品首发栏目商品显示数量
     * @param litemall_wx_catlog_goods 分类栏目商品显示数量
     * @param litemall_wx_catlog_list 分类栏目显示数量
     * @param litemall_wx_share 商品分享功能开关
     * @param litemall_wx_index_brand 品牌制造商直供栏目品牌商显示数量
     * @param litemall_wx_index_hot 人气推荐栏目商品显示数量
     * @param litemall_wx_index_topic 专题精选栏目显示数量
     * @return 返回参数：
     * {
     * 	"errno": 0,
     * 	"errmsg": "成功"
     * }
     */
    @RequestMapping(value = "wx", method = RequestMethod.POST)
    public BaseReqVo updateWxConfig(
            @RequestBody String litemall_wx_index_new,
            @RequestBody String litemall_wx_catlog_goods,
            @RequestBody String litemall_wx_catlog_list,
            @RequestBody String litemall_wx_share,
            @RequestBody String litemall_wx_index_brand,
            @RequestBody String litemall_wx_index_hot,
            @RequestBody String litemall_wx_index_topic
    ) {
        Map<String,String> map1 = new HashMap<>();
        Map<String,String> map2 = new HashMap<>();
        Map<String,String> map3 = new HashMap<>();
        Map<String,String> map4 = new HashMap<>();
        Map<String,String> map5 = new HashMap<>();
        Map<String,String> map6 = new HashMap<>();
        Map<String,String> map7 = new HashMap<>();
        map1.put("paramName","cskaoyan_mall_wx_index_new");
        map1.put("paramValue",litemall_wx_index_new);
        map2.put("paramName","cskaoyan_mall_wx_catlog_goods");
        map2.put("paramValue",litemall_wx_catlog_goods);
        map3.put("paramName","cskaoyan_mall_wx_catlog_list");
        map3.put("paramValue",litemall_wx_catlog_list);
        map4.put("paramName","cskaoyan_mall_wx_share");
        map4.put("paramValue",litemall_wx_share);
        map5.put("paramName","cskaoyan_mall_wx_index_brand");
        map5.put("paramValue",litemall_wx_index_brand);
        map6.put("paramName","cskaoyan_mall_wx_index_hot");
        map6.put("paramValue",litemall_wx_index_hot);
        map7.put("paramName","cskaoyan_mall_wx_index_topic");
        map7.put("paramValue",litemall_wx_index_topic);
        List<Map<String,String>> paramMapList = new ArrayList<>();
        paramMapList.add(map1);
        paramMapList.add(map2);
        paramMapList.add(map3);
        paramMapList.add(map4);
        paramMapList.add(map5);
        paramMapList.add(map6);
        paramMapList.add(map7);
        BaseReqVo baseReqVo = new BaseReqVo();
        int result = systemService.updateWxConfig(paramMapList);
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
            baseReqVo.setErrmsg("参数需大于等于0！");
            return baseReqVo;
        }
        if(result==4){
            baseReqVo.setErrmsg("参数需输入非负整数！");
            return baseReqVo;
        }
        baseReqVo.setErrmsg("修改微信小程序配置信息失败！");
        return baseReqVo;
    }
}
