package com.millionaire.millionaireuserservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionaireuserservice.dao.ReceptionUsersMapper;
import com.millionaire.millionaireuserservice.dao.UserBankMapper;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.request.ReceptionUsersQuery;
import com.millionaire.millionaireuserservice.request.UsersVerificationQuery;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.transport.ReceptionUsersDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Liu Kai
 * @Description: TODO 投资用户 查询 修改
 * @date 2018/8/20 12:40
 */

@Service
public class ReceptionUsersServiceImpl implements ReceptionUsersService {
    @Resource
    private ReceptionUsersMapper usersMapper;

    @Resource
    private UserBankMapper userBankMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
    }

    @Override
    public Long insert(ReceptionUsers record) {
        usersMapper.insert(record);
        return record.getId();
    }

    @Override
    public Long insertSelective(ReceptionUsers record) {
        return 0L;
    }

    @Override
    public Long updateByPrimaryKey(ReceptionUsers record) {
        return usersMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<ReceptionUsers> findByUser(ReceptionUsers record) {
        return usersMapper.findByUser(record);
    }

    /**
     * 通过电话查用户登陆
     * 孙壮壮
     * @param phone
     * @return
     */
    @Override
    public ReceptionUsers findByPhone(Long phone) {
        return usersMapper.findByPhone(phone);
    }

    /**
     * @param usersQuery 用户查询所需参数
     * @param pageSize   每页数据
     * @param pageNum    页码
     * @return 分页数据
     * @Description 分页查询投资用户信息
     **/
    @Override
    public PageInfo<ReceptionUsers> selectReceptionUserByPage(ReceptionUsersQuery usersQuery, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReceptionUsers> usersList = usersMapper.selectUserByPage(usersQuery);
        PageInfo<ReceptionUsers> pageInfo = new PageInfo<>(usersList);
        return pageInfo;
    }

    /**
     * @param id 用户id
     * @return 投资用户
     * @Description 根据id查询用户
     **/
    @Override
    public ReceptionUsers selectByPrimaryKey(Long id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    /**
     * @param record 投资用户
     * @return 受影响行数
     * @Description 编辑投资用户 不限定参数 封装更新时间
     **/
    @Override
    public Long updateByPrimaryKeySelective(ReceptionUsers record) {
        record.setGmtUpdate(System.currentTimeMillis());
        return usersMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @param verificationQuery
     * @Description 查询实名认证列表分页查看
     */
    @Override
    public PageInfo<ReceptionUsers> selectUserVerificationByPage( Integer pageSize, Integer pageNum,UsersVerificationQuery verificationQuery) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReceptionUsers> usersList = usersMapper.selectUserVerificationByPage(verificationQuery);
        PageInfo<ReceptionUsers> pageInfo = new PageInfo<>(usersList);
        return pageInfo;
    }
    /**
     * @param id
     * @return 成功0 失败-1
     * @Description 根据用户id查询用户信息和绑定银行卡表
     **/
    @Override
    public ReceptionUsersDTO selectByID(Long id) {
        ReceptionUsersDTO usersDTO = new ReceptionUsersDTO();
        ReceptionUsers users = usersMapper.selectByPrimaryKey(id);
        List<UserBank> listUserBank = userBankMapper.selectByUID(id);
        usersDTO.setUsers(users);
        usersDTO.setListUserBank(listUserBank);
        return usersDTO;
    }

    /**
     * @param uid
     * @Description 根据uid 删除银行卡
     */
    @Override
    public void deleteBankCardByUID(Long uid) {
        userBankMapper.deleteByUID(uid);
    }

    /**
     * @param cardNum 银行卡号码
     * @Description 根据卡号删除银行卡
     **/
    @Override
    public void deleteByCardNum(String cardNum) {
        userBankMapper.deleteByCardNum(cardNum);
    }


    @Override
    public UserBank selectByCardNum(String cardNum) {
        return userBankMapper.selectByCardNum(cardNum);
    }

    @Override
    public int updateUserAssets(Long uid, int amount,int type) {
        int assets = usersMapper.selectUserAssets(uid);
        int nowAssets = 0;
        if (type == 1) {
           nowAssets = assets + amount;
        }else {
            nowAssets=assets-amount;
        }
        usersMapper.updateUserAssets(uid, nowAssets, System.currentTimeMillis());
        return nowAssets;
    }

    @Override
    public int updateUserProfit(Long uid, int profit) {
        int profit1 = usersMapper.selectUserProfit(uid);
        int nowProfit = profit1+profit;
        usersMapper.updateUserProfit(uid, nowProfit, System.currentTimeMillis());
        return nowProfit;
    }

}
