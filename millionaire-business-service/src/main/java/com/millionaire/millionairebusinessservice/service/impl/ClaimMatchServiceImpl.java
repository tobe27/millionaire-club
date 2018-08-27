package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.ClaimInfoMapper;
import com.millionaire.millionairebusinessservice.dao.ClaimMatchMapper;
import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.ClaimMatchQuery;
import com.millionaire.millionairebusinessservice.service.ClaimMatchService;
import com.millionaire.millionairebusinessservice.transport.ClaimMatchDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Liu Kai
 * @Description: TODO 债权匹配信息查询
 * @date 2018/8/27 15:02
 */
@Service
public class ClaimMatchServiceImpl implements ClaimMatchService {

    @Resource
    private ClaimMatchMapper claimMatchMapper;
    @Resource
    private InvestmentUserMapper investmentUserMapper;
    @Resource
    private ClaimInfoMapper claimInfoMapper;


    /**
     * @param query
     * @Description 根据债权信息id动态查询债权匹配信息
     */
    @Override
    public List<ClaimMatchDTO> listClaimMatchByClaimID(ClaimMatchQuery query) {
        return claimMatchMapper.listClaimMatchByClaimID(query);
    }


    /**
     * @param claimID
     * @Description 根据债权id给出推荐匹配的用户投资列表
     */
    @Override
    public List<InvestmentUser> listRecommendInvestmentUser(long claimID) {
        ClaimInfo claimInfo = claimInfoMapper.selectByPrimaryKey(claimID);
        System.out.println("-------债权信息-----------");
        System.err.println(claimID);
        System.out.println("-------债权信息-----------");
        if (claimInfo == null) {
            return null;
        }
        //查询出可以使用的用户投资表
        List<InvestmentUser> usableInvestmentUserList = investmentUserMapper.selectUsableInvestment();
        System.out.println("-------初始可用用户投资表-----------");
        for(InvestmentUser investmentUser : usableInvestmentUserList){
            System.err.println(investmentUser);
        }
        System.out.println("-------初始可用用户投资表-----------");
        //同一用户同一时间多笔投资不能匹配相同债权
        //根据债权id查询用户投资列表
        List<Long> uidList = investmentUserMapper.selectMatchedUID(claimID);
        // list在循环中删除会报错，因此使用Iterator对其进行转换
        Iterator<InvestmentUser> investmentUserIterator = usableInvestmentUserList.iterator();
        //循环取出已匹配该债权的用户id
        for (long id : uidList) {
            while (investmentUserIterator.hasNext()) {
                InvestmentUser investmentUser = investmentUserIterator.next();
                //与可用的用户投资中的uid进行比对
                //如果相等 则从可使用的用户投资表中移除该用户投资信息
                if (id == investmentUser.getUid()) {
                    System.out.println("id = " + id);
                    System.out.println("investmentUser.getUid() = " + investmentUser.getUid());
                    investmentUserIterator.remove();
                }
            }
        }
        System.out.println("usableInvestmentUserList = " + usableInvestmentUserList);
        // 对用户投资进行排序
        //根据金额排序
        Collections.sort(usableInvestmentUserList, new Comparator<InvestmentUser>() {
            @Override
            public int compare(InvestmentUser o1, InvestmentUser o2) {
                if (o1.getInvestmentAmount() == claimInfo.getLendingAmount()) {
                    return 1;
                }
                if (o1.getInvestmentAmount() > claimInfo.getLendingAmount()) {
                    return -1;
                }
                return 0;
            }
        });
        System.out.println("usableInvestmentUserList = " + usableInvestmentUserList);
        //根据投资时间排序
        Collections.sort(usableInvestmentUserList, new Comparator<InvestmentUser>() {
            @Override
            public int compare(InvestmentUser o1, InvestmentUser o2) {
                if (o1.getValueDateEnd() == claimInfo.getExpirationDate()) {
                    return 1;
                }
                if (o1.getInvestmentAmount() > claimInfo.getLendingAmount()) {
                    return -1;
                }
                return 0;
            }
        });
        System.out.println("matchableInvestmentUser = " + usableInvestmentUserList);
        return usableInvestmentUserList;
    }
}
