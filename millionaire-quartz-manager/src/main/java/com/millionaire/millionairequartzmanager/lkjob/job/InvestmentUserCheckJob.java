package com.millionaire.millionairequartzmanager.lkjob.job;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.MessageUser;
import com.millionaire.millionairebusinessservice.service.ClaimMatchService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.service.MessageUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 用户投资过期提醒
 * @date 2018/9/1 17:04
 */

@Slf4j
@Component
@Configuration
@EnableScheduling
public class InvestmentUserCheckJob {
    @Resource
    private InvestmentUserService investmentUserService;

    @Resource
    private MessageUserService messageUserService;


    /**
     * @Description 用户投资过期提醒定时任务
     * 步骤
     * 1.查询所有有效的用户投资 investment_status =10 理财中
     * 2.对比当前时间和到息时间，如果到息时间<当前时间，则认为该投资过期
     * 3.生成用户消息，提醒用户投资即将过期
     **/
    public void investmentUserExpireWarn() {
        log.info("用户投资过期检查定时任务启动");
        //1.查询所有有效的用户投资
        List<InvestmentUser> effectInvestmentUser = investmentUserService.listEffectInvestmentUser();
        for (InvestmentUser investmentUser : effectInvestmentUser) {
            //2. 对比当前时间和到息时间，如果到息时间小于当前时间，则认为该投资过期
            if (investmentUser.getValueDateEnd() < System.currentTimeMillis()) {
                log.info("到期用户投资id:{}", investmentUser.getId());
                //3.生成用户消息，提醒用户投资即将过期
                MessageUser messageUser = new MessageUser();
                //code = 30 表示即将过期
                messageUser.setCode((byte) 30);
                messageUser.setInvestmentUserId(investmentUser.getId());
                messageUser.setUid(investmentUser.getUid());
                messageUser.setIsLook((byte) 0);
                messageUserService.insert(messageUser);
               log.info("新增用户消息id:{}",messageUser.getId());
               log.info("新增用户消息：{}",messageUser);
            }
        }
    }


}
