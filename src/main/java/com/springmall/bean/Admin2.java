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
    private String lastLoginIp;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date lastLoginTime;
    private String avatar;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date addTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    private int deleted;
    private int[] roleIds;
}
