package com.tuyue.webModules.courseGoods.biz.impl;

import cn.jpush.api.TestOrder;
import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.ObjectCopyUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.courseGoods.bean.*;
import com.tuyue.webModules.courseGoods.biz.TuiJianRen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.validation.Validator;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/8.
 */
@Service
@Transactional
public class TuiJianRenImpl implements TuiJianRen {
    @Autowired
    private IBaseDao<Agent> agentIBaseDao;
    @Autowired
    private IBaseDao<AgentAccount> agentAccountIBaseDao;
    @Autowired
    private IBaseDao<CourseGoodsOrder> courseGoodsOrderIBaseDao;
    @Autowired
    private  IBaseDao<ACourseGood> aCourseGoodIBaseDao;
    @Autowired
    private IBaseDao<Nstudent>nstudentIBaseDao;
    @Autowired
    private IBaseDao<WalletDetails> walletDetailsIBaseDao;
    @Autowired
    private IBaseDao<UserWallet> userWalletIBaseDao;

    /**
     * @Author: 徐慷慨
     * @Description:推荐人管理列表
     * @Date: 11:01 2017/12/8
     */
    @Override
    public Result tuiJianList(Integer type,Integer currentPage,Integer pageSize,String phone) throws Exception {
        StringBuffer HQL = new StringBuffer("FROM Agent where 1=1");
        StringBuffer COUNT = new StringBuffer("select count(*) from Agent where 1=1");
         if(type!=null&&type==1){//已购买
             HQL.append(" and agentStaus!=1");
             COUNT.append(" and agentStaus!=1");
         }
         if(type!=null&&type==2){
             HQL.append(" and agentStaus=1");
             COUNT.append(" and agentStaus=1");
         }
         if(phone!=null&&phone!=null){
             HQL.append(" and phone='"+phone+"'");
             COUNT.append(" and phone='"+phone+"'");
         }
        HQL.append(" order by creatTime desc");
        COUNT.append(" order by  creatTime desc");
        Page<Agent> page = agentIBaseDao.findPage(currentPage, pageSize, HQL.toString(), COUNT.toString());
         List<Agent> agentList=new ArrayList<Agent>();
         if(page.getList()!=null&&page.getList().size()>0){
             for (Agent agent : page.getList()) {
                 Nstudent one = nstudentIBaseDao.getOne(Nstudent.class, agent.getNid());
                 agent.setStudentName(one.getStudentName());
                 Nstudent one1 = nstudentIBaseDao.findOne("from Nstudent where username='" + one.getReferrerPhone() + "'");
                 if(one1!=null){
                     agent.setTuijainName(one1.getStudentName());
                 }
                 agentList.add(agent);
             }
             page.setList(agentList);
         }
            return ResultUtil.success("推荐人管理列表", page);
    }

    /**
     * @Author: 徐慷慨
     * @Description:添加推荐人
     * @Date: 11:01 2017/12/8
     */
    @Override
    public Result tuiJianIn(Agent agent) {
        int save1 = agentIBaseDao.save(agent);
        if (save1 > 0) {
            return ResultUtil.success("添加成功！");
        } else {
            return ResultUtil.error(2, "添加失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改代理人
     * @Date: 13:47 2017/12/8
     */
    @Override
    @Transactional
    public Result tuiJianUp(Agent agent) throws Exception {
        Agent one = agentIBaseDao.getOne(Agent.class, agent.getAgentId());
        if(agent.getAgentTel()!=null){
            if(!agent.getAgentTel().equals(one.getAgentTel())){
                nstudentIBaseDao.deleteWithHql("update Nstudent set username='"+agent.getAgentTel()+"'where nid="+one.getNid());
            }
        }
        if(agent.getAgentStatus()!=null&&agent.getAgentStatus()>0){
        }else{
            agent.setAgentStatus(one.getAgentStatus());
        }
        if(agent.getBankNumber()==null){
            agent.setBankNumber(one.getBankNumber());
        }
        if(agent.getBranchBank()==null){
            agent.setBranchBank(one.getBranchBank());
        }
        if(agent.getAgentName()==null){
            agent.setAgentName(one.getAgentName());
        }
        if(agent.getAgentTel()==null){
            agent.setAgentTel(one.getAgentTel());
        }
        if(agent.getProvince()==null){
            agent.setProvince(one.getProvince());
        }
        if(agent.getCity()==null){
            agent.setCity(agent.getCity());
        }
        if(agent.getRegion()==null){
            agent.setRegion(agent.getRegion());
        }
        agent.setNid(one.getNid());
        agent.setCreatTime(one.getCreatTime());
        agentIBaseDao.getSessions().evict(one);
        boolean update = agentIBaseDao.update(agent);
        if (update) {
            return ResultUtil.success("修改成功！");
        } else {
            return ResultUtil.error(2, "修改失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:导出excel
     * @Date: 13:51 2017/12/8
     */
    @Override
    public List<WithdrawBean> excel(String ids) throws Exception {
        List<WalletDetails> list1 = walletDetailsIBaseDao.findList("from WalletDetails where walletDetailsId in(" + ids + ")");
        List<WithdrawBean> beanList =new ArrayList<WithdrawBean>();
        for (WalletDetails walletDetails : list1) {
            WithdrawBean withdrawBean=new WithdrawBean();
            UserWallet one = userWalletIBaseDao.getOne(UserWallet.class, walletDetails.getWalletId());
            if(one!=null){
                Agent one1 = agentIBaseDao.findOne("from Agent where nid="+one.getNid());
                if(one1!=null){
                    withdrawBean.setUserName(one1.getAgentName());
                    withdrawBean.setPhone(one1.getAgentTel());
                    withdrawBean.setBankNumber(one1.getBankNumber());
                    withdrawBean.setMoney(walletDetails.getMoney());
                    withdrawBean.setOpenBank(one1.getOpenBank());
                    withdrawBean.setAgentStatus(one1.getAgentStatus());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    withdrawBean.setTime( sdf.format(walletDetails.getCreatTime()));
                    withdrawBean.setBranchBank(one1.getBranchBank());
                    withdrawBean.setWalletDetailsId(walletDetails.getWalletDetailsId());
                    withdrawBean.setMoneyStatus(walletDetails.getMoneyStatus());
                    withdrawBean.setAddress(one1.getProvince()+one1.getCity()+one1.getRegion());
                }
            }
            beanList.add(withdrawBean)  ;
        }
        return beanList;
    }

    /**
     * @Author: 徐慷慨
     * @Description:销售业绩列表 1代理人 2 课程名称
     * @Date: 14:07 2017/12/8
     */
    @Override
    public Result sellList(Integer currentPage, Integer pageSize, String name, Integer type, String time, String courseName) throws Exception {
        StringBuffer hql = new StringBuffer(" from CourseGoodsOrder where orderState=1 ");
        StringBuffer count = new StringBuffer(" select count(*) from CourseGoodsOrder where orderState=1");
        if (time != null) {
            hql.append(" and payTime like '" + time + "%' ");
            count.append(" and payTime like '" + time + "%' ");
        }
               if(name!=null){
                   List<Agent> agentList = agentIBaseDao.findList(" from Agent where agentName like '%" + name + "%'");
                   String ids = "";
                   for (Agent agent : agentList) {
                       ids += agent.getAgentId();
                   }
                   if (ids.length() > 0) {
                       hql.append(" and agentId in ("+ids+")");
                       count.append(" and agentId in ("+ids+") ");
                   }
               }

          if(courseName!=null){
              List<ACourseGood> lists = aCourseGoodIBaseDao.findList(" from ACourseGood where courseName like '%" + courseName + "%'");
              String idss = "";
              for (ACourseGood aCourseGood : lists) {
                  idss += aCourseGood.getCourseId();
              }

              if (idss.length() > 0) {
                  hql.append(" and courseId in ("+idss+")");
                  count.append(" and courseId in ("+idss+") ");
              }
          }
        hql.append("  order by orderTime desc");
        count.append("  order by orderTime desc ");
        System.out.println(hql.toString());
        Page<CourseGoodsOrder> page = courseGoodsOrderIBaseDao.findPage(currentPage, pageSize, hql.toString(), count.toString());
        double money=0.0;
        List<YeJiMingXi> list=new ArrayList<YeJiMingXi>();
        if(page.getList().size()<=0){
            Map map =new HashMap();
            map.put("currentPage",0);
            map.put("pageSize",0);
            map.put("list",list);
            map.put("allPage",0);
            map.put("totleMoney",0);
            map.put("num",0);
            map.put("total",0);
            return ResultUtil.success("销售业绩列表",map);
        }
        if(page.getList()!=null&&page.getList().size()>0){
            for (CourseGoodsOrder courseGoodsOrder : page.getList()) {
                YeJiMingXi yeJiMingXi =new YeJiMingXi();
                ACourseGood one = aCourseGoodIBaseDao.getOne(ACourseGood.class, courseGoodsOrder.getCourseId());
                yeJiMingXi.setCourseName(one.getCourseName());
                Nstudent one1 = nstudentIBaseDao.getOne(Nstudent.class, courseGoodsOrder.getNid());
                if(one1.getIsType()==1){
                    yeJiMingXi.setPeopleName(one1.getStudentName());
                }else{
                    yeJiMingXi.setPeopleName(one1.getUsername());
                }
                Agent one2 = agentIBaseDao.findOne("from Agent where nid="+courseGoodsOrder.getNid());
                yeJiMingXi.setAgentName(one2!=null?one2.getAgentName():"");
                if(courseGoodsOrder.getPayMoney()!=null){
                    yeJiMingXi.setMoney(courseGoodsOrder.getPayMoney());
                }
                yeJiMingXi.setTime(courseGoodsOrder.getPayTime().toString().substring(0,courseGoodsOrder.getPayTime().toString().length()-2));
                list.add(yeJiMingXi);
            }
        }
        Map map =new HashMap();
        long count1 = courseGoodsOrderIBaseDao.findCount("select count(*) from CourseGoodsOrder where orderState=1");
        List<CourseGoodsOrder> list1 = courseGoodsOrderIBaseDao.findList(" from CourseGoodsOrder where orderState=1");

        for (CourseGoodsOrder courseGoodsOrder : list1) {
            if(courseGoodsOrder.getPayMoney()!=null){
                money+=courseGoodsOrder.getPayMoney();
            }
        }
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        String format = decimalFormat.format(money);
        map.put("currentPage",page.getCurrentPage());
        map.put("pageSize",page.getPageSize());
        map.put("list",list);
        map.put("allPage",page.getAllpage());
        map.put("totleMoney",format);
        map.put("num",count1);
        map.put("total",page.getTotal());
        return ResultUtil.success("销售业绩列表",map);
    }
    /**
     * @Author: 徐慷慨
     * @Description:打款
     * @Date: 8:58 2017/12/11
     */
    @Override
    @Transactional
    public Result remit( List<Remit> list){
        if(list.size()>0){
            int i=0;
            for (Remit remit : list) {
                if(remit.getIds()!=null&&remit.getIds().length()>0){
                    agentAccountIBaseDao.deleteWithHql("update  AgentAccount set moneyState=1 where agentId="+remit.getAngentId()+" and accountId in ("+remit.getIds()+")");
                    i++;
                }

            }
            if(i>0){
                return ResultUtil.success("打款成功");
            }else{
                return ResultUtil.error(2,"打款失败");
            }
        }
      return ResultUtil.error(2,"未知错误！");
    }

    /**
     * @Author: 徐慷慨
     * @Description:累计佣金明细/待打款佣金明细
     * @param type 1是待打款2已打款
     * @Date: 9:12 2017/12/11
     */
    @Override
    public Result payMoneyList(Integer currentPage, Integer pageSize, Integer agentId, Integer type) throws Exception {
        String sql="";
        String count="";
        List<MoneyListBean> listBeans =new ArrayList<MoneyListBean>();
        Agent agent = agentIBaseDao.getOne(Agent.class, agentId);
        if(type==1){
            sql="from AgentAccount where agentId="+agentId+" and moneyState=2";
            count="select count(*) from AgentAccount where agentId="+agentId+" and moneyState=2 ";
        }else{
            sql="from AgentAccount where agentId="+agentId+" and moneyState=1";
            count="select count(*) from AgentAccount where agentId="+agentId+" and moneyState=1 ";
        }
        Page<AgentAccount> page = agentAccountIBaseDao.findPage(currentPage, pageSize, sql, count);
        double money=0;
        if(page.getList().size()>0){
            for (AgentAccount agentAccount : page.getList()) {
                MoneyListBean moneyListBean = new MoneyListBean();
                CourseGoodsOrder one = courseGoodsOrderIBaseDao.getOne(CourseGoodsOrder.class, agentAccount.getCourseOrderId());
                ACourseGood one1 = aCourseGoodIBaseDao.getOne(ACourseGood.class, one.getCourseId());
                Nstudent one2 = nstudentIBaseDao.getOne(Nstudent.class, one.getNid());
                moneyListBean.setCourseName(one1.getCourseName());
                if(one.getPayMoney()!=null){
                    moneyListBean.setCoursePrice(one.getPayMoney());
                }
                moneyListBean.setTime(one.getPayTime().toString());
                moneyListBean.setCommission(one1.getCommission());
                moneyListBean.setUserName(one2.getStudentName()!=null?one2.getStudentName():"注册用户");
                listBeans.add(moneyListBean);
            }

            List<AgentAccount> list = agentAccountIBaseDao.findList(sql);
            for (AgentAccount agentAccount : list) {
                CourseGoodsOrder one = courseGoodsOrderIBaseDao.getOne(CourseGoodsOrder.class, agentAccount.getCourseOrderId());
                ACourseGood one1 = aCourseGoodIBaseDao.getOne(ACourseGood.class, one.getCourseId());
                money+=one1.getCommission();
            }

            Map map =new HashMap();
            map.put("currentPage",page.getCurrentPage());
            map.put("pageSize",page.getPageSize());
            map.put("list",listBeans);
            map.put("allPage",page.getAllpage());
            map.put("total",page.getTotal());
            map.put("totalMoney",money);
            map.put("agentName",agent.getAgentName());
            return ResultUtil.success("",map);
        }
        Map map =new HashMap();
        map.put("currentPage",0);
        map.put("pageSize",0);
        map.put("list",listBeans);
        map.put("allPage",0);
        map.put("total",0);
        map.put("totalMoney",0);
        map.put("agentName",agent.getAgentName());
        return ResultUtil.success("",map);
    }
    /**
     * @Author: 徐慷慨
     * @Description:给推荐人添加待打款金额
     * @Date: 11:24 2017/12/26
     */
    @Override
    public boolean AgentCountIn(AgentAccount agentAccount){
        int save = agentAccountIBaseDao.save(agentAccount);
        if(save>0){
            return true;
        }
        return false;
    }

    /**
     * @Author: 徐慷慨
     * @Description:提现管理列表
     * @Date: 17:10 2018/2/23
     */
    @Override
    public Result withdrawList(Integer status,String phone,Integer currentPage,Integer pageSize) throws Exception {
        Map map =new HashMap();
        List<WithdrawBean>beanList =new ArrayList<WithdrawBean>();
        StringBuffer agenthql=new StringBuffer("from  Agent where 1=1");
            if(phone!=null){
                agenthql.append(" and phone='"+phone+"'");
            }
        List<Agent> agentList = agentIBaseDao.findList(agenthql.toString());
        System.out.println("::::"+agentList.size());
        if(agentList.size()==0){
           map.put("currentPage",1);
           map.put("pageSize",10);
           map.put("total",0);
           map.put("list",beanList);
           return ResultUtil.success("",map);
        }else{
            String  nids="";
            for (int i = 0; i < agentList.size(); i++) {
                if(i<agentList.size()-1){
                  nids +=agentList.get(i).getNid()+",";
                }else{
                    nids +=agentList.get(i).getNid();
                }
            }
            String hql="";
            String count="";
            if(status!=null){
                if(status==1){ //已处理
                    hql="select a from WalletDetails a,UserWallet b where a.walletId=b.walletId and a.moneyStatus=1 and a.detailsType=2 and b.nid in("+nids+") order by a.creatTime desc";
                    count="select count(a) from WalletDetails a,UserWallet b where a.walletId=b.walletId and a.moneyStatus=1 and a.detailsType=2 and b.nid in("+nids+") order by a.creatTime desc";
                }else{
                    hql="select a from WalletDetails a,UserWallet b where a.walletId=b.walletId and a.moneyStatus!=1 and a.detailsType=2 and b.nid in("+nids+") order by a.creatTime desc";
                    count="select count(a) from WalletDetails a,UserWallet b where a.walletId=b.walletId and a.moneyStatus!=1 and a.detailsType=2 and b.nid in("+nids+") order by a.creatTime desc";
                }
            }else if(status==null||status==0){
                hql="select a from WalletDetails a,UserWallet b where a.walletId=b.walletId  and a.detailsType=2 and b.nid in("+nids+") order by a.creatTime desc";
                count="select count(a) from WalletDetails a,UserWallet b where a.walletId=b.walletId  and a.detailsType=2 and b.nid in("+nids+") order by a.creatTime desc";
            }
            Page<WalletDetails> page = walletDetailsIBaseDao.findPage(currentPage, pageSize, hql, count);
            List<WalletDetails> list = page.getList();
            for (WalletDetails walletDetails : list) {
                WithdrawBean withdrawBean=new WithdrawBean();
                UserWallet one = userWalletIBaseDao.findOne("from UserWallet where walletId="+walletDetails.getWalletId());
                if(one!=null){
                    Agent one1 = agentIBaseDao.findOne("from Agent where nid="+one.getNid());
                    if(one1!=null){
                        withdrawBean.setUserName(one1.getAgentName());
                        withdrawBean.setPhone(one1.getAgentTel());
                        withdrawBean.setBankNumber(one1.getBankNumber());
                        withdrawBean.setOpenBank(one1.getOpenBank());
                        withdrawBean.setAgentStatus(one1.getAgentStatus());
                        withdrawBean.setBranchBank(one1.getBranchBank());
                        withdrawBean.setAddress(one1.getProvince()+one1.getCity()+one1.getRegion());
                    }
                    withdrawBean.setMoneyStatus(walletDetails.getMoneyStatus());;
                    withdrawBean.setWalletDetailsId(walletDetails.getWalletDetailsId());
                    DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    withdrawBean.setTime( sdf.format(walletDetails.getCreatTime()));
                    withdrawBean.setMoney(walletDetails.getMoney());
                    beanList.add(withdrawBean);
                }

            }
            map.put("currentPage",page.getCurrentPage());
            map.put("pageSize",page.getPageSize());
            map.put("total",page.getTotal());
            map.put("list",beanList);
            return ResultUtil.success("",map);
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:处理打款
     * @Date: 15:26 2018/3/5
     */
    @Override
    public Result remit(int id) throws Exception {
        int i = walletDetailsIBaseDao.deleteWithHql("update WalletDetails set moneyStatus=1 where walletDetailsId=" + id);
        if(i>0){
            return  ResultUtil.success("打款成功！");
        }else{
            return ResultUtil.error(2,"打款失败！");
        }
    }

    @Override
    public UserWallet ceshi() throws Exception {
        UserWallet one = userWalletIBaseDao.findOne("from UserWallet where walletId="+3);
        return one;
    }
}
