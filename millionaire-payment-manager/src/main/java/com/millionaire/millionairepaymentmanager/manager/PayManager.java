package com.millionaire.millionairepaymentmanager.manager;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.MessageUser;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import com.millionaire.millionairebusinessservice.service.TradingFlowService;
import com.millionaire.millionairepaymentmanager.fuyou.H5PayServlet;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
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


    private Logger logger = LoggerFactory.getLogger(PayManager.class);

    private static final int TIME_DAY = 24 * 60 * 60 * 1000;



    /**
     * 交易流程管理：
     * 1.根据productId 查询产品信息
     * 2.计算：用户投资收益、起息和到期时间、用户合同编号生成、用户投资表默认状态写入
     * 3.调用富友支付接口，
     * if1.支付成功调用定时任务接口、用户消息推送、交易流水表数据插入
     * else 2.支付失败用户消息推送、交易流水表数据插入
     */
    public String payment(UserInvestmentRequestBean requestBean, long uid) throws IOException {

//        查询购买的产品信息
        InvestmentProduct investmentProduct = investmentProductService.selectByPrimaryKey(requestBean.getProductId());
        logger.info("产品信息："+investmentProduct);

//        查询用户信息
        ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(uid);

        logger.info("用户信息："+receptionUsers);

//        查询用户银行信息
        UserBank userBank = userBankService.selectByPrimaryKey(requestBean.getUserBankId());
        logger.info("用户银行卡信息："+userBank);

        InvestmentUser investmentUser = new InvestmentUser();
        investmentUser.setProductId(requestBean.getProductId());
        investmentUser.setUid(uid);
        investmentUser.setContractSign(requestBean.getContractSign());
        investmentUser.setInvestmentAmount(requestBean.getAmount());
        investmentUser.setInvestmentStatus((byte) 0);

//        计算用户收益（=产品收益率*投资金额*到期时间）
        double income = investmentProduct.getAnnualizedIncome() * requestBean.getAmount() * investmentProduct.getDeadline();
        logger.info("计算用户收益:"+income);
        investmentUser.setExpectedIncome(income);
//        未分配收益
        investmentUser.setDistributedIncome(income);
//        当前用户投资id
        Long num = investmentUserService.selectTimeLimit() + 1;
        logger.info("当前用户id："+num);
        //        出借合同编号
        investmentUser.setLendingContractNumber(FlowNumberGeneration.lendProtocol(investmentProduct.getProductCode(), num));

//        用户投资的到期时间计算
        Long valueDateStart = 0L;
        if (investmentProduct.getValueDate() == 10) { // T+0 当天开始计算利息
            valueDateStart = System.currentTimeMillis();
        } else if (investmentProduct.getValueDate() == 20) { // T+1 开始计算利息
            valueDateStart = System.currentTimeMillis() + TIME_DAY;
        } else if (investmentProduct.getValueDate() == 30) {// T+2 当天开始计算利息
            valueDateStart = System.currentTimeMillis() + TIME_DAY*2;
        } else {
            logger.info("用户投资信息插入错误" + investmentProduct.getValueDate());
        }
        logger.info("投资到期时间："+valueDateStart);

        Long valueDateEnd = valueDateStart + investmentProduct.getDeadline() * TIME_DAY;
        investmentUser.setValueDateStart(valueDateStart);
        investmentUser.setValueDateEnd(valueDateEnd);
//        插入用户投资记录
        Long investmentUserId = investmentUserService.insert(investmentUser);
        logger.info("操作成功用户投资记录id"+investmentUserId);


//        交易流水生成
        TradingFlow tradingFlow = new TradingFlow();
        tradingFlow.setUid(uid);
        tradingFlow.setProductName(investmentProduct.getName());
        tradingFlow.setPhone(receptionUsers.getPhone().toString());
        tradingFlow.setName(receptionUsers.getIdName());
        tradingFlow.setAmount(requestBean.getAmount());
        tradingFlow.setType((byte)-1);
        tradingFlow.setBankCardId(userBank.getCardNumber());
        tradingFlow.setPayType(userBank.getCardType());
//        默认失败
        tradingFlow.setStatus((byte)20);
        tradingFlowService.insert(tradingFlow);
        logger.info("操作成功用户交易记录id"+tradingFlow.getId());


//        用户消息的生成
        MessageUser messageUser = new MessageUser();
//        默认投资失败
        messageUser.setCode((byte)20);
        messageUser.setInvestmentUserId(investmentUserId);
        messageUser.setUid(uid);
//        用户是否浏览过信息，默认没有看过
        messageUser.setIsLook((byte)0);
        messageUserService.insert(messageUser);
        logger.info("操作成功用户消息记录id"+messageUser.getId());


//        调用支付接口
        H5PayServlet h5PayServlet = new H5PayServlet();

//        返回支付页面
        return h5PayServlet.sentPost(uid,requestBean.getAmount(),receptionUsers.getIdNumber(),
                investmentUser.getLendingContractNumber(), userBank.getCardNumber(),receptionUsers.getIdName());
    }
}
