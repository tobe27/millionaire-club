package com.millionaire.millionaireclientweb.controller;

import com.millionaire.millionairecommonapi.aliyun.AliyunOSSUtil;
import com.millionaire.millionairecommonapi.excepion.AliyunAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AliyunAPIController {
    private Logger logger = LoggerFactory.getLogger(AliyunAPIController.class);

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    /**
     * 普通图片上传接口
     * @param file
     * @return
     * @throws IOException
     * @throws AliyunAPIException
     */
    @RequestMapping(value = "a/upload-picture",method = RequestMethod.POST)
    public String test(@RequestParam MultipartFile file) throws IOException, AliyunAPIException {
        String url = aliyunOSSUtil.imageUpload(file);
        logger.info("=========图片接口上传成功=========");
        return url;
    }

    /**
     * 身份证图片上传接口
     * @param file
     * @return
     * @throws IOException
     * @throws AliyunAPIException
     */
    @RequestMapping(value = "a/ID-card-upload",method = RequestMethod.POST)
    public String testID(@RequestParam MultipartFile file) throws IOException, AliyunAPIException {
        String url = aliyunOSSUtil.IDimageUpload(file);
        logger.info("=========图片接口上传成功=========");
        return url;
    }
}
