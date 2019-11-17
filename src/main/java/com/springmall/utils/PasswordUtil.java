package com.springmall.utils;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/16 17:31
 */
public class PasswordUtil {

    public static String string2Stringint(String code){
        int i = code.hashCode();
        int codes = i + "tangyan".hashCode();
        return String.valueOf(codes);
    }
}
