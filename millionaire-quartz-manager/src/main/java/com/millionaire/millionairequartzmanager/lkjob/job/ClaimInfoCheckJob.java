package com.millionaire.millionairequartzmanager.lkjob.job;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import com.millionaire.millionairebusinessservice.service.ClaimMatchService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 债权信息到期状态变更  债权到期提醒
 * @date 2018/8/31 18:41
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class ClaimInfoCheckJob {

    @Resource
    private ClaimInfoService claimInfoService;

    @Resource
    private InvestmentUserService investmentUserService;

    @Resource
    private ClaimMatchService claimMatchService;

    @Resource
    private RedisTemplate redisTemplate;


    /**
     * @Description 债权到期状态变更
     * 判断过期 债权信息的过期时间 小于当前时间 债权即为过期债权
     * 步骤
     * 1 将到期债权信息状态更新为已过期 status =2
     * 2.将与之匹配的用户投资表中绑定的债权id设为0
     * 3.将债权匹配表中的状态设为0 过期
     **/
    public void claimInfoExpire() {
        // 获取所有未过期的债权信息
        List<ClaimInfo> claimInfoCheckList = claimInfoService.selectClaimExpireCheck();
        log.info("债权过期查看定时任务启动");
        //循环取出所有债权信息进行过期时间比对
        for (ClaimInfo claimInfo : claimInfoCheckList) {
            // 如果债权信息的过期时间 小于当前时间 债权即为过期债权
            if (claimInfo.getExpirationDate() < System.currentTimeMillis()) {
                //步骤一
                //债权设置为过期债权
                claimInfo.setStatus(2);
                claimInfoService.updateByPrimaryKeySelective(claimInfo);
                log.info("更新过期债权id：{}", claimInfo.getId());
                log.info("过期债权信息;{}", claimInfo);

                //步骤二
                //查询出与之匹配的可用用户投资信息
                List<InvestmentUser> matchedInvestmentUser =
                        investmentUserService.selectMatchedInvestmentUser(claimInfo.getId());
                for (InvestmentUser investmentUser : matchedInvestmentUser) {
                    // 将用户投资表中的债权id设为0
                    log.info("更新用户投资的债权绑定 id:{}", claimInfo.getId());
                    investmentUser.setClaimId((long) 0);
                    investmentUserService.updateByPrimaryKeySelective(investmentUser);
                    log.info("更新用户投资信息:{}", claimInfo);
                }

                //步骤三
                //将债权匹配表中的状态设为0 过期
                List<ClaimMatch> effectClaimMatchList =
                        claimMatchService.listEffectClaimMatchByClaimID(claimInfo.getId());
                for (ClaimMatch claimMatch : effectClaimMatchList) {
                    log.info("更新债权匹配信息id：{}", claimMatch.getClaimId());
                    claimMatch.setStatus((byte) 0);
                    claimMatchService.updateByPrimaryKeySelective(claimMatch);
                    log.info("更新债权匹配信息:{}", claimMatch);
                }
            }
        }
    }

    /**
     * @Description 债权到期提醒  短信提醒未做
     * 从redis缓存中取出设置的到期提醒提前天数 与有效的债权过期时间计算
     **/
    public void claimInfoExpireWarn() {
        log.info("债权到期提醒任务启动");
        // 获取所有未过期的债权信息
        List<ClaimInfo> claimInfoCheckList = claimInfoService.selectClaimExpireCheck();
        //claimEnd    债权到期提前天数
        int claimEnd = (int) redisTemplate.opsForValue().get("claimEnd");
        //计算减去到期提醒天数后的过期对比时间
        long expireWarningDay = System.currentTimeMillis() - claimEnd * 24 * 60 * 60 * 1000;
        for (ClaimInfo claimInfo : claimInfoCheckList) {
            // 债权过期时间 小于 提醒时间 即为需要提醒过期的债权
            if (claimInfo.getExpirationDate() < expireWarningDay) {
                log.info("即将到期债权:{}",claimInfo);
                log.info("提醒管理员债权即将到期");
            }
        }
    }

   /**
    * @Description 债权投资警戒线提醒 短信提醒未作
    * 查询可用债权信息中未匹配金额占总金额的比例
    **/
 public  void  claimInfoUnmatchLineWarn(){
       log.info("债权投资警戒提醒任务启动");
     // 获取所有未过期的债权信息
     List<ClaimInfo> claimInfoCheckList = claimInfoService.selectClaimExpireCheck();
     // 从redis中取出投资警戒设定值 0-100  /100 即表示百分比
     int claimLine = (int) redisTemplate.opsForValue().get("claimLine");
     //循环取出债权信息对比未匹配金额占比 小于警戒线 进行提醒
     for(ClaimInfo claimInfo : claimInfoCheckList){
         if(claimInfo.getUnMatchAmount()/claimInfo.getLendingAmount()*100 < claimLine){
             log.info("债权投资低于设定警戒线");
             log.info("低于警戒线的债权信息:{}",claimInfo);
         }
     }
 }


}
