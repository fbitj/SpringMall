package com.springmall.bean;

import lombok.Data;

/**
 * Created by fwj on 2019-11-16.
 */

/**
 * 保存每日增加用户的数据
 * day为日期
 * users为当前日期用户增长数量
 */
@Data
public class UserStat {

    private String day;

    private Integer users;
}
