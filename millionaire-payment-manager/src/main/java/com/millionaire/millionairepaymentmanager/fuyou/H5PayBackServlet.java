package com.millionaire.millionairepaymentmanager.fuyou;


import com.millionaire.millionairepaymentmanager.fuyou.until.MD5;
import com.millionaire.millionairepaymentmanager.manager.PayBackManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * H5支付通知接收(2016-09-29)
 * 
 * @author xiaohao@fuiou.com
 *
 */
public class H5PayBackServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1419376384387536955L;

	@Autowired
    private PayBackManager payBackManager;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String version = req.getParameter("VERSION");
		String type = req.getParameter("TYPE");
		String responseCode = req.getParameter("RESPONSECODE");
		String responseMsg = req.getParameter("RESPONSEMSG");
		String mchntCd = req.getParameter("MCHNTCD");
		String mchntOrderId = req.getParameter("MCHNTORDERID");
		String orderId = req.getParameter("ORDERID");
		String bankCard = req.getParameter("BANKCARD");
		String amt = req.getParameter("AMT");
		String sign = req.getParameter("SIGN");
		String key = Constants.H5_MCHNT_KEY;
		Map<String, String> keyMap = new HashMap<String, String>();
		keyMap.put(Constants.H5_MCHNT_CD, Constants.H5_MCHNT_KEY);
		key = keyMap.get(mchntCd);
		// 校验签名
		String signPain = new StringBuffer().append(type).append("|").append(version).append("|").append(responseCode)
				.append("|").append(mchntCd).append("|").append(mchntOrderId).append("|").append(orderId).append("|")
				.append(amt).append("|").append(bankCard).append("|").append(key).toString();
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		if (MD5.MD5Encode(signPain).equals(sign))
		{
			if (Constants.RESP_CODE_SUCCESS.equals(responseCode))
			{

				payBackManager.backManage(Long.valueOf(mchntOrderId));
				System.out.println(mchntOrderId +"支付成功~");
				resp.getWriter().write("支付成功~");
			}
			else
			{
				System.out.println(mchntOrderId +"支付失败~"+responseMsg);
				resp.getWriter().write("支付失败~");
			}
		}
		else
		{
			System.out.println(mchntOrderId +"验签失败~");
			resp.getWriter().write("验签失败~");
		}
	}
}
