package com.tuyue.appModules.weChatGongZhong.biz;

import com.tuyue.pojo.Agent;
import com.tuyue.pojo.Nstudent;
import com.tuyue.result.Result;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/2/23.
 */
public interface UserCenterBiz {
    /**
     * 注册
     * @param nstudent
     * @return
     */
    public Result userLogin(Nstudent nstudent) throws Exception;

    /**
     * 修改用户
     * @param nstudent
     * @return
     */
    public Result upLogin(Nstudent nstudent) throws Exception;

    /**
     * 个人中心
     * @param nid
     * @return
     */
    public Result userCenter(int nid) throws Exception;

    /**
     * 一键领取金额
     * @param ids
     * @return
     */
    public Result getMoney(String ids, int nid) throws Exception;

    /**
     * 提现
     * @param nid
     * @param money
     * @return
     * @throws Exception
     */
    public Result withdraw(int nid, double money) throws Exception;

    /**
     * 流水记录
     * @param nid
     * @return
     */
    public Result flowRecord(int nid) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:我推荐的
     * @Date: 13:57 2018/2/27
     */
    public Result meTuijain(int nid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:消息中心
     * @Date: 14:39 2018/2/27
     */
    public Result newsCenter(int nid) throws Exception;

    /**
     * 绑定银行卡
     * @param agent
     * @return
     */
    public Result inAgentBank(Agent agent) throws Exception;

    /***
     * 公众号登录
     * @param nstudent
     * @return
     */
    public Result  login(Nstudent nstudent) throws Exception;

    /**
     * 我的银行卡
     * @param nid
     * @return
     */
    public Result meBankList(int nid) throws Exception;

}
