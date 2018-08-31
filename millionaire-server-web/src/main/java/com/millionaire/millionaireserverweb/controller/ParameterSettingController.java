package com.millionaire.millionaireserverweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Liu Kai
 * @Description: TODO 设置后台系统参数 放入redis中
 * @date 2018/8/31 9:29
 */
@RestController
@RequestMapping("/a")
@Validated
public class ParameterSettingController {
private Logger logger = LoggerFactory.getLogger(ParameterSettingController.class);
    @Resource
    private RedisTemplate redisTemplate;
      /**
       * @Description 讲以下
       * seal 印章
       * investmentEnd 投资到期消息提前天数
       * claimEnd    债权到期提前天数
       * claimLine   总债权投满警戒线
       *
       * @RequestParam("seal")@NotBlank String seal,
       *      @RequestParam("investmentEnd") @Min(0) int investmentEnd,
       *          @RequestParam("claimEnd") @Min(0) int claimEnd,
       *        @RequestParam("claimLine")@Min(0)@Max(100) int claimLine
       **/
    @PutMapping("/system-param")
    public ResultBean systemParamSetting(@RequestBody JSONObject jsonObject){

        String seal = jsonObject.getString("seal");
        if(seal == null){
            return new ResultBean(-1,"error null seal",seal);
        }
        int investmentEnd = jsonObject.getIntValue("investmentEnd");
        if(investmentEnd < 10 ){
            return new ResultBean(-1,"error investmentEnd",investmentEnd);
        }
        int claimEnd = jsonObject.getIntValue("claimEnd");
        if(claimEnd < 10 ){
            return new ResultBean(-1,"error claimEnd",claimEnd);
        }
        int claimLine = jsonObject.getIntValue("claimLine");
        if(claimLine < 0 | claimLine >100){
            return new ResultBean(-1,"error claimLine",claimLine);
        }

        logger.info("上传印章: {}",seal);
        redisTemplate.opsForValue().set("seal",seal);
        logger.info("修改投资到期天数提醒: {}",investmentEnd);
        redisTemplate.opsForValue().set("investmentEnd",investmentEnd);
        logger.info("修改债权到期提醒天数:{}",claimEnd);
        redisTemplate.opsForValue().set("claimEnd",claimEnd);
        logger.info("修改债权投满警戒线体醒");
        redisTemplate.opsForValue().set("claimLine",claimLine);

    return new ResultBean(1,"success");
    }


}
