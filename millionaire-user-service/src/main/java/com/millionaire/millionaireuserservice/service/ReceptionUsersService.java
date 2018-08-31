package com.millionaire.millionaireuserservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.request.ReceptionUsersQuery;
import com.millionaire.millionaireuserservice.request.UsersVerificationQuery;
import com.millionaire.millionaireuserservice.transport.ReceptionUsersDTO;
import com.millionaire.millionaireuserservice.transport.UserReceptionDTO;

import java.util.List;

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

    UserReceptionDTO findById(Long id);

    /**
     * 通过 手机 姓名 邮箱
     * @param record
     * @return
     */
    List<ReceptionUsers> findByUser(ReceptionUsers record);

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
PageInfo<ReceptionUsers> selectReceptionUserByPage(ReceptionUsersQuery usersQuery,
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

    /**
     *      * 用户的总资产修改,
     * 需要先查询用户的总资产，
     * 如果是用户投资成功：总资产+投资金额
     * 用户回款成功：总投资-回款金额
     * @param uid
     * @param amount
     * @param type -1表示回款，1表示用户投资
     * @return
     */
    int updateUserAssets(Long uid, int amount,int type);

    /**
     * 用户收益修改
     * 在回款成功后更新将用户收益：
     * 注意：这里的收益存储的是int类型，以分为单位
     */
    int updateUserProfit(Long uid, int profit);

}
