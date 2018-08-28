package com.millionaire.millionairequartzmanager.job;


import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairepaymentmanager.fuyou.CompanyPayServlet;
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

    /**
     * 调度任务执行方法
     * 1。时间触发调用后台给用户转账的方法
     * 调用成功后修改用户投资状态
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        if (companyPayServlet.httpURLConnectionPOST())





    }
}
