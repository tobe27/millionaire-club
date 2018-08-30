package com.millionaire.millionairequartzmanager.job;


import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairepaymentmanager.fuyou.CompanyPayServlet;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 此类为本息一次性付清时的调度方法
 */
//@Component
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

    /**
     * 扫描投资的定时任务表，筛选出符合条件的记录，执行任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


//        扫描定时任务表格，每次只删选100条记录避免，任务太多，发生阻塞（筛选条件：日期到期和状态为0的）

//        每次只扫描100条数据的实现就是一个分页查询的封装
//        先查出有多少数据（count）、每页显示的条数是100 算出一共有多少页，最后确定每页的数据
//        首先需要







    }
}
