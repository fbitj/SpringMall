package com.springmall.component;

import com.aliyun.oss.OSSClient;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
@ConfigurationProperties(prefix = "mall.aliyun")
public class AliyunComponent {
    String accessKeyId;
    String accessSecret;
    OssComponent oss;
    SmsComponent sms;

    public OSSClient getOssClient() {
        return new OSSClient(oss.getEndPoint(), accessKeyId, accessSecret);
    }

    public IAcsClient getIascClient() {
        DefaultProfile profile = DefaultProfile.getProfile(sms.getRegionId(), accessKeyId, accessSecret);
        return new DefaultAcsClient(profile);
    }

    /**
     * 给指定电话返回验证码
     *
     * @param mobile
     * @return
     */
    public String sendSms(String mobile) {
        IAcsClient client = getIascClient();
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", sms.getSignName());
        request.putQueryParameter("TemplateCode", sms.getTemplateCode());
        int code = (int) (Math.random() * 10000);
        System.out.println(mobile + ": " + code);
        request.putQueryParameter("TemplateParam", "{\"code\": \"" + code + "\"}");
        CommonResponse response = null;
        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getHttpStatus());
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        HashMap<String, String> resMap = new HashMap<>();
        return String.valueOf(code);
    }

}
