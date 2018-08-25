package com.millionaire.millionaireserverweb.controller;

import com.millionaire.millionaireserverweb.result.ResultBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author Liu Kai
 * @Description: TODO 统计模块，用于销量统计查询
 * @date 2018/8/24 18:05
 */
@RestController
@RequestMapping("/a")
public class StatisticsController {


    /**
     * @Description 查看投资产品销量统计
     **/
    @GetMapping("/investment-count")
    public ResultBean countInvestment() {
        return new ResultBean(0, "success");
    }
}
