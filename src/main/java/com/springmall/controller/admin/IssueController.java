package com.springmall.controller.admin;

import com.springmall.bean.BaseReqVo;
import com.springmall.bean.Issue;
import com.springmall.service.IssueService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/issue")
public class IssueController {
    @Autowired
    IssueService issueService;

    //显示and条件搜索
    @RequestMapping("list")
    @RequiresPermissions(value = {"admin:issue:list"})
    public BaseReqVo issueList(Integer page, Integer limit, String question) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Map<String, Object> map = issueService.queryIssues(page, limit, question);
        baseReqVo.setData(map);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("create")
    @RequiresPermissions(value = {"admin:issue:create"})
    public BaseReqVo issueCreate(@RequestBody Issue issue) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Issue addIssue = issueService.addIssue(issue);
        baseReqVo.setData(addIssue);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("update")
    @RequiresPermissions(value = {"admin:issue:update"})
    public BaseReqVo issueUpdate(@RequestBody Issue issue) {
        BaseReqVo baseReqVo = new BaseReqVo();
        Issue updateIssue = issueService.updateIssue(issue);
        baseReqVo.setData(updateIssue);
        baseReqVo.setErrno(0);
        baseReqVo.setErrmsg("成功");
        return baseReqVo;
    }

    @RequestMapping("delete")
    @RequiresPermissions(value = {"admin:issue:delete"})
    public BaseReqVo issueDelete(@RequestBody Issue issue) {
        BaseReqVo baseReqVo = new BaseReqVo();
        int i = issueService.deleteIssue(issue);
        if(i == 1) {
            baseReqVo.setErrno(0);
            baseReqVo.setErrmsg("成功");
        }
        return baseReqVo;
    }
}
