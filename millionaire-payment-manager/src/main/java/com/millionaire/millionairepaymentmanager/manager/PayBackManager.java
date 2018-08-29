package com.millionaire.millionairepaymentmanager.manager;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairequartzmanager.module.TimerTaskInvestment;
import com.millionaire.millionairequartzmanager.service.TimerTaskInvestmentService;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private InvestmentProductService investmentProductService;

    @Autowired
    private ReceptionUsersService receptionUsersService;

    @Autowired
    private TimerTaskInvestmentService timerTaskInvestmentService;


    public void backManage(Long investmentUserId) {
//        查询用户投资信息
        InvestmentUser investmentUser = investmentUserService.selectByPrimaryKey(investmentUserId);
//        查询投资产品
        InvestmentProduct investmentProduct = investmentProductService.selectByPrimaryKey(investmentUser.getProductId());


//        将用户投资状态修改为理财中
        investmentUserService.updateInvestmentUserIdStatus(investmentUserId, (byte) 10);
        logger.info("用户投资记录修改成功："+investmentUserId+"修改参数：10");
//        修改交易记录为成功
        tradingFlowService.updateTradingFlowStatus(investmentUserId, (byte) 10);
        logger.info("用户交易记录修改成功："+investmentUserId+"修改参数：10");
//        修改用户消息记录
        messageUserService.updateMessageUserCode(investmentUserId, (byte) 10);
        logger.info("用户消息记录修改成功："+investmentUserId+"修改参数：10");
//        用户总资产更新
        int nowAssets=receptionUsersService.updateUserAssets(investmentUser.getUid(), investmentUser.getInvestmentAmount(), 1);
        logger.info(investmentUser.getUid() + "用户资产总额" + nowAssets);

//        写入定时任务
        TimerTaskInvestment timerTaskInvestment = new TimerTaskInvestment();


        if (investmentProduct.getType() == 10) {//本息一次付款的任务写入
            timerTaskInvestment.setInvestmentUserId(investmentUserId);
//            付款金额，以分为单位
            int backAmount = (int) ((investmentUser.getInvestmentAmount() + investmentUser.getExpectedIncome())*100);
            timerTaskInvestment.setPaybackAmount(backAmount);
//            表示本息一次付清
            timerTaskInvestment.setExecuteType((byte) 10);
            timerTaskInvestment.setTimes((byte)1);
//            待执行状态
            timerTaskInvestment.setStatus((byte) 0);
//            定时任务执行时间
            timerTaskInvestment.setExecuteTime(investmentUser.getValueDateEnd());
            timerTaskInvestmentService.insert(timerTaskInvestment);
            return;
        }

        if (investmentProduct.getType() == 20) {//分期还息，最后一个还本和息
//            每月还息固定每月20号，最后一次的本息还款以实际到期日期为准



        }




    }



}
