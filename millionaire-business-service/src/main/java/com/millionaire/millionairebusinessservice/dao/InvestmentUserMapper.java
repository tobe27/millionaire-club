package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.InvestmentUserQuery;
import com.millionaire.millionairebusinessservice.transport.InvestmentUserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 支付成功修改用户投资状态
     * @param investmentUserId
     * @return
     */
    int updateInvestmentUserIdStatus(@Param("id") Long investmentUserId,
                                     @Param("investmentStatus") Byte investmentStatus,
                                     @Param("gmtUpdate") Long gmtUpdate) ;


    /**
     * 合同编号的插入
     * @param investmentUserId
     * @param lendingContractNumber
     * @param gmtUpdate
     * @return
     */
    int updateLendingContractNumber(@Param("id") Long investmentUserId,
                                    @Param("lendingContractNumber") String lendingContractNumber,
                                    @Param("gmtUpdate") Long gmtUpdate);


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



}