package com.millionaire.millionaireserverweb.controller;


import com.github.pagehelper.PageInfo;
import com.millionaire.millionairebusinessservice.module.InvestmentProduct;
import com.millionaire.millionairebusinessservice.service.InvestmentProductService;
import com.millionaire.millionaireserverweb.pojo.ProductQuery;
import com.millionaire.millionaireserverweb.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @author Liu Kai
 * @Description: TODO
 * @date 2018/8/18 12:41
 */

@RestController
@RequestMapping("/a")
public class InvestmentProductController {
    Logger logger = LoggerFactory.getLogger(InvestmentProductController.class);
    private final InvestmentProductService investmentProductService;

    @Autowired
    public InvestmentProductController(InvestmentProductService investmentProductService) {
        this.investmentProductService = investmentProductService;
    }

    /**
     * @param
     * @return
     * @Description 分页查看所有产品信息，默认第一页
     * @RequestParam(defaultValue = "10") String pageSize,@RequestParam(defaultValue = "1") String pageNum
     * @RequestBody ProductQuery productQuery
     **/
    @GetMapping("/list/investment-product")
    public ResultBean getListInvestmentProduct(@RequestParam("pageSize")int pageSize ,
                                               @RequestParam("pageNum") int pageNum,
                                               ProductQuery productQuery){
        if(pageNum==0){
            pageNum=1;
        }
        if(pageSize==0){
            pageSize=10;
        }

        PageInfo<InvestmentProduct> pageInfo = investmentProductService.selectByPage(pageSize, pageNum);
        return new ResultBean(1, "success", pageInfo);
    }


}
