package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.request.BankQuery;
import com.millionaire.millionairemanagerservice.service.BankService;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Liu Kai
 * @Description: TODO 后台银行管理
 * @date 2018/8/21 15:52
 */

@RestController
@RequestMapping("/a")
public class BankController {
Logger logger=LoggerFactory.getLogger(BankController.class);

    @Resource
    private BankService bankService;

    /**
     * @param
     * @return 成功0 失败-1
     * @Description 查询银行信息
     **/
    @GetMapping("/list/bank")
    public ResultBean selectBankByPage(@RequestParam("pageSize") Integer pageSize,
                                       @RequestParam("pageNum") Integer pageNum,
                                       BankQuery query) {
        if (pageNum == null || pageSize == null) {
            logger.info("页码为空:{}或每页数为空:{}", pageNum, pageSize);
            return new ResultBean(-1, "error pageSize or pageNum is null");
        } else {
            PageInfo<Bank> pageInfo = bankService.selectBankByPage(pageNum, pageSize, query);
            logger.info("查询银行信息:{}",query);
         return new ResultBean(0, "success", pageInfo);
        }
    }
}