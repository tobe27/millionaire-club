package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.ClaimMatchQuery;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import com.millionaire.millionairebusinessservice.service.ClaimMatchService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.transport.ClaimMatchDTO;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Liu Kai
 * @Description: TODO 债权匹配
 * @date 2018/8/27 11:45
 */
@RestController
@Validated
@RequestMapping("/a")
public class ClaimMatchController {
    private Logger logger = LoggerFactory.getLogger(ClaimMatchController.class);

    @Resource
    private ClaimInfoService claimInfoService;

    @Resource
    private ClaimMatchService claimMatchService;

    @Resource
    private InvestmentUserService investmentUserService;

    /**
     * @Description 债券与用户投资的匹配情况（成功交易未匹配的信息）
     **/
    @GetMapping("/list/claim-match/{claimid}")
    public ResultBean listClaimMatch(@PathVariable("claimid") long claimid,
                                     @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                     ClaimMatchQuery query) {
        ClaimInfo claimInfo = claimInfoService.selectByPrimaryKey(claimid);
        if (claimInfo == null) {
            return new ResultBean(-1, "error no such id", claimid);
        }
        query.setClaimID(claimid);
        logger.info("查询债权匹配信息，债权id:{}", claimid);
        logger.info("查询参数:{}", query);
        List<ClaimMatchDTO> claimMatchDTOList = claimMatchService.listClaimMatchByClaimID(query);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ClaimMatchDTO> pageInfo = new PageInfo<>(claimMatchDTOList);
        Map map = new HashMap();
        map.put("claimInfo", claimInfo);
        map.put("pageInfo", pageInfo);
        return new ResultBean(1, "success", map);
    }

    /**
     * @Description 为债权推荐匹配的用户投资    未完成
     **/
 @GetMapping("/list/investment-match-claim/{claimId}")
    public ResultBean listRecommendedMatch(@PathVariable("claimId")long claimId){
        ClaimInfo claimInfo=claimInfoService.selectByPrimaryKey(claimId);
        if (claimInfo == null){
            return new ResultBean(-1,"error no such claimID",claimId);
        }

        return new ResultBean(1,"success");
 }

 /**
  * @Description 更新接口，修改用户投资匹配情况 未完成
  **/
@PutMapping("/investment-credit")
public  ResultBean updateInvestmentCredit(@RequestParam("claimId")long claimId,
                                          @RequestParam("lending_contract_number")@NotBlank String lendingContractNumber){
ClaimInfo claimInfo=claimInfoService.selectByPrimaryKey(claimId);
if(claimInfo== null){
    return new ResultBean(-1,"error claimID",claimId);
}


  return null;
}



}
