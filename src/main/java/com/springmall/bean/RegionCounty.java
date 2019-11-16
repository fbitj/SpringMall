package com.springmall.bean;

import lombok.Data;
//type=3用此bean封装
@Data
public class RegionCounty {
    private Integer id;

    private String name;

    private Byte type;

    private Integer code;
}
