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
//        10代表本息一次回款
//        20代表分期还息
//        30代表最后一次还息回款
//        40表示用户投资到期后




    }
}
