package com.millionaire.millionaireclientweb;

import com.millionaire.millionairebusinessservice.dao.InvestmentUserMapper;
import com.millionaire.millionairebusinessservice.dao.MessageUserMapper;
import com.millionaire.millionairebusinessservice.dao.TradingFlowMapper;
import com.millionaire.millionairebusinessservice.module.InvestmentUser;
import com.millionaire.millionairebusinessservice.module.TradingFlow;
import com.millionaire.millionairebusinessservice.transport.UserInvestmentDTO;
import com.millionaire.millionairebusinessservice.transport.UserMessageDTO;
import com.millionaire.millionaireclientweb.result.ResultBean;
import com.millionaire.millionairemanagerservice.dao.ContentMapper;
import com.millionaire.millionairemanagerservice.dao.MessagePlatformMapper;
import com.millionaire.millionairemanagerservice.module.MessagePlatform;
import com.millionaire.millionaireuserservice.module.ReceptionUsers;
import com.millionaire.millionaireuserservice.service.ReceptionUsersService;
import com.millionaire.millionaireuserservice.transport.UserReceptionDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MillionaireClientWebApplicationTests {

	@Test
	public void contextLoads() {

	}
	@Resource
	private ReceptionUsersService receptionUsersService;
@Resource
private ContentMapper contentMapper;
	@Test
	public void Type() {
		System.out.println(contentMapper.findByType((byte) 20));
	}

	@Resource
	private TradingFlowMapper tradingFlowMapper;
	@Test
	public void Flow() {
		List<TradingFlow> tradingFlows = tradingFlowMapper.findByUid(8L);
			for (TradingFlow tradingFlow:tradingFlows) {
				System.out.println(tradingFlow);
		}
	}

	@Resource
	private InvestmentUserMapper investmentUserMapper;

	@Test
	public void investment() {
		InvestmentUser investmentUser = new InvestmentUser();
		investmentUser.setUid(8L);
		investmentUser.setInvestmentStatus((byte) 10);
		List<InvestmentUser> investmentUsers = investmentUserMapper.findByUidInvestmentStatus(investmentUser);
		System.out.println(investmentUsers);


	}

	@Resource
	private RedisTemplate redisTemplate;
	@Test
	public void agreement() {
		UserInvestmentDTO userInvestmentDTO = investmentUserMapper.findById(12L);
		System.out.println(userInvestmentDTO);

	}

	@Test
	public void sdfsdf() {
		InvestmentUser investmentUser = investmentUserMapper.selectByPrimaryKey(5L);
		System.out.println(investmentUser);
	}
	@Resource
	private MessageUserMapper messageUserMapper;

	@Test
	public void message() {
		List<UserMessageDTO> userMessageDTOS = messageUserMapper.findByUid(8L);
		for (UserMessageDTO userMessageDTO:userMessageDTOS) {
			System.out.println(userMessageDTO);
		}
	}
@Resource
private MessagePlatformMapper messagePlatformMapper;
	@Test
	public void dsfdf() {
		List<MessagePlatform> messagePlatforms = messagePlatformMapper.findBySendingCrowd((byte) 20);
		for (MessagePlatform messagePlatform:messagePlatforms) {
			System.out.println(messagePlatform);
		}
	}

	@Test
	public void dsafsdg() {
		UserReceptionDTO userReceptionDTO = receptionUsersService.findById(8L);
		System.out.println(userReceptionDTO);
	}
}
