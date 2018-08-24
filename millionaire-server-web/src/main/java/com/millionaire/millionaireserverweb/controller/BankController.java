package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionairemanagerservice.module.Bank;
import com.millionaire.millionairemanagerservice.request.BankQuery;
import com.millionaire.millionairemanagerservice.service.BankService;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.awt.geom.RoundRectangle2D;

/**
 * @author Liu Kai
 * @Description: TODO 后台银行管理
 * @date 2018/8/21 15:52
 */

@RestController
@RequestMapping("/a")
public class BankController {
    private Logger logger = LoggerFactory.getLogger(BankController.class);

    @Resource
    private BankService bankService;

    /**
     * @param bank 银行信息
     * @return 成功0 失败-1
     * @Description 添加银行信息
     **/
    @PostMapping("/bank")
    public ResultBean insertBank(@Validated Bank bank) {
        Long id = bankService.insert(bank);
        return new ResultBean(0, "success", id);
    }

    /**
     * @return 成功0 失败-1
     * @Description 编辑银行信息 限额
     **/
    @PutMapping("bank/{bankId}")
    public ResultBean updateBank(@PathVariable("bankId") Long id,
                                 @RequestParam(value = "singleLimit", required = false) Double singleLimit,
                                 @RequestParam(value = "dailyLimit", required = false) Double dailyLimit,
                                 String modifier) {
        Bank bank = bankService.selectByPrimaryKey(id);
        if (bank == null) {
            return new ResultBean(-1, "error no such id", id);
        }
        if (modifier == null) {
            return new ResultBean(-1, "error modifier cannot be null", modifier);
        }
        if (singleLimit != null) {
            bank.setSingleLimit(singleLimit);
        }
        if (dailyLimit != null) {
            bank.setDailyLimit(dailyLimit);
        }
        bank.setModifier(modifier);
        bankService.updateByPrimaryKey(bank);
        logger.info("修改银行信息 id:{},单笔限额:{},每日限额:{},修改人:{}", id, singleLimit, dailyLimit, modifier);
        return new ResultBean(0, "success");
    }


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
            logger.info("查询银行信息:{}", query);
            return new ResultBean(0, "success", pageInfo);
        }
    }
}