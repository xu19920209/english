package com.tuyue.appModules.course.biz.impl;

import com.tuyue.appModules.course.bean.FinishBean;
import com.tuyue.appModules.course.bean.WorkListBean;
import com.tuyue.appModules.course.biz.IadultWorkBiz;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
@Service
@Transactional
public class AdultWorkBizImpl implements IadultWorkBiz {
    @Autowired
    private IBaseDao<VadultWork> vdao;
    @Autowired
    private IBaseDao<Acourse> adao;
    @Autowired
    private IBaseDao<Bhour> bdao;
    @Autowired
    private IBaseDao<Ctopic> cdao;
    @Autowired
    private IBaseDao<WadultWorkDetails> wdao;
    @Autowired
    private IBaseDao<Nstudent> ndao;
    @Autowired
    private IBaseDao<CourseLevel>courseLevelIBaseDao;

    /**
     * @Author: 徐慷慨
     * @Description:成人作业列表
     * @Date: 11:05 2017/9/14
     */
    @Override
    public Result adultWorkList(Integer nid,Integer levelId) throws Exception {
        List<WorkListBean> list = new ArrayList<WorkListBean>();
        List<VadultWork> list1 = vdao.findList("from VadultWork where levelId="+levelId+"and vflag=1 GROUP BY bid order by layoutTime desc");
        for (VadultWork vadultWork : list1) {
            WorkListBean workListBean = new WorkListBean();
            workListBean.setAid(vadultWork.getAid());
            Bhour one1 = bdao.getOne(Bhour.class, vadultWork.getBid());
            if (one1 != null) {
                workListBean.setBname(one1.getBname());
                workListBean.setBid(one1.getBid());
            } else {
                workListBean.setAname("");
            }
            workListBean.setLayoutTime(vadultWork.getLayoutTime().toString().substring(0,vadultWork.getLayoutTime().toString().length()-2));
            double num = 0;//成绩
            //完成个数
            if(nid!=null&&nid>0){
                List<WadultWorkDetails> list2 = wdao.findList("select w from  WadultWorkDetails w ,VadultWork v where v.vflag=1 and v.bid=" + vadultWork.getBid() + " and v.vid=w.vid and w.nid="+nid+" and v.layoutTime='"+vadultWork.getLayoutTime()+"'");
                workListBean.setFinish(list2!=null?list2.size():0);
                if(list2!=null&&list2.size()>0){
                    for (WadultWorkDetails wadultWorkDetails : list2) {
                        num += wadultWorkDetails.getWorkScore();
                    }
                }
            }
            //总数
            List<VadultWork> vdaoList = vdao.findList(" from VadultWork where vflag=1 and bid=" + vadultWork.getBid()+" and layoutTime='"+vadultWork.getLayoutTime()+"'");
            String cid="";
            if(vdaoList.size()>0){
                for (VadultWork work : vdaoList) {
                    cid+=work.getCid()+",";
                }
                cid=cid.substring(0,cid.length()-1);
            }
            workListBean.setTotal(vdaoList.size());
            workListBean.setCids(cid);
            //得分
            double v = num / (vdaoList.size() * 100);
            System.out.println(v);
            workListBean.setGrade((int) (v*100));
            list.add(workListBean);
        }
        return ResultUtil.success("成人作业列表", list);
    }


    /**
     * @Author: 徐慷慨
     * @Description:成人作业详情
     * @Date: 11:07 2017/9/14
     */
    @Override
    public Result adultWorkDetails(Integer nid, Integer bid, Integer index, String cids, String time) throws Exception {
        FinishBean finishBean = new FinishBean();
//        if(index==0){
//            StringBuffer english =new StringBuffer("");
//            StringBuffer China =new StringBuffer("");
//            List<Ctopic> cdaoList = cdao.findList(" from Ctopic where cid in(" + cids + ") order by cid");
//            for (Ctopic ctopic : cdaoList) {
//                english.append(ctopic.getCsentence()+" ");
//                China.append(ctopic.getCsentence()+" ");
//            }
//            finishBean.setCsentence(english.toString());
//            finishBean.setCtranslate(China.toString());
//            return ResultUtil.success("全部文章",finishBean);
//        }
        List<VadultWork> vdaoList = vdao.findList(" from VadultWork where vflag=1 and bid=" + bid+" and layoutTime='"+time+"'order by cid");
        Ctopic one = cdao.getOne(Ctopic.class, vdaoList.get(index - 1).getCid());
        finishBean.setCsentence(one.getCsentence());
        finishBean.setCtranslate(one.getCtranslate());
        finishBean.setVid(vdaoList.get(index - 1).getVid());
        if (nid == 0 || nid == null) {
            finishBean.setWorkScore((double) 0);
            finishBean.setAdultTape("");
        } else {
            WadultWorkDetails one1 = wdao.findOne(" from WadultWorkDetails where vid=" + vdaoList.get(index - 1).getVid() + " and nid=" + nid);
            if (one1 != null) {
                finishBean.setWorkScore(one1.getWorkScore());
                finishBean.setAdultTape(one1.getAdultTape());
                finishBean.setErro(one1.getErro());
            } else {
                finishBean.setWorkScore((double) 0);
                finishBean.setAdultTape("");
                finishBean.setErro("");
            }
        }
        return ResultUtil.success("成人作业详情", finishBean);
    }

    /**
     * @Author: 徐慷慨
     * @Description:成人作业答题
     * @Date: 11:08 2017/9/14
     */
    @Override
    public Result adultToWork(WadultWorkDetails wadultWorkDetails) throws Exception {
        WadultWorkDetails one = wdao.findOne("from WadultWorkDetails where vid=" + wadultWorkDetails.getVid() + " and nid=" + wadultWorkDetails.getNid());
        if(one!=null){
            one.setFinishTime(new Timestamp(System.currentTimeMillis()));
            if(wadultWorkDetails.getAdultTape()!=null){
                one.setAdultTape(wadultWorkDetails.getAdultTape());
            }
            one.setErro(wadultWorkDetails.getErro());
            boolean update = wdao.update(one);
            if (update) {
                return ResultUtil.success("成人作业答题成功！");
            } else {
                return ResultUtil.error(2, "成人作业答题失败！");
            }
        }else{
            wadultWorkDetails.setFinishTime(new Timestamp(System.currentTimeMillis()));
            int save = wdao.save(wadultWorkDetails);
            if (save > 0) {
                return ResultUtil.success("成人作业答题成功！");
            } else {
                return ResultUtil.error(2, "成人作业答题失败！");
            }
        }
    }

    /**
     * 成人课程列表
     * @return
     */
    @Override
    public Result adultCourse() throws Exception {
        List<VadultWork> vdaoList = vdao.findList("from VadultWork where vflag=1 group by aid");
        List<Acourse> list=new ArrayList<Acourse>();
        if(vdaoList!=null&&vdaoList.size()>0){
            for (VadultWork vadultWork : vdaoList) {
                Acourse acourse = adao.getOne(Acourse.class, vadultWork.getAid());
                list.add(acourse);
            }
        }
        return ResultUtil.success(list);
    }

    /**
     * 成人课程级别列表
     * @param aid
     * @return
     */
    @Override
    public Result adultLevel(int aid) throws Exception {
        List<VadultWork> vdaoList = vdao.findList("from VadultWork where vflag=1 and aid="+aid+"group by levelId");
        List<CourseLevel> list=new ArrayList<CourseLevel>();
        if(vdaoList!=null&&vdaoList.size()>0){
            for (VadultWork vadultWork : vdaoList) {
                CourseLevel acourse = courseLevelIBaseDao.getOne(CourseLevel.class, vadultWork.getLevelId());
                list.add(acourse);
            }
        }
        return ResultUtil.success(list);
    }

}
