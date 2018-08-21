package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClaimInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClaimInfo record);

    int insertSelective(ClaimInfo record);

    ClaimInfo selectByPrimaryKey(Long id);

    ClaimInfo selectByCode(String code);

    int updateByPrimaryKeySelective(ClaimInfo record);

    int updateByPrimaryKey(ClaimInfo record);

    /**
     * @Description  查询所有债券信息
     * @param
     * @return
     **/
  List<ClaimInfo> listClaimInfo();
}