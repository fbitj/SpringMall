package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springmall.bean.Issue;
import com.springmall.bean.IssueExample;
import com.springmall.mapper.IssueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    IssueMapper issueMapper;
    @Override
    public Map<String, Object> queryIssues(Integer page, Integer limit, String question) {
        //分页
        PageHelper.startPage(page, limit);
        //查询
        IssueExample issueExample = new IssueExample();
        if(question != null) {//条件搜索
            issueExample.createCriteria().andQuestionLike("%" + question + "%");
        }
        List<Issue> issues = issueMapper.selectByExample(issueExample);
        Map<String, Object> map = new HashMap<>();
        PageInfo<Issue> issuePageInfo = new PageInfo<>(issues);
        long total = issuePageInfo.getTotal();
        map.put("total", total);
        map.put("items", issues);
        return map;
    }

    /**
     * 添加问题和回答
     * @param issue
     * @return
     */
    @Override
    public Issue addIssue(Issue issue) {
        Date date = new Date();
        issue.setAddTime(date);
        issue.setUpdateTime(date);
        int i = issueMapper.insertSelective(issue);
        //获取插入后的id
        Integer id = issue.getId();
        Issue addIssue = issueMapper.selectByPrimaryKey(id);
        return addIssue;
    }

    /**
     * 编辑
     * @param issue
     * @return
     */
    @Override
    public Issue updateIssue(Issue issue) {
        Date date = new Date();
        issue.setUpdateTime(date);
        int i = issueMapper.updateByPrimaryKeySelective(issue);
        Issue updateIssue = issueMapper.selectByPrimaryKey(issue.getId());
        return issue;
    }

    /**
     * 删除
     * @param issue
     * @return
     */
    @Override
    public int deleteIssue(Issue issue) {
        int delete = issueMapper.deleteByPrimaryKey(issue.getId());
        return delete;
    }
}
