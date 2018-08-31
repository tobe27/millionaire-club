package com.millionaire.millionaireuserservice.dao;

import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.request.ReceptionUsersQuery;
import com.millionaire.millionaireuserservice.request.UsersVerificationQuery;
import com.millionaire.millionaireuserservice.transport.UserReceptionDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReceptionUsersMapper {
    void deleteByPrimaryKey(Long id);

    Long insert(ReceptionUsers record);

    Long insertSelective(ReceptionUsers record);

    ReceptionUsers selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(ReceptionUsers record);

    Long updateByPrimaryKey(ReceptionUsers record);

    ReceptionUsers findByPhone(Long phone);

    UserReceptionDTO findById(Long id);

    /**
     * 通过手机，邮箱，姓名
     * @param record
     * @return
     */
    List<ReceptionUsers> findByUser(ReceptionUsers record);

    /**
     * @author Liu Kai
     * @return 投资用户list
     * @Description 查询所有投资用户
     **/
    List<ReceptionUsers> selectAll();
    List<ReceptionUsers> findAll();
    /**
     * @Description 根据查询参数查询投资用户 按照注册时间降序排列
     * @param usersQuery 用户查询所需参数包装类
     * @return  投资用户list
     **/
    List<ReceptionUsers> selectUserByPage(ReceptionUsersQuery usersQuery);
    /**
     * @Description  查询实名认证列表
     **/
    List<ReceptionUsers> selectUserVerificationByPage(UsersVerificationQuery verificationQuery);


    /**
     * 用户的总资产修改
     */
    int updateUserAssets(@Param("id") Long uid, @Param("assets") int assets, @Param("gmtUpdate") Long gmtUpdate);

    /**
     * 用户总资产查询
     */
    int selectUserAssets(Long id);

    /**
     * 用户收益修改
     */
    int updateUserProfit(@Param("id") Long uid, @Param("profit") int profit, @Param("gmtUpdate") Long gmtUpdate);

    /**
     * 用户收益查询
     */
    int selectUserProfit(Long id);

}