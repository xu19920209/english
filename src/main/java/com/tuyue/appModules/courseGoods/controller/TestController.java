package com.tuyue.appModules.courseGoods.controller;

import com.tuyue.appModules.courseGoods.biz.PartMoneyBiz;
import com.tuyue.appModules.courseGoods.biz.WeChatPayBiz;
import com.tuyue.util.JacksonUtils;
import com.tuyue.weichat.WeChatPaySignature;
import com.tuyue.weichat.WeChatUtil;
import com.tuyue.weichat.WeixinNotifyResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static com.vdurmont.emoji.EmojiParser.parseToUnicode;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/18.
 */
@Controller
@RequestMapping("a")
@ResponseBody
public class TestController {
    @Autowired
    private PartMoneyBiz partMoneyBiz;
    @RequestMapping("bbbb")
    public boolean b(String a) throws Exception {
         return  partMoneyBiz.partMoney(a);

    }

}
