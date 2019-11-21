package com.springmall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Coupon_user {
    private Integer id;

    private Integer userId;

    private Integer couponId;

    private Short status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date usedTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Integer orderId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;

    public Coupon_user(Integer userId, Integer couponId, Short status, Date startTime, Date endTime, Boolean deleted) {
        this.userId = userId;
        this.couponId = couponId;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deleted = deleted;
    }

    public Coupon_user(Integer id, Integer userId, Integer couponId, Short status, Date usedTime, Date startTime, Date endTime, Integer orderId, Date addTime, Date updateTime, Boolean deleted) {
        this.id = id;
        this.userId = userId;
        this.couponId = couponId;
        this.status = status;
        this.usedTime = usedTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.orderId = orderId;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.deleted = deleted;
    }
}
