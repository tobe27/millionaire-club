package com.millionaire.millionairebusinessservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.InvestmentUserQuery;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.transport.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/24 11:10
 */
@Service
public class InvestmentUserServiceImpl implements InvestmentUserService {

    private Logger logger = LoggerFactory.getLogger(InvestmentUserServiceImpl.class);
    @Resource
    private InvestmentUserMapper investmentUserMapper;

    /**
     * 用户投资记录插入，发生在用户签署合同以后
     *
     * @param record
     * @return
     */
    @Override
    public Long insert(InvestmentUser record) {
        record.setLendingContractNumber("0");
//        用户信息未查看
        record.setLook((byte)20);
        record.setGmtCreate(System.currentTimeMillis());
        record.setGmtUpdate(System.currentTimeMillis());
        investmentUserMapper.insert(record);
        logger.info("用户投资记录插入:"+record+
                "===================================================================>" );
        return record.getId();
    }

    @Override
    public Long selectTimeLimit() {
        return investmentUserMapper.selectTimeLimit();
    }

    /**
     * @param uid
     * @Description 通过用户id查询用户投资记录
     * @author Liu Kai
     */
    @Override
    public List<InvestmentUser> listInvestmentUserByUID(Long uid) {
        return investmentUserMapper.listInvestmentUserByUID(uid);
    }


    /**
     * @param query
     * @Description 通过查询参数查询用户投资记录
     * @author Liu Kai
     */
    @Override
    public List<InvestmentUserDTO> listInvestmentUserByQuery(InvestmentUserQuery query) {
        return investmentUserMapper.listInvestmentUserByQuery(query);
    }

    @Override
    public int updateInvestmentUserIdStatus(Long investmentUserId,Byte status) {
            int result = investmentUserMapper.updateInvestmentUserIdStatus(investmentUserId,status,System.currentTimeMillis());
            logger.info(investmentUserId+"用户投资状态更新"+status);
            logger.info("========================================================================================>");
        return result;
    }

    @Override
    public int updateInvestmentUserForEnd(Long investmentUserId, Byte status, Long claimId) {
        int result = investmentUserMapper.updateInvestmentUserForEnd(investmentUserId, status, claimId, System.currentTimeMillis());
        logger.info(investmentUserId+"用户投资状态到期更新"+status);
        logger.info("========================================================================================>");
        return result;
    }

    @Override
    public void updateLendingContractNumber(Long investmentUserId, String lendingContractNumber) {
        investmentUserMapper.updateLendingContractNumber(investmentUserId,lendingContractNumber,System.currentTimeMillis());
        logger.info("成功修改"+investmentUserId+"用户投资的债权协议编号"+lendingContractNumber+
        "<===========================================================================");
    }

    @Override
    public InvestmentUser selectByPrimaryKey(Long id) {
        return investmentUserMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(InvestmentUser record) {
        record.setGmtUpdate(System.currentTimeMillis());
        return investmentUserMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateById(InvestmentUser investmentUser) {
        return investmentUserMapper.updateById(investmentUser);
    }

    /**
     * @param lendingContractNumber
     * @Description 根据出借合同号查询用户投资
     */
    @Override
    public InvestmentUser selectByLendingContractNumber(String lendingContractNumber) {
        return investmentUserMapper.selectByLendingContractNumber(lendingContractNumber);
    }

    @Override
    public RenewalInvestmentDTO selectRenewalInvestmentById(Long id) {
        return investmentUserMapper.selectRenewalInvestmentById(id);
    }

    @Override
    public PageInfo listRenewalInvestments(Long end, Long now,Long uid,int pageSize,int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<RenewalInvestmentDTO> list = investmentUserMapper.listRenewalInvestments(end, now,uid);
        return new PageInfo(list);
    }

    @Override
    public ContractResponse selectContractResponse(Long id) {
        return investmentUserMapper.selectContractResponse(id);
    }


    /**
     * @param claimID
     * @Description 根据债权id查找与之匹配的用户投资
     * 与 List<Long> selectMatchedUID(long claimID); 类似，
     * 以后编写查询方法时能总体考虑最好
     */
    @Override
    public List<InvestmentUser> selectMatchedInvestmentUser(long claimID) {
        return investmentUserMapper.selectMatchedInvestmentUser(claimID);
    }

    /**
     * @Description 查看所有理财中的用户投资 用于用户投资到期的定时任务
     * 查询条件 investment_status = 10 理财中的用户投资
     **/
    @Override
    public List<InvestmentUser> listEffectInvestmentUser() {
        return investmentUserMapper.listEffectInvestmentUser();
    }

    @Override
    public List<InvestmentUsersDTO> findByUidInvestmentStatus(InvestmentUser user) {
        return investmentUserMapper.findByUidInvestmentStatus(user);
    }

    @Override
    public UserInvestmentDTO findById(Long id) {
        return investmentUserMapper.findById(id);
    }

    @Override
    public int selectExistNovicePlan(Long uid) {
        if (null==investmentUserMapper.selectExistNovicePlan(uid)||investmentUserMapper.selectExistNovicePlan(uid).isEmpty()) {
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public int updateDistributedIncome(Long investmentId, Double distributedIncome) {
        double nowDistributedIncome = investmentUserMapper.getInvestmentDistributedIncome(investmentId);
        double update = nowDistributedIncome + distributedIncome;
        int result = investmentUserMapper.updateDistributedIncome(investmentId, update, System.currentTimeMillis());
        logger.info(investmentId + "用户投资收益分配，原纪录收益" + nowDistributedIncome+"更新后收益"+update);
        logger.info("============================================================================================>");
        return result;
    }
}
