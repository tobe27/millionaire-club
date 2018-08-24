package com.millionaire.millionairepaymentmanager.manager;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairepaymentmanager.PayMananger;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
import com.millionaire.millionairepaymentmanager.until.FlowNumberGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class InvestmentUserInsert {
    @Autowired
    private InvestmentProductService investmentProductService;

    @Autowired
    private InvestmentUserService investmentUserService;

    private Logger logger = LoggerFactory.getLogger(PayMananger.class);

    private static final int TIME_DAY = 24 * 60 * 60 * 1000;

    InvestmentUser investmentUser = new InvestmentUser();

    /**
     * 交易流程管理：
     * 1.根据productId 查询产品信息
     * 2.计算：用户投资收益、起息和到期时间、用户合同编号生成、用户投资表默认状态写入
     * 3.调用富友支付接口，
     * if1.支付成功调用定时任务接口、用户消息推送、交易流水表数据插入
     * else 2.支付失败用户消息推送、交易流水表数据插入
     */
    public void investmentUserInsert(UserInvestmentRequestBean requestBean, long uid) {

//        查询购买的产品信息
        InvestmentProduct investmentProduct = investmentProductService.selectByPrimaryKey(requestBean.getProductId());

        investmentUser.setProductId(requestBean.getProductId());
        investmentUser.setUid(uid);
        investmentUser.setContractSign(requestBean.getContractSign());
        investmentUser.setInvestmentAmount(requestBean.getAmount());
        investmentUser.setInvestmentStatus((byte) 0);

//        计算用户收益（=产品收益率*投资金额*到期时间）
        double income = investmentProduct.getAnnualizedIncome() * requestBean.getAmount() * investmentProduct.getDeadline();
        investmentUser.setExpectedIncome(income);
//        未分配收益
        investmentUser.setDistributedIncome(income);
//        当前用户id
        Long num = investmentUserService.selectTimeLimit() + 1;
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

        Long valueDateEnd = valueDateStart + investmentProduct.getDeadline() * TIME_DAY;
        investmentUser.setValueDateStart(valueDateStart);
        investmentUser.setValueDateEnd(valueDateEnd);

//        插入插入用户投资记录
        Long investmentUserId = investmentUserService.insert(investmentUser);
        logger.info("插入用户投资记录id"+investmentUserId);


//        用户交易记录的生成


        return ;
    }
}
