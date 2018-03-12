package com.tuyue.weichat;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/13.
 */
public class WeChatHttp {
    //1) 统一下单API
    public static String UNIFEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        try {

            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            conn.setRequestMethod(requestMethod);
            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 当outputStr不为null时向输出流写数据
            if (null != outputStr) {
                OutputStream outputStream = conn.getOutputStream();
                // 注意编码格式
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            return buffer.toString();
        } catch (ConnectException ce) {
            System.out.println("连接超时：{}"+ ce);
        } catch (Exception e) {
            System.out.println("https请求异常：{}"+ e);
        }
        return null;
    }

    public static void main(String[] args) {
        String s = WeChatHttp.httpsRequest(WeChatHttp.UNIFEDORDER, "POST", "<xml><appid>wxff1e39bf7a9d9b17</appid><sign>672ED68A09C2EFEF5799992E105DA965</sign><body>啊啊啊</body><mch_id>1493813302</mch_id><nonce_str>151330538915790944</nonce_str><sign_type>MD5</sign_type><out_trade_no>1513300486121noUDl</out_trade_no><total_fee>72000</total_fee><spbill_create_ip>192.168.8.22</spbill_create_ip><notify_url>http://192.168.8.22:8090/app/WeiXinPay/weixinotify.do</notify_url><trade_type>APP</trade_type></xml>\n");
        System.out.println(s);
    }
}
