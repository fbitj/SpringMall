package com.springmall.bean;

import lombok.Data;

import java.util.List;
// type=2 用此bean封装
@Data
public class RegionCity {
    private Integer id;

    private String name;

    private Byte type;

    private Integer code;

    private List<RegionCounty> children;
}
