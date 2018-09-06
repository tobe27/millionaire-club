package com.millionaire.millionairebusinessservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.InvestmentUserQuery;
import com.millionaire.millionairebusinessservice.transport.*;

import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 用户交易信息查询
 * @date 2018/8/24 11:09
 */
public interface InvestmentUserService {

    /**
     * @Description 通过用户id查询用户投资记录
     * @author Liu Kai
     **/
    List<InvestmentUser> listInvestmentUserByUID(Long uid);

    Long insert(InvestmentUser record);

    /**
     * 查询最新记录的用户id
     */
    Long selectTimeLimit();

    /**
     * @Description 通过查询参数查询用户投资记录
     * @author Liu Kai
     **/
    List<InvestmentUserDTO> listInvestmentUserByQuery(InvestmentUserQuery query);

    /**
     * 修改用户投资状态
     */
    int updateInvestmentUserIdStatus(Long investmentUserId,Byte status);


  /**
   *@author qiaobao
   *@datetime  2018/9/6 5:47
   *@decribe 用户投资到期后的修改信息
   */
    int updateInvestmentUserForEnd(Long investmentUserId,Byte status,Long claimId);

    /**
     * 插入出借合同编号
     */
    void updateLendingContractNumber(Long investmentUserId, String lendingContractNumber);

    InvestmentUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InvestmentUser record);


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
    PageInfo listRenewalInvestments(Long end, Long now,Long uid,int pageSize,int pageNum);

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
     **/
    List<InvestmentUser> selectMatchedInvestmentUser(long claimID);

    /**
     * @Description 查看所有理财中的用户投资 用于用户投资到期的定时任务
     * 查询条件 investment_status = 10 理财中的用户投资
     **/
    List<InvestmentUser> listEffectInvestmentUser();

    List<InvestmentUsersDTO> findByUidInvestmentStatus(InvestmentUser user);

    UserInvestmentDTO findById(Long id);

    /**
     * 判断用户是否有购买过新手计划产品
     * @param uid
     * @return
     */
    int selectExistNovicePlan(Long uid);

    /**
     *@author qiaobao
     *@datetime  2018/9/6 20:23
     *@decribe 回款时更新用户已分配收益
     */

    int updateDistributedIncome(Long investmentId, Double distributedIncome);

}
