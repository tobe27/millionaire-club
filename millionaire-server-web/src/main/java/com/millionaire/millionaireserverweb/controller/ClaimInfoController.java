package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import com.millionaire.millionairebusinessservice.validatedgroup.InsertClaimInfoGroup;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Liu Kai
 * @Description: TODO 债券信息 新增 查看
 * @date 2018/8/21 10:48
 */
@RestController
@RequestMapping("/a")
public class ClaimInfoController {
Logger logger=LoggerFactory.getLogger(ClaimInfoController.class);
    @Resource
 private ClaimInfoService claimInfoService;

    /**
     * @param claimInfo
     * @return 成功0 失败-1
     * @Description 新增债券信息
     **/
    @PostMapping("/claim_info")
    public ResultBean insertClaimInfo(@Validated(value = {InsertClaimInfoGroup.class}) ClaimInfo claimInfo) {
        String code = claimInfo.getClaimCode();
        ClaimInfo claimInfoCheck =claimInfoService.selectByCode(code);
        if(claimInfoCheck != null){
            return new ResultBean(-1, "error code exist",code);
        }else {
            claimInfoService.insert(claimInfo);
            Long id=claimInfo.getId();
            logger.info("新增债权信息 id:{}",id);
            return new ResultBean(0, "success",id);
        }


    }






//return new ResultBean(0,"success");
}
