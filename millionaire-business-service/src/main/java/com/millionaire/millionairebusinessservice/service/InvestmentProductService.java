package com.millionaire.millionairebusinessservice.service;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 投资产品 新增 编辑 上下架
 * @date 2018/8/17 18:57
 */
@Service
public interface InvestmentProductService {

    /**
     * @Description  新增产品 返回产品id
     * @param investmentProduct 投资产品
     * @return    新增投资产品id
     **/
Long insertProductSelective(InvestmentProduct investmentProduct);

/**
 * @Description 编辑投资产品方法一 不限定参数 包装更新时间
 * @param  investmentProduct 投资产品
 * @return 返回编辑投资产品id
 **/
Long updateProductByPrimaryKeySelective(InvestmentProduct investmentProduct);

/**
 * @Description 编辑投资产品方法二 限定只能更新1.产品分类 2.推荐 3.限购 包装更新时间
 * @param id 产品id
 * @param type 产品分类
 * @param isRecommend 推荐
 * @param isPurchaseLimit 限购
 * @return 返回编辑投资产品id
 **/
Long updateProductByPrimaryKeyLimit(Long id,Integer type,Byte isRecommend, Byte isPurchaseLimit);

/**
 * @Description 查看投资产品
 * @param
 * @return
 **/

//List<InvestmentProduct> select


}
