package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.Proposal;
import com.millionaire.millionairemanagerservice.request.ProposalQuery;
import com.millionaire.millionairemanagerservice.service.ProposalService;
import com.millionaire.millionairemanagerservice.transport.UserProposalDTO;
import com.millionaire.millionaireserverweb.request.UserProposalQuery;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Liu Kai
 * @Description: TODO 用户意见反馈
 * @date 2018/8/23 13:28
 */
@RestController
@RequestMapping("a")
public class ProposalController {
    private Logger logger = LoggerFactory.getLogger(ProposalController.class);

    @Resource
    private ProposalService proposalService;

    /**
     * @Description 查看意见反馈列表
     **/
    @GetMapping("/list/proposal")
    public ResultBean listProposalByPage(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                         @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                         ProposalQuery query) {
        PageInfo<UserProposalDTO> pageInfo=proposalService.listProposalByPage(pageNum,pageSize,query);
       logger.info("查询用户意见反馈列表:{}",query);
        return new ResultBean(0, "success",pageInfo);
    }

    /**
     * @Description 查看用户意见详细
     **/
    @GetMapping("/proposal/{proposalId}")
    public ResultBean selectProposal(@PathVariable("proposalId") Long id) {
        UserProposalDTO proposal = proposalService.selectUserProposalById(id);
        logger.info("查询反馈意见详细id：{}",id);
        return new ResultBean(0, "success", proposal);
    }


    /**
     * @Description 删除用户意见
     **/
    @DeleteMapping("/proposal/{proposalId}")
    public ResultBean deleteProposal(@PathVariable("proposalId") Long id) {
        proposalService.deleteByPrimaryKey(id);
        logger.info("删除用户意见id:{}",id);
        return new ResultBean(0, "success", id);
    }

}
