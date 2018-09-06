package com.millionaire.millionairequartzmanager.job;


import com.millionaire.millionairebusinessservice.module.*;
import com.millionaire.millionairebusinessservice.service.*;
import com.millionaire.millionairepaymentmanager.exception.FuYouException;
import com.millionaire.millionairepaymentmanager.fuyou.CompanyPayServlet;
import com.millionaire.millionairepaymentmanager.manager.InvestmentTaskInsert;
import com.millionaire.millionairepaymentmanager.manager.PayBackManager;
import com.millionaire.millionairepaymentmanager.until.FlowNumberGeneration;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *@author qiaobao
 *@datetime  2018/9/6 7:07
 *@decribe 修改完成，用户投资的定时任务
 */

@Component
@Configuration
@EnableScheduling
public class InvestmentUserJob  {

    private Logger logger = LoggerFactory.getLogger(InvestmentUserJob.class);

    @Autowired
    private InvestmentUserService investmentUserService;

    @Autowired
    private TradingFlowService tradingFlowService;

    @Autowired
    private MessageUserService messageUserService;

    @Autowired
    private ReceptionUsersService receptionUsersService;

    @Autowired
    private TimerTaskInvestmentService taskInvestmentService;

    @Autowired
    private InvestmentProductService productService;

    @Autowired
    private CompanyPayServlet companyPay;

    @Autowired
    private ClaimMatchService matchService;

    @Autowired
    private InvestmentTaskInsert taskInsert;


    /**
     * 扫描投资的定时任务表，筛选出符合条件的记录，执行任务
     * @throws JobExecutionException
     */
    public void execute( ) {
        logger.info("=======================================================================================>");
        logger.info("用户投资回款任务执行=====当前时间"+ LocalDateTime.now());
//        扫描获得所有当前需要执行的定时任务
        List<TimerTaskInvestment> listTaskForExecute = taskInvestmentService.listTimerTaskForExecute();

        logger.info("待执行的定时任务" + listTaskForExecute);
        logger.info("=======================================================================================>");

        //Output : C
        listTaskForExecute.forEach(taskInvestment->{

            logger.info("当前执行任务" + taskInvestment);
            logger.info("=======================================================================================>");
            //                用户投资记录的自增id
            long investmentUserId = taskInvestment.getInvestmentUserId();
//                定时任务的自增id
            long taskInvestmentId = taskInvestment.getId();

//            续投投资的id
            long installInvestmentId = taskInvestment.getAssociationInvestment();

//                查询用户投资信息
            InvestmentUser investmentUser = investmentUserService.selectByPrimaryKey(investmentUserId);
//                查询产品信息
            InvestmentProduct investmentProduct = productService.selectByPrimaryKey(investmentUser.getProductId());
//                查询用户手机号
            ReceptionUsers users = receptionUsersService.selectByPrimaryKey(investmentUser.getUid());
//                查询债权匹配消息
            ClaimMatch claimMatch = matchService.selectEffectByInvestmentUID(investmentUserId);


            long uid = investmentUser.getUid();

            byte executeType = taskInvestment.getExecuteType();

            String productName = investmentProduct.getName();

            String phone = String.valueOf(users.getPhone());

            String IDName = users.getIdName();

            int payBackAmount = taskInvestment.getPaybackAmount();

            String bankCardNumber = investmentUser.getBankCardNumber();

            String bankName = investmentUser.getBankName();

            long matchId = claimMatch.getId();


            //                生成交易流水记录
            TradingFlow tradingFlow = new TradingFlow();
            tradingFlow.setInvestmentUserId(investmentUserId);
            tradingFlow.setUid(uid);
            tradingFlow.setProductName(productName);
            tradingFlow.setPhone(phone);
            tradingFlow.setName(IDName);
            tradingFlow.setAmount(payBackAmount);
            tradingFlow.setType((byte)1);
            tradingFlow.setBankCardId(bankCardNumber);
            tradingFlow.setPayType(bankName);
            tradingFlow.setStatus((byte)0);
            tradingFlowService.insert(tradingFlow);
            long newTradingFlowId = tradingFlow.getId();
            logger.info("交易信息插入："+newTradingFlowId);


            //                    生成用户消息，用户投资正在回款中
            MessageUser messageUser = new MessageUser();


            if(executeType == 10 || executeType == 30  ){//        10代表本息一次回款  30代表最后一次还息回款

//                调用支付接口转账
                boolean result = companyPay.httpURLConnectionPOST(newTradingFlowId, payBackAmount);
                //                修改定时任务状态  status = 10 执行成功

                if (result){    //支付结果成功
//                    更新交易流水的状态
                    tradingFlowService.updateTradingFlowStatusById(newTradingFlowId, (byte)10);
                    //                    修改定时任务状态执行成功
                    taskInvestmentService.updateTaskStatus((byte) 10, taskInvestmentId);
//                   修改用户信息总资产和总收益
                    receptionUsersService.updateUserAssets(uid, payBackAmount, -1);
                    receptionUsersService.updateUserProfit(uid, payBackAmount);
//                    用户消息状态设置为40 正在汇款中
                    messageUser.setCode((byte)40);

                }else {  //支付结果失败
//                    更新交易流水的状态
                    tradingFlowService.updateTradingFlowStatusById(newTradingFlowId, (byte)10);
                    //                    修改定时任务状态执行失败
                    taskInvestmentService.updateTaskStatus((byte) 30, taskInvestmentId);
//                    用户消息状态设置为60 回款失败
                    messageUser.setCode((byte)60);
                }
                //                更新用户投资状态,和债权信息
                investmentUserService.updateInvestmentUserForEnd(investmentUserId, (byte) 20,0L);
//                    取消债权匹配
                while (claimMatch !=  null) {
                    matchService.updateStatus(matchId, (byte) 0);
                }
            }


            if(executeType == 20){//        20代表分期还息

//                调用支付接口转账
                boolean result = companyPay.httpURLConnectionPOST(newTradingFlowId, payBackAmount);
                //                修改定时任务状态  status = 10 执行成功

                if (result){    //支付结果成功
//                    更新交易流水的状态
                    tradingFlowService.updateTradingFlowStatusById(newTradingFlowId, (byte)10);
                    //                    修改定时任务状态执行成功
                    taskInvestmentService.updateTaskStatus((byte) 10, taskInvestmentId);
//                   修改用户信息中总收益
                    receptionUsersService.updateUserProfit(uid, payBackAmount);
//                    用户消息状态设置为40 正在汇款中
                    messageUser.setCode((byte)40);

                }else {  //支付结果失败
//                    更新交易流水的状态
                    tradingFlowService.updateTradingFlowStatusById(newTradingFlowId, (byte)10);
                    //                    修改定时任务状态执行失败
                    taskInvestmentService.updateTaskStatus((byte) 30, taskInvestmentId);
//                    用户消息状态设置为60 回款失败
                    messageUser.setCode((byte)60);
                }

            }

            if(executeType == 40){//        40表示用户投资到期后已续投
                    //                    修改定时任务状态执行成功
                    taskInvestmentService.updateTaskStatus((byte) 10, taskInvestmentId);
//                   修改用户信息总收益
                    receptionUsersService.updateUserProfit(uid, payBackAmount);
//                    用户消息状态设置为40 开始续投
                    messageUser.setCode((byte)80);
//                    启用续投的用户投资
                investmentUserService.updateInvestmentUserIdStatus(installInvestmentId, (byte) 10);
                //                更新用户投资状态,和债权信息
                investmentUserService.updateInvestmentUserForEnd(installInvestmentId, (byte) 20,0L);
//                写入下一轮的定时任务
                taskInsert.insert(installInvestmentId);
//                    取消债权匹配
                while (claimMatch != null) {
                    matchService.updateStatus(matchId, (byte) 0);
//                    将原债权匹配转移
                    ClaimMatch newClaimMatch = new ClaimMatch();
                    newClaimMatch.setClaimId(claimMatch.getClaimId());
                    newClaimMatch.setInvestmentUserId(installInvestmentId);
                    Long count = matchService.countClaimMatch();
                    String creditContractNumber = FlowNumberGeneration.claimProtocol(count);
                    newClaimMatch.setCreditContractNumber(creditContractNumber);
                    matchService.insertClaimMatch(newClaimMatch);
                }
            }

            messageUser.setInvestmentUserId(investmentUserId);
            messageUser.setUid(uid);
            messageUser.setIsLook((byte)0);
            messageUserService.insert(messageUser);
        });


    }

}
