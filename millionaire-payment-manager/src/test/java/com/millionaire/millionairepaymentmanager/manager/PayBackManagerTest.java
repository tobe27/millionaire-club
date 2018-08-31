package com.millionaire.millionairepaymentmanager.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PayBackManagerTest {

    @Autowired
    private PayBackManager payBackManager;
    @Test
    public void backManage() {

        payBackManager.backManage(23L);
    }
}