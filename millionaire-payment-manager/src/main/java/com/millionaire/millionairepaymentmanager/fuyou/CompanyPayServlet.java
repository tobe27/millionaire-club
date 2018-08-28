


package com.millionaire.millionairepaymentmanager.fuyou;

import com.millionaire.millionairepaymentmanager.fuyou.until.MD5Util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompanyPayServlet {

//    public static final String GET_URL = "http://112.4.27.9/mall-back/if_user/store_list?storeId=32";
    //    public static final String POST_URL = "http://112.4.27.9/mall-back/if_user/store_list";
    // 富友测试接口
    public static final String POST_URL = "https://fht-test.fuiou.com/fuMer/req.do";

    //商户号
    public static String merid = "0002900F0345178";

    //    请求类型,表示代付接口
    public static String reqtype = "payforreq";

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
    public static void  httpURLConnectionPOST() {
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
                    "<amt>10000</amt>"+
                    "<entseq>05</entseq>"+
                    "<memo>测试</memo>"+
                    "<mobile>13275869228</mobile>"+
                    "<addDesc>1</addDesc>"+
                    "</payforreq>";

            String macSource = "0002900F0345178|123456|"+"payforreq"+"|"+xml;
            String mac = MD5Util.encode(macSource, "UTF-8").toUpperCase();

            // 将参数输出到连接
            dataout.writeBytes(merid);
            dataout.writeBytes(reqtype);
            dataout.writeBytes(xml);
            dataout.writeBytes(mac);

            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

//            System.out.println(connection.getResponseCode());

            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
//                sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDate(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyyMMdd");
        Date date=new Date();
        String date1=sf.format(date);
        return date1;
    }

    public static void main(String[] args) {
//        httpURLConectionGET();
//        httpURLConnectionPOST();
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
                "<amt>10000</amt>"+
                "<entseq>05</entseq>"+
                "<memo>测试</memo>"+
                "<mobile>13275869228</mobile>"+
                "<addDesc>1</addDesc>"+
                "</payforreq>";

        String macSource = "0002900F0345178|123456|"+"payforreq"+"|"+xml;
        String mac = MD5Util.encode(macSource, "UTF-8").toUpperCase();

        System.out.println("=============================================");
        System.out.println(xml);
        System.out.println(mac);
    }
}
