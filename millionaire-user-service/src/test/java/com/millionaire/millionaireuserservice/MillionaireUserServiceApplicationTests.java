package com.millionaire.millionaireuserservice;

import com.millionaire.millionairemanagerservice.dao.BankMapper;
import com.millionaire.millionaireuserservice.dao.ReceptionUsersMapper;
import com.millionaire.millionaireuserservice.dao.UserBankMapper;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.module.UserBank;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.service.UserBankService;
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

	@Resource
	private UserBankMapper userBankMapper;
	@Resource
	private UserBankService userBankService;
	@Test
	public void insertBan() {
		UserBank userBank = new UserBank();
		userBank.setCity("上海");
		userBank.setBankName("工商银行");
		userBank.setBankPhone("15224985586");
		userBank.setCardNumber("12242242153214");
		userBank.setCardType("储蓄卡");
		userBank.setUid(1L);
		userBank.setGmtCreate(System.currentTimeMillis());
		userBank.setGmtUpdate(System.currentTimeMillis());
		userBankService.insert(userBank);
		System.out.println(userBank.getId());
	}

	@Test
	public void delett() {
//		userBankMapper.deleteByPrimaryKey(1L);
	}

	@Test
	public void updateBank() {

	}
	@Resource
	private BankMapper bankMapper;

	@Test
	public void bank() {
		System.out.println(bankMapper.selectByPrimaryKey(1L));
	}

	@Resource
	private ReceptionUsersService receptionUsersService;
	@Test
	public void findBy() {
		ReceptionUsers receptionUsers = new ReceptionUsers();
		receptionUsers.setPhone(1522L);
		receptionUsers.setEmail("123");
		receptionUsers.setIdName("孙壮壮");
		List<ReceptionUsers> receptionUsers1 = receptionUsersService.findByUser(receptionUsers);
		for (ReceptionUsers receptionUsers2:receptionUsers1) {
			System.out.println(receptionUsers2);
		}


	}
//	@Test
}
