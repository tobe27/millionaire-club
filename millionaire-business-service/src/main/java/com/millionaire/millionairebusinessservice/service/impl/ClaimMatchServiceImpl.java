package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.ClaimInfoMapper;
import com.millionaire.millionairebusinessservice.dao.ClaimMatchMapper;
import com.millionaire.millionairebusinessservice.dao.InvestmentProductMapper;
import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.ClaimMatchQuery;
import com.millionaire.millionairebusinessservice.service.ClaimMatchService;
import com.millionaire.millionairebusinessservice.transport.ClaimMatchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Liu Kai
 * @Description: TODO 债权匹配信息查询
 * @date 2018/8/27 15:02
 */
@Service
public class ClaimMatchServiceImpl implements ClaimMatchService {
 private   Logger logger =LoggerFactory.getLogger(ClaimMatchServiceImpl.class);

    @Resource
    private ClaimMatchMapper claimMatchMapper;
    @Resource
    private InvestmentUserMapper investmentUserMapper;
    @Resource
    private ClaimInfoMapper claimInfoMapper;
    @Resource
    private InvestmentProductMapper investmentProductMapper;

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
        logger.info("-------债权信息-----------");
        logger.info("债权信息:{}",claimInfo);
        logger.info("-------债权信息-----------");
        if (claimInfo == null) {
            return null;
        }
        //查询出可以使用的用户投资表
        List<InvestmentUser> usableInvestmentUserList = investmentUserMapper.selectUsableInvestment();
        System.out.println("初始可用用户投资表总数"+usableInvestmentUserList.size());
        logger.info("-------初始可用用户投资表-----------");

        for (InvestmentUser investmentUser : usableInvestmentUserList) {
            logger.info("初始可用用户投资表:{}",investmentUser);
        }
        logger.info("-------初始可用用户投资表-----------");
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
                    logger.info("---------剔除重复id用户投资---------");
                    logger.info("id = " + id);
                    logger.info("investmentUser.getUid() = " + investmentUser.getUid());
                    logger.info("---------剔除重复id用户投资---------");
                    investmentUserIterator.remove();
                }
            }
        }
        System.out.println("剔除重复id后可用用户投资数"+usableInvestmentUserList.size());
        System.out.println("剔除重复id后可用用户投资usableInvestmentUserList = " + usableInvestmentUserList);
         Iterator<InvestmentUser> investmentUserIterator2 = usableInvestmentUserList.iterator();
        while (investmentUserIterator2.hasNext()){
            InvestmentUser investmentUser = investmentUserIterator2.next();
            //循环剔除用户投资大于未匹配投资金额的用户投资
            if (investmentUser.getInvestmentAmount() > claimInfo.getUnMatchAmount()) {
                logger.info("---------剔除过大用户投资---------");
                logger.info("investmentUser.getInvestmentAmount() = " + investmentUser.getInvestmentAmount());
                logger.info("claimInfo.getUnMatchAmount() = " + claimInfo.getUnMatchAmount());
                logger.info("---------剔除过大用户投资---------");
                investmentUserIterator2.remove();
            }
        }
        System.out.println("剔除过大用户投资后可用用户投资数"+usableInvestmentUserList.size());
        System.out.println("剔除过大用户投资可用用户投资usableInvestmentUserList = " + usableInvestmentUserList);


        logger.info("-------剔除后可用用户投资表-----------");
        for (InvestmentUser investmentUser : usableInvestmentUserList) {
            logger.info("剔除后可用用户投资表:{}",investmentUser);
        }
        logger.info("-------剔除后可用用户投资表-----------");

        //设置时间格式用于后续对比
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");

        // 对用户投资进行排序
        //根据金额时间排序
        Collections.sort(usableInvestmentUserList, new Comparator<InvestmentUser>() {
            @Override
            public int compare(InvestmentUser o1, InvestmentUser o2) {
                //金额相等优先级最高
                if (o1.getInvestmentAmount() == claimInfo.getLendingAmount()) {
                    //随后时间相等排序优先
                    // 用户投资结束时间转化为年月日的string类型
                    String investUserEndDate=sd.format(new Date(o1.getValueDateEnd()));
                    // 债权信息结束时间转化为年月日的string类型
                    String claimEndDate = sd.format(new Date(claimInfo.getExpirationDate()));
                    //对比两个string格式的时间 一致
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
        logger.info("-------根据金额时间排序后可用用户投资表-----------");
        for (InvestmentUser investmentUser : usableInvestmentUserList) {
            logger.info("根据金额时间排序后可用用户投资表:{}",investmentUser);
        }
        logger.info("-------根据金额时间排序后可用用户投资表-----------");
        return usableInvestmentUserList;
    }

    /**
     * @param claimMatch
     * @Description 新增债权匹配信息
     */
    @Override
    public Long insertClaimMatch(ClaimMatch claimMatch) {
        long time = System.currentTimeMillis();
        claimMatch.setGmtCreate(time);
        claimMatch.setGmtUpdate(time);
        // status =1 表示该债权匹配信息有效
        claimMatch.setStatus((byte) 1);
        claimMatchMapper.insert(claimMatch);
        logger.info("生成在债权匹配"+claimMatch.getInvestmentUserId());
        logger.info("=================================================================================>");
        return claimMatch.getId();
    }

    /**
     * @Description 获取当前存储的债权协议编号总数
     **/
    @Override
    public long countClaimMatch() {
        return claimMatchMapper.countClaimMatch();
    }


    /**
     * @param claimID
     * @Description 根据债权id 查找有效的债权匹配信息
     * status = 1 表示该债权可用
     */
    @Override
    public List<ClaimMatch> listEffectClaimMatchByClaimID(long claimID) {
        return claimMatchMapper.listEffectClaimMatchByClaimID(claimID);
    }


    /**
     * @param record
     * @Description 动态更新债权匹配信息 返回债权信息id
     */
    @Override
    public long updateByPrimaryKeySelective(ClaimMatch record) {
        record.setGmtUpdate(System.currentTimeMillis());
        claimMatchMapper.updateByPrimaryKeySelective(record);
        return record.getId();
    }

    /**
     * @param investmentUserID
     * @Description 根据用户投资id查找当前有效的债权匹配信息
     * 一份用户投资同一时间只有一份有效的债权匹配信息
     * 查询条件
     * investmentUserID
     * status=1 该债权匹配信息有效
     **/
    @Override
    public ClaimMatch selectEffectByInvestmentUID(long investmentUserID) {
        return claimMatchMapper.selectEffectByInvestmentUID(investmentUserID);
    }

    @Override
    public int updateStatus(Long id, Byte status) {
        int result = claimMatchMapper.updateStatus(id, status, System.currentTimeMillis());
        logger.info(id+"修改债权匹配信息"+status);
        logger.info("=========================================================================");
        return result;
    }
}
