package com.tuyue.appModules.weChatGongZhong.biz.impl;

import com.feilong.core.Validator;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tuyue.appModules.weChatGongZhong.bean.MettuijianList;
import com.tuyue.appModules.weChatGongZhong.bean.UserCenterBean;
import com.tuyue.appModules.weChatGongZhong.biz.UserCenterBiz;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/2/23.
 */
@Service
public class UserCenterBizImpl implements UserCenterBiz {
    @Autowired
    private IBaseDao<Nstudent> nstudentIBaseDao;
    @Autowired
    private IBaseDao<Agent>agentIBaseDao;
    @Autowired
    private IBaseDao<AgentAccount>agentAccountIBaseDao;
    @Autowired
    private IBaseDao<UserWallet> userWalletIBaseDao;
    @Autowired
    private IBaseDao<WalletDetails>walletDetailsIBaseDao;
    @Autowired
    private IBaseDao<Message> messageIBaseDao;
    /**
     * 公众号注册
     * @param nstudent
     * @return
     */
    @Override
    @Transactional
    public Result userLogin(Nstudent nstudent) throws Exception {
        Nstudent one1 = nstudentIBaseDao.findOne("from Nstudent where username='" + nstudent.getUsername() + "' and password='" + nstudent.getPassword() + "'");
        if(one1!=null){
            return ResultUtil.error(2,"该用户已被注册");
        }
        nstudent.setIsType(2);
        boolean nullOrEmpty = Validator.isNullOrEmpty(nstudent.getReferrerPhone());
        nstudent.setCreateTime(new Timestamp(System.currentTimeMillis()));
        if(!nullOrEmpty){
            Nstudent one = nstudentIBaseDao.findOne("from Nstudent where username='" + nstudent.getReferrerPhone() + "'");
            if(one==null){
                return ResultUtil.error(2,"您输入的推荐人不存在！");
            }
            if(one!=null){
                if(one.getParentIds()==null){
                    nstudent.setParentIds(String.valueOf(one.getNid()));
                }else{
                    nstudent.setParentIds(one.getParentIds()+","+one.getNid());
                }

            }
        }
        int save = nstudentIBaseDao.save(nstudent);
        nstudent.setNid(save);
        Agent agent=new Agent();
        agent.setNid(save);
        agent.setAgentName(nstudent.getStudentName());
        agent.setAgentTel(nstudent.getUsername());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        agent.setCreatTime(simpleDateFormat.format(new Date()));
        agent.setAgentStatus((byte) 1);
        int save1 = agentIBaseDao.save(agent);
        System.out.println(save+":"+save1);
        if(save>0&&save1>0){
            Map map=new HashMap();
            map.put("id",nstudent.getNid());
            return ResultUtil.success("注册成功",map);
        }else{
            return ResultUtil.error(2,"注册失败");
        }
    }

    /**
     * 修改密码
     * @param nstudent
     * @return
     */
    @Override
    public Result upLogin(Nstudent nstudent) throws Exception {
        Nstudent one = nstudentIBaseDao.getOne(Nstudent.class, nstudent.getNid());
        if(one==null){
            return ResultUtil.error(2,"参数错误！");
        }else{
            int i = nstudentIBaseDao.deleteWithHql(" update Nstudent set password='" + nstudent.getPassword() + "' where nid=" + nstudent.getNid());
            if(i>0){
                return ResultUtil.success("修改密码成功！");
            }else{
                return ResultUtil.error(2,"修改密码失败");
            }
        }
    }

    /**
     * 个人中心
     * @param nid
     * @return
     */
    @Override
    public Result userCenter(int nid) throws Exception {
        UserCenterBean userCenterBean=new UserCenterBean();
        Nstudent one = nstudentIBaseDao.findOne("from Nstudent where nid='"+nid+"'");
        if(one==null){
            return ResultUtil.error(2,"请先注册");
        }else{
            userCenterBean.setImgUrl(one.getImgUrl());
            Nstudent one1 = nstudentIBaseDao.findOne("from Nstudent where username='" + one.getReferrerPhone() + "'");
            if(one1!=null){
                userCenterBean.setReferrer(one1.getStudentName());
            }
            long count = nstudentIBaseDao.findCount("select count(*) from Nstudent where referrerPhone='" + one.getUsername() + "'");
            userCenterBean.setInviteNum((int) count);
            Agent one2 = agentIBaseDao.findOne("from Agent where nid=" + one.getNid());
            userCenterBean.setUserType(one2!=null?one2.getAgentStatus():0);
            List<AgentAccount> list = agentAccountIBaseDao.findList("from AgentAccount where nid =" + one.getNid());
            double sumMoney=0;
            double canMoney=0;
            String ids="";
            for (AgentAccount agentAccount : list) {
                sumMoney+=agentAccount.getOrderMoney();
                if(agentAccount.getMoneyState()==2){
                    canMoney+=agentAccount.getOrderMoney();
                    ids+=agentAccount.getAccountId()+",";
                }
            }
            if(ids.endsWith(",")){
                ids=ids.substring(0,ids.length()-1);
            }
            if(one2!=null&&one2.getBankNumber()!=null){
                userCenterBean.setType(1);
            }else{
                userCenterBean.setType(0);
            }
            userCenterBean.setIds(ids);
            userCenterBean.setCanMoney(canMoney);
            userCenterBean.setSumMoney(sumMoney);
            userCenterBean.setNid(one.getNid());
            userCenterBean.setUsername(one.getUsername());
            userCenterBean.setStudentName(one.getStudentName());
            userCenterBean.setPhone(one.getUsername());
            UserWallet one3 = userWalletIBaseDao.findOne("from UserWallet where nid=" + one.getNid());
            userCenterBean.setMeMoney(one3!=null?one3.getMoney():0);
            return ResultUtil.success("",userCenterBean);
        }
    }
    /**
     * 一键领取金额
     * @param ids
     * @return
     */
    @Override
    @Transactional
    public Result getMoney(String ids,int nid) throws Exception {
        double num=0;

        List<AgentAccount> list = agentAccountIBaseDao.findList("from AgentAccount where accountId in (" + ids + ")");
        for (AgentAccount agentAccount : list) {
            num+=agentAccount.getOrderMoney();
        }
        //先查该用户是否有钱包
        UserWallet one = userWalletIBaseDao.findOne(" from UserWallet where nid=" + nid);
        int walletId=0;
        double money=0;
        if(one==null){
            //没有钱包
            UserWallet userWallet =new UserWallet();
            userWallet.setMoney(num);
            userWallet.setNid(nid);
            walletId = userWalletIBaseDao.save(userWallet);
            money=num;
        }else{
            //有钱包
            walletId=one.getWalletId();
            money=num+one.getMoney();
            one.setMoney(num+one.getMoney());
            userWalletIBaseDao.update(one);
        }
        WalletDetails walletDetails=new WalletDetails();
        walletDetails.setCreatTime(new Timestamp(System.currentTimeMillis()));
        walletDetails.setDetailsType((byte) 1);
        walletDetails.setWalletId(walletId);
        walletDetails.setMoneyStatus((byte) 1);
        walletDetails.setMoney(money);
        int save = walletDetailsIBaseDao.save(walletDetails);
        Message message=new Message();
        message.setNid(nid);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setCreatTime(simpleDateFormat.format(new Date()));
        message.setMessage("您申请的"+num+"元已经放入您的钱包中，请查看");
       if(save>0){
           messageIBaseDao.save(message);
           agentAccountIBaseDao.deleteWithHql("update AgentAccount set moneyState=1 where accountId in (" + ids + ")");
           return ResultUtil.success("成功");
       }else{
           return ResultUtil.error(2,"失败");
       }
    }
    /**
     * 提现
     * @param nid
     * @param money
     * @return
     * @throws Exception
     */
    @Override
    @Transactional
    public Result withdraw(int nid,double money) throws Exception{
        UserWallet one = userWalletIBaseDao.findOne("from UserWallet where nid=" + nid);
        if(one==null){
            return ResultUtil.error(2,"您的余额为0，暂不能提现");
        }
        if(one.getMoney()==null){
            one.setMoney(0.0);
        }
        if(one.getMoney()<money){
            return ResultUtil.error(2,"您输入的金额超支");
        }else{
            one.setMoney(one.getMoney()-money);
            userWalletIBaseDao.update(one);
            WalletDetails walletDetails=new WalletDetails();
            walletDetails.setCreatTime(new Timestamp(System.currentTimeMillis()));
            walletDetails.setDetailsType((byte) 2);
            walletDetails.setWalletId(one.getWalletId());
            walletDetails.setMoneyStatus((byte) 2);
            walletDetails.setMoney(money);
            int save = walletDetailsIBaseDao.save(walletDetails);
            Message message=new Message();
            message.setNid(nid);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            message.setCreatTime(simpleDateFormat.format(new Date()));
            message.setMessage("您申请的"+money+"元提现到银行卡正在处理中...");
            if(save>0){
                messageIBaseDao.save(message);
                return ResultUtil.success("成功");
            }else{
                return ResultUtil.error(2,"失败");
            }
        }

    }
    /**
     * 流水记录
     * @param nid
     * @return
     */
    @Override
    public Result flowRecord(int nid) throws Exception {
        List<WalletDetails> list=new ArrayList<WalletDetails>();
        UserWallet one = userWalletIBaseDao.findOne("from UserWallet where nid=" + nid);
        if(one!=null){
             list = walletDetailsIBaseDao.findList(" from WalletDetails where walletId=" + one.getWalletId() + " and moneyStatus=1");
        }
       return ResultUtil.success("流水记录" ,list);
    }
    /**
     * @Author: 徐慷慨
     * @Description:我推荐的
     * @Date: 13:57 2018/2/27
     */
    @Override
    public Result meTuijain(int nid) throws Exception {
        Nstudent one = nstudentIBaseDao.getOne(Nstudent.class, nid);
        if(one==null){
            return ResultUtil.error(2,"查不到该用户");
        }
        List<Agent> list = agentIBaseDao.findList(" select a from Agent a,Nstudent b where a.nid=b.nid and b.referrerPhone='" + one.getUsername() + "' order by a.creatTime desc");
        List<MettuijianList> lists=new ArrayList<MettuijianList>();
        for (Agent agent : list) {
            MettuijianList mettuijianList=new MettuijianList();
            mettuijianList.setName(agent.getAgentName());
            mettuijianList.setTime(agent.getCreatTime());
            mettuijianList.setUserType(agent.getAgentStatus());
            lists.add(mettuijianList);
        }
        return ResultUtil.success(lists);
    }
    /**
     * @Author: 徐慷慨
     * @Description:消息中心
     * @Date: 14:39 2018/2/27
     */
    @Override
    public Result newsCenter(int nid) throws Exception {
        List<Message> list = messageIBaseDao.findList("from Message where nid=" + nid + " order by creatTime desc");
        return ResultUtil.success("流水记录" ,list);
    }

    /**
     * 绑定银行卡
     * @param agent
     * @return
     */
    @Override
    @Transactional
    public Result inAgentBank(Agent agent) throws Exception {
        System.out.println(agent.toString());
        if(agent==null||agent.getNid()==null){
            return ResultUtil.error(2,"参数不能为空！");
        }
        Agent one = agentIBaseDao.findOne(" from Agent where nid="+agent.getNid());
        if(one==null){
            Nstudent one1 = nstudentIBaseDao.getOne(Nstudent.class, agent.getNid());
            if(one1!=null){
                agent.setNid(one1.getNid());
                SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                agent.setCreatTime(simpleDateFormat.format(new Date()));
                agent.setAgentStatus((byte) 1);
                agentIBaseDao.save(agent);
                return  ResultUtil.success();
            }else{
                return ResultUtil.error(2,"查不到该用户");
            }
        }else{
            agent.setNid(one.getNid());
            agent.setCreatTime(one.getCreatTime());
            agent.setAgentId(one.getAgentId());
            agent.setAgentTel(one.getAgentTel());
            agent.setAgentStatus(one.getAgentStatus());
            agentIBaseDao.getSessions().evict(one);
            boolean update = agentIBaseDao.update(agent);
            return  ResultUtil.success();
        }

    }

    /***
     * 公众号登录
     * @param nstudent
     * @return
     */
    @Override
    public Result  login(Nstudent nstudent) throws Exception {
        Nstudent one = nstudentIBaseDao.findOne("from Nstudent where username='" + nstudent.getUsername() + "' and password='" + nstudent.getPassword() + "'");
        if(one==null){
            return ResultUtil.error(2,"用户名或密码错误！");
        }else{
            one.setOpenId(nstudent.getOpenId());
            nstudentIBaseDao.update(one);
//            Map map=new HashMap();
//            map.put("nid",one.getNid());
//            map.put("openId",one.getOpenId());
//            map.put("",one.getReferrerPhone());
            return ResultUtil.success(one);
        }
    }
    /**
     * 我的银行卡
     * @param nid
     * @return
     */
    @Override
    public Result meBankList(int nid) throws Exception {
        Agent one = agentIBaseDao.findOne("from Agent where nid=" + nid);
        return ResultUtil.success(one);
    }


}
