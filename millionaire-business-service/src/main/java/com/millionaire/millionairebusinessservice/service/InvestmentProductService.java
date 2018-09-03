package com.millionaire.millionairebusinessservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.request.ProductQuery;

import java.util.List;

/**
        * @author Liu Kai
        * @Description: TODO 投资产品 新增 编辑 上下架
        * @date 2018/8/17 18:57
        */

public interface InvestmentProductService {

    /**
     * @param investmentProduct 投资产品
     * @return 新增投资产品id
     * @Description 新增产品 返回产品id
     **/
    Long insertProductSelective(InvestmentProduct investmentProduct);

    /**
     * @param investmentProduct 投资产品
     * @return 受影响行数
     * @Description 编辑投资产品方法一 不限定参数 包装更新时间
     **/
    int updateProductByPrimaryKeySelective(InvestmentProduct investmentProduct);

    /**
     * @param id              产品id
     * @param type            产品分类
     * @param isRecommend     推荐
     * @param isPurchaseLimit 限购
     * @return 收影响行数
     * @Description 编辑投资产品方法二 限定只能更新1.产品分类 2.推荐 3.限购 包装更新时间
     **/
    int updateProductByPrimaryKeyLimit(Long id, Byte type, Byte isRecommend, Byte isPurchaseLimit);


    /**
     * @param id
     * @return 投资产品
     * @Description 通过id查找 投资产品
     **/
    InvestmentProduct selectByPrimaryKey(Long id);

    /**
     * @param pageNum
     * @param pageSize
     * @return
     * @Description 分页查询所有投资产品 无参数
     **/
    PageInfo<InvestmentProduct> selectByPage(int pageSize, int pageNum);

    /**
     * @param pageSize
     * @param pageNum
     * @param productQuery 包装投资产品参数类
     * @return 投资产品列表
     * @Description 投资产品分页查看 包含查询参数
     **/
    PageInfo<InvestmentProduct> selectProductByPage(ProductQuery productQuery,
                                                    Integer pageSize, Integer pageNum);

    /**
     * @param name 产品名称
     * @return 投资产品
     * @Description 根据产品名称查询产品
     **/
    InvestmentProduct selectByProductName(String name);


    /**
     * @param code
     * @return 投资产品
     * @Description 根据产品代号查询产品
     **/
    InvestmentProduct selectByProductCode(String code);

    /**
     * Todo bug 修复
     * 所有上架的产品查询（无参数）
     * @return
     */
    PageInfo listProductOnShelf(int pageSize, int pageNum);

    /**
     * Todo 推荐产品 is_recommend =1  and is_shelf = 1
     * @return
     */
    List<InvestmentProduct> listProductsRecommend();
}
