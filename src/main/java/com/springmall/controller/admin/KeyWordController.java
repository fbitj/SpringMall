package com.springmall.controller.admin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Keyword;
import com.springmall.service.KeywordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @RequiresPermissions(value = {"admin:keyword:list"})
    public BaseReqVo keyWordList(Integer page, Integer limit, String keyword, String url) {
        BaseReqVo baseReqVo = new BaseReqVo();
//        Map<String, Object> map = null;
//        if (keyword !=null || url != null) {
//            map = keywordService.queryIssues(page, limit, keyword, url);
//        } else {
//            map = keywordService.queryIssues(page, limit);
//        }
        Map<String, Object> map = keywordService.queryIssues(page, limit, keyword, url);
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("create")
    @RequiresPermissions(value = {"admin:keyword:create"})
    public BaseReqVo keyWordCreate(@RequestBody Keyword keyword) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Keyword addKeyword = keywordService.addKeyword(keyword);
        baseReqVo.setData(addKeyword);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    @RequestMapping("update")
    @RequiresPermissions(value = {"admin:keyword:update"})
    public BaseReqVo keyWordUpdate(@RequestBody Keyword keyword) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Keyword updateKeyword = keywordService.updateKeyword(keyword);
        baseReqVo.setData(updateKeyword);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
    @RequestMapping("delete")
    @RequiresPermissions(value = {"admin:keyword:delete"})
    public BaseReqVo keyWordDelete(@RequestBody Keyword keyword) {
        BaseReqVo baseReqVo = new BaseReqVo();
        int i = keywordService.deleteKeyword(keyword);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }
}
