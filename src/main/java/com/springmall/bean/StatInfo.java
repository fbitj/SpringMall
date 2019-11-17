package com.springmall.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by fwj on 2019-11-15.
 */

/**
 * 统计返回的数据类型
 */
@Data
public class StatInfo {

    private List<String> columns;

    private Object rows;
}
