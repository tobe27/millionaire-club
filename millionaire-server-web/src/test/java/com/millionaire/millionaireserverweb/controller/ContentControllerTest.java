package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairemanagerservice.module.Content;
import com.millionaire.millionairemanagerservice.service.ContentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ContentControllerTest {

    @Resource
    private ContentService contentService;
    @Test
    public void listContent() {
    }

    @Test
    public void getContent() {
    }

    @Test
    public void insertContent() {
        for(int i =0 ;i<6;i++ ){
            Content content =new Content();
            content.setTitle("banner"+i);
            content.setType((byte)10);
            content.setCover("http://majorjoe.oss-cn-beijing.aliyuncs.com/780fb779-6972-422d-9d03-8d2f7dc62f35.png");
            content.setContent("http://majorjoe.oss-cn-beijing.aliyuncs.com/780fb779-6972-422d-9d03-8d2f7dc62f35.png");
            content.setState((byte)10);
            content.setEditors("admin");
      contentService.insertSelective(content);

        }

    }

    @Test
    public void updateContent() {
    }

    @Test
    public void updateStatus() {
    }

    @Test
    public void deleteContent() {
    }
}