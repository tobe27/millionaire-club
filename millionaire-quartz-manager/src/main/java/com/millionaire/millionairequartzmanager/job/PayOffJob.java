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

/**
 * 此类为本息一次性付清时的调度方法
 */
public class PayOffJob implements Job {

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
     * 扫描用户投资表，筛选出正在理财中
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        if (companyPayServlet.httpURLConnectionPOST())





    }
}
