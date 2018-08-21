package com.millionaire.millionairebusinessservice.service.impl;

import com.millionaire.millionairebusinessservice.dao.ClaimInfoMapper;
import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/21 10:44
 */

@Service
public class ClaimInfoServiceImpl implements ClaimInfoService {

    @Resource
    ClaimInfoMapper claimInfoMapper;

    /**
     * TODO 这个实现类需要修改
     * @param claim 债券信息
     * @return 成功0 失败-1
     * @Description 新增债券信息 动态插入 封装部分参数
     * create update
     * status=0  使用状态
     * expirationDate = lendingdate + lendingperiod 到期日期=出借日期+出借期限，单位为月
     * matchAmount=0 匹配金额
     * ClaimContract ="" 债券协议
     **/
    @Override
    public Long insert(ClaimInfo claim) {
        long time = System.currentTimeMillis();
        claim.setGmtCreate(time);
        claim.setGmtUpdate(time);

//        过期时间计算（=lendingDate+lendingPeriod）转存
        Long expirationDate = claim.getLendingDate() + claim.getLendingPeriod() * (24 * 60 * 60 * 1000);
        claim.setExpirationDate(expirationDate);
        claim.setUnMatchAmount(claim.getLendingAmount());
        claim.setMatchRate(0.0);
        claim.setStatus(0);
        claimInfoMapper.insert(claim);

        return claim.getId();
    }


    /**
     * @param code 债权代码
     * @return 成功0 失败-1
     * @Description 根据债券代码查询
     **/
    @Override
    public ClaimInfo selectByCode(String code) {
        return claimInfoMapper.selectByCode(code);
    }
}