package com.millionaire.millionairebusinessservice.dao;

import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.InvestmentUserQuery;
import com.millionaire.millionairebusinessservice.transport.*;
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
     *
     * @param investmentUserId
     * @return
     */
    int updateInvestmentUserIdStatus(@Param("id") Long investmentUserId,
                                     @Param("investmentStatus") Byte investmentStatus,
                                     @Param("gmtUpdate") Long gmtUpdate);


    /**
     * 合同编号的插入
     *
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
     * 壮壮接口
     *
     * @param user
     * @return
     */
    List<InvestmentUsersDTO> findByUidInvestmentStatus(InvestmentUser user);

    /**
     * @Description 查询可用的用户投资，claimId = 0 或 claimId is null
     * 用户投资状态 investment_status =10
     **/
    List<InvestmentUser> selectUsableInvestment();

    List<Long> selectMatchedUID(long claimID);

    UserInvestmentDTO findById(Long id);

    /**
     * @Description 根据出借合同号查询用户投资
     **/
    InvestmentUser selectByLendingContractNumber(String lendingContractNumber);

    /**
     * 查询可续投用户投资信息，详情
     */
    RenewalInvestmentDTO selectRenewalInvestmentById(Long id);

    /**
     * 查询可续投产品列表
     */
    List<RenewalInvestmentDTO> listRenewalInvestments(@Param("end") Long end, @Param("now") Long now,@Param("uid")Long uid);

    /**
     * 查询投资合同信息
     * @param id
     * @return
     */
    ContractResponse selectContractResponse(Long id);


    /**
     * @Description 根据债权id查找与之匹配的用户投资
     *  与 List<Long> selectMatchedUID(long claimID); 类似，
     *  以后编写查询方法时能总体考虑最好
     *  查询条件 claimid  investment_status =10可使用的投资
     *
     **/
    List<InvestmentUser> selectMatchedInvestmentUser(long claimID);


/**
 * @Description 查看所有理财中的用户投资 用于用户投资到期的定时任务
 * 查询条件 investment_status = 10 理财中的用户投资
 **/
    List<InvestmentUser> listEffectInvestmentUser();

    /**
     * 判断用户是否有购买过新手计划产品
     * @param uid
     * @return
     */
    List<RenewalInvestmentDTO> selectExistNovicePlan(Long uid);


    /**
     *@author qiaobao
     *@datetime  2018/9/6 5:47
     *@decribe 用户投资到期后的修改信息
     */
    int updateInvestmentUserForEnd(@Param("id") Long investmentUserId,
                                   @Param("status") Byte status,
                                   @Param("claimId") Long claimId,
                                   @Param("gmtUpdate") Long gmtUpdate);



}