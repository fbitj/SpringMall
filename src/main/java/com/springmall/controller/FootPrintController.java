package com.springmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Footprint;
import com.springmall.service.FootPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理——会员足迹——controller
 */
@Controller
@RequestMapping("admin")
public class FootPrintController {
    @Autowired
    FootPrintService footPrintService;

    @RequestMapping("footprint/list")
    @ResponseBody
    public BaseReqVo<Map<String, Object>> getFootPrintList(Integer page, Integer limit, String add_time, String order, Integer userId, Integer goodsId) {
        BaseReqVo<Map<String, Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(page, limit);
        List<Footprint> footprintList = footPrintService.queryFootPrintList(userId, goodsId);
        //分頁
        PageInfo<Footprint> footprintPageInfo=new PageInfo<>(footprintList);
        map.put("total", footprintList.size());
        map.put("items", footprintList);
        mapBaseReqVo.setErrno(0);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        return mapBaseReqVo;
    }
}
