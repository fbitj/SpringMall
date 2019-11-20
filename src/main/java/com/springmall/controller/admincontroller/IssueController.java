package com.springmall.controller.admincontroller;

import com.springmall.bean.BaseRespVo;
import com.springmall.bean.Issue;
import com.springmall.service.IssueService;
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
    public BaseRespVo issueList(Integer page, Integer limit, String question) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Map<String, Object> map = issueService.queryIssues(page, limit, question);
        BaseRespVo.setData(map);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }

    @RequestMapping("create")
    public BaseRespVo issueCreate(@RequestBody Issue issue) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Issue addIssue = issueService.addIssue(issue);
        BaseRespVo.setData(addIssue);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }

    @RequestMapping("update")
    public BaseRespVo issueUpdate(@RequestBody Issue issue) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        Issue updateIssue = issueService.updateIssue(issue);
        BaseRespVo.setData(updateIssue);
        BaseRespVo.setErrno(0);
        BaseRespVo.setErrmsg("成功");
        return BaseRespVo;
    }

    @RequestMapping("delete")
    public BaseRespVo issueDelete(@RequestBody Issue issue) {
        BaseRespVo BaseRespVo = new BaseRespVo();
        int i = issueService.deleteIssue(issue);
        if(i == 1) {
            BaseRespVo.setErrno(0);
            BaseRespVo.setErrmsg("成功");
        }
        return BaseRespVo;
    }
}
