package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.ClaimMatchQuery;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import com.millionaire.millionairebusinessservice.service.ClaimMatchService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.transport.ClaimMatchDTO;
import com.millionaire.millionairepaymentmanager.until.FlowNumberGeneration;
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
     * @Description 为债权推荐匹配的用户投资
     **/
    @GetMapping("/list/investment-match-claim/{claimId}")
    public ResultBean listRecommendedMatch(@PathVariable("claimId") long claimId) {
        ClaimInfo claimInfo = claimInfoService.selectByPrimaryKey(claimId);
        if (claimInfo == null) {
            return new ResultBean(-1, "error no such claimID", claimId);
        }
        logger.info("根据债权id开始推荐匹配债权信息:{}", claimId);
        List<InvestmentUser> investmentUserList = claimMatchService.listRecommendInvestmentUser(claimId);
        logger.info("推荐匹配的用户投资列表", investmentUserList);
        return new ResultBean(1, "success", investmentUserList);
    }

    /**
     * @Description 更新接口，修改用户投资匹配情况
     **/
    @PutMapping("/investment-credit")
    public ResultBean updateInvestmentCredit(@RequestParam("claimId") long claimId,
                                             @RequestParam("lending_contract_number") String lendingContractNumber) {
        ClaimInfo claimInfo = claimInfoService.selectByPrimaryKey(claimId);
        if (claimInfo == null) {
            return new ResultBean(-1, "error claimID", claimId);
        }
        InvestmentUser investmentUser = investmentUserService.selectByLendingContractNumber(lendingContractNumber);
        if (investmentUser == null) {
            return new ResultBean(-1, "error lendingContractNumber", lendingContractNumber);
        }

        //整体业务逻辑校验
        // 如果该用户投资还在匹配中 返回业务逻辑错误信息
        if(investmentUser.getClaimId() != 0 || investmentUser.getClaimId() != null){
            return new ResultBean(-1, "error investmentUser is in using", investmentUser);
        }
        // 如果该用户投资状态不为可使用  返回业务逻辑错误信息
        // status = 10 表示可用投资
        if(investmentUser.getInvestmentStatus() != 10){
            return new ResultBean(-1, "error investmentUser can not use", investmentUser);
        }
        // 如果用户投资大于债权未匹配金额 返回业务逻辑错误信息
        if(investmentUser.getInvestmentAmount() > claimInfo.getUnMatchAmount()){
            return new ResultBean(-1, "error investmentUser amount > claim unMatchAmount", investmentUser);
        }


        //添加债权匹配记录
        ClaimMatch claimMatch = new ClaimMatch();
        //添加用户投资id
        claimMatch.setInvestmentUserId(investmentUser.getId());
        // 添加债权信息id
        claimMatch.setClaimId(claimId);
        //添加债权合同编号
        //获取当前债权匹配表中所有所有数据记录
        long count = claimMatchService.countClaimMatch();
        //生成规则为递增数字，存入count+1
        String creditContractNumber = FlowNumberGeneration.claimProtocol(count + 1);
        claimMatch.setCreditContractNumber(creditContractNumber);
        //新增债权信息，获取新增id
        long id = claimMatchService.insertClaimMatch(claimMatch);
        logger.info("新增债权匹配信息id:{}", id);
        logger.info("新增债权匹配信息:{}", claimMatch);

        // 更新用户投资表信息
        // 更新用户投资表里的债权id
        investmentUser.setClaimId(claimId);
        investmentUserService.updateByPrimaryKeySelective(investmentUser);
        logger.info("修改用户投资表id:{}", investmentUser.getId());

        // 更新债权信息表 未匹配金额
        //剩余未匹配金额 = 债权信息未匹配金额 - 用户投资投资金额
        int unMatchAmount = claimInfo.getUnMatchAmount() - investmentUser.getInvestmentAmount();
        claimInfo.setUnMatchAmount(unMatchAmount);
        claimInfoService.updateByPrimaryKeySelective(claimInfo);
        logger.info("更新债权信息表id:{}",claimInfo.getId());
        return new ResultBean(1, "success");
    }


}
