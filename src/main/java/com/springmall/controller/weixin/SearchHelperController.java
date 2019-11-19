package com.springmall.controller.weixin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Keyword;
import com.springmall.service.KeywordService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 *  搜索帮助
 */
@RestController
@RequestMapping("wx")
public class SearchHelperController {
    @Autowired
    KeywordService keywordService;
    @RequestMapping("search/helper")
    public BaseReqVo<List<String>> searchHelper(@Param("keyword")String keyword){
        BaseReqVo<List<String>> listBaseReqVo = new BaseReqVo<>();
        List<Keyword> keywordList=keywordService.queryKeyWordList(keyword);
        List<String> list=new LinkedList<>();
        for (Keyword keyword1 : keywordList) {
           list.add(keyword1.getKeyword());
        }
        listBaseReqVo.setErrno(0);
        listBaseReqVo.setData(list);
        listBaseReqVo.setErrmsg("成功");
        return listBaseReqVo;
    }
}
