package com.springmall.controller.admincontroller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.BaseRespVo;
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
    public BaseRespVo<Map<String, Object>> getFootPrintList(Integer page, Integer limit, String add_time, String order, Integer userId, Integer goodsId) {
        BaseRespVo<Map<String, Object>> mapBaseRespVo = new BaseRespVo<>();
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(page, limit);
        List<Footprint> footprintList = footPrintService.queryFootPrintList(userId, goodsId);
        //分頁
        PageInfo<Footprint> footprintPageInfo=new PageInfo<>(footprintList);
        map.put("total", footprintList.size());
        map.put("items", footprintList);
        mapBaseRespVo.setErrno(0);
        mapBaseRespVo.setData(map);
        mapBaseRespVo.setErrmsg("成功");
        return mapBaseRespVo;
    }
}
