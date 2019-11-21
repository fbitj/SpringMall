package com.springmall;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.springmall.component.AliyunComponent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

    @Autowired
    AliyunComponent aliyunComponent;
    @Test
    public void tets3(){
        String mobile = "15388365292";
        IAcsClient client = aliyunComponent.getIascClient();
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", aliyunComponent.getSms().getRegionId());
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", aliyunComponent.getSms().getSignName());
        request.putQueryParameter("TemplateCode", aliyunComponent.getSms().getTemplateCode());
        int code = (int) (Math.random() * 10000);
        System.out.println(code);
        request.putQueryParameter("TemplateParam", "{\"code\": \""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
