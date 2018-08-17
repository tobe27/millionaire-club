package com.millionaire.millionairebusinessservice.service;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import org.springframework.stereotype.Service;

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



}
