package com.springmall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/16 22:02
 */
@Data
public class Role2 {

    private Integer id;

    private String name;

    private String desc;

    private int enabled;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    private int deleted;
}
