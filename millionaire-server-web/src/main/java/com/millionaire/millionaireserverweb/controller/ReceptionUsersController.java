package com.millionaire.millionaireserverweb.controller;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionaireserverweb.result.ResultBean;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.ReceptionUsersQuery;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Liu Kai
 * @Description: TODO 后台投资用户管理 查询 修改
 * @date 2018/8/20 13:38
 */
@RestController
@RequestMapping("/a")
public class ReceptionUsersController {
    Logger logger = LoggerFactory.getLogger(ReceptionUsersController.class);
    @Autowired
    ReceptionUsersService usersService;

    /**
     * @param pageNum    页码
     * @param pageSize   每页大小
     * @param usersQuery 查询参数
     * @return 成功0 失败-1
     * @Description 根据条件查询投资用户
     **/
    @GetMapping("/list/user")
    public ResultBean getListReceptionUsers(@RequestParam(value = "pageSize") Integer pageSize,
                                            @RequestParam(value = "pageNum") Integer pageNum,
                                            ReceptionUsersQuery usersQuery) {
        if (pageNum == null || pageSize == null) {
            logger.info("页码为空:{}或每页数为空:{}", pageNum, pageSize);
            return new ResultBean(-1, "error pageSize or pageNum is null");
        } else {
            PageInfo<ReceptionUsers> pageInfo =
                    usersService.selectProductByPage(usersQuery, pageSize, pageNum);
            logger.info("查询投资用户参数：{}", usersQuery);
            return new ResultBean(0, "success", pageInfo);
        }
    }

    /**
     * @param uid    用户id
     * @param status 账户冻结状态 10正常 20冻结
     * @return 成功0 失败-1
     * @Description 用户账户状态修改
     **/
    @PutMapping("/user-status/{uid}")
    public ResultBean updateUserStatus(@PathVariable("uid") Long uid,
                                       @RequestParam("status") Byte status) {
        ReceptionUsers users = usersService.selectByPrimaryKey(uid);
        if (users == null) {
            return new ResultBean(-1, "error no such uid", uid);
        } else {
            users.setStatus(status);
            usersService.updateByPrimaryKeySelective(users);
            logger.info("更新用户id{}账户冻结状态:{}", uid, status);
            return new ResultBean(0, "success");
        }
    }

    /**
     * @param uid 用户id
     * @return 成功0 失败-1
     * @Description 查询用户详细信息
     **/
    @GetMapping("/user/{uid}")
    public ResultBean selectUsers(@PathVariable("uid") Long uid) {
        ReceptionUsers users = usersService.selectByPrimaryKey(uid);
        if (users == null) {
            return new ResultBean(-1, "error no such uid", uid);
        } else {
            logger.info("查看投资用户id:{}", uid);
            return new ResultBean(0, "success", users);
        }
    }

    /**
     * @param uid        用户id
     * @param managerNum 经理工号
     * @param phone      用户手机号
     * @return 成功0 失败-1
     * @Description 修改用户信息 手机号 产品经理工号
     **/
    @PutMapping("/user/{uid}")
    public ResultBean updateUserPhoneMangerNum(@PathVariable("uid") Long uid,
                                               @RequestParam(value = "phone", required = false) String phone,
                                               @RequestParam(value = "managerNumber", required = false) String managerNum) {
        ReceptionUsers users = usersService.selectByPrimaryKey(uid);
        if (users == null) {
            return new ResultBean(-1, "error no such uid", uid);
        }
        //手机号正则式判断
        String pattern = "1[0-9]{10}";
        if (phone != null && phone.matches(pattern)) {
            users.setPhone(phone);
        }
        if (managerNum != null) {
            users.setManagerNumber(managerNum);
        }
        usersService.updateByPrimaryKeySelective(users);
        logger.info("修改用户信息 用户id:{},手机号:{},经理工号:{}", uid, phone, managerNum);
        return new ResultBean(0, "success", users.getId());
    }

    /**
     * @param
     * @return 成功0 失败-1
     * @Description 删除绑定银行卡
     **/
    @DeleteMapping("/user-bank/{uid}")
    public ResultBean deleteBankCard(@PathVariable("uid") Long uid,
                                     @RequestParam("cardNumber")String cardNumber) {


        return new ResultBean(0, "success");
    }

}
