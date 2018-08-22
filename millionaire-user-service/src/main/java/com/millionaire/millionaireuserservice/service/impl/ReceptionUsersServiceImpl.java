package com.millionaire.millionaireuserservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.millionaire.millionaireuserservice.dao.ReceptionUsersMapper;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.ReceptionUsersQuery;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
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
    public PageInfo<ReceptionUsers> selectProductByPage(ReceptionUsersQuery usersQuery, Integer pageSize, Integer pageNum) {
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

}
