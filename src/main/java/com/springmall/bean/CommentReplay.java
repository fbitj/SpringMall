package com.springmall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Date;

@Data
public class CommentReplay {
    private Integer id;
    private Integer commentId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date replayTime;
//    private boolean replied;
    private boolean deleted;
}
