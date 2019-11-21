package com.springmall.utils;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Log3;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/21 14:17
 */

public class LogUtil {

    public static Log3 getLogMessage(BaseReqVo baseReqVo, String uri){
        int status = 0;
        String action = "";
        String result = "";
        if("/admin/collect/list".equals(uri)){
            action = "查询用户收藏";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/feedback/list".equals(uri)){
            action = "查询意见反馈";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/user/list".equals(uri)){
            action = "查询会员";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/footprint/list".equals(uri)){
            action = "查询用户足迹";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/history/list".equals(uri)){
            action = "查询搜索历史";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/address/list".equals(uri)){
            action = "查询收货地址";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/groupon/listRecord".equals(uri)){
            action = "查看团购详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/groupon/update".equals(uri)){
            action = "编辑团购信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/groupon/delete".equals(uri)){
            action = "删除团购信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/groupon/create".equals(uri)){
            action = "添加团购信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/groupon/list".equals(uri)){
            action = "查询团购";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/ad/update".equals(uri)){
            action = "编辑广告信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/ad/read".equals(uri)){
            action = "查看广告详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/ad/delete".equals(uri)){
            action = "删除广告";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/ad/create".equals(uri)){
            action = "添加广告";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/ad/list".equals(uri)){
            action = "查询广告";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/topic/update".equals(uri)){
            action = "编辑专题";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/topic/read".equals(uri)){
            action = "查看专题";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/topic/delete".equals(uri)){
            action = "删除专题";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/topic/create".equals(uri)){
            action = "添加专题";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/topic/list".equals(uri)){
            action = "查询专题";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/coupon/listuser".equals(uri)){
            action = "查询用户优惠券";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/coupon/update".equals(uri)){
            action = "编辑优惠券";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/coupon/read".equals(uri)){
            action = "查看优惠券详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/coupon/delete".equals(uri)){
            action = "删除优惠券";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/coupon/create".equals(uri)){
            action = "添加优惠券";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/coupon/list".equals(uri)){
            action = "查询优惠券";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/config/wx".equals(uri)){
            action = "小程序查看或编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/config/express".equals(uri)){
            action = "运费详情，编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/config/mall".equals(uri)){
            action = "商场详情，编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/config/order".equals(uri)){
            action = "订单详情，编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/index/write".equals(uri)){
            action = "权限测试写";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/index/read".equals(uri)){
            action = "权限测试读";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/stat/user".equals(uri)){
            action = "用户统计";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/stat/order".equals(uri)){
            action = "订单统计";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/stat/goods".equals(uri)){
            action = "商品统计";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/admin/update".equals(uri)){
            action = "编辑管理员";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/admin/read".equals(uri)){
            action = "管理员详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/admin/delete".equals(uri)){
            action = "删除管理员";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/admin/create".equals(uri)){
            action = "添加管理员";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/admin/list".equals(uri)){
            action = "查询管理员";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/log/list".equals(uri)){
            action = "查询日志信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/role/permissions".equals(uri)){
            action = "角色权限变更";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/role/update".equals(uri)){
            action = "编辑角色";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/role/read".equals(uri)){
            action = "角色详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/role/delete".equals(uri)){
            action = "删除角色";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/role/permissions".equals(uri)){
            action = "权限信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/role/create".equals(uri)){
            action = "添加角色";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/role/list".equals(uri)){
            action = "角色查询";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/storage/update".equals(uri)){
            action = "编辑图片";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/storage/read".equals(uri)){
            action = "查看图片详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/storage/delete".equals(uri)){
            action = "删除图片";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/storage/create".equals(uri)){
            action = "上传图片";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/storage/list".equals(uri)){
            action = "查询图片信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/brand/update".equals(uri)){
            action = "品牌编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/brand/read".equals(uri)){
            action = "品牌详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/brand/delete".equals(uri)){
            action = "删除品牌";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/brand/create".equals(uri)){
            action = "添加品牌";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/brand/list".equals(uri)){
            action = "查询品牌信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/order/refund".equals(uri)){
            action = "订单退款";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/order/reply".equals(uri)){
            action = "订单商品回复";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/order/ship".equals(uri)){
            action = "订单发货";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/order/detail".equals(uri)){
            action = "订单详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/order/list".equals(uri)){
            action = "查询订单";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/keyword/update".equals(uri)){
            action = "编辑关键词";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/keyword/read".equals(uri)){
            action = "关键词详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/keyword/delete".equals(uri)){
            action = "删除关键词";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/keyword/create".equals(uri)){
            action = "添加关键词";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/keyword/list".equals(uri)){
            action = "查询关键词";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/category/update".equals(uri)){
            action = "类目编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/category/read".equals(uri)){
            action = "类目详情";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/category/delete".equals(uri)){
            action = "删除类目";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/category/create".equals(uri)){
            action = "添加类目";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/category/list".equals(uri)){
            action = "查询类目信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/issue/update".equals(uri)){
            action = "通用问题编辑";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/issue/delete".equals(uri)){
            action = "通用问题删除";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/issue/create".equals(uri)){
            action = "通用问题添加";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/issue/list".equals(uri)){
            action = "通用问题查询";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/goods/detail".equals(uri)){
            action = "商品详情信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/goods/update".equals(uri)){
            action = "编辑商品信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "编辑失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/goods/delete".equals(uri)){
            action = "删除商品";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/goods/delete".equals(uri)){
            action = "上架商品";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "添加失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/goods/list".equals(uri)){
            action = "查询商品信息";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/comment/delete".equals(uri)){
            action = "删除评论";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "删除失败";
                return new Log3(action,status,result);
            }
        }
        if("/admin/comment/list".equals(uri)){
            action = "查询评论";
            if(baseReqVo.getErrno() == 0){ //成功
                status = 1;
                result = baseReqVo.getErrmsg();
                return new Log3(action,status,result);
            }
            if(baseReqVo.getErrno() != 0){ //失败
                status = 0;
                result = "查询失败";
                return new Log3(action,status,result);
            }
        }
        return null;
    }
}
