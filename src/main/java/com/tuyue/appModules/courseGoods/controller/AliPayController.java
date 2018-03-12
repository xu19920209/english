package com.tuyue.appModules.courseGoods.controller;

import com.alipay.api.internal.util.AlipaySignature;
import com.tuyue.aLi.AliParam;
import com.tuyue.aLi.OrderInfoUtil2_0;
import com.tuyue.appModules.courseGoods.biz.WeChatPayBiz;
import com.tuyue.pojo.ACourseGood;
import com.tuyue.pojo.CourseGoodsOrder;
import com.tuyue.pojo.Nstudent;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.*;
import com.tuyue.webModules.courseGoods.biz.TuiJianRen;
import com.tuyue.weichat.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/15.
 */
@Controller
@ResponseBody
@RequestMapping("app/AliPay")
public class AliPayController {
    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(AliPayController.class);
    @Autowired
    private WeChatPayBiz weChatPayBiz;
    @Autowired
    private TuiJianRen tuiJianRen;

    /**
     * @Author: 徐慷慨
     * @Description:支付宝统一下单
     * @Date: 14:01 2017/12/20
     */
    @RequestMapping("unifiedorder.do")
    public Result unifiedorder(String orderNo, Integer nid, String body, HttpServletRequest request,String subject) throws Exception {
        String ipAddr = GetIp.getIpAddr(request);
        CourseGoodsOrder unifiedorder = weChatPayBiz.unifiedorder(orderNo, nid);
        if (unifiedorder == null) {
            return ResultUtil.error(2, "查不到改订单");
        }
        //将订单改为支付中状态
//        weChatPayBiz.OrderState(orderNo, 3);
        //进行签名
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(AliParam.appid,body,"30m",AliParam.alUrl,orderNo,subject,String.valueOf(unifiedorder.getPayMoney()),false);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        String sign = OrderInfoUtil2_0.getSign(params, AliParam.ali_privateKey, false);
        final String orderInfo = orderParam + "&" + sign;
        //Map map=new HashMap();
        return ResultUtil.success("SUCCESS",orderInfo);
    }

    /**
     * 支付宝回调地址
     * @param request
     * @param response
     * @throws Exception
     */
    @Transactional
    @RequestMapping("notifyUrl.do")
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //将map转xml保存数据库
        String xml = WeChatUtil.callMapToXML(params);
        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        boolean flag = AlipaySignature.rsaCheckV1(params, AliParam.alipaypublickey, "UTF-8", "RSA");
        String back = "failure";
        if (flag) {
  // 1、商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号
            CourseGoodsOrder courseGoodsOrder1 = weChatPayBiz.booleanOrderNo(params.get("out_trade_no"));
            if(courseGoodsOrder1!=null){
                    back="success";
                }else{
                    logger.error("查不到改订单");
                }
         //2、判断total_amount是否确实为该订单的实际金额
                ACourseGood aCourseGood = weChatPayBiz.selectByOrderNo(params.get("out_trade_no"));
                if(aCourseGood!=null){
                    if(aCourseGood.getCoursePrice()==Double.parseDouble(params.get("total_amount"))){
                        back="success";
                    }else{
                        logger.error("订单金额不符合");
                    }

                }
               // 3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
                if(AliParam.seller_id.equals(params.get("seller_id"))){
                    back="success";
                }else{
                    logger.error("商户号不对");
                }
               // 4、验证app_id是否为该商户本身
                if(AliParam.appid.equals(params.get("app_id"))){
                    back="success";
                }else{
                    logger.error("appid不对");
                }
                //数据库操作
                CourseGoodsOrder courseGoodsOrder = new CourseGoodsOrder();
                if (back.equals("success")) {
                    courseGoodsOrder.setOrderState(1);
                } else {
                    courseGoodsOrder.setOrderState(4);
                }
                courseGoodsOrder.setPayWay(2);
                courseGoodsOrder.setPayTime(new Timestamp(System.currentTimeMillis()));
                courseGoodsOrder.setOrderNo(params.get("out_trade_no"));
                courseGoodsOrder.setDetails(xml);
                courseGoodsOrder.setPayMoney(Double.parseDouble(params.get("total_amount")));
                boolean b1 = weChatPayBiz.courseGoodOrder(courseGoodsOrder);
                if(courseGoodsOrder.getOrderState()==1){
                Nstudent nstudent = weChatPayBiz.selectNstudentByOrderNo((String) params.get("out_trade_no"));
                HashMap<String, String> maps = new HashMap<String, String>();
                String s1 = Md5Util.toMD5(nstudent.getUsername());
                maps.put("type", String.valueOf(3));
                JPushUtil.sendMsgToIos("购买通知","","您已成功购买"+aCourseGood.getCourseName()+"课程",maps,s1);
                JPushUtil.sendMsgToApp("购买通知","","您已成功购买"+aCourseGood.getCourseName()+"课程",maps,s1);
            }
                //上述1、2、3、4有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
            if(b1&&courseGoodsOrder.getOrderState()==1){
                back="success";
            }else{
                back="failure";
            }
            System.out.println("验证成功！"+back);
            PrintWriter out = response.getWriter();
            out.println(back);
             } else {
            // TODO 验签失败则记录异常日志，并在response中返回failure.
            System.out.println("验证失败");
            PrintWriter out = response.getWriter();
              out.println("failure");
            }
        }
    }

