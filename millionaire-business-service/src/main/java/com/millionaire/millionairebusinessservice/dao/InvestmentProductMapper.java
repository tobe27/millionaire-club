package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.request.ProductQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvestmentProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestmentProduct record);

    int insertSelective(InvestmentProduct record);

    InvestmentProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvestmentProduct record);

    int updateByPrimaryKey(InvestmentProduct record);

    /**
     * @param code
     * @return 投资产品
     * @Description 根据产品代号查询产品
     **/
    InvestmentProduct selectByProductCode(String code);

    /**
     * @param name 产品名称
     * @return 投资产品
     * @Description 根据产品名称查询产品
     **/
    InvestmentProduct selectByProductName(String name);

    /**
     * @param
     * @return  投资产品列表
     * @Description 投资产品分页查看 按照更新时间降序排列
     **/
    List<InvestmentProduct> selectAll();

    /**
     * @Description 投资产品分页查看 包含查询参数
     * @param productQuery 包装投资产品参数类
     * @return 投资产品列表 模糊查询
     **/
    List<InvestmentProduct> selectProductByPage(ProductQuery productQuery);

    /**
     * 所有上架的产品查询（无参数）
     * @return
     */
    List<InvestmentProduct> listProductOnShelf(int pageSize, int pageNum);

}