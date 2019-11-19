package com.springmall.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Search_history;
import com.springmall.service.SearchHistoryService;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理——搜索历史——controller
 */
@Controller
@RequestMapping("admin")
public class SearchHistoryController {
    @Autowired
    SearchHistoryService searchHistoryService;
    @RequestMapping("history/list")
    @ResponseBody
    public BaseReqVo<Map<String,Object>> getSearchHistory(Integer page,Integer limit,String sort,String order,Integer userId,String keyword){
        BaseReqVo<Map<String, Object>> re = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        PageHelper.startPage(page,limit);
        List<Search_history> searchHistoryList=searchHistoryService.querySearchHistoryList(userId,keyword);
        PageInfo<Search_history> searchHistoryPageInfo=new PageInfo<>(searchHistoryList);
        map.put("total",searchHistoryList.size());
        map.put("items",searchHistoryList);
        re.setErrno(0);
        re.setData(map);
        re.setErrmsg("成功");
        return re;
    }
}