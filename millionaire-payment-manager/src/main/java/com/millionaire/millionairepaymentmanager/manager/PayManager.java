package com.millionaire.millionairepaymentmanager.manager;


import com.millionaire.millionairebusinessservice.exception.TimerTaskException;
import com.millionaire.millionairebusinessservice.module.*;
import com.millionaire.millionairebusinessservice.service.*;
import com.millionaire.millionairepaymentmanager.exception.FuYouException;
import com.millionaire.millionairepaymentmanager.fuyou.H5PayServlet;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
import com.millionaire.millionairepaymentmanager.until.CalulateUntil;
import com.millionaire.millionairepaymentmanager.until.FlowNumberGeneration;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.service.UserBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Component("payManager")
public class PayManager {
    @Autowired
    private InvestmentProductService investmentProductService;

    @Autowired
    private InvestmentUserService investmentUserService;

    @Autowired
    private UserBankService userBankService;

    @Autowired
    private ReceptionUsersService receptionUsersService;

    @Autowired
    private TradingFlowService tradingFlowService;

    @Autowired
    private MessageUserService messageUserService;

    @Autowired
    private TimerTaskInvestmentService taskInvestmentService;

    private CalulateUntil calulateUntil = new CalulateUntil();

    private Logger logger = LoggerFactory.getLogger(PayManager.class);

    InvestmentUser investmentUser = new InvestmentUser();

    InvestmentProduct investmentProduct = new InvestmentProduct();

    private static final Long TIME_DAY = 24 * 60 * 60 * 1000L;


    /**
     * 交易流程管理：
     * 1.根据productId 查询产品信息
     * 2.计算：用户投资收益、起息和到期时间、用户合同编号生成、用户投资表默认状态写入
     * 3.调用富友支付接口，
     * if1.支付成功调用定时任务接口、用户消息推送、交易流水表数据插入
     * else 2.支付失败用户消息推送、交易流水表数据插入
     */
    public String payment(UserInvestmentRequestBean requestBean, long uid, int isHavingNovicePlan) throws IOException, FuYouException {

//        查询购买的产品信息
        InvestmentProduct investmentProduct = investmentProductService.selectByPrimaryKey(requestBean.getProductId());
        logger.info("产品信息：" + investmentProduct);

        if (isHavingNovicePlan == 1 && investmentProduct.getType() == 10) {
            logger.info("新手计划只能购买一次"+requestBean.getProductId());
            return "新手计划只能购买一次";
        }

        if (requestBean.getAmount() < investmentProduct.getStartingAmount()) {
            logger.info("投资金额小于起投金额"+requestBean.getAmount());
            return "投资金额小于起投金额";
        }

        /**
         * Todo bug
         */
//        查询用户信息
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);

        logger.info("用户信息：" + receptionUsers);

//        查询用户银行信息
        UserBank userBank = userBankService.selectByPrimaryKey(requestBean.getUserBankId());
        logger.info("用户银行卡信息：" + userBank);

        InvestmentUser investmentUser = new InvestmentUser();
//        插入交易银行信息
        investmentUser.setBankCardNumber(userBank.getCardNumber());
        investmentUser.setBankName(userBank.getBankName());

        investmentUser.setProductId(requestBean.getProductId());
        investmentUser.setUid(uid);
        investmentUser.setContractSign(requestBean.getContractSign());
        investmentUser.setInvestmentAmount(requestBean.getAmount());
        investmentUser.setInvestmentStatus((byte) 0);

//        调用工具类，计算用户收益（=本金*利率/360*期限）
        double income = calulateUntil.incomeCalulate(requestBean.getAmount(), investmentProduct.getAnnualizedIncome(), investmentProduct.getDeadline());

        logger.info("计算用户收益:" + income);
        investmentUser.setExpectedIncome(income);
//        未分配收益
        investmentUser.setDistributedIncome(income);
////        当前用户投资id
//        Long num = investmentUserService.selectTimeLimit() + 1;
//        logger.info("当前用户id："+num);


//        用户投资的到期时间计算
        Long valueDateStart = 0L;
        if (investmentProduct.getValueDate() == 10) { // T+0 当天开始计算利息
            valueDateStart = System.currentTimeMillis();
        } else if (investmentProduct.getValueDate() == 20) { // T+1 开始计算利息
            valueDateStart = System.currentTimeMillis() + TIME_DAY;
        } else if (investmentProduct.getValueDate() == 30) {// T+2 当天开始计算利息
            valueDateStart = System.currentTimeMillis() + TIME_DAY * 2;
        } else {
            logger.info("用户投资信息插入错误" + investmentProduct.getValueDate());
        }
        logger.info("投资到期时间：" + valueDateStart);

        Long valueDateEnd = valueDateStart + investmentProduct.getDeadline() * TIME_DAY;
        investmentUser.setValueDateStart(valueDateStart);
        investmentUser.setValueDateEnd(valueDateEnd);

//      bug修改
        investmentUser.setBankName(userBank.getBankName());
        investmentUser.setBankCardNumber(userBank.getCardNumber());

//        插入用户投资记录
        Long investmentUserId = investmentUserService.insert(investmentUser);
        //        出借合同编号
        String num = FlowNumberGeneration.lendProtocol(investmentProduct.getProductCode(), investmentUserId);
//        将出借合同编号更新至数据库中
        investmentUserService.updateLendingContractNumber(investmentUserId, num);

        logger.info("操作成功用户投资记录id" + investmentUserId);


//        交易流水生成
        TradingFlow tradingFlow = new TradingFlow();
        tradingFlow.setInvestmentUserId(investmentUserId);
        tradingFlow.setUid(uid);
        tradingFlow.setProductName(investmentProduct.getName());
        tradingFlow.setPhone(receptionUsers.getPhone().toString());
        tradingFlow.setName(receptionUsers.getIdName());
        tradingFlow.setAmount(requestBean.getAmount());
        tradingFlow.setType((byte) -1);
        tradingFlow.setBankCardId(userBank.getCardNumber());
        tradingFlow.setPayType(userBank.getCardType());
//        默认失败
        tradingFlow.setStatus((byte) 20);
        tradingFlowService.insert(tradingFlow);
        logger.info("操作成功用户交易记录id" + tradingFlow.getId());


//        用户消息的生成
        MessageUser messageUser = new MessageUser();
//        默认投资失败
        messageUser.setCode((byte) 20);
        messageUser.setInvestmentUserId(investmentUserId);
        messageUser.setUid(uid);
//        用户是否浏览过信息，默认没有看过
        messageUser.setIsLook((byte) 0);
        messageUserService.insert(messageUser);
        logger.info("操作成功用户消息记录id" + messageUser.getId());

//        调用支付接口
        H5PayServlet h5PayServlet = new H5PayServlet();

//        付款金额,富友支付以分为单位计算
        int paymentAmount = requestBean.getAmount() * 100;

        /**
         * Todo bug
         */
//        返回支付页面,传入用户投资表的id作为订单号
        return h5PayServlet.sentPost(uid, paymentAmount, receptionUsers.getIdNumber(),
                investmentUserId, userBank.getCardNumber(), receptionUsers.getIdName());
    }

    /**
     * TODO 交易流水记录也需要生成
     * 产品续投交易
     */
    public int postRenewal(Long id, String contactSign) throws TimerTaskException {
//        定义所需要查询的定时任务
        TimerTaskInvestment taskInvestment ;

        //        查询用户投资信息
        investmentUser = investmentUserService.selectByPrimaryKey(id);
//        查询产品信息
        investmentProduct = investmentProductService.selectByPrimaryKey(investmentUser.getProductId());
//        查询用户信息
        ReceptionUsers users = receptionUsersService.selectByPrimaryKey(investmentUser.getUid());

//        新手计划只允许买一次
        if (investmentProduct.getType() ==10 ){
            logger.info("新手计划产品重复购买"+id);
            return 10001;
        }


//        修改起息时间,到息时间即为起息时间
        investmentUser.setValueDateStart(investmentUser.getValueDateEnd());

        Long valueDateEnd = investmentUser.getValueDateStart() + investmentProduct.getDeadline() * TIME_DAY;
        investmentUser.setValueDateEnd(valueDateEnd);
//        匹配债权的id设为默认值null
        investmentUser.setClaimId(null);
        investmentUser.setContractSign(contactSign);
//        5续投任务
        investmentUser.setInvestmentStatus((byte) 5);
        /**
         * TODO 债权协议编号重复，bug修复
         */
        investmentUser.setLendingContractNumber("0");
        //        插入用户投资记录
        Long investmentUserId = investmentUserService.insert(investmentUser);
        //        出借合同编号
        String num = FlowNumberGeneration.lendProtocol(investmentProduct.getProductCode(), investmentUserId);
//        将出借合同编号更新至数据库中
        long newInvestmentUserId = investmentUserService.updateLendingContractNumber(investmentUserId, num);

        logger.info("操作成功续投用户投资id" + newInvestmentUserId);

//        生成用户交易记录
        TradingFlow tradingFlow = new TradingFlow();
        tradingFlow.setInvestmentUserId(newInvestmentUserId);
        tradingFlow.setUid(investmentUser.getUid());
        tradingFlow.setProductName(investmentProduct.getName());
        tradingFlow.setPhone(users.getPhone().toString());
        tradingFlow.setName(users.getIdName());
        tradingFlow.setAmount(investmentUser.getInvestmentAmount());
//        表示产品续投
        tradingFlow.setType((byte)2);
        tradingFlow.setBankCardId(investmentUser.getBankCardNumber());
        tradingFlow.setPayType(investmentUser.getBankName());
        tradingFlow.setStatus((byte)10);
        logger.info("插入的交易流水信息"+tradingFlow);

//        关于用户投资定时任务的修改
//        根据用户投资的id查询定时任务  execute_type= 10 and 30
        try {
             taskInvestment = taskInvestmentService.selectIdForRenewalInvestment(id);
        } catch (Exception e) {
            throw new TimerTaskException("用户投资定时表格数据异常，多个符合要求的任务");
        }
        if (taskInvestment == null) {
            logger.info("用户投资定时表格数据异常,未能查询数据");
            return 10002;
        }
//        查询到符合要求的最后一次还息回款的任务id，将回款金额减去本金，同时写入下一周期的还款任务
        int newPaybackAmount = taskInvestment.getPaybackAmount() - investmentUser.getInvestmentAmount() * 100;
//        修改定时任务执行类型,将关联的新的用户投资写入记录
        taskInvestmentService.updateTimerTaskForRenewal(newPaybackAmount, (byte) 40L, newInvestmentUserId,taskInvestment.getId());
        logger.info("用户投资定时表格数据已更新"+taskInvestment.getId());
        return 1;
    }
}
