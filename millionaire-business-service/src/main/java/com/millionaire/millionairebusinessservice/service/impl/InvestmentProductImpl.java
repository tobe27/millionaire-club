package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.InvestmentProductMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Liu Kai
 * @Description: TODO 投资产品 新增 编辑 上下架
 * @date 2018/8/17 19:46
 */
@Service
public class InvestmentProductImpl implements InvestmentProductService {
    /**
     * 投资产品mmapper
     */
    @Autowired
    InvestmentProductMapper investmentProductMapper;

    /**
     * @param investmentProduct 投资产品
     * @return id
     * @Description  新增产品 封装创建时间和更新时间 返回产品id
     **/
    @Override
    public Long insertProductSelective(InvestmentProduct investmentProduct) {
           long time=System.currentTimeMillis();
           investmentProduct.setGmtCreate(time);
           investmentProduct.setGmtUpdate(time);
           investmentProductMapper.insertSelective(investmentProduct);            ;
        return investmentProduct.getId();
    }


    /**
     * @param investmentProduct 投资
     * @return 返回编辑投资产品id
     * @Description 编辑投资产品
     **/
    @Override
    public Long updateProductByPrimaryKeySelective(InvestmentProduct investmentProduct) {
        return null;
    }
}
