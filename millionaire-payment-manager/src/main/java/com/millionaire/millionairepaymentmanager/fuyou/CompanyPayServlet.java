
package com.millionaire.millionairepaymentmanager.fuyou;
import com.millionaire.millionairepaymentmanager.exception.FuYouException;
import com.millionaire.millionairepaymentmanager.fuyou.until.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component("companyPayServlet")
public class CompanyPayServlet {

    //    public static final String GET_URL = "http://112.4.27.9/mall-back/if_user/store_list?storeId=32";
    //    public static final String POST_URL = "http://112.4.27.9/mall-back/if_user/store_list";
    // 富友测试接口
    private static final String POST_URL = "https://fht-test.fuiou.com/fuMer/req.do";

    private Logger logger = LoggerFactory.getLogger(CompanyPayServlet.class);

    /**
     * 接口调用 GET
     */
//    public static void httpURLConectionGET() {
//        try {
//            URL url = new URL(GET_URL);    // 把字符串转换为URL请求地址
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
//            connection.connect();// 连接会话
//            // 获取输入流
//            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//            String line;
//            StringBuilder sb = new StringBuilder();
//            while ((line = br.readLine()) != null) {// 循环读取流
//                sb.append(line);
//            }
//            br.close();// 关闭流
//            connection.disconnect();// 断开连接
//            System.out.println(sb.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("失败!");
//        }
//    }

    /**
     * 接口调用  POST
     */
    public  boolean  httpURLConnectionPOST(Long taskId,int amount)  {
        try {
            URL url = new URL(POST_URL);

            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中

            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);

            // 设置连接输入流为true
            connection.setDoInput(true);

            // 设置请求方式为post
            connection.setRequestMethod("POST");

            // post请求缓存设为false
            connection.setUseCaches(false);

            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);

            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            // ;charset=utf-8 必须要，不然妙兜那边会出现乱码【★★★★★】
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();

            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());

            String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>"+
                    "<payforreq>"+
                    "<ver>1.00</ver>"+
                    "<merdt>"+getDate()+"</merdt>"+
                    "<orderno>"+System.currentTimeMillis()+"</orderno>"+
                    "<bankno>0102</bankno>"+
                    "<cityno>2900</cityno>"+
                    "<branchnm>中国银行股份有限公司北京西单支行</branchnm>"+
                    "<accntno>6212261904006115311</accntno>"+
                    "<accntnm>似曾相识</accntnm>"+
                    "<amt>"+amount+"</amt>"+
                    "<entseq>"+taskId+"</entseq>"+
                    "<memo>测试</memo>"+
                    "<mobile>13275869228</mobile>"+
                    "<addDesc>1</addDesc>"+
                    "</payforreq>";

            String macSource = "0002900F0345178|123456|"+"payforreq"+"|"+xml;
            logger.info("企业代付请求参数："+macSource);

            String mac = MD5Util.encode(macSource, "UTF-8").toUpperCase();

            String merid = "merid="+ URLEncoder.encode("0002900F0345178", "utf-8");		// 商户号
            String reqtype = "&reqtype="+ URLEncoder.encode("payforreq", "utf-8");			//请求类型,表示代付接口
            String xmlParam = "&xml="+ URLEncoder.encode(xml, "utf-8");				// 请求参数
            String macParam = "&mac="+ URLEncoder.encode(mac, "utf-8");           //检较值

            String param = merid+ reqtype+ xmlParam+ macParam;
            logger.info("post表单数据："+param);

            // 将参数输出到连接
            dataout.writeBytes(param);

            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

//            System.out.println(connection.getResponseCode());
            logger.info("响应参数："+connection.getResponseCode());

            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
//                sb.append(bf.readLine());
//                这也是换行符,功能和"\n"是一致的,但是此种写法屏蔽了 Windows和Linux的区别 ，更保险一些.
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            logger.info("响应数据"+sb.toString());

            //根据响应参数判断代付是否成功
            if (parse(sb.toString()).equals("000000")) {
                logger.info("代付成功"+taskId);
                return true;
            }else {
                logger.info("代付失败"+taskId);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("系统代付异常");
            return false;
        }
    }

    public static String getDate(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
        Date date=new Date();
        String date1=sf.format(date);
        return date1;
    }

    /**
     * xml文件解析工具类
     * @param protocolXML
     * @return
     */
    public static String parse(String protocolXML) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder
                    .parse(new InputSource(new StringReader(protocolXML)));

            Element root = doc.getDocumentElement();
            NodeList books = root.getChildNodes();
            if (books != null) {
                for (int i = 0; i < books.getLength(); i++) {
                    Node book = books.item(i);
                    if (book.getNodeName().equals("ret")) {
                        return book.getFirstChild().getNodeValue();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return protocolXML;
    }

}
