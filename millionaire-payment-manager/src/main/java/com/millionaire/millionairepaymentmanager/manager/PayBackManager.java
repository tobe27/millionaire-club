package com.millionaire.millionairepaymentmanager.manager;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import com.millionaire.millionairebusinessservice.service.*;
import com.millionaire.millionairepaymentmanager.until.CalulateUntil;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 * 富友支付成功后的业务处理，主要包括:
 * 用户投资状态的修改、交易记录的修改、用户消息的修改和回息的定时任务调用
 */
@Component("payBackManager")
public class PayBackManager {

    private Logger logger = LoggerFactory.getLogger(PayBackManager.class);

    @Autowired
    private InvestmentUserService investmentUserService;

    @Autowired
    private TradingFlowService tradingFlowService;

    @Autowired
    private MessageUserService messageUserService;

    @Autowired
    private ReceptionUsersService receptionUsersService;

    @Autowired
    private InvestmentTaskInsert investmentTaskInsert;

    public void backManage(Long investmentUserId) {
//        查询用户投资信息
        InvestmentUser investmentUser = investmentUserService.selectByPrimaryKey(investmentUserId);

//        将用户投资状态修改为理财中
        investmentUserService.updateInvestmentUserIdStatus(investmentUserId, (byte) 10);
        logger.info("用户投资记录修改成功：" + investmentUserId + "修改参数：10");
//        修改交易记录为成功
        tradingFlowService.updateTradingFlowStatus(investmentUserId, (byte) 10);
        logger.info("用户交易记录修改成功：" + investmentUserId + "修改参数：10");
//        修改用户消息记录
        messageUserService.updateMessageUserCode(investmentUserId, (byte) 10);
        logger.info("用户消息记录修改成功：" + investmentUserId + "修改参数：10");
//        用户总资产更新
        int nowAssets = receptionUsersService.updateUserAssets(investmentUser.getUid(), investmentUser.getInvestmentAmount(), 1);
        logger.info(investmentUser.getUid() + "用户资产总额" + nowAssets);

        investmentTaskInsert.insert(investmentUserId);

    }
}



