package com.springmall.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/21 21:12
 */
public class MD5Util {

    public static String getMd5(String content){
        byte[] bytes = content.getBytes();
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //转为MD5 128bit数据 16位
            byte[] digest = md5.digest(bytes);
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                //0~255
                int x = b & 0xff;
                String string = Integer.toHexString(x);
                if(string.length() == 1){
                    sb.append(0);
                }
                sb.append(string);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getMd5s(String content,String sault,int i){
        sault = "tianmei";
        if(i > 7){
            return content;
        }
        i++;
        String md5 = getMd5(content);
        return getMd5s(md5,sault,i);
    }

    public static String getMd5Password(String content){
        return getMd5s(content,"tangyan",0);
    }

    public static String getFileMd5(File file){
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = fileInputStream.read(bytes,0,1024) ) > 0){
                md5.update(bytes,0,length);
            }
            byte[] digest = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                int x = b & 0xff;
                String string = Integer.toHexString(x);
                if(string.length() == 1){
                    sb.append(0);
                }
                sb.append(string);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
