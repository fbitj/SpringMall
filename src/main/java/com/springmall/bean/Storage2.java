package com.springmall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Storage2 {
    private Integer id;

    private String key;

    private String name;

    private String type;

    private Integer size;

    private String url;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date updateTime;

    private int deleted;
}