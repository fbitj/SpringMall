package com.springmall.bean;

import lombok.Data;

import java.util.List;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/19 17:14
 */
@Data
public class RolePermission {
    private int roleId;
    private List<String> permissions;
}
