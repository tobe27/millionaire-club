package com.millionaire.millionairecommonapi.aliyun;

import com.aliyuncs.exceptions.ClientException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MessageVerificationTest {

    @Autowired
    private MessageVerification messageVerification;

    @Test
    public void setSendSmsResponse() throws ClientException {

        messageVerification.setSendSmsResponse("15224985586", 123456);
    }
}