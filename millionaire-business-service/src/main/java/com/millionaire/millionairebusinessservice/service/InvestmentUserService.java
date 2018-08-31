package com.millionaire.millionairebusinessservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.InvestmentUserQuery;
import com.millionaire.millionairebusinessservice.transport.ContractResponse;
import com.millionaire.millionairebusinessservice.transport.InvestmentUserDTO;
import com.millionaire.millionairebusinessservice.transport.RenewalInvestmentDTO;

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
     * 插入出借合同编号
     */
    int updateLendingContractNumber(Long investmentUserId, String lendingContractNumber);

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
    PageInfo listRenewalInvestments(Long end, Long now,int pageSize,int pageNum);

    /**
     * 查询投资合同信息
     * @param id
     * @return
     */
    ContractResponse selectContractResponse(Long id);
}
