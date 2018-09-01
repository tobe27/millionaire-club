package com.millionaire.millionairequartzmanager.lkjob.job;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.ClaimMatchService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 用户投资相关的定时任务 用户投资过期 乔师兄已经编写
 * @date 2018/9/1 17:04
 */

@Slf4j
public class InvestmentUserCheckJob {
    @Resource
    private InvestmentUserService investmentUserService;

    @Resource
    private ClaimMatchService claimMatchService;

    /**
     * @Description 用户投资过期定时任务
     * 步骤
     * 1.查询所有有效的用户投资 investment_status =10 理财中
     * 2.对比当前时间和到息时间，如果到息时间小于当前时间，则认为该投资过期
     * 修改用户投资状态为退出中 investment_status =20 退出中
     * 3.查看该用户投资对应的债权匹配表记录，若存在 设置该债权匹配记录状态为0 已过期
     * 4.查看该用户投资对应的债权信息，修改债权信息的未匹配金额 = 未匹配金额+用户投资金额
     * <p>
     * 5.调用支付接口 支付用户本金+利息
     **/
    public void investentUserEffectCheck() {
        log.info("用户投资过期检查定时任务启动");
        //查询所有有效的用户投资
        List<InvestmentUser> effectInvestmentUser = investmentUserService.listEffectInvestmentUser();
        for (InvestmentUser investmentUser : effectInvestmentUser) {
            // 对比当前时间和到息时间，如果到息时间小于当前时间，则认为该投资过期
            if (investmentUser.getValueDateEnd() < System.currentTimeMillis()) {
                log.info("到期用户投资id:{}", investmentUser.getId());
                // 修改用户投资状态为退出中 investment_status =20 退出中
                investmentUser.setInvestmentStatus((byte) 20);
                investmentUserService.updateByPrimaryKeySelective(investmentUser);
                log.info("修改到期用户投资信息：{}", investmentUser);

                //查看该用户投资对应的债权匹配表记录，设置该债权匹配记录状态为0 已过期
                ClaimMatch claimMatch = claimMatchService.selectEffectByInvestmentUID(investmentUser.getId());
                // 若存在有效的匹配信息 设置该债权匹配记录状态为0 已过期
                if (claimMatch != null) {
                    log.info("需要修改的债权匹配 id：{}", claimMatch.getId());
                    claimMatch.setStatus((byte) 0);
                    claimMatchService.updateByPrimaryKeySelective(claimMatch);
                    log.info("修改债权匹配状态:{}", claimMatch);
                }
        // 查看该用户投资对应的债权信息，修改债权信息的未匹配金额 = 未匹配金额+用户投资金额
//                ClaimInfo claimInfo =
            }
        }
    }


}
