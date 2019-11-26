package com.springmall.shiro;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

@Data
public class CustomToken extends UsernamePasswordToken {
    private String type;
    public CustomToken(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }
}
