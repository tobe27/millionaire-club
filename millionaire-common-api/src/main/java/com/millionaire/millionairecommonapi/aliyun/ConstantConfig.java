package com.millionaire.millionairecommonapi.aliyun;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
public class ConstantConfig  {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    @Value("${aliyun.endpoint}")
    private String IMAGE_END_POINT;
    //    图片访问的域名
    @Value("${aliyun.ossDomain}")
    private String OSS_DOMAIN;
    //    阿里云主账号AccessKey拥有所有API的访问权限.
    @Value("${aliyun.accessKeyId}")
    private String ALIYUN_ACCESS_KEY_ID;
    @Value("${aliyun.accessKeySecret}")
    private String ALIYUN_ACCESS_KEY_SECRET;
    @Value("${aliyun.bucket}")
    private String IMAGE_BUCKET_NAME;
    //   身份证照片储存格式
    @Value("${aliyun.IDStyle}")
    private String ID_CARD_STYLE;
}