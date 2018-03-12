package com.tuyue.appModules.weChatGongZhong.controller;

import com.tuyue.appModules.personalCenter.biz.IpersonalCenterBiz;
import com.tuyue.appModules.weChatGongZhong.util.WeiUtil;
import com.tuyue.pojo.Nstudent;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.weichat.WeChatUtil;
import com.tuyue.weichat.WeixinNotifyResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiLabelUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/15.
 */
@Controller
@ResponseBody
@RequestMapping("app/weChatCommon")
public class WeChatCommonController {
    private static final Logger logger = LoggerFactory.getLogger(WeChatCommonController.class);

    @Autowired
    private IpersonalCenterBiz ipersonalCenterBiz;

    @RequestMapping("com.do")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String echostr = request.getParameter("echostr");
//        System.out.println("echostr::" + echostr);
//        if (echostr != null && !"".equals(echostr)) {
//            //log.info("服务器接入生效..........");
//            response.getWriter().print(echostr);// 完成相互认证
//        }
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String respMessage = "";
        PrintWriter out = null;
        StringBuffer xmlStr = new StringBuffer();
        WeixinNotifyResult weixinNotifyResult = new WeixinNotifyResult();
        boolean flag = true;
        BufferedReader reader = request.getReader();
        String line = null;
        while ((line = reader.readLine()) != null) {
            xmlStr.append(line);
        }
        System.out.println("::" + xmlStr);
        //将微信传来的xml转map
        Map<String, Object> map = WeChatUtil.toXml(xmlStr.toString());
        String msgType = (String) map.get("MsgType");
        try {
            if (msgType.equals("event")) {
                // 发送方账号
                String fromUserName = (String) map.get("FromUserName");
                // 公众账号
                String toUserName = (String) map.get("ToUserName");
                // 消息ID 64位整型
                String MsgId = (String) map.get("MsgId");

                String createTime = (String) map.get("createTime");
                // 如果是事件的话 进入
                String Event = (String) map.get("Event");
                System.out.println("Event::" + Event);
                // 点击按钮事件
                if (Event.equals("scancode_waitmsg")) {
                    //拿取scanResult
                    int j = xmlStr.indexOf("<ScanResult><![CDATA[");
                    int j1 = xmlStr.indexOf("]]></ScanResult>");
                    String scanResult = xmlStr.substring(j + 21, j1);
                    String EventKey = (String) map.get("EventKey");
                    System.out.println("我是点击事件");
                    if (EventKey.equals("sacn01")) { // 扫码签到
                        System.out.println("scan = " + scanResult);
                        Nstudent nstudent = ipersonalCenterBiz.selectByopenId(fromUserName);
                        if (nstudent == null) {
                            respMessage = WeiUtil.createMessageImageText(fromUserName, toUserName, (String) map.get("CreateTime"), "注册账号", "点击在此注册账号", "http://www.ai4001120150.com/AAEnglish/img/1.jpg", "http://www.ai4001120150.com/aiwebapp/list.html?page=3&openId=" + fromUserName);
                        } else {
                            if (scanResult != null) {
                                int i = scanResult.indexOf("&/");
                                if (i == -1) {
                                    respMessage = WeiUtil.createMessageImageText(fromUserName, toUserName, (String) map.get("CreateTime"), "请扫老师提供的二维码", "扫码失败", "http://www.ai4001120150.com/AAEnglish/img/1.jpg", "");
                                } else {
                                    String subUrl = scanResult.substring(0, i);
                                    respMessage = WeiUtil.createMessageImageText(fromUserName, toUserName, (String) map.get("CreateTime"), "扫码签到", "点击扫码", "http://www.ai4001120150.com/AAEnglish/img/1.jpg", subUrl);
                                }
                            } else {
                                respMessage = WeiUtil.createMessageImageText(fromUserName, toUserName, (String) map.get("CreateTime"), "请扫老师提供的二维码", "扫码失败", "http://www.ai4001120150.com/AAEnglish/img/1.jpg", "");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out = response.getWriter();
            out.print(respMessage);
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:根据code获取openid
     * @Date: 11:39 2017/12/21
     */
 /*   String curCode = "";
    String curOpenId = "";*/
    @RequestMapping("getOpenId.do")
    public Result getOpenId(String code) throws Exception {
        System.out.println("<><><>>>>>>>>>>><<<<<<<<<<< code = "+code);
        String openid = WeiUtil.getOpenid(code);
        if (openid != null) {
           // Nstudent nstudent = ipersonalCenterBiz.selectByopenId(openid);
            Map map = new HashMap();
            map.put("openId", openid);
            System.out.println("根据code获取openid:" + openid);
            //if (nstudent != null) {
               /// map.put("nid", nstudent.getNid());
//                logger.error("获取用户成功");
//                System.out.println("返回的数据："+map.toString());
               // return ResultUtil.success("获取用户成功！", map);
           // }
            logger.error("获取openId成功");
            System.out.println("返回的数据"+map.toString());
            return ResultUtil.success("获取openId！", map);
        }
        return ResultUtil.error(2, "获取openId失败！");
    }


}
