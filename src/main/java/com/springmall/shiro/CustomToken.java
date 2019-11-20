package com.springmall.shiro;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by fwj on 2019-11-20.
 */

/**
 * 自定义的Token，携带了type属性，用于域的分发
 */
@Data
public class CustomToken extends UsernamePasswordToken {
    private String type;

    public CustomToken(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }
}
