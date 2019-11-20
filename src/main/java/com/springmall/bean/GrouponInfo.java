package com.springmall.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by fwj on 2019-11-20.
 */
@Data
public class GrouponInfo {

    private Goods goods;

    Integer groupon_member;

    Integer groupon_price;


}
