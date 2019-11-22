package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Keyword;
import com.springmall.bean.Search_history;
import com.springmall.bean.User;
import com.springmall.service.KeywordService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class SearchKeyWordController {
    @Autowired
    KeywordService keywordService;
    @RequestMapping("search/index")
    public BaseReqVo<Map<String,Object>> querySearchList(){
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        List<Search_history> searchHistoryList=null;
        if(user!=null){
            Integer id = user.getId();
            //历史搜索按照用户的id进行搜索的进行显示
            searchHistoryList=keywordService.querySearchHistoryList(id);
        }
        BaseReqVo<Map<String, Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        //进行热门搜索进行显示
        List<Keyword> keywordList = keywordService.queryKeyWordList();
        //将热门搜索的第一个KeyWord进行显示在输入框中
        map.put("defaultKeyword",keywordList.get(0));
        map.put("hotKeywordList",keywordList);
        map.put("historyKeywordList",searchHistoryList);
        mapBaseReqVo.setErrno(0);
        mapBaseReqVo.setData(map);
        mapBaseReqVo.setErrmsg("成功");
        return mapBaseReqVo;
    }
}
