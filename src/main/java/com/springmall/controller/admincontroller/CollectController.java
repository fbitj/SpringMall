package com.springmall.controller.admincontroller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.BaseRespVo;
import com.springmall.bean.Collect;
import com.springmall.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理——会员收藏——controller
 */
@RestController
@RequestMapping("admin")
public class CollectController {
    @Autowired
    CollectService collectService;

    @RequestMapping("collect/list")
    public BaseRespVo<Map<String, Object>> getCollectList(Integer page, Integer limit, Integer userId, Integer valueId, String sort, String order) {
        BaseRespVo<Map<String, Object>> mapBaseRespVo = new BaseRespVo<>();
        PageHelper.startPage(page, limit);
        HashMap<String, Object> map = new HashMap<>();
        List<Collect> collects = collectService.queryCollectList(userId, valueId);
        PageInfo<Collect> collectPageInfo = new PageInfo<>(collects);
        long total = collectPageInfo.getTotal();
        map.put("total", total);
        map.put("items", collects);
        mapBaseRespVo.setErrno(0);
        mapBaseRespVo.setData(map);
        mapBaseRespVo.setErrmsg("成功");
        return mapBaseRespVo;
    }
}
