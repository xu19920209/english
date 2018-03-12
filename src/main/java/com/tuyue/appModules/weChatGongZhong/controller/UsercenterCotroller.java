package com.tuyue.appModules.weChatGongZhong.controller;

import com.tuyue.appModules.weChatGongZhong.biz.UserCenterBiz;
import com.tuyue.pojo.Agent;
import com.tuyue.pojo.Nstudent;
import com.tuyue.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/2/27.
 */
@RestController
@RequestMapping("app/gongzhong")
public class UsercenterCotroller {
    @Autowired
    private UserCenterBiz userCenterBiz;
    /**
     * 注册
     * @param nstudent
     * @return
     */
    @RequestMapping("userLogin")
    public Result userLogin(Nstudent nstudent) throws Exception{
        if(nstudent.getNid()>0){
            return userCenterBiz.upLogin(nstudent);
        }
        return userCenterBiz.userLogin(nstudent);
    }

    /**
     * 修改用户
     * @param nstudent
     * @return
     */
    @RequestMapping("upLogin")
    public Result upLogin(Nstudent nstudent) throws Exception{
        return userCenterBiz.upLogin(nstudent);
    }

    /**
     * 个人中心
     * @param nid
     * @return
     */
    @RequestMapping("userCenter")
    public Result userCenter(@RequestParam(required = true) int nid) throws Exception{
        return userCenterBiz.userCenter(nid);
    }

    /**
     * 一键领取金额
     * @param ids
     * @return
     */
    @RequestMapping("getMoney")
    public Result getMoney(@RequestParam(required = true)String ids,@RequestParam(required = true)int nid) throws Exception{
        return userCenterBiz.getMoney(ids,nid);
    }

    /**
     * 提现
     * @param nid
     * @param money
     * @return
     * @throws Exception
     */
    @RequestMapping("withdraw")
    public Result withdraw(@RequestParam(required = true)int nid,@RequestParam(required = true)double money) throws Exception{
        return userCenterBiz.withdraw(nid,money);
    }

    /**
     * 流水记录
     * @param nid
     * @return
     */
    @RequestMapping("flowRecord")
    public Result flowRecord(@RequestParam(required = true)int nid) throws Exception{
        return userCenterBiz.flowRecord(nid);
    }
    /**
     * @Author: 徐慷慨
     * @Description:我推荐的
     * @Date: 13:57 2018/2/27
     */
    @RequestMapping("meTuijain")
    public Result meTuijain(@RequestParam(required = true)int nid) throws Exception{
        return userCenterBiz.meTuijain(nid);
    }
    /**
     * @Author: 徐慷慨
     * @Description:消息中心
     * @Date: 14:39 2018/2/27
     */
    @RequestMapping("newsCenter")
    public Result newsCenter(@RequestParam(required = true)int nid) throws Exception{
        return userCenterBiz.newsCenter(nid);
    }
    /**
     * 绑定银行卡
     * @param agent
     * @return
     */
    @RequestMapping("inAgentBank")
    public Result inAgentBank( Agent agent) throws Exception{
        return userCenterBiz.inAgentBank(agent);
    }

    /***
     * 公众号登录
     * @param nstudent
     * @return
     */
    @RequestMapping("login")
    public Result  login( Nstudent nstudent) throws Exception{
        return userCenterBiz.login(nstudent);
    }
    /**
     * 我的银行卡
     * @param nid
     * @return
     */
    @RequestMapping("meBankList")
    public Result meBankList(@RequestParam(required = true) int nid) throws Exception{
        return userCenterBiz.meBankList(nid);
    }
}
