package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.ClaimInfoMapper;
import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.ClaimMatchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ClaimMatchServiceImplTest {
    @Resource
    private ClaimMatchService claimMatchService;
    @Resource
    private ClaimInfoMapper claimInfoMapper;
    @Resource
    private InvestmentUserMapper investmentUserMapper;

    @Test
    public void listClaimMatchByClaimID() {
    }

    @Test
    public void listRecommendInvestmentUser() {
        long id = 10;
        ClaimInfo claimInfo = claimInfoMapper.selectByPrimaryKey(id);
        System.out.println("claimInfo = " + claimInfo);
        List<InvestmentUser> list = claimMatchService.listRecommendInvestmentUser(id);
        for (InvestmentUser aList : list) {

            System.out.println(aList);
        }
    }

    @Test
    public void hardTest() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long claimID = 10;
        ClaimInfo claimInfo = claimInfoMapper.selectByPrimaryKey(claimID);
        System.out.println("-------债权信息-----------");
        System.out.println(claimInfo);
        System.out.println("-------未匹配金额-----------");
        System.out.println(claimInfo.getUnMatchAmount());
        System.out.println("-------到期时间-----------");
        System.out.println( sd.format(new Date(claimInfo.getExpirationDate())));
        System.out.println("-------债权信息-----------");

        //查询出可以使用的用户投资表
        List<InvestmentUser> usableInvestmentUserList = investmentUserMapper.selectUsableInvestment();
        System.out.println("-------初始可用用户投资表-----------");
        for (InvestmentUser investmentUser : usableInvestmentUserList) {
            System.out.println(investmentUser.getId());
            System.out.println(investmentUser.getUid());
            System.out.println(investmentUser.getInvestmentAmount());
            System.out.println(investmentUser.getValueDateEnd());
        }
        System.out.println("-------初始可用用户投资表-----------");
        //同一用户同一时间多笔投资不能匹配相同债权
        //根据债权id查询用户投资列表
        List<Long> uidList = investmentUserMapper.selectMatchedUID(claimID);
        System.out.println("-------已匹配该债权用户id-----------");
        for (long id : uidList) {
            System.out.println(id);
        }
        System.out.println("-------已匹配该债权用户id-----------");

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
        System.out.println("-------剔除重复uid后可用的用户投资表-----------");
        for (InvestmentUser investmentUser : usableInvestmentUserList) {
            System.out.println("--------------------------");
            System.out.println(investmentUser.getId());
            System.out.println(investmentUser.getUid());
            System.out.println(investmentUser.getInvestmentAmount());
            System.out.println(investmentUser.getValueDateEnd());
            System.out.println("--------------------------");
        }
        System.out.println("-------剔除重复uid后可用的用户投资表-----------");
        // 对用户投资进行排序
        //根据金额排序
        Collections.sort(usableInvestmentUserList, new Comparator<InvestmentUser>() {
            @Override
            public int compare(InvestmentUser o1, InvestmentUser o2) {
                if (o1.getValueDateEnd() == claimInfo.getExpirationDate()) {
                    if (o1.getInvestmentAmount() == claimInfo.getUnMatchAmount()) {
                        return 1;
                    }

                    if (o1.getInvestmentAmount() > claimInfo.getUnMatchAmount()) {
                        return 0;
                    }
                }
                return -1;
            }
        });
        System.out.println("-------排序后的用户投资表-----------");
        for (InvestmentUser investmentUser : usableInvestmentUserList) {
            System.out.println(investmentUser.getId());
            System.out.println(investmentUser.getUid());
            System.out.println(investmentUser.getInvestmentAmount());
            System.out.println(investmentUser.getValueDateEnd());
        }
        System.out.println("-------排序后的用户投资表-----------");
//        //根据投资时间排序
//        Collections.sort(usableInvestmentUserList, new Comparator<InvestmentUser>() {
//              @Override
//            public int compare(InvestmentUser o1, InvestmentUser o2) {
//                if (o1.getValueDateEnd() == claimInfo.getExpirationDate()) {
//
//                    return 0;
//                }
//                if (o1.getInvestmentAmount() > claimInfo.getLendingAmount()) {
//                    return 1;
//                }
//                return -1;
//            }
//        });
//        System.out.println("-------时间排序-----------");
//        for (InvestmentUser investmentUser : usableInvestmentUserList) {
//            System.out.println(investmentUser.getId());
//            System.out.println(investmentUser.getUid());
//            System.out.println(investmentUser.getInvestmentAmount());
//            System.out.println(investmentUser.getValueDateEnd());
//        }
//        System.out.println("-------时间排序-----------");


    }
}