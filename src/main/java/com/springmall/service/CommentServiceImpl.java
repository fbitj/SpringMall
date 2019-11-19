package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.springmall.bean.Comment;
import com.springmall.bean.CommentExample;
import com.springmall.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public int deleteCommentById(Integer id) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setDeleted(true);
        return commentMapper.updateByPrimaryKeySelective(comment);
    }

    @Override
    public List<Comment> queryCommentsByPage(int page, int limit, String sortField, String order) {
        PageHelper.startPage(page, limit);
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause(sortField + " " +order);
        commentExample.createCriteria().andDeletedEqualTo(false);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return comments;
    }
}
