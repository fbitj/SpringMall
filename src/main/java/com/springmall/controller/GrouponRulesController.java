package com.springmall.controller;

import com.springmall.bean.*;
import com.springmall.service.GrouponService;
import com.springmall.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/groupon")
public class GrouponRulesController {

    @Autowired
    GrouponService grouponService;




    /**
     * 获取团购信息并分页
     * @param request
     * @return
     */
    @RequestMapping("list")
    public BaseReqVo showWholesaleRulesByPage(AdRequest request) {
        DataForPage<Groupon_rules> result = grouponService.showWholesaleByPage(request);
        return ResultUtil.success(result);
    }

    /*@RequestMapping("create")
    public BaseReqVo addWholesaleRules(@RequestBody Groupon_rules rules) {
        //查看商品id是否存在
    }*/

    /**
     * 显示团购活动信息并分页
     * @param request
     * @return
     */
    /*@RequestMapping("listRecord")
    public BaseReqVo showWholesaleByPage(AdRequest request) {

    }*/
}
