package com.tuyue.appModules.course.biz.impl;

import com.tuyue.appModules.course.bean.AdultCompleteBean;
import com.tuyue.appModules.course.bean.CompleteBean;
import com.tuyue.appModules.course.biz.IAdultCompleteBiz;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/15.
 * @Modified By:
 */
@Service
public class AdultCompleteBizImpl implements IAdultCompleteBiz {
    @Autowired
    private IBaseDao<Acourse> acourseIBaseDao;

    @Autowired
    private IBaseDao<Ctopic> ctopicIBaseDao;

    @Autowired
    private IBaseDao<Bhour> bhourIBaseDao;

    @Autowired
    private IBaseDao<VadultWork> vadultWorkIBaseDao;

    @Autowired
    private IBaseDao<WadultWorkDetails> wadultWorkDetailsIBaseDao;

    @Autowired
    private IBaseDao<SclassWork> sclassWorkIBaseDao;

    @Autowired
    private IBaseDao<TstuWorkFinish> tstuWorkFinishIBaseDao;
    @Autowired
    private IBaseDao<Encourage> encourageIBaseDao;

    @Override
    public AdultCompleteBean list(Integer aid, Integer bid, String cids, Integer nid) throws Exception {
        AdultCompleteBean adultCompleteBean = new AdultCompleteBean();
        adultCompleteBean.setAname(acourseIBaseDao.getOne(Acourse.class, aid).getAname());
        adultCompleteBean.setBname(bhourIBaseDao.getOne(Bhour.class, bid).getBname());
        String[] split = cids.split(",");
        adultCompleteBean.setSumctopic(split.length);
        ArrayList<CompleteBean> completeBeans = new ArrayList<CompleteBean>();
        Double fen = 0.0;
        int count = 0;
        for (String s : split) {
            VadultWork vadultWork = vadultWorkIBaseDao.findOne("from VadultWork where cid=" + Integer.parseInt(s));
            WadultWorkDetails wadultWorkDetails = wadultWorkDetailsIBaseDao.findOne("from WadultWorkDetails where vid=" + vadultWork.getVid() + " and nid=" + nid);

            Ctopic ctopic = ctopicIBaseDao.getOne(Ctopic.class, Integer.parseInt(s));
            CompleteBean completeBean = new CompleteBean();
            //英语
            completeBean.setCsentence(ctopic.getCsentence());
            if (wadultWorkDetails != null) {
                count++;
                fen = fen + wadultWorkDetails.getWorkScore();
//分数
                completeBean.setWorkScore(wadultWorkDetails.getWorkScore());
                //读音
                completeBean.setAdultTape(wadultWorkDetails.getAdultTape());
            }
            completeBeans.add(completeBean);
        }
        //总得分
        adultCompleteBean.setSumworkScore((fen / (split.length * 100)) * 100);
        //完成个数
        adultCompleteBean.setFinishsum(count);
        //具体详情
        adultCompleteBean.setCompleteBeans(completeBeans);
        return adultCompleteBean;
    }

    @Override
    public AdultCompleteBean Studentlist(Integer aid, Integer bid, String cids, String layoutTime, Integer nid) throws Exception {
        AdultCompleteBean adultCompleteBean = new AdultCompleteBean();
        adultCompleteBean.setAname(acourseIBaseDao.getOne(Acourse.class, aid).getAname());
        adultCompleteBean.setBname(bhourIBaseDao.getOne(Bhour.class, bid).getBname());
        String[] split = cids.split(",");
//        if(split.length==1){
//            long count = sclassWorkIBaseDao.findCount(" select count(*) from SclassWork where cid=" + split[0] + " and nid=" + nid);
//            if(count<=2){
//                List<Encourage> list = encourageIBaseDao.findList("FROM Encourage WHERE type=1");
//                java.util.Random random=new java.util.Random();
//                int result=random.nextInt(list.size());
//                Encourage one = list.get(result);
//                adultCompleteBean.setEncourage(one.getEncourage());
//            }
//            if(count>2&&count<=4){
//                List<Encourage> list = encourageIBaseDao.findList("FROM Encourage WHERE type=2");
//                java.util.Random random=new java.util.Random();
//                int result=random.nextInt(list.size());
//                Encourage one = list.get(result);
//                adultCompleteBean.setEncourage(one.getEncourage());
//            }
//            if(count>4){
//                List<Encourage> list = encourageIBaseDao.findList("FROM Encourage WHERE type=3");
//                java.util.Random random=new java.util.Random();
//                int result=random.nextInt(list.size());
//                Encourage one = list.get(result);
//                adultCompleteBean.setEncourage(one.getEncourage());
//            }
//        }

        List<Encourage> list1 = encourageIBaseDao.findList("FROM Encourage ");
        java.util.Random random = new java.util.Random();
        int result = random.nextInt(list1.size());
        Encourage one = list1.get(result);
        adultCompleteBean.setEncourage(one.getEncourage());

        List<String> list = Arrays.asList(split);
        Collections.sort(list);
        adultCompleteBean.setSumctopic(split.length);
        ArrayList<CompleteBean> completeBeans = new ArrayList<CompleteBean>();
        Double fen = 0.0;
        int count = 0;
        for (String s : list) {
            SclassWork sclassWork = sclassWorkIBaseDao.findOne("from SclassWork where cid=" + Integer.parseInt(s) + " and layoutTime='" + layoutTime + "'");
            if (sclassWork == null) {
                continue;
            }
            TstuWorkFinish tstuWorkFinish = tstuWorkFinishIBaseDao.findOne(" select t from TstuWorkFinish t ,SclassWork s where t.sid='" + sclassWork.getSid() + "' and t.nid=" + nid + " and t.sid=s.sid order by s.cid");
            Ctopic ctopic = ctopicIBaseDao.getOne(Ctopic.class, Integer.parseInt(s));
            CompleteBean completeBean = new CompleteBean();
            //英语
            completeBean.setCsentence(ctopic.getCsentence());
            if (tstuWorkFinish != null) {
                count++;
                fen = fen + tstuWorkFinish.getWorkScore();
//分数
                completeBean.setWorkScore(tstuWorkFinish.getWorkScore());
                //读音
                completeBean.setAdultTape(tstuWorkFinish.getStudentTape());
            }
            completeBeans.add(completeBean);
        }
        //总得分
        adultCompleteBean.setSumworkScore((fen / (split.length * 100)) * 100);
        //完成个数
        adultCompleteBean.setFinishsum(count);
        //具体详情
        adultCompleteBean.setCompleteBeans(completeBeans);
        return adultCompleteBean;
    }
}
