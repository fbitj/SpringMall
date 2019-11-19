package com.springmall.mapper;

import com.springmall.bean.CommentReplay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentReplayMapper {
    List<CommentReplay> queryReplayByCommentId(@Param("commentId") int commentId);

    void insertReplay(@Param("commentId") int commentId, @Param("content") String content);
}
