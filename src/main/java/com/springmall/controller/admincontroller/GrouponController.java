package com.springmall.controller.admincontroller;

import com.springmall.bean.*;
import com.springmall.service.GrouponService;
import com.springmall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/groupon")
public class GrouponController {

    @Autowired
    GrouponService grouponService;


    /**
     * 获取团购信息并分页
     * @param request
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo showWholesaleRulesByPage(PageRequest request) {
        DataForPage<Groupon_rules> result = grouponService.showWholesaleByPage(request);
        return ResultUtil.success(result);
    }

    /**
     * 删除团购信息
     * @param rules
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo deleteWholesaleRulesById(@RequestBody Groupon_rules rules) {
        grouponService.deleteRulesById(rules);
        return ResultUtil.success(null);
    }

    /*@RequestMapping("create")
    public BaseRespVo addWholesaleRules(@RequestBody Groupon_rules rules) {
        //查看商品id是否存在
    }*/

    /**
     * 显示团购活动信息并分页
     * @param request
     * @return
     */
    @RequestMapping("listRecord")
    public BaseRespVo showWholesaleByPage(PageRequest request) {
        DataForPage result = grouponService.showWholesale(request);
        return ResultUtil.success(result);
    }
}
