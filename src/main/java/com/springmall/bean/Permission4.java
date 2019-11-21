package com.springmall.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Date;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/19 21:15
 */
@Data
public class Permission4 {

    private Integer id;

    private Integer roleId;

    private String permission;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    private Integer deleted;
}
