package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.InvestmentProductMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Liu Kai
 * @Description: TODO 投资产品 新增 编辑 上下架
 * @date 2018/8/17 19:46
 */
@Service
public class InvestmentProductImpl implements InvestmentProductService {
    Logger logger=LoggerFactory.getLogger(InvestmentProductImpl.class);
    /**
     * 投资产品mmapper
     */
    @Resource
    InvestmentProductMapper investmentProductMapper;

    /**
     * @param investmentProduct 投资产品
     * @return id
     * @Description  新增产品 封装创建时间和更新时间 返回产品id
     **/
    @Override
    public Long insertProductSelective(InvestmentProduct investmentProduct) {
           long time=System.currentTimeMillis();
           //创建时间和更新时间
           investmentProduct.setGmtCreate(time);
           investmentProduct.setGmtUpdate(time);
           investmentProductMapper.insertSelective(investmentProduct);
           Long id=investmentProduct.getId();
           logger.info("新增投资产品 :{}",id);
        return id;
    }


    /**
     * @param investmentProduct 投资产品
     * @return id 返回编辑投资产品id
     * @Description 编辑投资产品方法一 不限定参数 包装更新时间
     **/
    @Override
    public Long updateProductByPrimaryKeySelective(InvestmentProduct investmentProduct) {
        investmentProduct.setGmtUpdate(System.currentTimeMillis());
        investmentProductMapper.updateByPrimaryKeySelective(investmentProduct);
        Long id=investmentProduct.getId();
        logger.info("编辑投资产品 :{}",id);
        return id;
    }

    /**
     * @param  id 产品id
     * @param type            产品分类
     * @param isRecommend     推荐
     * @param isPurchaseLimit 限购
     * @return 返回编辑投资产品id
     * @Description 编辑投资产品方法二 限定只能更新1.产品分类 2.推荐 3.限购 包装更新时间
     **/
    @Override
    public Long updateProductByPrimaryKeyLimit(Long id,Integer type, Byte isRecommend, Byte isPurchaseLimit) {
        logger.info("编辑投资产品 :{}",id);
        InvestmentProduct product=investmentProductMapper.selectByPrimaryKey(id);
        //产品分类
        product.setType(type);
        //推荐
        product.setIsRecommend(isRecommend);
        //限购
        product.setIsPurchaseLimit(isPurchaseLimit);
        //更新时间
        product.setGmtUpdate(System.currentTimeMillis());
        investmentProductMapper.updateByPrimaryKeySelective(product);
        return id;
    }
}
