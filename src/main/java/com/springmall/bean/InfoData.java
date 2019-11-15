package com.springmall.bean;

import lombok.Data;

import java.util.List;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/15 16:59
 */
@Data
public class InfoData {

    private List<String> roles;
    private String name;
    private List<String> perms;
    private String avatar;
}
