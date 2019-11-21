package com.springmall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/16 16:45
 */
@Data
public class Admin2 {

    private Integer id;
    private String username;
    private String password;
    private String avatar;
    private int[] roleIds;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss",timezone = "GMT+8")
    private Date updateTime;
}
