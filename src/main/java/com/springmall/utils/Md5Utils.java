package com.springmall.utils;

/**
 * Created by fwj on 2019-11-21.
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密算法
 */
public class Md5Utils {

    /**
     * 对明文进行MD5加密，返回密文
     * @param content 明文
     * @return 密文
     */
    public static String getMd5(String content){
        byte[] bytes = content.getBytes(); // 明文转换为byte数组
        //通过md5的实例去实现消息摘要
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5"); // 获取md5实例
            byte[] digest = md5.digest(bytes); // 生成了大小为16的byte数组信息摘要，即128bit
            //128bit
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) { // 将长度16的byte数组转换为长度32的16进制字符串
                int x = b & 0xff; // 0a = 10 a0 = 160
                String s = Integer.toHexString(x); //a 0
                if (s.length() == 1){
                    sb.append(0); //补位
                }
                sb.append(s);
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 加盐的md5算法
     * @param content
     * @param sault
     * @return
     */
    public static String getMd5(String content,String sault){
        content = content + "{11111" + sault;
        String md5 = getMd5(content);
        return md5;
    }

    // 对内容使用默认盐进行加密
    public static String getDefaultMd5Encrypt(String content) {
        return getMd5(content, "jjffssaa");
    }

}
