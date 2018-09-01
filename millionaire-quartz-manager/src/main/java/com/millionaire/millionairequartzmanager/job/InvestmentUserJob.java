package com.millionaire.millionairequartzmanager.job;


import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import com.millionaire.millionairebusinessservice.service.TimerTaskInvestmentService;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairepaymentmanager.fuyou.CompanyPayServlet;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类为本息一次性付清时的调度方法
 */
@Component
public class InvestmentUserJob implements Job {

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
//                调用支付接口转账
//                修改定时任务状态
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
