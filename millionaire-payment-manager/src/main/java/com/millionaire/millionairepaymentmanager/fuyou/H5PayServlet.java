package com.millionaire.millionairepaymentmanager.fuyou;

import com.millionaire.millionairepaymentmanager.exception.FuYouException;
import com.millionaire.millionairepaymentmanager.fuyou.until.DESCoderFUIOU;
import com.millionaire.millionairepaymentmanager.fuyou.until.HttpFormUtil;
import com.millionaire.millionairepaymentmanager.fuyou.until.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * H5支付(2016-09-29)
 * 
 * @author xiaohao@fuiou.com
 *
 */
public class H5PayServlet
{
	private static final long	serialVersionUID	= 1419376384387536955L;

//  回调接口
	private static final String BACK_URL = "http://47.98.219.40:8081/api/back_url";
//	支付成功后的跳转页面
	private static final String HOME_URL = "http://47.98.219.40:8081/api/home_url";
//	支付失败后的跳转页面
	private static final String RETURN_URL = "http://47.98.219.40:8081/api/return_url";
//	测试的支付接口页面
	private static final String PAY_URL ="http://www-1.fuiou.com:18670/mobile_pay/h5pay/payAction.pay";

    private Logger logger =  LoggerFactory.getLogger(H5PayServlet.class);


	public String sentPost(long userId,long amt,String idNo,Long orderId,String bankCard,String name) throws IOException, FuYouException {
	    logger.info("调用支付接口"+
	            "<===============================================================================");
		String respMsg = "";
		try
		{
			String idType = "0";
			String type="11";
			StringBuffer orderPlain = new StringBuffer();
//			String orderId = "FY"+ DateTimeUtil.getCurrentDate("yyyyMMddHHmmssSSS");
			String signPlain = type+"|"+"2.0"+"|"+Constants.H5_MCHNT_CD+"|"+orderId+"|"+userId
					+"|"+amt+"|"+bankCard+"|"+BACK_URL+"|"+name+"|"+idNo+"|"+idType+"|"+"0"+"|"
					+ HOME_URL+"|"+RETURN_URL+"|"+Constants.H5_MCHNT_KEY;

			String sign= MD5.MD5Encode(signPlain);

			logger.info("[签名明文:]"+signPlain+
                    "<=======================================================================================");

//			xml参数的生成
			orderPlain.append("<ORDER>")
			.append("<VERSION>2.0</VERSION>")
			.append("<LOGOTP>0</LOGOTP>")
			.append("<MCHNTCD>").append(Constants.H5_MCHNT_CD).append("</MCHNTCD>")
			.append("<TYPE>").append(type).append("</TYPE>")
			.append("<MCHNTORDERID>").append(orderId).append("</MCHNTORDERID>")
			.append("<USERID>").append(userId).append("</USERID>")
			.append("<AMT>").append(amt).append("</AMT>")
			.append("<BANKCARD>").append(bankCard).append("</BANKCARD>")
			.append("<BACKURL>").append(BACK_URL).append("</BACKURL>")
			.append("<HOMEURL>").append(HOME_URL).append("</HOMEURL>")
			.append("<REURL>").append(RETURN_URL).append("</REURL>")
			.append("<NAME>").append(name).append("</NAME>")
			.append("<IDTYPE>").append(idType).append("</IDTYPE>")
			.append("<IDNO>").append(idNo).append("</IDNO>")
			.append("<REM1>").append(userId).append("</REM1>")
			.append("<REM2>").append(userId).append("</REM2>")
			.append("<REM3>").append(userId).append("</REM3>")
			.append("<SIGNTP>").append("md5").append("</SIGNTP>")
			.append("<SIGN>").append(sign).append("</SIGN>")
			.append("</ORDER>");

			logger.info("[订单信息:]"+orderPlain.toString());
			logger.info("====================================================================================");

			Map<String,String> param = new HashMap<String, String>();
			param.put("VERSION", "2.0");
			param.put("ENCTP", "1");
			param.put("LOGOTP", "0");
			param.put("MCHNTCD", Constants.H5_MCHNT_CD);
//			通过用户私钥对请求信息desc算法加密
			param.put("FM", DESCoderFUIOU.desEncrypt(orderPlain.toString(), DESCoderFUIOU.getKeyLength8(Constants.H5_MCHNT_KEY)));
//			发送post请求的工具类
			respMsg = HttpFormUtil.formForward(PAY_URL, param);

			logger.info("[请求信息:]"+param);
            logger.info("====================================================================================");
		}
		catch (Exception e)
		{
		    logger.info("支付系统异常");
            logger.info("====================================================================================");
            throw new FuYouException("H5支付系统异常");
		}
		return respMsg;
	}
}
