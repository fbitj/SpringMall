package com.springmall.bean;

import lombok.Data;

/**
 * 物流查询的javabean
 */
@Data
public class ExpressInfo {
    private String shipperName;
    private String logisticCode;
    private String Traces;
}
