package com.springmall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Date;

@Data
public class Log2 {
    private Integer id;

    private String admin;

    private String ip;

    private Integer type;

    private String action;

    private Integer status;

    private String result;

    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    private Integer deleted;
}