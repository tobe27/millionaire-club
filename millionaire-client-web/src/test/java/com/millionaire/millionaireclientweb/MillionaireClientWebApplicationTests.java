package com.millionaire.millionaireclientweb;

import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MillionaireClientWebApplicationTests {

	@Test
	public void contextLoads() {

	}
	@Resource
	private ReceptionUsersService receptionUsersService;
	@Test
	public void dummy() {
		ReceptionUsers receptionUsers = new ReceptionUsers();
		receptionUsers.setIdName("小");
		receptionUsersService.findByUser(receptionUsers);
	}
}
