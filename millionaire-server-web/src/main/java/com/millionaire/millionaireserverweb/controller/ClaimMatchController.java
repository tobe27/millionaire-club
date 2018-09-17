package com.millionaire.millionaireserverweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.ClaimMatch;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.request.ClaimMatchQuery;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import com.millionaire.millionairebusinessservice.service.ClaimMatchService;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionairebusinessservice.service.InvestmentUserService;
import com.millionaire.millionairebusinessservice.transport.ClaimMatchDTO;
import com.millionaire.millionairepaymentmanager.until.FlowNumberGeneration;
import com.millionaire.millionaireserverweb.result.ClaimMatchInvestmentUserDTO;
import com.millionaire.millionaireserverweb.result.ResultBean;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.*;

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

    @Resource
    private ReceptionUsersService receptionUsersService;

    @Resource
    private InvestmentProductService investmentProductService;


    /**
     * @Description 债券与用户投资的匹配情况
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
        List<ClaimMatchDTO> claimMatchDTOList = claimMatchService.listClaimMatchByClaimID(query);
        logger.info("查询参数:{}", query);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ClaimMatchDTO> pageInfo = new PageInfo<>(claimMatchDTOList);
//        Map map = new HashMap();
//        map.put("claimInfo", claimInfo);
//        map.put("pageInfo", pageInfo);
        return new ResultBean(1, "success", pageInfo);
    }

    /**
     * @Description 为债权推荐匹配的用户投资
     **/
    @GetMapping("/list/investment-match-claim/{claimId}")
    public ResultBean listRecommendedMatch(@PathVariable("claimId") long claimId) {
        ClaimInfo claimInfo = claimInfoService.selectByPrimaryKey(claimId);
        if (claimInfo == null) {
            logger.error("错误债权id:{}", claimId);
            return new ResultBean(-1, "error no such claimID", claimId);
        }
        logger.info("根据债权id开始推荐匹配债权信息:{}", claimId);
        List<InvestmentUser> investmentUserList = claimMatchService.listRecommendInvestmentUser(claimId);

        //将推荐用户投资参数加入包装类
        List<ClaimMatchInvestmentUserDTO> claimMatchInvestmentUserDTOList = new ArrayList<>();
        for(InvestmentUser user:investmentUserList){
            //参数包装类
            ClaimMatchInvestmentUserDTO claimMatchInvestmentUserDTO = new ClaimMatchInvestmentUserDTO();

            //查询匹配用户
            ReceptionUsers receptionUsers = receptionUsersService.selectByPrimaryKey(user.getUid());
            InvestmentProduct product = investmentProductService.selectByPrimaryKey(user.getProductId());
           // 参数封装
            claimMatchInvestmentUserDTO.setId(user.getId());
            claimMatchInvestmentUserDTO.setValueDateEnd(user.getValueDateEnd());
            claimMatchInvestmentUserDTO.setUserName(receptionUsers.getIdName());
            claimMatchInvestmentUserDTO.setProductName(product.getName());
            claimMatchInvestmentUserDTO.setLendingContractNumber(user.getLendingContractNumber());
            claimMatchInvestmentUserDTO.setInvestmentAmount(user.getInvestmentAmount());
            claimMatchInvestmentUserDTOList.add(claimMatchInvestmentUserDTO);
        }


        logger.info("推荐匹配的用户投资列表", claimMatchInvestmentUserDTOList);
        return new ResultBean(1, "success", claimMatchInvestmentUserDTOList);
    }

    /**
     * TODO 更新接口，修改用户投资匹配情况
     * @RequestParam("claimId") long claimId,
     * @RequestParam("lending_contract_number") String lendingContractNumber
     *整理逻辑整理
     * 1.参数校验
     *      claimId 有效
     *      lendingContractNumber 有效
     *      lendingContractNumber 对应的 用户投资investmentUser 处于未匹配状态且金额不大于债权未匹配金额
     *      该债权未匹配过该用户的其他投资
     * 2.用户投资为可用投资，进行匹配更新
     *       添加债权匹配记录 包括债权合同编号，用户投资id 债权id
     *       用户投资表中添加匹配的债权id
     *       债权信息表更新未匹配金额 unMatchAmount = claimInfo.getUnMatchAmount() - investmentUser.getInvestmentAmount();
     *       更新债权已匹配的比率
     **/
    @PutMapping("/investment-credit")
    public ResultBean updateInvestmentCredit(@RequestBody JSONObject jsonObject) {
           logger.info("运行步骤1");
        Long claimId = jsonObject.getLong("claimId");
        if (claimId == null) {
            return new ResultBean(-1, "error claimID", claimId);
        }
        String lendingContractNumber = jsonObject.getString("lendingContractNumber");
        if (lendingContractNumber == null) {
            return new ResultBean(-1, "error lendingContractNumber", lendingContractNumber);
        }
        ClaimInfo claimInfo = claimInfoService.selectByPrimaryKey(claimId);
        if (claimInfo == null) {
            logger.error("传入错误债权id:{}", claimId);
            return new ResultBean(-1, "error claimID", claimId);
        }
        logger.info("运行步骤2");
        InvestmentUser investmentUser = investmentUserService.selectByLendingContractNumber(lendingContractNumber);
        if (investmentUser == null) {
            logger.error("传入错误出借合同编号:{}", lendingContractNumber);
            return new ResultBean(-1, "error lendingContractNumber", lendingContractNumber);
        }
        logger.info("运行步骤3");
        //整体业务逻辑校验
        // 如果该用户投资还在匹配中 返回业务逻辑错误信息
        if (investmentUser.getClaimId() != null && investmentUser.getClaimId() != 0 ) {
            System.out.println("用户投资绑定的债权id"+investmentUser.getClaimId());
            logger.error("债权匹配规则漏洞，用户投资正在使用 用户投资：{}", investmentUser);
            return new ResultBean(-1, "error investmentUser is in using", investmentUser);
        }
        logger.info("运行步骤3.1");
        // 如果该用户投资状态不为可使用  返回业务逻辑错误信息
        // status = 10 表示可用投资
        if (investmentUser.getInvestmentStatus() != 10) {
            logger.error("债权匹配规则漏洞，用户投资不可使用 用户投资：{}", investmentUser);
            return new ResultBean(-1, "error investmentUser can not use", investmentUser);
        }
        logger.info("运行步骤3.2");
        // 如果用户投资大于债权未匹配金额 返回业务逻辑错误信息
        if (investmentUser.getInvestmentAmount() > claimInfo.getUnMatchAmount()) {
            logger.error("债权匹配规则漏洞，用户投资大于未匹配金额 用户投资：{}", investmentUser);
            return new ResultBean(-1, "error investmentUser amount > claim unMatchAmount", investmentUser);
        }
        // 如果该债权已经匹配过此用户其他的用户投资 返回业务逻辑错误信息
        //根据债权id查询用户投资列表
        logger.info("运行步骤4");
        List<InvestmentUser> investmentUserList = investmentUserService.selectMatchedInvestmentUser(claimId);
        logger.info("investmentUserList:{}",investmentUserList.size());
        if(investmentUserList.size()!=0) {
            for (InvestmentUser user : investmentUserList) {
                if (user.getUid() != null && user.getUid() != 0) {
                    if (user.getUid().equals(investmentUser.getUid())) {
                        return new ResultBean(-1, "该债权已经匹配过该用户其他的用户投资", investmentUser);
                    }
                }
            }
        }
      logger.info("匹配更新开始");

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
        //更新债权已匹配比率

        Double matchRate = 1.00-((double)unMatchAmount/(double)claimInfo.getLendingAmount());
       logger.info("matchRate：{}",matchRate);
        claimInfo.setMatchRate(matchRate);
        claimInfoService.updateByPrimaryKeySelective(claimInfo);
        logger.info("更新债权信息表id:{}", claimInfo.getId());
        return new ResultBean(1, "success");
    }


}
