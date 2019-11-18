package com.springmall.service;

import com.springmall.bean.Comment;

import java.util.List;

public interface CommentService {
    /**
     * 分页查询评论
     * @param page 当前页
     * @param limit 每页显示条目数
     * @param sortField 排序字段
     * @param order 排序方式
     * @return
     */
    List<Comment> queryCommentsByPage(int page, int limit, String sortField, String order);

    /**
     * 通过id删除评论
     * @param id
     * @return
     */
    int deleteCommentById(Integer id);
}
