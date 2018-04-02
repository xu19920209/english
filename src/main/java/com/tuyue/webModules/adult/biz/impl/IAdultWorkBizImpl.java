package com.tuyue.webModules.adult.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Acourse;
import com.tuyue.pojo.Bhour;
import com.tuyue.pojo.Ctopic;
import com.tuyue.pojo.VadultWork;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Dateutil;
import com.tuyue.util.Page;
import com.tuyue.webModules.adult.bean.InsertWorBean;
import com.tuyue.webModules.adult.bean.VadultWorkBean;
import com.tuyue.webModules.adult.biz.IAdultWorkBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/11.
 */
@Service
@Transactional
public class IAdultWorkBizImpl implements IAdultWorkBiz {

    @Autowired
    private IBaseDao<VadultWork> vadultWorkIBaseDao;
    @Autowired
    private IBaseDao<Bhour> bhourIBaseDao;
    @Autowired
    private IBaseDao<Acourse> acourseIBaseDao;
    @Autowired
    private IBaseDao<Ctopic> ctopicIBaseDao;

    /**
     * @Author: 徐慷慨
     * @Description:给成人布置作业
     * @Date: 15:48 2017/9/13
     */
    @Override
    public Result insetAdltWork(InsertWorBean insertWorBean) throws Exception{
        Timestamp timestamp = Dateutil.DateToTime(new Date());
        List<VadultWork> list =new ArrayList<VadultWork>();
        String[] split = insertWorBean.getCid().split(",");
        List<VadultWork> list1 = vadultWorkIBaseDao.findList(" from VadultWork where vflag=1");
        List<Integer> haveId=new ArrayList<Integer>();
        for(VadultWork vadultWork :list1){
            haveId.add(vadultWork.getCid());
        }
        List<Integer> ID=new ArrayList<Integer>();
        for(String id : split){
            ID.add(Integer.parseInt(id));
        }
        if(list1.size()>0){
            ID.removeAll(haveId);
        }

        for (Integer id : ID) {
            VadultWork vadultWork = new VadultWork();
            vadultWork.setCid(id);
            vadultWork.setLayoutTime(timestamp);
            vadultWork.setAid(insertWorBean.getAid());
            vadultWork.setBid(insertWorBean.getBid());
            vadultWork.setVflag(1);
            vadultWork.setLevelId(insertWorBean.getLevelId());
            list.add(vadultWork);
        }
        int i = vadultWorkIBaseDao.batchSave(list);
        if(i==list.size()){
            return ResultUtil.success("添加成功！");
        }
        return ResultUtil.error(2,"添加失败！");
    }

    @Override
    public boolean del(String vids) throws Exception {
        String[] split = vids.split(",");
        int i = vadultWorkIBaseDao.deleteWithHql("UPDATE  VadultWork SET vflag=0 WHERE vid IN (" + vids + ")");
        if (i==split.length){
            return true;
        }
        return false;
    }

    @Override
    public Page<VadultWorkBean> list(Integer currentPage,Integer pageSize) throws Exception {
        String hql="SELECT v FROM VadultWork v WHERE vflag=1 GROUP BY aid,bid,layoutTime order by layoutTime desc";
        String chql="SELECT count(*) FROM VadultWork where vid in (SELECT vid FROM VadultWork WHERE vflag=1 GROUP BY aid,bid,layoutTime) order by layoutTime desc";
        out.println(chql);
        Page<VadultWork> page = vadultWorkIBaseDao.findPage(currentPage, pageSize, hql, chql);
        ArrayList<VadultWorkBean> vadultWorkBeans = new ArrayList<VadultWorkBean>();
        if(page!=null&&page.getList()!=null&&page.getList().size()>0){
            for (VadultWork vadultWork : page.getList()) {
                VadultWorkBean vadultWorkBean = new VadultWorkBean();
                vadultWorkBean.setAid(vadultWork.getAid());
                vadultWorkBean.setAname(acourseIBaseDao.getOne(Acourse.class,vadultWork.getAid()).getAname());
                vadultWorkBean.setBid(vadultWork.getBid());
                vadultWorkBean.setBname(bhourIBaseDao.getOne(Bhour.class,vadultWork.getBid()).getBname());
                vadultWorkBean.setLayoutTime(vadultWork.getLayoutTime());
                List<VadultWork> vadultWorkList = vadultWorkIBaseDao.findList("FROM VadultWork WHERE aid ="+vadultWork.getAid() + " and bid=" + vadultWork.getBid() + " and layoutTime='" + vadultWork.getLayoutTime() + "' and vflag=1 ");
                ArrayList<Ctopic> ctopics = new ArrayList<Ctopic>();
                StringBuffer vids = new StringBuffer("");
                int count=0;
                for (VadultWork work : vadultWorkList) {
                    count++;
                    Ctopic ctopic = ctopicIBaseDao.getOne(Ctopic.class, work.getCid());
                    ctopics.add(ctopic);
                    int vid = work.getVid();
                    vids.append(vid);
                    if (count!=vadultWorkList.size()){
                        vids.append(",");
                    }
                }
                vadultWorkBean.setVids(vids.toString());
                vadultWorkBean.setCtopics(ctopics);
                vadultWorkBeans.add(vadultWorkBean);
            }
        }
        Page<VadultWorkBean> vadultWorkBeanPage = new Page<VadultWorkBean>();
        vadultWorkBeanPage.setList(vadultWorkBeans);
        vadultWorkBeanPage.setTotal(page.getTotal());
        vadultWorkBeanPage.setallPage(page.getAllpage());
        vadultWorkBeanPage.setCurrentPage(page.getCurrentPage());
        vadultWorkBeanPage.setPageSize(page.getPageSize());
        return vadultWorkBeanPage;
    }
}
