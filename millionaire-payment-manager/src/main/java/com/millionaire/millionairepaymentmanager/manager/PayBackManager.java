package com.millionaire.millionairepaymentmanager.manager;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
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


//        定时任务的调用
        /**
         * 关于定时任务分类的思考：
         * 有哪几种定时任务：
         * 1.用户投资到期
         * 用户投资到期分为两种：
         * 到期本息一次付清---------分期付息，到期还本
         * 给用户还款，这里就不能再调用手机支付的接口，而是商户转账的接口，同时涉及到用户信息、用户投资、消息和记录表的改动
         * 2.债权到期
         * 债权到期主要是在插入债权信息的时候调用，这的操作就是债权到期以后的债权匹配表格状态的取消
         * 3.平台消息定时推送
         * ...较为简单这里的比较困难的就是怎么动态控制定时任务
         */







        

    }



}
