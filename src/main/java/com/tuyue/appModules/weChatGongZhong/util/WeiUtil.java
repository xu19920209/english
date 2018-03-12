package com.tuyue.appModules.weChatGongZhong.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tuyue.appModules.weChatGongZhong.bean.KaiKe;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.crypto.dom.DOMCryptoContext;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/18.
 */
public class WeiUtil {
    private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(this.getClass().getName());
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";// 获取access
    public static final String APP_ID = "wxe3a90d951480bf12";  //微信公众号的appid  wx1ef3b83d2c564b89
    public static final String SECRET = "bfcacc2444408a103ac6ae7a6508f19a";//微信账公众号的secret  cfd387f49b20216c6aeb6971cbbef11d

    /**
     * dxml解析
     *
     * @param document 文档解析为map集合
     * @return
     */
    public static Map<String, String> xmljx(Document document) {

        Map<String, String> map = new HashMap<String, String>();

        Element root = document.getRootElement();
        @SuppressWarnings("unchecked")
        List<Element> elementList = root.elements();

        for (Element e : elementList) {
            if (e.getName().equals("SendLocationInfo")) {
                Iterator it = e.elementIterator();
                while (it.hasNext()) {
                    Element user = (Element) it.next();
                    map.put(user.getName(), user.getText());
                }
                continue;
            }
            if (e.getName().equals("ScanCodeInfo")) {
                Iterator it = e.elementIterator();
                while (it.hasNext()) {
                    Element user = (Element) it.next();
                    map.put(user.getName(), user.getText());
                }
                continue;
            }
            map.put(e.getName(), e.getText());
            System.out.println("e.getName():::" + e.getName()
                    + "     e.getText():::" + e.getText());
        }

        return map;
    }

    /**
     * @Author: 徐慷慨
     * @Description:获取accessToken //https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     * @Date: 10:38 2017/12/18
     */
    public static String accessToken() {
        String turl = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WeGongZhongParam.appid + "&secret=" + WeGongZhongParam.AppSecret);
        System.out.println("url:" + turl);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(turl);
        String accessToken = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            // 将json字符串转换为json对象
            JSONObject json = JSON.parseObject(responseContent);
            System.out.println(json);
            accessToken = json.getString("access_token");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return accessToken;
        }
    }


    /**
     * 创建微信自定义菜单
     *
     * @param params accessToken
     * @return
     */
    public static void createMenu(String params, String accessToken) {
        StringBuffer bufferRes = new StringBuffer();
        try {
            URL realUrl = new URL(
                    " https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
                            + accessToken);
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 连接超时
            conn.setConnectTimeout(25000);
            // 读取超时 --服务器响应比较慢，增大时间
            conn.setReadTimeout(25000);

            HttpURLConnection.setFollowRedirects(true);
            // 请求方式
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
            conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            OutputStreamWriter out = new OutputStreamWriter(
                    conn.getOutputStream());
            // 发送请求参数
            out.write(URLEncoder.encode(params, "UTF-8"));
            // out.write(params);
            out.flush();
            out.close();

            InputStream in = conn.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in,
                    "UTF-8"));
            String valueString = null;
            while ((valueString = read.readLine()) != null) {
                bufferRes.append(valueString);
            }
            System.out.println(bufferRes.toString());
            in.close();
            if (conn != null) {
                // 关闭连接
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建微信自定义菜单
     *
     * @param
     * @return
     */
    public static String getToken(String apiurl, String appid, String secret) {
        String turl = String.format(
                "%s?grant_type=client_credential&appid=%s&secret=%s", apiurl,
                appid, secret);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(turl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            JSONObject json = JSON.parseObject(responseContent);
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                if (json.get("errcode") != null) {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                } else {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                    result = json.get("access_token").toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            System.out.println("token::" + result);
            return result;
        }
    }

    // 通过openid获取用户信息

    /**
     * 通过openid获取用户信息
     *
     * @param openid
     * @return
     */
    public static String getUserInfor(String openid) {
        String apiurl = "https://api.weixin.qq.com/cgi-bin/user/info";
        String accessToken = getToken(GET_TOKEN_URL, APP_ID, SECRET);
        String turl = String.format("%s?access_token=%s&openid=%s", apiurl,
                accessToken, openid);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(turl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            JSONObject json = JSON.parseObject(responseContent);
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                if (json.get("errcode") != null) {
                    // 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                } else {
                    // 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                    result = json.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }

    // 通过code获取用户openid

    /**
     * 通过code获取用户openid
     *
     * @param code
     * @return
     */
    public static String getOpenid(String code) {
        String apiurl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + APP_ID
                + "&secret="
                + SECRET
                + "&code="
                + code
                + "&grant_type=authorization_code";
        // String accessToken = getToken(GET_TOKEN_URL, APP_ID, SECRET);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(apiurl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            // 将json字符串转换为json对象
            JSONObject json = JSON.parseObject(responseContent);
            System.out.println(":::" + json.toJSONString());
            result = (String) json.get("openid");
//                if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                    if (json.get("errcode") != null) {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
//                    } else {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
//                        result = json.toString();
//                    }
//                }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }

    // 客服回复接口
    public static void ServiceCon(String openid, String content) {

        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"touser\": \"" + openid + "\", ");
        sb.append("\"msgtype\": \"text\",");
        sb.append(" \"text\": {");
        String ss = " \"content\": \"" + content + "\"";
        System.out.println(ss);
        sb.append(ss);
        sb.append("}}");
        String json = sb.toString();
        System.out.println(json);
        StringBuffer bufferRes = new StringBuffer();
        String accessToken = getToken(GET_TOKEN_URL, APP_ID, SECRET);
        try {
            URL realUrl = new URL(
                    "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
                            + accessToken);
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();

            // 连接超时
            conn.setConnectTimeout(25000);
            // 读取超时 --服务器响应比较慢，增大时间
            conn.setReadTimeout(25000);

            HttpURLConnection.setFollowRedirects(true);
            // 请求方式
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
            conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            OutputStreamWriter out = new OutputStreamWriter(
                    conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            // out.write(URLEncoder.encode(params,"UTF-8"));
            out.write(json);
            out.flush();
            out.close();

            InputStream in = conn.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in,
                    "UTF-8"));
            String valueString = null;
            while ((valueString = read.readLine()) != null) {
                bufferRes.append(valueString);
            }
            in.close();
            if (conn != null) {
                // 关闭连接
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String createMessageText(String fromUserName, String toUserName,
                                           String createTime, String content) {
        StringBuffer revert = new StringBuffer();
        revert.append("<xml>");
        revert.append("<ToUserName><![CDATA[" + fromUserName
                + "]]></ToUserName>");
        revert.append("<FromUserName><![CDATA[" + toUserName
                + "]]></FromUserName>");
        revert.append("<CreateTime>" + createTime + "</CreateTime>");
        revert.append("<MsgType><![CDATA[text]]></MsgType>");
        revert.append("<Content><![CDATA[" + content + "]]></Content>");
        revert.append("</xml>");
        return revert.toString();
    }


    // 创建图文xml
    public static String createMessageImageText(String fromUserName,
                                                String toUserName, String createTime, String title,
                                                String description, String picurl, String url) {
        StringBuffer revert = new StringBuffer();
        revert.append("<xml>");
        revert.append("<ToUserName><![CDATA[" + fromUserName
                + "]]></ToUserName>");
        revert.append("<FromUserName><![CDATA[" + toUserName
                + "]]></FromUserName>");
        revert.append("<CreateTime>" + createTime + "</CreateTime>");
        revert.append("<MsgType><![CDATA[news]]></MsgType>");
        revert.append("<ArticleCount>1</ArticleCount>");
        revert.append("<Articles>");
        revert.append("<item>");
        revert.append("<Title><![CDATA[" + title + "]]></Title> ");
        revert.append("<Description><![CDATA[" + description
                + "]]></Description>");
        revert.append("<PicUrl><![CDATA[" + picurl + "]]></PicUrl>");
        revert.append("<Url><![CDATA[" + url + "]]></Url>");
        revert.append("</item>");
        revert.append("</xml>");
        return revert.toString();
    }

    // 通过GPS经纬度获取百度地图经纬度
    public static String getMapInfor(String lox, String loy)
            throws UnsupportedEncodingException {

        String apiurl = "http://api.map.baidu.com/geocoder/v2/?ak=M7wzOdfhh3nhGXQsB4tw8jQUwrGCEvne&callback=renderReverse&location="
                + lox + "," + loy + "&output=json&pois=1";
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(apiurl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            System.out.println("responseContent::::" + responseContent);
            String getSignInfo = responseContent.substring(
                    responseContent.indexOf("(") + 1,
                    responseContent.lastIndexOf(")"));
            System.out.println("getSignInfo:::" + getSignInfo);
            JSONObject json = JSON.parseObject(getSignInfo);
            result = json.toString();
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // if (json.get("errcode") != null)
                // {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                // }
                // else
                // {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                // result = json.toString();
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }

    // 通过GPS经纬度获取百度地图经纬度
    public static String getbaiduMapLocation(String lox, String loy) {
        String apiurl = "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x="
                + loy + "&y=" + lox + "";
        System.out.println(apiurl);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(apiurl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");

            result = responseContent;
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // if (json.get("errcode") != null)
                // {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                // }
                // else
                // {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                // result = json.toString();
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }

    // code换取oppenid
    public static String getOpenidByCode(String code) {
        String apiurl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + APP_ID
                + "&secret="
                + SECRET
                + "&code="
                + code
                + "&grant_type=authorization_code";
        System.out.println(apiurl);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(apiurl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");

            result = responseContent;
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // if (json.get("errcode") != null)
                // {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                // }
                // else
                // {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                // result = json.toString();
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }

    /**
     * 调用微信JS接口的临时票据
     *
     * @param
     * @return
     */
    public static String getJsApiTicket() {
        String accessToken = getToken(GET_TOKEN_URL, APP_ID, SECRET);

        String apiurl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                + accessToken + "&type=jsapi";
        System.out.println(apiurl);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(apiurl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");

            result = responseContent;
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // if (json.get("errcode") != null)
                // {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                // }
                // else
                // {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                // result = json.toString();
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }

    public static String gettianqi(String x, String y) {
        String apiurl = "http://api.map.baidu.com/telematics/v3/weather?location="
                + y
                + ","
                + x
                + ";&output=json&ak=M7wzOdfhh3nhGXQsB4tw8jQUwrGCEvne";
        System.out.println(apiurl);
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(apiurl);
        String result = null;
        try {
            HttpResponse res = client.execute(get);
            String responseContent = null; // 响应内容
            HttpEntity entity = res.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");

            result = responseContent;
            // 将json字符串转换为json对象
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // if (json.get("errcode") != null)
                // {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                // }
                // else
                // {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                // result = json.toString();
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接 ,释放资源
            client.getConnectionManager().shutdown();
            return result;
        }
    }


    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, null, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
                    .openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(
                    inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(
                    inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSON.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    // 创建模板消息
    public static String createTemplateMsg(String openid, String template_id,
                                           String url, String first, String keynote1, String keynote2,
                                           String remark) {
        StringBuffer sb = new StringBuffer();

        sb.append("{\"touser\":\"");
        sb.append(openid);
        sb.append("\",");

        sb.append("\"template_id\":\"");
        sb.append(template_id);
        sb.append("\",");

        sb.append("\"url\":\"");
        sb.append(url);
        sb.append("\",");
        sb.append("\"data\":{");
        sb.append("\"first\": {");
        sb.append("\"value\":\"");
        sb.append(first);
        sb.append("\",");
        sb.append("\"color\":\"#173177\"");
        sb.append("},");

        sb.append("\"keyword1\":{");
        sb.append("\"value\":\"");
        sb.append(keynote1);
        sb.append("\",");
        sb.append("\"color\":\"#173177\"");
        sb.append(" },");

        sb.append("\"keyword2\": {");
        sb.append("\"value\":\"");
        sb.append(keynote2);
        sb.append("\",");
        sb.append("\"color\":\"#173177\"");
        sb.append(" },");

        sb.append("\"remark\":{");
        sb.append("\"value\":\"");
        sb.append(remark);
        sb.append("\",");
        sb.append("\"color\":\"#173177\"");
        sb.append("}");
        sb.append("}");
        sb.append("}");

        System.out.println(sb.toString());
        return sb.toString();
    }

    // 发送模板消息
    public static void sendTemplateMsg(String jsonData, String accessToken) {
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="
                + accessToken;
        System.out.println(requestUrl);
        JSONObject jsonObject = httpRequest(requestUrl, "POST", jsonData);
        System.out.println("::::::" + jsonObject.toString());
        if (jsonObject != null) {
            if ("0".equals(jsonObject.getString("errcode"))) {
                System.out.println("发送模板消息成功！");
            } else {
                System.out.println(jsonObject.getString("errcode"));
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        String json = "{\n" +
//                "           \"touser\":\"oRARAwtWrivQCX4SJKA95tQNmEqg\",\n" +
//                "           \"template_id\":\"wO7RyHGgvoFQCMR_ynIr-GGGPQwIHMJxXvwyaqbq3WI\",\n" +
//                "           \"url\":\"http://weixin.qq.com/download\",  \n" +
//                "                 \n" +
//                "           \"data\":{\n" +
//                "                   \"first\": {\n" +
//                "                       \"value\":\"恭喜你购买成功！\",\n" +
//                "                       \"color\":\"#173177\"\n" +
//                "                   },\n" +
//                "                   \"class\":{\n" +
//                "                       \"value\":\"巧克力\",\n" +
//                "                       \"color\":\"#173177\"\n" +
//                "                   },\n" +
//                "                   \"time\": {\n" +
//                "                       \"value\":\"39.8元\",\n" +
//                "                       \"color\":\"#173177\"\n" +
//                "                   },\n" +
//                "                   \"remark\":{\n" +
//                "                       \"value\":\"欢迎再次购买！\",\n" +
//                "                       \"color\":\"#173177\"\n" +
//                "                   }\n" +
//                "           }\n" +
//                "       }";
//        System.out.println(":::::::::::::"+json);
        //sendTemplateMsg(json, WeiUtil.accessToken());
        KaiKe kaiKe =new KaiKe();
        kaiKe.pay("oRARAwtWrivQCX4SJKA95tQNmEqg",null,"恭喜你购买成功！","asdsa","2017-12-12","xian",WeiUtil.accessToken());
    }


    public void sendWxTemplate(String openId,String template_id,String title,String courseName,String time,String addr,String access_token) {
        if (template_id==null) {
            template_id = "wO7RyHGgvoFQCMR_ynIr-GGGPQwIHMJxXvwyaqbq3WI";
        }
        String json = "{\n" +
                "    \"touser\": "+openId+", \n" +
                "    \"template_id\":"+template_id+", \n" +
                "    \"data\": {\n" +
                "        \"first\": {\n" +
                "            \"value\": "+title+", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }, \n" +
                "        \"class\": {\n" +
                "            \"value\": "+courseName+", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }, \n" +
                "        \"time\": {\n" +
                "            \"value\": "+time+", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }, \n" +
                "        \"remark\": {\n" +
                "            \"value\": \"欢迎再次购买！\", \n" +
                "            \"color\": \"#173177\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        sendTemplateMsg(json, access_token);
    }

}
