package com.springmall.bean;

import lombok.Data;

import java.util.List;
//type=1用此bean封装
@Data
public class RegionProvince {
    private Integer id;

    private String name;

    private Byte type;

    private Integer code;

    private List<RegionCity> children;
}
