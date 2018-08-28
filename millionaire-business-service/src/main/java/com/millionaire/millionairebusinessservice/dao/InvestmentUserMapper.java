package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.InvestmentUserQuery;
import com.millionaire.millionairebusinessservice.transport.InvestmentUserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvestmentUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InvestmentUser record);

    int insertSelective(InvestmentUser record);

    InvestmentUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvestmentUser record);

    int updateByPrimaryKey(InvestmentUser record);

    /**
     * @Description 通过用户id查询用户投资记录
     * @author Liu Kai
     **/
    List<InvestmentUser> listInvestmentUserByUID(Long uid);

    /**
     * @Description 通过查询参数查询用户投资记录
     * @author Liu Kai
     **/
    List<InvestmentUserDTO> listInvestmentUserByQuery(InvestmentUserQuery query);

    //    查询最新的用户id
    Long selectTimeLimit();

    /**
     *壮壮接口
     * @param user
     * @return
     */
    List<InvestmentUser> findByUidInvestmentStatus(InvestmentUser user);

    /**
     * @Description  查询可用的用户投资，claimId = 0 或 claimId is null
    **/
    List<InvestmentUser> selectUsableInvestment();

    List<Long> selectMatchedUID(long claimID);

    /**
     * 修改用户投资状态
     */
    int updateInvestmentUserIdStatus(Long investmentUserId,Byte status,Long gmtUpdate);

    /**
     * 插入出借合同编号
     */
    int updateLendingContractNumber(Long investmentUserId, String lendingContractNumber,Long gmtUpdate);


}