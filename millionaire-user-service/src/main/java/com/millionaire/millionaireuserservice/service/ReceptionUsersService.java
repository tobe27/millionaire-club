package com.millionaire.millionaireuserservice.service;

import com.github.pagehelper.PageInfo;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.ReceptionUsersQuery;

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
}
