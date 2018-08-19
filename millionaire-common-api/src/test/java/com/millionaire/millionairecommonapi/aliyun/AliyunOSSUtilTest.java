package com.millionaire.millionairecommonapi.aliyun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AliyunOSSUtilTest {

    @Autowired
    private ConstantConfig constantConfig;

    @Test
    public void fileCheck() {
        System.out.println("=======================================================");
        System.err.println(constantConfig.getIMAGE_END_POINT());
    }
}