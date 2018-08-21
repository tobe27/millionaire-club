package com.millionaire.millionaireuserservice.dao;

import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.ReceptionUsersQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReceptionUsersMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ReceptionUsers record);

    int insertSelective(ReceptionUsers record);

    ReceptionUsers selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReceptionUsers record);

    int updateByPrimaryKey(ReceptionUsers record);

    /**
     * @author Liu Kai
     * @return 投资用户list
     * @Description 查询所有投资用户
     **/
    List<ReceptionUsers> selectAll();
    /**
     * @Description 根据查询参数查询投资用户 按照注册时间降序排列
     * @param usersQuery 用户查询所需参数包装类
     * @return  投资用户list
     **/
    List<ReceptionUsers> selectUserByPage(ReceptionUsersQuery usersQuery);
}