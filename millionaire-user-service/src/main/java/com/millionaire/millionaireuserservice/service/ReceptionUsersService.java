package com.millionaire.millionaireuserservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.request.ReceptionUsersQuery;
import com.millionaire.millionaireuserservice.request.UsersVerificationQuery;
import com.millionaire.millionaireuserservice.transport.ReceptionUsersDTO;

/**
 * @author Liu Kai
 * @Description: TODO 投资用户 查询 修改
 * @date 2018/8/20 12:37
 */
public interface ReceptionUsersService {

    void deleteByPrimaryKey(Long id);

    Long insert(ReceptionUsers record);


    Long insertSelective(ReceptionUsers record);

    Long updateByPrimaryKey(ReceptionUsers record);

    /**
     * 通过电话查用户 登陆时使用
     * 孙壮壮
     * @param phone
     * @return
     */
    ReceptionUsers findByPhone(Long phone);
/**
 * @Description 分页查询投资用户信息
 * @param usersQuery 用户查询所需参数
 * @param pageNum  页码
 * @param pageSize  每页数据
 * @return 分页查询数据
 **/
PageInfo<ReceptionUsers> selectProductByPage(ReceptionUsersQuery usersQuery,
                                             Integer pageSize, Integer pageNum);

/**
 * @Description 根据id查询用户
 * @param id 用户id
 * @return  投资用户
 **/
ReceptionUsers selectByPrimaryKey(Long id);

/**
 * @Description 编辑投资用户 不限定参数 封装更新时间
 * @param record 投资用户
 * @return 收影响行数
 **/

Long updateByPrimaryKeySelective(ReceptionUsers record);

    /**
     * @Description  查询实名认证列表分页查看
     **/
    PageInfo<ReceptionUsers> selectUserVerificationByPage( Integer pageSize, Integer pageNum,UsersVerificationQuery verificationQuery);

    /**
     * @param id
     * @return 成功0 失败-1
     * @Description 根据用户id查询用户信息和绑定银行卡表
     **/
    ReceptionUsersDTO selectByID(Long id);

    /**
     * @Description 根据uid 删除银行卡
     **/
    void deleteBankCardByUID(Long uid);

    /**
     * @param cardNum 银行卡号码
     * @Description 根据卡号删除银行卡
     **/
    void deleteByCardNum(String cardNum);


    UserBank selectByCardNum(String cardNum);

}
