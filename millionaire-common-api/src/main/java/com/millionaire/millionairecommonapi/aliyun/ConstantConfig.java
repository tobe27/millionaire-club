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

    public String getIMAGE_END_POINT() {
        return IMAGE_END_POINT;
    }

    public void setIMAGE_END_POINT(String IMAGE_END_POINT) {
        this.IMAGE_END_POINT = IMAGE_END_POINT;
    }

    public String getOSS_DOMAIN() {
        return OSS_DOMAIN;
    }

    public void setOSS_DOMAIN(String OSS_DOMAIN) {
        this.OSS_DOMAIN = OSS_DOMAIN;
    }

    public String getALIYUN_ACCESS_KEY_ID() {
        return ALIYUN_ACCESS_KEY_ID;
    }

    public void setALIYUN_ACCESS_KEY_ID(String ALIYUN_ACCESS_KEY_ID) {
        this.ALIYUN_ACCESS_KEY_ID = ALIYUN_ACCESS_KEY_ID;
    }

    public String getALIYUN_ACCESS_KEY_SECRET() {
        return ALIYUN_ACCESS_KEY_SECRET;
    }

    public void setALIYUN_ACCESS_KEY_SECRET(String ALIYUN_ACCESS_KEY_SECRET) {
        this.ALIYUN_ACCESS_KEY_SECRET = ALIYUN_ACCESS_KEY_SECRET;
    }

    public String getIMAGE_BUCKET_NAME() {
        return IMAGE_BUCKET_NAME;
    }

    public void setIMAGE_BUCKET_NAME(String IMAGE_BUCKET_NAME) {
        this.IMAGE_BUCKET_NAME = IMAGE_BUCKET_NAME;
    }

    public String getID_CARD_STYLE() {
        return ID_CARD_STYLE;
    }

    public void setID_CARD_STYLE(String ID_CARD_STYLE) {
        this.ID_CARD_STYLE = ID_CARD_STYLE;
    }
}