package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Keyword;
import com.springmall.bean.Search_history;
import com.springmall.service.KeywordService;
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
        BaseReqVo<Map<String, Object>> mapBaseReqVo = new BaseReqVo<>();
        HashMap<String, Object> map = new HashMap<>();
        //进行热门搜索进行显示
        List<Keyword> keywordList = keywordService.queryKeyWordList();
        //历史搜索进行显示
        List<Search_history> searchHistoryList=keywordService.querySearchHistoryList();
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
