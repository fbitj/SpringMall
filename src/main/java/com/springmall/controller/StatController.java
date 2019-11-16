package com.springmall.controller;

import com.alibaba.druid.support.spring.stat.annotation.Stat;
import com.springmall.bean.*;
import com.springmall.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fwj on 2019-11-15.
 */
@Controller
@RequestMapping("admin/stat")
public class StatController {
    @Autowired
    StatService statService;

    /**
     * 统计每天用户增长的数量
     * @return
     */
    @RequestMapping("user")
    @ResponseBody
    public BaseReqVo userStat() {
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        StatInfo statInfo = new StatInfo();  // 统计返回的data类型为自定义的StatInfo类型
        List<String> columnList = new ArrayList<String>(); // 列数据
        columnList.add("day");
        columnList.add("users");
        statInfo.setColumns(columnList); // 添加列数据
        List<UserStat> userStats = statService.userStat(); // 行数据
        statInfo.setRows(userStats); // 添加行数据
        baseReqVo.setData(statInfo);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    /**
     * 查询每天的订单量，下单用户，订单总额，客单价的统计数据
     * @return
     */
    @RequestMapping("order")
    @ResponseBody
    public BaseReqVo userOrder() {
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        StatInfo statInfo = new StatInfo();  // 统计返回的data类型为自定义的StatInfo类型
        List<String> columnList = new ArrayList<String>(); // 列数据
        columnList.add("day");
        columnList.add("orders");
        columnList.add("customers");
        columnList.add("amount");
        columnList.add("pcr");
        statInfo.setColumns(columnList); // 添加列数据
        List<OrderStat> userStats = statService.OrderStat(); // 行数据
        statInfo.setRows(userStats); // 添加行数据
        baseReqVo.setData(statInfo);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }


    /**
     * 查询每天的订单量、下单货品数量、下单货品总额的统计数据
     * @return
     */
    @RequestMapping("goods")
    @ResponseBody
    public BaseReqVo userGoods() {
        BaseReqVo baseReqVo = new BaseReqVo();
        baseReqVo.setErrno(0);
        StatInfo statInfo = new StatInfo();  // 统计返回的data类型为自定义的StatInfo类型
        List<String> columnList = new ArrayList<String>(); // 列数据
        columnList.add("day");
        columnList.add("orders");
        columnList.add("products");
        columnList.add("amount");
        statInfo.setColumns(columnList); // 添加列数据
        List<GoodsStat> userStats = statService.GoodsStat(); // 行数据
        statInfo.setRows(userStats); // 添加行数据
        baseReqVo.setData(statInfo);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

}
