package com.springmall.service;

import com.springmall.bean.*;
import com.springmall.bean.System;
import com.springmall.mapper.SystemMapper;
import com.springmall.utils.RegexUtil;
import com.springmall.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    SystemMapper systemMapper;

    /**
     * 获取商场配置信息
     * @return 现商场配置信息对象
     */
    @Override
    public MallConfig queryMallConfig () {
        SystemExample systemExample = new SystemExample();
        systemExample.createCriteria().andKeyNameLike("cskaoyan_mall_mall%");
        List<System> systemList = systemMapper.selectByExample(systemExample);
        MallConfig mallConfig = new MallConfig();
        for (System system : systemList) {
            if ("cskaoyan_mall_mall_name".equals(system.getKeyName())) {
                mallConfig.setLitemall_mall_name(system.getKeyValue());
            }
            if ("cskaoyan_mall_mall_address".equals(system.getKeyName())) {
                mallConfig.setLitemall_mall_address(system.getKeyValue());
            }
            if ("cskaoyan_mall_mall_phone".equals(system.getKeyName())) {
                mallConfig.setLitemall_mall_phone(system.getKeyValue());
            }
            if ("cskaoyan_mall_mall_qq".equals(system.getKeyName())) {
                mallConfig.setLitemall_mall_qq(system.getKeyValue());
            }
        }
        return mallConfig;
    }

    /**
     * 更新商场配置信息
     * @param paramMapList
     * @return
     * -1表示服务器繁忙，
     * 0表示更新失败，
     * 1表示更新成功，
     * 2表示更新条件输入不完整。
     * 3表示商场电话不正确，既不是手机号也不是座机号。
     */
    @Override
    public int updateMallConfig(List<Map<String,String>> paramMapList) {
        for(Map<String,String> paramMap : paramMapList){
            String paramName = paramMap.get("paramName");
            String paramValue = paramMap.get("paramValue");
            if(StringUtils.isEmpty(paramValue)){    //修改条件不完整
                return 2;
            }
            if ("cskaoyan_mall_mall_phone".equals(paramName)){
                if (!(RegexUtil.checkMobilePhone(paramValue)) && (!RegexUtil.checkTelephone(paramValue))){
                    return 3;
                }
            }
            System system = new System();    //set更新数据
            system.setKeyName(paramName);
            system.setKeyValue(paramValue);
            SystemExample systemExample = new SystemExample();
            systemExample.createCriteria().andKeyNameEqualTo(paramName);    //添加查找条件，根据表中key_name字段的值找到该行数据。
            int result = 0;
            if(systemMapper.countByExample(systemExample)==0){    //表中无该配置的相关数据，需插入一条配置信息。
                result = systemMapper.insertSelective(system);
                if (result==0){
                    return result;    //foreach中有一者插入失败，则提前return。
                }
            }
            else{    //表中有该配置的相关数据，更新该条配置信息。
                result = systemMapper.updateByExampleSelective(system,systemExample);
                if (result==0){
                    return result;    //foreach中有一者更新失败，则提前return。
                }
            }
        }
        return 1;    //更新成功
    }

    /**
     * 获取运费配置信息
     * @return 现运费配置信息对象
     */
    @Override
    public ExpressConfig queryExpressConfig() {
        SystemExample systemExample = new SystemExample();
        systemExample.createCriteria().andKeyNameLike("cskaoyan_mall_express%");
        List<System> systemList = systemMapper.selectByExample(systemExample);
        ExpressConfig expressConfig = new ExpressConfig();
        for (System system : systemList) {
            if ("cskaoyan_mall_express_freight_min".equals(system.getKeyName())) {
                expressConfig.setLitemall_express_freight_min(system.getKeyValue());
            }
            if ("cskaoyan_mall_express_freight_value".equals(system.getKeyName())) {
                expressConfig.setLitemall_express_freight_value(system.getKeyValue());
            }
        }
        return expressConfig;
    }

    /**
     * 更新运费配置信息
     * @param paramMapList
     * @return
     * -1表示服务器繁忙，
     * 0表示更新失败，
     * 1表示更新成功，
     * 2表示更新条件输入不完整，
     * 3表示更新参数存在不为正数的值，
     * 4表示更新参数中存在非数字。
     */
    @Override
    public int updateExpressConfig(List<Map<String,String>> paramMapList) {
        for(Map<String,String> paramMap : paramMapList){
            String paramName = paramMap.get("paramName");
            String paramValue = paramMap.get("paramValue");
            if(StringUtils.isEmpty(paramValue)){    //修改条件不完整
                return 2;
            }
            try{
                if (Double.parseDouble(paramValue)<=0){    //参数需大于0
                    return 3;
                }
            }catch (Exception e){    //类型转换失败，存在非数字。
                return 4;
            }
            System system = new System();    //set更新数据
            system.setKeyName(paramName);
            system.setKeyValue(paramValue);
            SystemExample systemExample = new SystemExample();
            systemExample.createCriteria().andKeyNameEqualTo(paramName);    //添加查找条件，根据表中key_name字段的值找到该行数据。
            int result = 0;
            if(systemMapper.countByExample(systemExample)==0){    //表中无该配置的相关数据，需插入一条配置信息。
                result = systemMapper.insertSelective(system);
                if (result==0){
                    return result;    //foreach中有一者插入失败，则提前return。
                }
            }
            else{    //表中有该配置的相关数据，更新该条配置信息。
                result = systemMapper.updateByExampleSelective(system,systemExample);
                if (result==0){
                    return result;    //foreach中有一者更新失败，则提前return。
                }
            }
        }
        return 1;    //更新成功
    }

    /**
     * 获取订单配置信息
     * @return 现订单配置信息对象
     */
    @Override
    public OrderConfig queryOrderConfig() {
        SystemExample systemExample = new SystemExample();
        systemExample.createCriteria().andKeyNameLike("cskaoyan_mall_order%");
        List<System> systemList = systemMapper.selectByExample(systemExample);
        OrderConfig orderConfig = new OrderConfig();
        for (System system : systemList) {
            if ("cskaoyan_mall_order_comment".equals(system.getKeyName())) {
                orderConfig.setLitemall_order_comment(system.getKeyValue());
            }
            if ("cskaoyan_mall_order_unpaid".equals(system.getKeyName())) {
                orderConfig.setLitemall_order_unpaid(system.getKeyValue());
            }
            if ("cskaoyan_mall_order_unconfirm".equals(system.getKeyName())) {
                orderConfig.setLitemall_order_unconfirm(system.getKeyValue());
            }
        }
        return orderConfig;
    }

    /**
     * 更新订单配置信息
     * @param paramMapList
     * @return
     * -1表示服务器繁忙，
     * 0表示更新失败，
     * 1表示更新成功，
     * 2表示更新条件输入不完整，
     * 3表示更新参数存在不为正数的值，
     * 4表示更新参数中存在非整数。
     */
    @Override
    public int updateOrderConfig(List<Map<String,String>> paramMapList) {
        for(Map<String,String> paramMap : paramMapList){
            String paramName = paramMap.get("paramName");
            String paramValue = paramMap.get("paramValue");
            if(StringUtils.isEmpty(paramValue)){    //修改条件不完整
                return 2;
            }
            try{
                if (Integer.parseInt(paramValue)<=0){    //参数需大于0
                    return 3;
                }
            }catch (Exception e){    //类型转换失败，存在非整数。
                return 4;
            }
            System system = new System();    //set更新数据
            system.setKeyName(paramName);
            system.setKeyValue(paramValue);
            SystemExample systemExample = new SystemExample();
            systemExample.createCriteria().andKeyNameEqualTo(paramName);    //添加查找条件，根据表中key_name字段的值找到该行数据。
            int result = 0;
            if(systemMapper.countByExample(systemExample)==0){    //表中无该配置的相关数据，需插入一条配置信息。
                result = systemMapper.insertSelective(system);
                if (result==0){
                    return result;    //foreach中有一者插入失败，则提前return。
                }
            }
            else{    //表中有该配置的相关数据，更新该条配置信息。
                result = systemMapper.updateByExampleSelective(system,systemExample);
                if (result==0){
                    return result;    //foreach中有一者更新失败，则提前return。
                }
            }
        }
        return 1;    //更新成功
    }

    /**
     * 获取微信小程序配置信息
     * @return 现微信小程序配置信息对象
     */
    @Override
    public WxConfig queryWxConfig() {
        SystemExample systemExample = new SystemExample();
        systemExample.createCriteria().andKeyNameLike("cskaoyan_mall_wx%");
        List<System> systemList = systemMapper.selectByExample(systemExample);
        WxConfig wxConfig = new WxConfig();
        for (System system : systemList) {
            if ("cskaoyan_mall_wx_index_new".equals(system.getKeyName())) {
                wxConfig.setLitemall_wx_index_new(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_catlog_goods".equals(system.getKeyName())) {
                wxConfig.setLitemall_wx_catlog_goods(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_catlog_list".equals(system.getKeyName())) {
                wxConfig.setLitemall_wx_catlog_list(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_share".equals(system.getKeyName())) {
                wxConfig.setLitemall_wx_share(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_index_brand".equals(system.getKeyName())) {
                wxConfig.setLitemall_wx_index_brand(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_index_hot".equals(system.getKeyName())) {
                wxConfig.setLitemall_wx_index_hot(system.getKeyValue());
            }
            if ("cskaoyan_mall_wx_index_topic".equals(system.getKeyName())) {
                wxConfig.setLitemall_wx_index_topic(system.getKeyValue());
            }
        }
        return wxConfig;
    }

    /**
     * 更新微信小程序配置信息
     * @param paramMapList
     * @return
     * -1表示服务器繁忙，
     * 0表示更新失败，
     * 1表示更新成功，
     * 2表示更新条件输入不完整，
     * 3表示更新参数中除去商品分享功能开关，其余的参数存在负数值，
     * 4表示更新参数中存在非整数。
     */
    @Override
    public int updateWxConfig(List<Map<String,String>> paramMapList) {
        for(Map<String,String> paramMap : paramMapList){
            String paramName = paramMap.get("paramName");
            String paramValue = paramMap.get("paramValue");
            if(StringUtils.isEmpty(paramValue)){    //修改条件不完整
                return 2;
            }
            if (!"cskaoyan_mall_wx_share".equals(paramName)){
                try{
                    if (Integer.parseInt(paramValue)<0){    //除去商品分享功能开关，其余的参数需大于等于0。
                        return 3;
                    }
                }catch (Exception e){    //类型转换失败，存在非整数。
                    return 4;
                }
            }
            System system = new System();    //set更新数据
            system.setKeyName(paramName);
            system.setKeyValue(paramValue);
            SystemExample systemExample = new SystemExample();
            systemExample.createCriteria().andKeyNameEqualTo(paramName);    //添加查找条件，根据表中key_name字段的值找到该行数据。
            int result = 0;
            if(systemMapper.countByExample(systemExample)==0){    //表中无该配置的相关数据，需插入一条配置信息。
                result = systemMapper.insertSelective(system);
                if (result==0){
                    return result;    //foreach中有一者插入失败，则提前return。
                }
            }
            else{    //表中有该配置的相关数据，更新该条配置信息。
                result = systemMapper.updateByExampleSelective(system,systemExample);
                if (result==0){
                    return result;    //foreach中有一者更新失败，则提前return。
                }
            }
        }
        return 1;    //更新成功
    }
}
