package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvestmentProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestmentProduct record);

    int insertSelective(InvestmentProduct record);

    InvestmentProduct selectByPrimaryKey(Long id);

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

    int updateByPrimaryKeySelective(InvestmentProduct record);

    int updateByPrimaryKey(InvestmentProduct record);


}