package com.millionaire.millionairequartzmanager.lkjob.job;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 债权信息到期状态变更  债权到期提醒 债权警戒限度提醒
 * @date 2018/8/31 18:41
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class ClaimInfoCheckJob {

@Resource
private ClaimInfoService claimInfoService;


    /**
     * @Description 债权到期状态变更 未完成
     **/
     public void  claimInfoExpire(){
         // 获取所有未过期的债权信息
         List<ClaimInfo> claimInfoCheckList = claimInfoService.selectClaimExpireCheck();
         log.info("债权过期定期任务启动：{}",new Date());
         //循环取出所有债权信息进行过期时间比对
         for(ClaimInfo claimInfo:claimInfoCheckList){
             // 如果债权信息的过期时间 小于当前时间 债权即为过期债权
             if(claimInfo.getExpirationDate() < System.currentTimeMillis()){
                 //债权设置为过期债权
                claimInfo.setStatus(2);
                claimInfoService.updateByPrimaryKeySelective(claimInfo);
                log.info("更新过期债权id：{}",claimInfo.getId());












             }

         }


     }



}
