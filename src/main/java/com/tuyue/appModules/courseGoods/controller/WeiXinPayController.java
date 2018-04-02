package com.tuyue.appModules.courseGoods.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tuyue.appModules.courseGoods.biz.WeChatPayBiz;
import com.tuyue.appModules.weChatGongZhong.bean.KaiKe;
import com.tuyue.appModules.weChatGongZhong.util.WeiUtil;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.*;
import com.tuyue.webModules.courseGoods.biz.TuiJianRen;
import com.tuyue.weichat.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/13.
 */
@Controller
@ResponseBody
@RequestMapping("app/WeiXinPay")
public class WeiXinPayController {
    private final static Logger logger = LoggerFactory.getLogger(WeiXinPayController.class);
    @Autowired
    private WeChatPayBiz weChatPayBiz;
    @Autowired
    private TuiJianRen tuiJianRen;


    /**
     * 统一下单
     *
     * @param orderNo
     * @param nid
     * @param body
     * @param request
     * @return
     * @throws Exception
     */
    @Transactional
    @RequestMapping("unifiedorder.do")
    public Result unifiedorder(String orderNo, Integer nid, String body, HttpServletRequest request, Integer type, String openid) throws Exception {
        System.out.println("统一下单");
        String ipAddr = GetIp.getIpAddr(request);
        System.out.println("-------ip = " + ipAddr);
        ipAddr = ipAddr.split(",")[0];
        System.out.println("ip::" + ipAddr);
        if (type == null || type != 1) {
            //body = new String(body.getBytes("ISO-8859-1"), "utf-8");
        }
        CourseGoodsOrder unifiedorder = weChatPayBiz.unifiedorder(orderNo, nid);
        if (unifiedorder == null) {
            return ResultUtil.error(2, "查不到改订单");
        }
        //将订单改为支付中状态
        weChatPayBiz.OrderState(orderNo, 3);
        //签名
        WXinPay wXinPay = new WXinPay();
        String key = "";
        if (type != null && type == 1) {
            //app支付
            wXinPay.setAppid(WeiXinParam.appid);
            wXinPay.setMch_id(WeiXinParam.mchId);
            wXinPay.setTrade_type("APP");
            key = WeiXinParam.Key;
        } else {
            //公众号支付
            wXinPay.setAppid("wxe3a90d951480bf12");
            wXinPay.setMch_id(WeiXinParam.gongMchId);
            if (openid!=null&&!"".equals(openid))
            {
                wXinPay.setTrade_type("JSAPI");
                wXinPay.setOpenid(openid);
            }else{
                wXinPay.setTrade_type("JSAPI");
                //wXinPay.setTrade_type("MWEB");
               // wXinPay.setScene_info("{\"h5_info\": {\"type\":\"Wap\",\"wap_url\": \"http://www.ai4001120150.com\",\"wap_name\": \"课程购买\"}}");
            }
            key = WeiXinParam.gongKey;
        }
        wXinPay.setNonce_str(System.currentTimeMillis() + RandomUtil.randomString(5));
        wXinPay.setOut_trade_no(unifiedorder.getOrderNo());
        wXinPay.setTotal_fee((int) (unifiedorder.getPayMoney() * 10 * 10));
        wXinPay.setBody(body);
        wXinPay.setSign_type("MD5");
        wXinPay.setSpbill_create_ip(ipAddr);
        wXinPay.setNotify_url(WeiXinParam.weiUrl);
        String sign = WeChatPaySignature.getSign(wXinPay, key);
        System.out.println("sign:" + sign);
        wXinPay.setSign(sign);
        //将信息转为xml
        String xml = JacksonUtils.objectParseXml(wXinPay);
        System.out.println(xml);
        String result = WeChatHttp.httpsRequest(WeChatHttp.UNIFEDORDER, "POST", xml);
        System.out.println(result);
        Map<String, Object> map = WeChatUtil.toXml(result);
        System.out.println(map.toString());
        if (map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")) {
            long millis = System.currentTimeMillis() / 1000;
            if (type != null && type == 1) {
                String s = "appid=" + map.get("appid") + "&noncestr=" + map.get("nonce_str") + "&package=Sign=WXPay" + "&partnerid=" + map.get("mch_id") + "&prepayid=" + map.get("prepay_id") + "&timestamp=" + millis + "&key=" + WeiXinParam.Key;
                result = WeChatPayMD5.MD5Encode(s).toUpperCase();
            } else {
                Map mapSign = new HashMap();
                mapSign.put("appId", map.get("appid"));
                mapSign.put("timeStamp", millis);
                mapSign.put("nonceStr", map.get("nonce_str"));
                mapSign.put("package", "prepay_id=" + map.get("prepay_id"));
                mapSign.put("signType", "MD5");
                result = WeChatPaySignature.getSignMap(mapSign, WeiXinParam.gongKey);
            }
            System.out.println("SUCCESSsign:" + result);
            Map map1 = new HashMap();
            map1.put("sign", result);
            map1.put("prepayid", map.get("prepay_id"));
            map1.put("noncestr", map.get("nonce_str"));
            map1.put("timestamp", millis);
            map1.put("mchId", WeiXinParam.mchId);
            return ResultUtil.success(map1);
        }
        System.out.println("erroe");
        return ResultUtil.error(2, (String) map.get("return_msg"));
    }

    /**
     * 支付回调 app
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @Transactional
    @RequestMapping("weixinotify.do")
    public void weixinotify(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        System.out.println("微信支付通知进来了");
        // 微信支付通知进来了
        PrintWriter out = null;
        StringBuffer xmlStr = new StringBuffer();
        WeixinNotifyResult weixinNotifyResult = new WeixinNotifyResult();
        weixinNotifyResult.setReturn_code("SUCCESS");
        weixinNotifyResult.setReturn_msg("支付成功！");
        boolean flag = true;
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                xmlStr.append(line);
            }
            if (xmlStr.length() <= 0) {
                flag = false;
                logger.error("接受参数为空可能被第三方篡改");
                weixinNotifyResult.setReturn_code("FAIL");
                weixinNotifyResult.setReturn_msg("接受参数为空可能被第三方篡改");
            }
            System.out.println("微信返回信息：：：" + xmlStr);
            //将微信传来的xml转map
            Map<String, Object> map = WeChatUtil.toXml(xmlStr.toString());
            String sign1 = (String) map.get("sign");
            String appid = (String) map.get("appid");
            String key = "";
            if (appid.equals(WeiXinParam.appid)) {
                key = WeiXinParam.Key;
            } else {
                key = WeiXinParam.gongKey;
            }
            map.put("sign", "");
            //校验签名是否正确
            String sign = WeChatPaySignature.getSignMap(map, key);
            System.out.println("生成的签名" + sign);
            //支付成功了
            if (map.get("return_code").equals("SUCCESS")) {
                if (sign.equals(sign1)) {
                } else {
                    flag = false;
                    logger.error("签名验证失败！");
                    weixinNotifyResult.setReturn_code("FAIL");
                    weixinNotifyResult.setReturn_msg("签名验证失败");
                }
                //验证金额
                ACourseGood aCourseGood1 = weChatPayBiz.selectByOrderNo((String) map.get("out_trade_no"));
                if (aCourseGood1 == null) {
                    flag = false;
                    logger.error("订单号为空可能被第三方篡改");
                    weixinNotifyResult.setReturn_code("FAIL");
                    weixinNotifyResult.setReturn_msg("订单号为空可能被第三方篡改");
                }
                double money = 0;
                money = aCourseGood1.getCoursePrice() * 10 * 10;
                System.out.println("money1" + (int) money);
                System.out.println("money2" + Integer.parseInt((String) map.get("total_fee")));
                if ((int) money != Integer.parseInt((String) map.get("total_fee"))) {
                    flag = false;
                    logger.error("订单金额不符可能被第三方篡改");
                    weixinNotifyResult.setReturn_code("FAIL");
                    weixinNotifyResult.setReturn_msg("订单金额不符可能被第三方篡改");
                }


                CourseGoodsOrder courseGoodsOrder = new CourseGoodsOrder();
                if (flag) {
                    courseGoodsOrder.setOrderState(1);
                } else {
                    courseGoodsOrder.setOrderState(4);
                }
                courseGoodsOrder.setPayMoney(aCourseGood1.getCoursePrice());
                courseGoodsOrder.setPayWay(1);
                courseGoodsOrder.setPayTime(new Timestamp(System.currentTimeMillis()));
                courseGoodsOrder.setOrderNo((String) map.get("out_trade_no"));
                courseGoodsOrder.setDetails(xmlStr.toString());
                DecimalFormat df = new DecimalFormat("0.00");
                String mony = (String) map.get("total_fee");
                int moneys = Integer.parseInt(mony);
                String s = df.format(((float) moneys / (10 * 10)));
                //修改订单状态分销
                boolean b = weChatPayBiz.courseGoodOrder(courseGoodsOrder);
                if(!b){
                    flag = false;
                    logger.error("修改订单信息失败");
                    weixinNotifyResult.setReturn_code("FAIL");
                    weixinNotifyResult.setReturn_msg("修改订单信息失败");
                }else{
                    if (flag) {
                        weixinNotifyResult.setReturn_code("SUCCESS");
                        weixinNotifyResult.setReturn_msg("支付成功！");
                    }
                }
                if (courseGoodsOrder.getOrderState() == 1) {
                    if (map.get("trade_type").equals("APP")) {
                        Nstudent nstudent = weChatPayBiz.selectNstudentByOrderNo((String) map.get("out_trade_no"));
                        HashMap<String, String> maps = new HashMap<String, String>();
                        String s1 = Md5Util.toMD5(nstudent.getUsername());
                        maps.put("type", String.valueOf(3));
                        JPushUtil.sendMsgToIos("购买通知", "您已成功购买" + aCourseGood1.getCourseName() + "课程", "", maps, s1);
                        JPushUtil.sendMsgToApp("购买通知", "购买通知", "您已成功购买" + aCourseGood1.getCourseName() + "课程", maps, s1);
                    } else {
                        KaiKe kaiKe = new KaiKe();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String format = simpleDateFormat.format(new Date());
                        String addr = "AA英语";
                        if (aCourseGood1.getEid() != null) {
                            Ebranchschool ebranchschool = weChatPayBiz.byEid(aCourseGood1.getEid());
                            if (ebranchschool == null) {
                                addr = ebranchschool.getName();
                            }
                        }
                        kaiKe.pay((String) map.get("openid"), null, "恭喜你购买成功！", aCourseGood1.getCourseName(), format, addr, WeiUtil.accessToken());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //告诉微信已经接到通知了
            String xml = JacksonUtils.objectParseXml(weixinNotifyResult);
            if (out != null) {
                out.close();
            }
            response.getWriter().write(xml);
        }
    }

}