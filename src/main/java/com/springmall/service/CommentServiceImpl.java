package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.springmall.bean.Comment;
import com.springmall.bean.CommentExample;
import com.springmall.bean.CommentReplay;
import com.springmall.mapper.CommentMapper;
import com.springmall.mapper.CommentReplayMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentReplayMapper commentReplayMapper;

    @Override
    public int replay(int commentId, String content) {
        // 查看评论是否回复
        List<CommentReplay> commentReplays = commentReplayMapper.queryReplayByCommentId(commentId);
        if (commentReplays.size() != 0){// 代表已经回复
            return 0;
        }
        // 没有回复，添加回复
        commentReplayMapper.insertReplay(commentId, content);
        return 1;
    }

    @Override
    public List queryCommentsByGoodsId(Integer id) {
        return commentMapper.queryCommentsByGoodsId(id);
    }

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
