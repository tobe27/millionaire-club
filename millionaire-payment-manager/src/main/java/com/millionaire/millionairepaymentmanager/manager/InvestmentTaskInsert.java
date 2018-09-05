package com.millionaire.millionairepaymentmanager.manager;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.TimerTaskInvestment;
import com.millionaire.millionairebusinessservice.service.*;
import com.millionaire.millionairepaymentmanager.until.CalulateUntil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Component
public class InvestmentTaskInsert {

    private Logger logger = LoggerFactory.getLogger(InvestmentTaskInsert.class);

    @Autowired
    private InvestmentUserService investmentUserService;

    @Autowired
    private InvestmentProductService investmentProductService;

    @Autowired
    private TimerTaskInvestmentService timerTaskInvestmentService;

    private CalulateUntil calulateUntil = new CalulateUntil();


    public void insert(Long investmentUserId) {

        logger.info(investmentUserId+"用户投资的写入定时任务----->");

        //        查询用户投资信息
        InvestmentUser investmentUser = investmentUserService.selectByPrimaryKey(investmentUserId);
//        查询投资产品
        InvestmentProduct investmentProduct = investmentProductService.selectByPrimaryKey(investmentUser.getProductId());

//        写入定时任务
        TimerTaskInvestment timerTaskInvestment = new TimerTaskInvestment();

        timerTaskInvestment.setInvestmentUserId(investmentUserId);

//      付款方式
        Byte repaymentMode = investmentProduct.getRepaymentMode();
//      投资金额
        int amount = investmentUser.getInvestmentAmount();
//        预期收入
        double expectedIncome = investmentUser.getExpectedIncome();
//        起息时间
        long valueDateStart = investmentUser.getValueDateStart();
//        到息时间
        long valueDateEnd = investmentUser.getValueDateEnd();
//        年收益率
        double annualizedIncome = investmentProduct.getAnnualizedIncome();


        /**
         * todo bug修复
         */
        logger.info("产品付款类型"+repaymentMode);
        //本息一次付款的任务写入
        if (repaymentMode == 10) {
//            付款金额，以分为单位
            int backAmount = (int) ((amount + expectedIncome) * 100);
            timerTaskInvestment.setPaybackAmount(backAmount);
//            表示本息一次付清
            timerTaskInvestment.setExecuteType((byte) 10);
            timerTaskInvestment.setTimes((byte) 1);
//            待执行状态
            timerTaskInvestment.setStatus((byte) 0);
//            定时任务执行时间
            timerTaskInvestment.setExecuteTime(valueDateEnd);
            Long id = timerTaskInvestmentService.insert(timerTaskInvestment);
            logger.info("定时任务信息："+timerTaskInvestment);
            logger.info(investmentUserId + "用户投资,本息一次付清，定时任务id" + id);

            return;
        }


//            以分为单位  总回息金额(投资期望收益*100)
        int expectIncomeForPenny = (int) (investmentUser.getExpectedIncome() * 100);
        //分期还息，最后一个还本和息，每月还息固定每月20号
        if (repaymentMode == 20) {
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
            logger.info(investmentUserId + "用户投资，分期付款执行次数：" + taskTimes);

            logger.info("总收益"+expectIncomeForPenny);

            if (taskTimes == 1) {
//            将到期日期作为触发时间写入数据库
//            付款金额，以分为单位
                int backAmount = (int) ((amount + expectedIncome) * 100);
                timerTaskInvestment.setPaybackAmount(backAmount);

//            表示本息一次付清
                timerTaskInvestment.setExecuteType((byte) 30);
                timerTaskInvestment.setTimes((byte) 1);
//            待执行状态
                timerTaskInvestment.setStatus((byte) 0);
//            定时任务执行时间
                timerTaskInvestment.setExecuteTime(valueDateEnd);
                Long id = timerTaskInvestmentService.insert(timerTaskInvestment);

                logger.info("定时任务信息："+timerTaskInvestment);
                logger.info(investmentUserId + "用户投资，分期回款，分期次数为1，定时任务id" + id);

            } else {
//                起息日期上个月的20号，作为循环的起点
                String fixedTime = startYear + "-" + String.format("%02d", startMonth) + "-20";
                LocalDate fixedDate = LocalDate.parse(fixedTime);
                for (int i = 1; i < taskTimes; i++) {

                    if (i == 1) {
//                        计算一个月后的日期
                        LocalDate nextMonths = fixedDate.plus(i, ChronoUnit.MONTHS);
//                       计算两个日期之间的天数
                        Period periodToNext = Period.between(startDate,nextMonths);
                        int periodDays = periodToNext.getDays();
                        //            付款金额，以分为单位
                        int backAmount = (int) (calulateUntil.incomeCalulate(amount, annualizedIncome, periodDays) * 100);
                        logger.info("回息金额" + backAmount);
                        expectIncomeForPenny -= backAmount;
                        timerTaskInvestment.setPaybackAmount(backAmount);
//                         表示本息一次付清
                        timerTaskInvestment.setExecuteType((byte) 20);
                        timerTaskInvestment.setTimes((byte) i);
//                         待执行状态
                        timerTaskInvestment.setStatus((byte) 0);
//                         定时任务执行时间
//                        转换时间戳格式
                        long timerGmt = nextMonths.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                        timerTaskInvestment.setExecuteTime(timerGmt);
                        logger.info("回息时间："+nextMonths);
                        Long id = timerTaskInvestmentService.insert(timerTaskInvestment);
                        logger.info("定时任务信息："+timerTaskInvestment);
                        logger.info(investmentUserId + "用户投资，分期回款，分期次数为" + taskTimes + "第" + i + "次回息" + "，定时任务id" + id);

                    } else {
                        LocalDate currentMonths = fixedDate.plus(i - 1, ChronoUnit.MONTHS);
//                        获取月份长度
                        int maonthsDay = currentMonths.lengthOfMonth();
                        //            付款金额，以分为单位
                        int backAmount = (int) (calulateUntil.incomeCalulate(amount, annualizedIncome, maonthsDay) * 100);
                        logger.info("回息金额" + backAmount);
                        expectIncomeForPenny -= backAmount;
                        timerTaskInvestment.setPaybackAmount(backAmount);
//                         表示本息一次付清
                        timerTaskInvestment.setExecuteType((byte) 20);
                        timerTaskInvestment.setTimes((byte) i);
//                         待执行状态
                        timerTaskInvestment.setStatus((byte) 0);
//                         定时任务执行时间
                        LocalDate nextMonths = fixedDate.plus(i, ChronoUnit.MONTHS);
//                        转换时间戳格式
                        long timerGmt = nextMonths.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
                        timerTaskInvestment.setExecuteTime(timerGmt);
                        Long id = timerTaskInvestmentService.insert(timerTaskInvestment);
                        logger.info("回息时间："+nextMonths);
                        logger.info("定时任务信息："+timerTaskInvestment);
                        logger.info(investmentUserId + "用户投资，分期回款，分期次数为" + taskTimes + "第" + i + "次回息" + "，定时任务id" + id);
                    }
                }
                //            最后一次本息一次回款，将到期日期作为触发时间写入数据库
//            付款金额，以分为单位
                /**
                 * bug修复
                 */
                int backAmount = amount*100 + expectIncomeForPenny;
                timerTaskInvestment.setPaybackAmount(backAmount);
//            表示本息一次付清
                timerTaskInvestment.setExecuteType((byte) 30);
                timerTaskInvestment.setTimes((byte) taskTimes);
//            待执行状态
                timerTaskInvestment.setStatus((byte) 0);
//            定时任务执行时间
                timerTaskInvestment.setExecuteTime(valueDateEnd);
                Long id = timerTaskInvestmentService.insert(timerTaskInvestment);
                logger.info("定时任务信息："+timerTaskInvestment);
                logger.info(investmentUserId + "用户投资，最后一次分期回款，分期次数为" + taskTimes + "，定时任务id" + id);
            }

        }
    }
}
