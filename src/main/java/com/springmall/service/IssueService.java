package com.springmall.service;

import com.springmall.bean.Issue;

import java.util.Map;

public interface IssueService {
    Map<String, Object> queryIssues(Integer page, Integer limit, String question);
    Issue addIssue(Issue issue);
    Issue updateIssue(Issue issue);
    int deleteIssue(Issue issue);

}
