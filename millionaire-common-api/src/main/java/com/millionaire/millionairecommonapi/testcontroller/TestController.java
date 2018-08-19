package com.millionaire.millionairecommonapi.testcontroller;

import com.millionaire.millionairecommonapi.aliyun.AliyunOSSUtil;
import com.millionaire.millionairecommonapi.excepion.AliyunAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@RestController
/**
 * TODO 测试类项目整合时删除
 */
public class TestController {

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;
    @RequestMapping(value = "a/upload",method = RequestMethod.POST)
    public String test(@RequestParam MultipartFile file) throws IOException, AliyunAPIException {
        System.out.println("=====================123333========================");
        String s =aliyunOSSUtil.imageUpload(file);
        return s;
    }
    @RequestMapping(value = "a/id/upload",method = RequestMethod.POST)
    public String testID(@RequestParam MultipartFile file) throws IOException, AliyunAPIException {
        System.out.println("=====================123333========================");
        String s = aliyunOSSUtil.IDimageUpload(file);
        return s;
    }

}
