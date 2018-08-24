package com.millionaire.millionaireuserservice;

import com.millionaire.millionaireuserservice.dao.ReceptionUsersMapper;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MillionaireUserServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Resource
	private ReceptionUsersMapper receptionUsersMapper;
	@Test
	public void findAll(){
		List<ReceptionUsers> receptionUsers = receptionUsersMapper.findAll();
		for (ReceptionUsers receptionUsers1:receptionUsers) {
			System.out.println(receptionUsers1);
		}
	}
	@Test
	public void findByPhone(){
		ReceptionUsers receptionUsers = receptionUsersMapper.findByPhone(123L);
		System.out.println(receptionUsers);

	}
	@Test
    public void testInsertReceptionUsers(){
	    ReceptionUsers receptionUsers = new ReceptionUsers();
	    receptionUsers.setPhone(1L);  // 账号
	    receptionUsers.setPassword("123546");  //密码
        receptionUsers.setSalt("salt");  //盐
        receptionUsers.setUserNumber("007");  //用户编号
        receptionUsers.setStatus((byte) 10);  //用户状态
        receptionUsers.setIdAuthentication((byte) 10); //实名状态
        receptionUsers.setGmtCreate(System.currentTimeMillis());
		receptionUsersMapper.insert(receptionUsers);
        System.out.println(receptionUsers.getId());
    }
    @Test
	public void testUpdateReceptionUsers(){
		ReceptionUsers receptionUsers = new ReceptionUsers();
		receptionUsers.setPhone(1568532L);  // 账号
		receptionUsers.setPassword("123546");  //密码
		receptionUsers.setSalt("salt");  //盐
		receptionUsers.setUserNumber("007");  //用户编号
		receptionUsers.setStatus((byte) 10);  //用户状态
		receptionUsers.setIdAuthentication((byte) 10); //实名状态
		receptionUsers.setGmtUpdate(System.currentTimeMillis());
		receptionUsers.setId(4L);
		receptionUsersMapper.updateByPrimaryKey(receptionUsers);
		System.out.println(receptionUsers.getId());
	}


}
