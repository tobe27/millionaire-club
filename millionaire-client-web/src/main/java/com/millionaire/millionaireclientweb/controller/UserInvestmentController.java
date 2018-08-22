package com.millionaire.millionaireclientweb.controller;

import com.millionaire.millionaireclientweb.result.ResultBean;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInvestmentController {

    private final Logger logger = LoggerFactory.getLogger(UserInvestmentController.class);

    /**
     * 用户投资接口
     * @param requestBean
     * @return
     */
    @PostMapping("/u/user-investment")
    public ResultBean userInvestment(@RequestBody UserInvestmentRequestBean requestBean) {

        return null;
    }

}
