package com.millionaire.millionairecommonapi.aliyun;

import com.aliyun.oss.OSSClient;
import com.millionaire.millionairecommonapi.excepion.AliyunAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Component
public class AliyunOSSUtil {


    private Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);
    @Qualifier("constantConfig")
    @Autowired
    private ConstantConfig constantConfig;

    public String fileCheck(MultipartFile file) throws AliyunAPIException {

        if (file==null) {
            logger.info("上传图片不存在");
            throw new AliyunAPIException("上传图片不存在");
        }

        //获得图片后缀
        String[] fileName = file.getOriginalFilename().split("\\.");
        String photoSuffix = fileName[fileName.length - 1];

        //判断图片格式
        if (!photoSuffix.equals("jpg") && !photoSuffix.equals("png") && !photoSuffix.equals("img")){
            logger.info("上传图片格式不正确");
            throw new AliyunAPIException("上传图片格式不正确");
        }

        return photoSuffix;
    }


    /**
     * 身份证实名认证的上传接口，图片信息要求打有水印
     * @param file
     * @return
     * @throws AliyunAPIException
     * @throws IOException
     */
    public String IDimageUpload(MultipartFile file) throws AliyunAPIException, IOException {
//        参数检验数据正常，返回图片后缀
        String photoSuffix=fileCheck(file);
        // UUID是1.5中新增的一个类，在java.util下，用它可以产生一个号称全球唯一的ID  07ca3dec-b674-41d0-af9e-9c37583b08bb
        UUID uuid = UUID.randomUUID();
        String objectName =uuid +"."+ photoSuffix;

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(constantConfig.getIMAGE_END_POINT(), constantConfig.getALIYUN_ACCESS_KEY_ID(),
                constantConfig.getALIYUN_ACCESS_KEY_SECRET());

        // 上传文件。
        ossClient.putObject(constantConfig.getIMAGE_BUCKET_NAME(),objectName, new ByteArrayInputStream(file.getBytes()));

        // 关闭OSSClient。
        ossClient.shutdown();

        // 身份证信息访问地址,加上照片的处理类型
        String url= constantConfig.getOSS_DOMAIN()+objectName+"?x-oss-process=style/"+constantConfig.getID_CARD_STYLE();
        logger.info("==========身份验证====图片上传成功================="+url);
        return url;
    }

    /**
     * 图片上传接口
     * @param file
     * @return
     * @throws AliyunAPIException
     * @throws IOException
     */
    public String imageUpload(MultipartFile file) throws AliyunAPIException, IOException {
//        参数检验数据正常，返回图片后缀
        String photoSuffix=fileCheck(file);
        // UUID是1.5中新增的一个类，在java.util下，用它可以产生一个号称全球唯一的ID  07ca3dec-b674-41d0-af9e-9c37583b08bb
        UUID uuid = UUID.randomUUID();
        String objectName =uuid + "."+photoSuffix;

        System.out.println(constantConfig.getIMAGE_END_POINT());

        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(constantConfig.getIMAGE_END_POINT(), constantConfig.getALIYUN_ACCESS_KEY_ID(),
                constantConfig.getALIYUN_ACCESS_KEY_SECRET());

        // 上传文件。
        ossClient.putObject(constantConfig.getIMAGE_BUCKET_NAME(),objectName, new ByteArrayInputStream(file.getBytes()));

        // 关闭OSSClient。
        ossClient.shutdown();

        // 身份证信息访问地址,加上照片的处理类型
        String url= constantConfig.getOSS_DOMAIN()+objectName;
        logger.info("=============图片上传成功================="+url);
        return url;
    }
}
