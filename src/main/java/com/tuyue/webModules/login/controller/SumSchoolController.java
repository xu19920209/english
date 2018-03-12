package com.tuyue.webModules.login.controller;

import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Tools;
import com.tuyue.webModules.login.biz.ISumLoginBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/8.
 * @Modified By:
 */
@Controller
@RequestMapping("login")
public class SumSchoolController {
    private final static Logger logger = LoggerFactory.getLogger(SumSchoolController.class);
    @Autowired
    private ISumLoginBiz sumLoginBiz;

    /**
     * 登陆
     * 王金海
     * @param name 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
    @RequestMapping("SumSchoolLogin")
    @ResponseBody
    public Result SumSchoolLogin( String name , String password) throws Exception {
        logger.error("登陆");
        if (Tools.isEmpty(name)&&Tools.isEmpty(password)) {
            return ResultUtil.error(2,"参数不能为空");
        }
        Result login = sumLoginBiz.login( name, password);
        return login;
    }
}
