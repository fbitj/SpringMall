package com.springmall.service;

import com.github.pagehelper.PageHelper;
import com.springmall.bean.Comment;
import com.springmall.bean.CommentExample;
import com.springmall.bean.CommentReplay;
import com.springmall.bean.Order_goods;
import com.springmall.mapper.CommentMapper;
import com.springmall.mapper.CommentReplayMapper;
import com.springmall.mapper.Order_goodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        if (commentReplays.size() != 0) {// 代表已经回复
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
        commentExample.setOrderByClause(sortField + " " + order);
        commentExample.createCriteria().andDeletedEqualTo(false);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        return comments;
    }

    @Autowired
    Order_goodsMapper orderGoodsMapper;
    /**
     * 提交评论
     * @return 1：提交成功，0：未提交，-1：超期不能评论
     */
    @Override
    @Transactional
    public int commentSubmit(int userId, Integer orderGoodsId, String content, boolean hasPicture, short star, String[] picUrls) {
        // 查询该订单中该商品是否已经提交评论
        Order_goods order_goods = orderGoodsMapper.selectByPrimaryKey(orderGoodsId);
        int commentStatus = order_goods.getComment();
        if (commentStatus == 0){
            //否：修改订单中商品的评论相关信息
            //在评论表中插入品论
            Comment comment = new Comment();
            comment.setValueId(order_goods.getGoodsId());
            comment.setType((byte) 0);
            comment.setContent(content);
            comment.setUserId(userId);
            comment.setHasPicture(hasPicture);
            comment.setStar(star);
            comment.setAddTime(new Date());
            comment.setUpdateTime(new Date());
            comment.setDeleted(false);
            comment.setPicUrls(picUrls);
            commentMapper.insertSelective(comment);
            //在订单商品表中修改数据
            orderGoodsMapper.updateCommentById(orderGoodsId, comment.getId());
            return 0;
        } else if (commentStatus == -1){
            // 超期不能评论
            return 1;
        } else {
            // 已经评论
            return -1;
        }
    }
}
