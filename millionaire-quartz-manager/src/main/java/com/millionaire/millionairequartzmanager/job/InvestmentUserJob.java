package com.millionaire.millionairequartzmanager.job;


import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.service.*;
import com.millionaire.millionairepaymentmanager.exception.FuYouException;
import com.millionaire.millionairepaymentmanager.fuyou.CompanyPayServlet;
import com.millionaire.millionairepaymentmanager.manager.PayBackManager;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类为本息一次性付清时的调度方法
 */
@Component
public class InvestmentUserJob implements Job {

    private Logger logger = LoggerFactory.getLogger(InvestmentUserJob.class);

    @Autowired
    private InvestmentUserService investmentUserService;

    @Autowired
    private CompanyPayServlet companyPayServlet;

    @Autowired
    private TradingFlowService tradingFlowService;

    @Autowired
    private MessageUserService messageUserService;

    @Autowired
    private ReceptionUsersService receptionUsersService;

    @Autowired
    private InvestmentUserService investmentUser;

    @Autowired
    private TimerTaskInvestmentService taskInvestmentService;

    @Autowired
    private InvestmentProductService productService;

    @Autowired
    private CompanyPayServlet companyPay;

    /**
     * 扫描投资的定时任务表，筛选出符合条件的记录，执行任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        扫描获得所有当前需要执行的定时任务
        List<TimerTaskInvestment> listTaskForExecute = taskInvestmentService.listTimerTaskForExecute();

        //Output : C
        listTaskForExecute.forEach(taskInvestment->{
            if(taskInvestment.getExecuteType() == 10){//        10代表本息一次回款

//                生成交易流水记录
                TradingFlow tradingFlow = new TradingFlow();
//                查询用户投资信息
                InvestmentUser investmentUser = investmentUserService.selectByPrimaryKey(taskInvestment.getInvestmentUserId());
//                查询产品信息
                InvestmentProduct investmentProduct = productService.selectByPrimaryKey(investmentUser.getProductId());
//                查询用户手机号
                ReceptionUsers users = receptionUsersService.selectByPrimaryKey(investmentUser.getUid());

                tradingFlow.setInvestmentUserId(taskInvestment.getInvestmentUserId());
                tradingFlow.setUid(investmentUser.getUid());
                tradingFlow.setId(0L);
                tradingFlow.setProductName(investmentProduct.getName());
                tradingFlow.setPhone(String.valueOf(users.getPhone()));
                tradingFlow.setName(users.getIdName());
                tradingFlow.setAmount(taskInvestment.getPaybackAmount());
                tradingFlow.setType((byte)0);
                tradingFlow.setBankCardId(investmentUser.getBankCardNumber());
                tradingFlow.setPayType(investmentUser.getBankName());
                tradingFlow.setStatus((byte)0);
                tradingFlowService.insert(tradingFlow);
                long tradingFlowId = tradingFlow.getId();
                logger.info("交易信息插入："+tradingFlowId);

//                调用支付接口转账
                boolean result = companyPay.httpURLConnectionPOST(taskInvestment.getId(), taskInvestment.getPaybackAmount());
                //                修改定时任务状态  status = 10 执行成功

                if (result){    //支付结果成功


                }

//                更新用户账户的余额和收益



//                更新用户投资状态
//                取消债权匹配
//                生成用户消息
            }
            if(taskInvestment.getExecuteType() == 20){//        20代表分期还息
                System.out.println(taskInvestment);
            }
            if(taskInvestment.getExecuteType() == 30){//        30代表最后一次还息回款
                System.out.println(taskInvestment);
            }
            if(taskInvestment.getExecuteType() == 40){//        40表示用户投资到期后
                System.out.println(taskInvestment);
            }
        });


    }

    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

        items.forEach(item->{
            if("C".equals(item)){
                System.out.println(item);
            }
            if("B".equals(item)){
                System.err.println(item);
            }
        });




    }
}
