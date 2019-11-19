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

    /**
     * 回复商品评论
     * @param commentId 评论id
     * @param content 回复内容
     * @return 1：回复成功，0：回复失败（该评论已经回复）
     */
    int replay(int commentId, String content);
}
