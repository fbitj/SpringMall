package com.springmall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class OrderResp {
    private Integer id;

    private Integer userId;

    private String orderSn;

    private Short orderStatus;

    private String consignee;

    private String mobile;

    private String address;

    private String message;

    private BigDecimal goodsPrice;

    private BigDecimal freightPrice;

    private BigDecimal couponPrice;

    private BigDecimal integralPrice;

    private BigDecimal grouponPrice;

    private BigDecimal orderPrice;

    private BigDecimal actualPrice;

    private Short comments;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    //物流编号
    private String shipSn;

    //物流公司
    private String shipChannel;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Boolean deleted;
}
