package com.millionaire.millionairebusinessservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.dao.InvestmentProductMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 投资产品 新增 编辑 上下架
 * @date 2018/8/17 19:46
 */
@Service
public class InvestmentProductImpl implements InvestmentProductService {
    Logger logger = LoggerFactory.getLogger(InvestmentProductImpl.class);
    /**
     * 投资产品mmapper
     */
    @Resource
    InvestmentProductMapper investmentProductMapper;

    /**
     * @param investmentProduct 投资产品
     * @return id
     * @Description 新增产品 封装创建时间和更新时间 返回产品id
     **/
    @Override
    public Long insertProductSelective(InvestmentProduct investmentProduct) {
        //判断产品代号是否重复 若重复返回0
        String productCode = investmentProduct.getProductCode();
        String name = investmentProduct.getName();
        InvestmentProduct product1 = investmentProductMapper.selectByProductCode(productCode);
        InvestmentProduct product2 = investmentProductMapper.selectByProductName(name);
        if (product1 != null) {
            logger.info("产品代号重复:{}", productCode);
            return 0L;
        }
        //判断产品名称是否重复，若重复，返回0
        if (product2 != null) {
            logger.info("产品名称重复:{}",name);
            return 0L;
        }
        long time = System.currentTimeMillis();
        //创建时间和更新时间
        investmentProduct.setGmtCreate(time);
        investmentProduct.setGmtUpdate(time);
        investmentProductMapper.insertSelective(investmentProduct);
        Long id = investmentProduct.getId();
        logger.info("新增投资产品 :{}", id);
        return id;
    }


    /**
     * @param investmentProduct 投资产品
     * @return id 成功 返回编辑投资产品id 失败返回0
     * @Description 编辑投资产品方法一 不限定参数 包装更新时间
     **/
    @Override
    public Long updateProductByPrimaryKeySelective(InvestmentProduct investmentProduct) {
        Long id = investmentProduct.getId();
        //判断产品是否存在
        InvestmentProduct product= investmentProductMapper.selectByPrimaryKey(id);
        if(product==null){
            logger.info("更新产品失败，产品id:{}不存在",id);
            return 0L;
        }
        investmentProduct.setGmtUpdate(System.currentTimeMillis());
        investmentProductMapper.updateByPrimaryKeySelective(investmentProduct);

        logger.info("编辑投资产品 :{}", id);
        return id;
    }

    /**
     * @param id              产品id
     * @param type            产品分类
     * @param isRecommend     推荐
     * @param isPurchaseLimit 限购
     * @return 成功 返回编辑投资产品id 异常返回0
     * @Description 编辑投资产品方法二 限定只能更新1.产品分类 2.推荐 3.限购 包装更新时间
     **/
    @Override
    public Long updateProductByPrimaryKeyLimit(Long id, Integer type, Byte isRecommend, Byte isPurchaseLimit) {
        InvestmentProduct product1= investmentProductMapper.selectByPrimaryKey(id);
        if(product1==null){
            logger.info("更新产品失败，产品id:{}不存在",id);
            return 0L;
        }
        InvestmentProduct product = investmentProductMapper.selectByPrimaryKey(id);
        //产品分类
        product.setType(type);
        //推荐
        product.setIsRecommend(isRecommend);
        //限购
        product.setIsPurchaseLimit(isPurchaseLimit);
        //更新时间
        product.setGmtUpdate(System.currentTimeMillis());
        logger.info("编辑投资产品 :{}", id);
        investmentProductMapper.updateByPrimaryKeySelective(product);
        return id;
    }

    /**
     * @return
     * @Description 分页查询所有投资产品
     **/
    @Override
    public PageInfo<InvestmentProduct> selectByPage(int pageSize,int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<InvestmentProduct> productList=investmentProductMapper.selectAll();
        PageInfo<InvestmentProduct> pageInfo=new PageInfo<InvestmentProduct>(productList);
        logger.info("查询产品页:{}",pageNum);
        return pageInfo;
    }
}
