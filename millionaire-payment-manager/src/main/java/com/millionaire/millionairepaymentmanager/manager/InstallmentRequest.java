package com.millionaire.millionairepaymentmanager.manager;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairepaymentmanager.requst.InstallTaskBean;
import com.millionaire.millionairepaymentmanager.requst.InstallmentBean;
import com.millionaire.millionairepaymentmanager.until.CalulateUntil;
import lombok.extern.java.Log;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


@Component
public class InstallmentRequest {

    @Autowired
    private InvestmentProductService investmentProductService;

    CalulateUntil calulateUntil = new CalulateUntil();

    private org.slf4j.Logger logger = LoggerFactory.getLogger(InstallmentRequest.class);

    private static final Long TIME_DAY = 24 * 60 * 60 * 1000L;


    public InstallmentBean request(Long productId, int amount) {

        logger.info(productId + "用户投资计算----->");

        InstallmentBean installmentBean = new InstallmentBean();

        List list = new ArrayList();


//        查询投资产品
        InvestmentProduct investmentProduct = investmentProductService.selectByPrimaryKey(productId);

        double expectedIncome = calulateUntil.incomeCalulate(amount, investmentProduct.getAnnualizedIncome(), investmentProduct.getDeadline());

        installmentBean.setExpectIncome(expectedIncome);
        Long valueDateStart = calulateUntil.ValueDateCalculate(investmentProduct.getValueDate());
        //    到息日期
        Long valueDateEnd = valueDateStart + investmentProduct.getDeadline() * TIME_DAY;

        installmentBean.setValueDateStart(valueDateStart);
        installmentBean.setValueDateEnd(valueDateEnd);

        logger.info("产品付款类型" + investmentProduct.getRepaymentMode());
        //本息一次付款的任务写入
        if (investmentProduct.getRepaymentMode() == 10) {
            InstallTaskBean installTaskBean = new InstallTaskBean();
//            付款金额，以分为单位
            installTaskBean.setPaybackAmount(expectedIncome);
            installTaskBean.setTimes((byte) 1);
//            定时任务执行时间
            installTaskBean.setExecuteTime(valueDateEnd);
            list.add(installTaskBean);
            installmentBean.setList(list);
            return installmentBean;
        }


        //分期还息，最后一个还本和息，每月还息固定每月20号
        if (investmentProduct.getRepaymentMode() == 20) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//可以方便地修改日期格式
            String startDateFormat = dateFormat.format(valueDateStart);
            String endDateFormat = dateFormat.format(valueDateEnd);

            LocalDate startDate = LocalDate.parse(startDateFormat);
            LocalDate endDate = LocalDate.parse(endDateFormat);

            int startYear = startDate.getYear();
            int startMonth = startDate.getMonth().getValue();

            int endYear = endDate.getYear();
            int endMonth = endDate.getMonth().getValue();

//        分期付款次数
            int taskTimes = (endYear - startYear) * 12 + (endMonth - startMonth);
            logger.info("用户投资，分期付款执行次数：" + taskTimes);


            if (taskTimes == 1) {
                InstallTaskBean installTaskBean = new InstallTaskBean();
                installTaskBean.setPaybackAmount(expectedIncome);
                installTaskBean.setTimes((byte) 1);
//            定时任务执行时间
                installTaskBean.setExecuteTime(valueDateEnd);
                logger.info("用户投资，分期回款，分期次数为1，定时任务id");
                list.add(installTaskBean);
                installmentBean.setList(list);
                return installmentBean;

            } else {
//                起息日期上个月的20号，作为循环的起点
                String fixedTime = startYear + "-" + String.format("%02d", startMonth) + "-20";
                LocalDate fixedDate = LocalDate.parse(fixedTime);
                for (int i = 1; i < taskTimes; i++) {
                    InstallTaskBean installTaskBean = new InstallTaskBean();
                    if (i == 1) {
//                        计算一个月后的日期
                        LocalDate nextMonths = fixedDate.plus(i, ChronoUnit.MONTHS);
//                       计算两个日期之间的天数
                        Period periodToNext = Period.between(startDate, nextMonths);
                        int periodDays = periodToNext.getDays();
                        //            付款金额，以分为单位
                        double backAmount = (calulateUntil.incomeCalulate(amount, investmentProduct.getAnnualizedIncome(), periodDays));
                        logger.info("回息金额" + backAmount);
                        expectedIncome -= backAmount;
                        installTaskBean.setPaybackAmount(backAmount);
//                         表示本息一次付清
                        installTaskBean.setTimes((byte) i);
//                         待执行状态
//                         定时任务执行时间
//                        转换时间戳格式
                        long timerGmt = nextMonths.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                        installTaskBean.setExecuteTime(timerGmt);
                        logger.info("用户投资，分期回款，分期次数为" + taskTimes + "第" + i + "次回息" + "，定时任务id");
                        list.add(installTaskBean);

                    } else {
                        LocalDate currentMonths = fixedDate.plus(i - 1, ChronoUnit.MONTHS);
//                        获取月份长度
                        int maonthsDay = currentMonths.lengthOfMonth();
                        //            付款金额，以分为单位
                        double backAmount = calulateUntil.incomeCalulate(amount, investmentProduct.getAnnualizedIncome(), maonthsDay);
                        logger.info("回息金额" + backAmount);
                        expectedIncome -= backAmount;
                        installTaskBean.setPaybackAmount(backAmount);
                        installTaskBean.setTimes((byte) i);
//                         定时任务执行时间
                        LocalDate nextMonths = fixedDate.plus(i, ChronoUnit.MONTHS);
//                        转换时间戳格式
                        long timerGmt = nextMonths.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                        installTaskBean.setExecuteTime(timerGmt);
                        logger.info("用户投资，分期回款，分期次数为" + taskTimes + "第" + i + "次回息");
                        list.add(installTaskBean);
                    }
                }
                //            最后一次本息一次回款，将到期日期作为触发时间写入数据库
//            付款金额，以分为单位
                /**
                 * bug修复
                 */
                InstallTaskBean installTaskBean = new InstallTaskBean();
                installTaskBean.setPaybackAmount(expectedIncome);
                installTaskBean.setTimes((byte) taskTimes);
//            定时任务执行时间
                installTaskBean.setExecuteTime(valueDateEnd);
                logger.info("用户投资，最后一次分期回款，分期次数为" + taskTimes);
                list.add(installTaskBean);
                installmentBean.setList(list);
                logger.info("==================================================================================>");
                return installmentBean;
            }
        }
        logger.info("信息错误");
        return null;
    }
}





