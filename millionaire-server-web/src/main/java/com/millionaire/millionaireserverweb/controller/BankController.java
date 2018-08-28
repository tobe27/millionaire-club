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
     * @return 成功1 失败-1
     * @Description 添加银行信息
     **/
    @PostMapping("/bank")
    public ResultBean insertBank(@Validated Bank bank) {
        Long id = bankService.insert(bank);
        logger.info("新增银行id：{}",id);
        logger.info("新增银行信息:{}",bank);
        return new ResultBean(1, "success", bank);
    }

    /**
     * @return 成功1 失败-1
     * @Description 编辑银行信息 限额
     **/
    @PutMapping("bank/{bankId}")

    public ResultBean updateBank(@PathVariable("bankId") Long id,
                                 @RequestParam(value = "singleLimit", required = false) Double singleLimit,
                                 @RequestParam(value = "dailyLimit", required = false) Double dailyLimit,
                                  String modifier) {
        // modifier 校验未做
        // 此处可以通过modifier查找后台用户信息，如果为空，返回错误
        //因此时adminservice依赖未通 未进行校验
        Bank bank = bankService.selectByPrimaryKey(id);
        if (bank == null) {

            return new ResultBean(-1, "error no such id", id);
        }
        if (modifier == null) {
            return new ResultBean(-1, "error modifier cannot be null", modifier);
        }
        if(singleLimit != null && singleLimit < 0){
            return new  ResultBean(-1, "error 限额为负数", singleLimit);
        }
        if (dailyLimit != null && dailyLimit < 0){
            return new  ResultBean(-1, "error 限额为负数", dailyLimit);
        }
        //单笔限额不为空且大于0
        if(singleLimit != null){
            //更新单笔限额
             bank.setSingleLimit(singleLimit);
             //每日限额不为空
             if(dailyLimit != null){
                 //更新每日限额
                 bank.setDailyLimit(dailyLimit);
                 bankService.updateByPrimaryKey(bank);
                 logger.info("修改银行信息id:{},单笔限额：{}，每日限额:{}",id,singleLimit,dailyLimit);
                 logger.info("银行信息:{}",bank);
                 return new ResultBean(1, "success",bank);
             }
            bankService.updateByPrimaryKey(bank);
            logger.info("修改银行信息 id:{},单笔限额：{}",id,singleLimit);
            logger.info("银行信息:{}",bank);
            return new ResultBean(1, "success",bank);
        }
        bank.setModifier(modifier);
        bankService.updateByPrimaryKey(bank);
        logger.info("修改银行信息 id:{}",id);
        logger.info("银行信息:{}",bank);
        return new ResultBean(1, "success",bank);
    }


    /**
     * @param
     * @return 成功1 失败-1
     * @Description 查询银行列表信息
     **/
    @GetMapping("/list/bank")
    public ResultBean selectBankByPage(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                       BankQuery query) {
            PageInfo<Bank> pageInfo = bankService.selectBankByPage(pageNum, pageSize, query);
            logger.info("查询银行信息:{}", query);
            return new ResultBean(1, "success", pageInfo);
    }


    /**
     * @Description 查询银行详细信息
     **/
    @GetMapping("/bank/{bankId}")
    public  ResultBean selectBank(@PathVariable("bankId")long id){
        Bank bank= bankService.selectByPrimaryKey(id);
       logger.info("查询银行详细信息id:{}",id);
       return  new ResultBean(1,"success",bank);
    }
}