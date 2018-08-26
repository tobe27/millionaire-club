package com.millionaire.millionairepaymentmanager;

import com.millionaire.millionairepaymentmanager.manager.PayManager;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestPayController {
    @Autowired
    private PayManager payManager;

    @GetMapping("/api/test")
    public String test(UserInvestmentRequestBean requestBean,Long uid) throws IOException {
        System.out.println(requestBean);
        return payManager.payment(requestBean,uid);
    }

}
