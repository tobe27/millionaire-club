package com.millionaire.millionairepaymentmanager;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairepaymentmanager.requst.UserInvestmentRequestBean;
import org.springframework.beans.factory.annotation.Autowired;

public class PayMananger {

    @Autowired
    private InvestmentProductService investmentProductService;

    InvestmentUser investmentUser = new InvestmentUser();

    /**
     *交易流程管理：
     * 1.根据productId 查询产品信息
     * 2.计算：用户投资收益、起息和到期时间、用户合同编号生成、用户投资表默认状态写入
     * 3.调用富友支付接口，
     * if1.支付成功调用定时任务接口、用户消息推送、交易流水表数据插入
     * else 2.支付失败用户消息推送、交易流水表数据插入
     */
    public void payManager(UserInvestmentRequestBean requestBean,long uid) {
//        查询购买的产品信息
        InvestmentProduct investmentProduct=investmentProductService.selectByPrimaryKey(requestBean.getProductId());

    }




}
