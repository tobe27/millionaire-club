package com.millionaire.millionaireuserservice.dao;

import com.millionaire.millionaireuserservice.module.UserBank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserBankMapper {
    void deleteByPrimaryKey(Long id);

    Long insert(UserBank record);

    Long insertSelective(UserBank record);

    UserBank selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(UserBank record);

    Long updateByPrimaryKey(UserBank record);

    /**
     * @param uid 用户id
     * @Description 根据用户id查询银行卡
     **/
    List<UserBank> selectByUID(Long uid);


    /**
     * @Description 根据uid 删除银行卡
     **/
    void deleteByUID(Long uid);

    /**
     * @param cardNum 银行卡号码
     * @Description 根据卡号删除银行卡
     **/
    void deleteByCardNum(String cardNum);

    UserBank selectByCardNum(String cardNum);

}