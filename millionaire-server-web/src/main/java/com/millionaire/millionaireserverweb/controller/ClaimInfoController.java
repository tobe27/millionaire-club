package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.ClaimInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.request.ClaimInfoQuery;
import com.millionaire.millionairebusinessservice.service.ClaimInfoService;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Liu Kai
 * @Description: TODO 债券信息 新增 查看
 * @date 2118/8/21 10:48
 */
@RestController
@RequestMapping("/a")
public class ClaimInfoController {
 private    Logger logger = LoggerFactory.getLogger(ClaimInfoController.class);
    @Resource
    private ClaimInfoService claimInfoService;

    /**
     * @param claimInfo
     * @return 成功1 失败-1
     * @Description 新增债券信息
     **/
    @PostMapping("/claim-info")
    public ResultBean insertClaimInfo(@Validated ClaimInfo claimInfo) {
        String code = claimInfo.getClaimCode();
        ClaimInfo claimInfoCheck = claimInfoService.selectByCode(code);
        if (claimInfoCheck != null) {
            return new ResultBean(-1, "error code exist", code);
        } else {
            claimInfoService.insertSelective(claimInfo);
            Long id = claimInfo.getId();
            logger.info("新增债权信息 id:{}", id);
            return new ResultBean(1, "success", id);
        }
    }

    /**
     * @param id 债权id
     * @return 成功1 失败-1
     * @Description 产看债权详细
     **/

    @GetMapping("/claim-info/{claimId}")
    public ResultBean selectClaimInfo(@PathVariable("claimId") Long id) {
        ClaimInfo claimInfo = claimInfoService.selectByPrimaryKey(id);
        if (claimInfo == null) {
            return new ResultBean(-1, "error no such id", id);
        } else {
            logger.info("查询债权信息id：{}", id);
            return new ResultBean(1, "success", claimInfo);
        }
    }
    /**
     * @Description 分页查询债权信息
     * @param claimInfoQuery 查询参数
     * @return  成功1 失败-1
     **/
        @GetMapping("/list/claim-info")
        public ResultBean selectClaimByPage (@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                             @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                             ClaimInfoQuery claimInfoQuery) {

                PageInfo<ClaimInfo> pageInfo =
                        claimInfoService.selectClaimBypage(pageSize, pageNum, claimInfoQuery);
                logger.info("查询债权信息：{}", claimInfoQuery);
                return new ResultBean(1, "success", pageInfo);

        }

}
