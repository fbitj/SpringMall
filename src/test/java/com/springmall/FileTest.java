package com.springmall;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.internal.OSSBucketOperation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;


/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/17 20:50
 */

@SpringBootTest
public class FileTest {

    @Test
    public void test(){
        /*File file = new File("C:/SpringMallImage/" + "29d5854bac9946a6a898d88375e497b8.jpg");
        file.delete();*/
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1,"a");
        map.put(2,"a");
        System.out.println(map.size());
    }

    @Test
    public void test1() throws FileNotFoundException {
        //自己的阿里云        没有备案域名，目前还不能行
        /*String accessKeyId = "LTAI4FunKFf6BqPpmGsou2qE";
        String accessKeySecret = "e46K71mQ28l4qB8Q9x3GR0Os3FFvVP";
        String bucket = "springmall";
        String endPoint = "oss-cn-beijing.aliyuncs.com";*/
        //老师的阿里云
        String accessKeyId = "LTAI4Fr5gfYhcVjLMqeRGbuT";
        String accessKeySecret = "IrkcHu6dZyrjPZRushgO76P5392HJ1";
        String bucket = "cskaoyan";
        String endPoint = "oss-cn-beijing.aliyuncs.com";

        File file = new File("H:\\桌面\\临时" + "\\1.jpg");
        OSSClient ossClient = new OSSClient(endPoint,accessKeyId,accessKeySecret);
        FileInputStream fileInputStream = new FileInputStream(file);
        ossClient.putObject(bucket,"test.jpg",fileInputStream);

    }
}
