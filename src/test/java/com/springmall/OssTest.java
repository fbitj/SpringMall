package com.springmall;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
@SpringBootTest
public class OssTest {

    @Test
    public void mytest() throws FileNotFoundException {
        //用户名 密码
        //bucket endPoint
        String accessKeyId = "LTAI4Fr5gfYhcVjLMqeRGbuT";
        String accessSecret = "IrkcHu6dZyrjPZRushgO76P5392HJ1";
        String bucket = "cskaoyan";
        String endPoint = "oss-cn-beijing.aliyuncs.com";

        File file = new File("C:\\Users\\Administrator\\Desktop", "car.png");

        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessSecret);
        FileInputStream fileInputStream = new FileInputStream(file);
        PutObjectResult putObjectResult = ossClient.putObject(bucket, "songge.jpg", fileInputStream);
        System.out.println(putObjectResult);

    }
    @Test
    public void mytest2() throws FileNotFoundException {
        //用户名 密码
        //bucket endPoint
        String accessKeyId = "LTAI4Fr5gfYhcVjLMqeRGbuT";
        String accessSecret = "IrkcHu6dZyrjPZRushgO76P5392HJ1";
        String bucket = "cskaoyan";
        String endPoint = "oss-cn-beijing.aliyuncs.com";

        File file = new File("C:\\Users\\Administrator\\Desktop", "car.png");

        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessSecret);
        FileInputStream fileInputStream = new FileInputStream(file);
//        bucket, "songge.jpg", fileInputStream
        ObjectMetadata metadata = new ObjectMetadata();

        PutObjectResult putObjectResult = ossClient.putObject(new PutObjectRequest(bucket, "songge.jpg", fileInputStream, metadata));
        System.out.println(putObjectResult);

    }
}
