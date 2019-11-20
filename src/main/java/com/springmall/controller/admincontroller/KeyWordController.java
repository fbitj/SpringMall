package com.springmall.controller.admincontroller;

import com.springmall.bean.BaseRespVo;
import com.springmall.bean.Keyword;
import com.springmall.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/keyword")
public class KeyWordController {
    @Autowired
    KeywordService keywordService;

    @RequestMapping("list")
    public BaseRespVo keyWordList(Integer page, Integer limit, String keyword, String url) {
        BaseRespVo BaseRespVo = new BaseRespVo();
//        Map<String, Object> map = null;
//        if (keyword !=null || url != null) {
//            map = keywordService.queryIssues(page, limit, keyword, url);
//        } else {
//            map = keywordService.queryIssues(page, limit);
//        }
        Map<String, Object> map = keywordService.queryIssues(page, limit, keyword, url);
        BaseRespVo.setData(map);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }

    @RequestMapping("create")
    public BaseRespVo keyWordCreate(@RequestBody Keyword keyword) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Keyword addKeyword = keywordService.addKeyword(keyword);
        BaseRespVo.setData(addKeyword);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }
    @RequestMapping("update")
    public BaseRespVo keyWordUpdate(@RequestBody Keyword keyword) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Keyword updateKeyword = keywordService.updateKeyword(keyword);
        BaseRespVo.setData(updateKeyword);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }
    @RequestMapping("delete")
    public BaseRespVo keyWordDelete(@RequestBody Keyword keyword) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        int i = keywordService.deleteKeyword(keyword);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }
}
