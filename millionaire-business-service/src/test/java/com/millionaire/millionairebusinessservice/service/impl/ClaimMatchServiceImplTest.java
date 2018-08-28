package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.ClaimInfoMapper;
import com.millionaire.millionairebusinessservice.dao.InvestmentProductMapper;
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

    @Resource
    private InvestmentProductMapper investmentProductMapper;

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
        System.out.println(sd.format(new Date(claimInfo.getExpirationDate())));
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
        //循环剔除用户投资大于未匹配投资金额的用户投资
        for (long id : uidList) {
            while (investmentUserIterator.hasNext()) {
                InvestmentUser investmentUser = investmentUserIterator.next();
                //与可用的用户投资中的uid进行比对
                //如果相等 则从可使用的用户投资表中移除该用户投资信息
                if (id == investmentUser.getUid()) {
                    System.out.println("---------剔除重复id---------");
                    System.out.println("id = " + id);
                    System.out.println("investmentUser.getUid() = " + investmentUser.getUid());
                    System.out.println("---------剔除重复id---------");
                    investmentUserIterator.remove();
                }
                //循环剔除用户投资大于未匹配投资金额的用户投资
                if (investmentUser.getInvestmentAmount() > claimInfo.getUnMatchAmount()) {
                    System.out.println("---------剔除过大用户投资---------");
                    System.out.println("investmentUser.getInvestmentAmount() = " + investmentUser.getInvestmentAmount());
                    System.out.println("claimInfo.getUnMatchAmount() = " + claimInfo.getUnMatchAmount());
                    System.out.println("---------剔除过大用户投资---------");
                    investmentUserIterator.remove();
                }
            }
        }
        System.out.println("-------剔除重复uid和用户投资大于未匹配金额后可用的用户投资表-----------");
        for (InvestmentUser investmentUser : usableInvestmentUserList) {
            System.out.println("--------------------------");
            System.out.println(investmentUser.getId());
            System.out.println(investmentUser.getUid());
            System.out.println(investmentUser.getInvestmentAmount());
            System.out.println(investmentUser.getValueDateEnd());
            System.out.println("--------------------------");
        }
        System.out.println("-------剔除重复uid和用户投资大于未匹配金额后可用的用户投资表-----------");

        //设置时间格式用于后续对比
        SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd");

        // 对用户投资进行排序
        //根据金额 时间排序
        Collections.sort(usableInvestmentUserList, new Comparator<InvestmentUser>() {
            @Override
            public int compare(InvestmentUser o1, InvestmentUser o2) {
                //金额相等后进行时间排序
                if (o1.getInvestmentAmount() == claimInfo.getLendingAmount()) {
                    //如果时间相等 排序最先
                    String investUserEndDate=sd2.format(new Date(o1.getValueDateEnd()));
                    String claimEndDate = sd2.format(new Date(claimInfo.getExpirationDate()));
                    if (investUserEndDate.equals(claimEndDate)) {
                        //获取该笔用户投资对应的投资产品的投资期限 天转化为月
                        int userPeriod = investmentProductMapper.selectByPrimaryKey(o1.getProductId()).getDeadline() / 30;
                        //如果债权出借时间小于6个月 优先匹配6个月内的投资产品
                        if (claimInfo.getLendingPeriod() <= 6) {
                            if (userPeriod <= 6) {
                                return -60;
                            }
                            return -59;
                        }
                        //如果债权出借时间大于6个月 优先匹配6个月以上的投资产品
                        if (claimInfo.getLendingPeriod() > 6) {
                            if (userPeriod > 6) {
                                return -58;
                            }
                            return -57;
                        }
                        return -50;
                    }
                    //用户投资时间大于过期时间 排序次之
                    if (o1.getValueDateEnd() > claimInfo.getExpirationDate()) {
                        return -40;
                    }
                    //用户投资时间小于过期时间 排序再次之
                    if (o1.getValueDateEnd() < claimInfo.getExpirationDate()) {
                        return -30;
                    }
                }
                //剔除后余下的用户投资金额小于未匹配金额 按照时间排序
                //用户投资时间大于过期时间 排序次之
                if (o1.getValueDateEnd() > claimInfo.getExpirationDate()) {
                    return -20;
                }
                //用户投资时间小于过期时间 排序再次之
                if (o1.getValueDateEnd() < claimInfo.getExpirationDate()) {
                    return -10;
                }
                return 0;
            }
        });
        System.out.println("-------排序后的用户投资表-----------");
        for (InvestmentUser investmentUser : usableInvestmentUserList) {
            System.out.println(investmentUser.getId());
            System.out.println(investmentUser.getUid());
            System.out.println(investmentUser.getInvestmentAmount());
            System.out.println(new Date(investmentUser.getValueDateEnd()));
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