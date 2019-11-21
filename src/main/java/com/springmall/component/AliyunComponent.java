package com.springmall.component;

import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mall.aliyun")
public class AliyunComponent {
    String accessKeyId;
    String accessSecret;
    OssComponent oss;
    public OSSClient getOssClient(){
        return new OSSClient(oss.getEndPoint(),accessKeyId,accessSecret);
    }
}
