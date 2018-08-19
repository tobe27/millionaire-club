package com.millionaire.millionairebusinessservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.dao.InvestmentProductMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.ProductQuery;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * @return 成功返回产品id 错误返回0
     * @Description 新增产品 封装创建时间和更新时间 返回产品id
     **/
    @Override
    public Long insertProductSelective(InvestmentProduct investmentProduct) {
        //封装创建时间和更新时间
        long time = System.currentTimeMillis();
        investmentProduct.setGmtCreate(time);
        investmentProduct.setGmtUpdate(time);
        investmentProductMapper.insertSelective(investmentProduct);
        Long id = investmentProduct.getId();
//        logger.info("新增投资产品 :{}", id);
        return id;
    }


    /**
     * @param investmentProduct 投资产品
     * @return id 成功 返回编辑投资产品id 失败抛出异常
     * @Description 编辑投资产品方法一 不限定参数 包装更新时间
     **/
    @Override
    public Long updateProductByPrimaryKeySelective(InvestmentProduct investmentProduct)  {
        Long id = investmentProduct.getId();
        //封装更新时间
        investmentProduct.setGmtUpdate(System.currentTimeMillis());
        investmentProductMapper.updateByPrimaryKeySelective(investmentProduct);
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
        InvestmentProduct product1 = investmentProductMapper.selectByPrimaryKey(id);
        if (product1 == null) {
            logger.info("更新产品失败，产品id:{}不存在", id);
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
//        logger.info("编辑投资产品 :{}", id);
        investmentProductMapper.updateByPrimaryKeySelective(product);
        return id;
    }

    /**
     * @param id
     * @return 投资产品
     * @Description 通过id查找 投资产品
     **/
    @Override
    public InvestmentProduct selectByPrimaryKey(Long id)  {
        return investmentProductMapper.selectByPrimaryKey(id);
    }

    /**
     * @return
     * @Description 分页查询所有投资产品 不含查询参数
     **/
    @Override
    public PageInfo<InvestmentProduct> selectByPage(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<InvestmentProduct> productList = investmentProductMapper.selectAll();
        PageInfo<InvestmentProduct> pageInfo = new PageInfo<InvestmentProduct>(productList);
//        logger.info("查询产品页:{}", pageNum);
        return pageInfo;
    }

    /**
     * @param productQuery 包装投资产品参数类
     * @param pageSize
     * @param pageNum
     * @return 投资产品列表
     * @Description 投资产品分页查看 包含查询参数
     **/
    @Override
    public PageInfo<InvestmentProduct> selectProductByPage(ProductQuery productQuery,
                                                           Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<InvestmentProduct> productList = investmentProductMapper.selectProductByPage(productQuery);
        PageInfo<InvestmentProduct> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }

    /**
     * @param name 产品名称
     * @return 投资产品
     * @Description 根据产品名称查询产品
     **/
    @Override
    public InvestmentProduct selectByProductName(String name) {
        return investmentProductMapper.selectByProductName(name);
    }

    /**
     * @param code
     * @return 投资产品
     * @Description 根据产品代号查询产品
     **/
    @Override
    public InvestmentProduct selectByProductCode(String code) {
        return investmentProductMapper.selectByProductCode(code);
    }
}
