package com.tuyue.appModules.course.biz.impl;
import com.tuyue.appModules.course.bean.*;
import com.tuyue.appModules.course.biz.IstudentWorkBiz;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.ObjectCopyUtil;
import com.tuyue.util.Page;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
@Service

public class StudentWorkBizImpl implements IstudentWorkBiz {
    @Autowired
    private IBaseDao<Acourse> adao;
    @Autowired
    private IBaseDao<Bhour> bdao;
    @Autowired
    private IBaseDao<Ctopic> cdao;
    @Autowired
    private IBaseDao<SclassWork> sdao;
    @Autowired
    private IBaseDao<TstuWorkFinish> tdao;
    @Autowired
    private IBaseDao<Nstudent> ndao;
    @Autowired
    private IBaseDao<UteacherReply> udao;
    @Autowired
    private IBaseDao<Oclass> odao;
    @Autowired
    private IBaseDao<AllText> allTextIBaseDao;
    @Autowired
    private IBaseDao<AllTestAnswer> allTestAnswerIBaseDao;
    @Autowired
    private IBaseDao<Tests>testsIBaseDao;
    @Autowired
    private IBaseDao<TestGrade>testGradeIBaseDao;
    @Autowired
    private IBaseDao<Rule> ruleIBaseDao;
    @Autowired
    private IBaseDao<RuleLevel>ruleLevelIBaseDao;


    /**
     * @Author: 徐慷慨
     * @Description:学生作业列表
     * @Date: 16:34 2017/9/14
     */
    @Override
    @Transactional
    public Result stuWorkList(Integer nid) throws Exception {
        List<WorkListBean> list = new ArrayList<WorkListBean>();
        Nstudent one = ndao.getOne(Nstudent.class, nid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.roll(Calendar.DATE, -7);//日期回滚7天
        String format = sdf.format(lastDate.getTime());
        if (one != null) {
            List<SclassWork> list1 = sdao.findList(" from SclassWork where oid=" + one.getOid() + " and layoutTime >='" + format + "' group by bid,layoutTime order by layoutTime desc");
            for (SclassWork sclassWork : list1) {
                WorkListBean workListBean = new WorkListBean();
                List<SclassWork> list2 = sdao.findList(" from SclassWork where oid=" + one.getOid() + " and bid=" + sclassWork.getBid() + " and layoutTime >='" + format + "'and layoutTime='"+sclassWork.getLayoutTime()+"' order by layoutTime desc");
                String cids = "";
                if (list2.size() > 0) {
                    for (SclassWork work : list2) {
                        cids += work.getCid() + ",";
                    }
                    cids = cids.substring(0, cids.length() - 1);
                    workListBean.setCids(cids);
                }
                workListBean.setTotal(list2.size());
                Acourse adaoOne = adao.getOne(Acourse.class, sclassWork.getAid());
                Bhour bdaoOne = bdao.getOne(Bhour.class, sclassWork.getBid());
                workListBean.setAid(adaoOne.getAid());
                workListBean.setAname(adaoOne.getAname());
                workListBean.setBid(bdaoOne.getBid());
                workListBean.setBname(bdaoOne.getBname());
                //完成条数
                List<TstuWorkFinish> list3 = tdao.findList("select t from  TstuWorkFinish t ,SclassWork s where s.sid=t.sid and s.oid=" + one.getOid() + " and s.bid=" + sclassWork.getBid() +" and t.nid="+nid+" and s.layoutTime='"+sclassWork.getLayoutTime()+"'");
                workListBean.setFinish(list3.size());
                workListBean.setLayoutTime(sclassWork.getLayoutTime().toString().substring(0,sclassWork.getLayoutTime().toString().length()-2));
                double num = 0;
                for (TstuWorkFinish tstuWorkFinish : list3) {
                    num += tstuWorkFinish.getWorkScore();
                }
                //得分
                double v = num / (list2.size() * 100);
                workListBean.setGrade((int)(v * 100));
                list.add(workListBean);
            }
            return ResultUtil.success("学生作业列表", list);
        } else {
            return ResultUtil.error(2, "参数错误！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:学生作业详情
     * @Date: 16:34 2017/9/14
     */
    @Override
    @Transactional
    public Result stuWorkDetails(Integer nid, Integer bid, Integer index, String cids,String time) throws Exception {
        StuFinshBean stuFinshBean = new StuFinshBean();
        Nstudent one = ndao.getOne(Nstudent.class, nid);
        if (one != null) {
            List<SclassWork> list1 = sdao.findList(" from SclassWork where oid=" + one.getOid() + " and bid=" + bid +" and layoutTime='"+time+"'order by cid");
            Ctopic cdaoOne = cdao.getOne(Ctopic.class, list1.get(index - 1).getCid());
            stuFinshBean.setCsentence(cdaoOne.getCsentence());
            stuFinshBean.setCtranslate(cdaoOne.getCtranslate());
            stuFinshBean.setSid(list1.get(index - 1).getSid());
            TstuWorkFinish one1 = tdao.findOne(" from TstuWorkFinish where sid='" + list1.get(index - 1).getSid() + "'" + " and nid=" + nid);
            if (one1 != null) {
                stuFinshBean.setStudentTape(one1.getStudentTape());
                stuFinshBean.setErro(one1.getErro());
                stuFinshBean.setWorkScore(one1.getWorkScore());
                stuFinshBean.setSysGrade(one1.getSysGrade());
                stuFinshBean.setCoefficient(one1.getCoefficient());
            }
            return ResultUtil.success("学生作业详情", stuFinshBean);
        }
        return ResultUtil.error(2, "参数错误！");
    }

    /**
     * @Author: 徐慷慨
     * @Description:请教老师/跳过
     * @Date: 16:39 2017/9/14
     */
    @Override
    @Transactional
    public Result stuToWork(TstuWorkFinish tstuWorkFinish) throws Exception {
        System.out.println("请教老师/跳过"+tstuWorkFinish.toString());
        TstuWorkFinish one = tdao.findOne(" from TstuWorkFinish where sid='" + tstuWorkFinish.getSid() + "' and nid=" + tstuWorkFinish.getNid());
        if (one != null) {
            one.setFinishTime(new Timestamp(System.currentTimeMillis()));
            one.setErro(tstuWorkFinish.getErro());
            one.setWorkScore(tstuWorkFinish.getWorkScore());
            one.setSysGrade(tstuWorkFinish.getSysGrade());
            one.setCoefficient(tstuWorkFinish.getCoefficient());
            one.setReplyStatus(2);
            if(tstuWorkFinish.getStudentTape()!=null){
                one.setStudentTape(tstuWorkFinish.getStudentTape());
            }
            if (tstuWorkFinish.getIsConsult() == 3) {
                one.setIsConsult(tstuWorkFinish.getIsConsult());
                one.setConsultTime(new Timestamp(System.currentTimeMillis()));
            }
            if(tstuWorkFinish.getStartTime()!=null&&!tstuWorkFinish.getStartTime().equals("")&&tstuWorkFinish.getEndTime()!=null&&!tstuWorkFinish.getEndTime().equals("")){
                one.setStartTime(tstuWorkFinish.getStartTime());
                one.setEndTime(tstuWorkFinish.getEndTime());
            }
            boolean update1 = tdao.update(one);
            if (update1) {
                return ResultUtil.success("学生答题成功",tstuWorkFinish.getWorkScore());
            }
            return ResultUtil.error(2, "学生答题失败！");
        } else {
            if (tstuWorkFinish.getIsConsult() == 3) {
                tstuWorkFinish.setConsultTime(new Timestamp(System.currentTimeMillis()));
                tstuWorkFinish.setReplyStatus(2);
            }
            tstuWorkFinish.setFinishTime(new Timestamp(System.currentTimeMillis()));
            int save = tdao.save(tstuWorkFinish);
            if (save > 0) {

                return ResultUtil.success("学生答题成功");
            }
            return ResultUtil.error(2, "学生答题失败！");
        }
    }


    /**
     * @Author: 徐慷慨
     * @Description:请教列表
     * @Date: 14:38 2017/9/15
     */
    @Override
    @Transactional
    public Result consultList(Integer nid) throws Exception{
        List<TstuWorkFinish> list = tdao.findList(" from TstuWorkFinish where nid=" + nid + " and isConsult=3 order by consultTime desc");
        List<ConsultListBean> list1 =new ArrayList<ConsultListBean>();
        for (TstuWorkFinish tstuWorkFinish : list) {
            ConsultListBean consultListBean =new ConsultListBean();
            SclassWork one = sdao.findOne(" from SclassWork where sid='" + tstuWorkFinish.getSid() + "'");
            Acourse adaoOne = adao.getOne(Acourse.class, one.getAid());
            Bhour bdaoOne = bdao.getOne(Bhour.class, one.getBid());
            consultListBean.setAname(adaoOne.getAname());
            consultListBean.setBname(bdaoOne.getBname());
            consultListBean.setTid(tstuWorkFinish.getTid());
            consultListBean.setReplyStatus(tstuWorkFinish.getReplyStatus());
            consultListBean.setCid(one.getCid());
            consultListBean.setConsultTime(tstuWorkFinish.getConsultTime());
            list1.add(consultListBean);
        }
        Map map =new HashMap();
        map.put("list",list1);
        return ResultUtil.success("请教列表",map);
    }

    /**
     * @Author: 徐慷慨
     * @Description:请教详情
     * @Date: 15:19 2017/9/15
     */
    @Override
    @Transactional
    public Result consultDetiles(Integer tid,Integer cid) throws Exception{
        ConsultDetilesBean bean =new ConsultDetilesBean();
        TstuWorkFinish one1 = tdao.getOne(TstuWorkFinish.class, tid);
        SclassWork one3 = sdao.findOne("from SclassWork where sid='"+one1.getSid()+"'");
        Oclass one2 = odao.getOne(Oclass.class, one3.getOid());
        if(one2!=null){
            bean.setClassName(one2.getClassName());
        }else{
            bean.setClassName("");
        }
        Acourse adaoOne = adao.getOne(Acourse.class, one3.getAid());
        Bhour bdaoOne = bdao.getOne(Bhour.class, one3.getBid());
        Ctopic one = cdao.getOne(Ctopic.class, cid);
        if(one!=null){
            bean.setCsentence(one.getCsentence());
            bean.setCtranslate(one.getCtranslate());
        }else{
            bean.setCsentence("");
            bean.setCtranslate("");
        }
        bean.setAname(adaoOne.getAname());
        bean.setBname(bdaoOne.getBname());

        UteacherReply udaoOne = udao.findOne("from UteacherReply where tid=" + tid);
        if(udaoOne!=null){
            bean.setReplyVoice(udaoOne.getReplyVoice()!=null?udaoOne.getReplyVoice():"");
            bean.setReplyWord(udaoOne.getReplyWord()!=null?udaoOne.getReplyWord():"");
        }
        bean.setConsultTime(one1.getConsultTime());
        bean.setStudentTape(one1.getStudentTape());
        bean.setSysGrade(one1.getSysGrade());
        bean.setCoefficient(one1.getCoefficient());
        return ResultUtil.success("请教详情",bean);
    }

    /**
     * @Author: 徐慷慨
     * @Description:全部作业列表
     * @Date: 16:34 2017/9/14
     */
    @Override
    @Transactional
    public Result stuWorkListALL(Integer nid,Integer currentPage, Integer pageSize) throws Exception{
        List<WorkListBean> list = new ArrayList<WorkListBean>();
        Nstudent one = ndao.getOne(Nstudent.class, nid);
        if (one != null) {
            StringBuffer hql =new StringBuffer(" from SclassWork where oid=" + one.getOid() + "group by bid,layoutTime order by layoutTime ");
            StringBuffer count =new StringBuffer("select count(*) from SclassWork where oid=" + one.getOid() + "group by bid,layoutTime order by layoutTime desc");
            Page<SclassWork> page = sdao.findPage(currentPage, pageSize, hql.toString(), count.toString());
            for (SclassWork sclassWork : page.getList()) {
                WorkListBean workListBean = new WorkListBean();
                List<SclassWork> list2 = sdao.findList(" from SclassWork where oid=" + one.getOid() + " and bid=" + sclassWork.getBid()+"and layoutTime='"+sclassWork.getLayoutTime()+"'");
                String cids = "";
                if (list2.size() > 0) {
                    for (SclassWork work : list2) {
                        cids += work.getCid() + ",";
                    }
                    cids = cids.substring(0, cids.length() - 1);
                    workListBean.setCids(cids);
                }
                workListBean.setTotal(list2.size());
                Acourse adaoOne = adao.getOne(Acourse.class, sclassWork.getAid());
                Bhour bdaoOne = bdao.getOne(Bhour.class, sclassWork.getBid());
                workListBean.setAid(adaoOne.getAid());
                workListBean.setAname(adaoOne.getAname());
                workListBean.setBid(bdaoOne.getBid());
                workListBean.setBname(bdaoOne.getBname());
                //完成条数
                List<TstuWorkFinish> list3 = tdao.findList("select t from  TstuWorkFinish t ,SclassWork s where s.sid=t.sid and s.oid=" + one.getOid() + " and s.bid=" + sclassWork.getBid()+" and t.nid="+nid+" and s.layoutTime='"+sclassWork.getLayoutTime()+"'");
                workListBean.setFinish(list3.size());
                workListBean.setLayoutTime(sclassWork.getLayoutTime().toString());
                double num = 0;
                for (TstuWorkFinish tstuWorkFinish : list3) {
                    num += tstuWorkFinish.getWorkScore();
                }
                //得分
                double v = num / (list2.size() * 100);
                workListBean.setGrade((int)(v * 100));
                list.add(workListBean);
            }
            return ResultUtil.success("学生作业列表", list);
        } else {
            return ResultUtil.error(2, "参数错误！");
        }
    }


    //20171107


    /**
     * @Author: 徐慷慨
     * @Description:学生全文列表
     * @Date: 12:02 2017/11/7
     */
    @Override
    @Transactional
    public Result allTest(Integer nid) throws Exception {
        List<AllTestBean>beanList=new ArrayList<AllTestBean>();
        Nstudent one = ndao.getOne(Nstudent.class, nid);
        List<AllText> list = allTextIBaseDao.findList(" from AllText where oid="+one.getOid()+" order by allId desc");
        if(list.size()>0){
            for (AllText allText : list) {
                AllTestBean allTestBean=new AllTestBean();
                String aname = adao.getOne(Acourse.class, allText.getAid()).getAname();
                String bname = bdao.getOne(Bhour.class, allText.getBid()).getBname();
                allTestBean.setAname(aname);
                allTestBean.setBname(bname);
                allTestBean.setEnglish(allText.getEnglish());
                allTestBean.setChinese(allText.getChinese());
                allTestBean.setAllId(allText.getAllId());
                AllTestAnswer one1 = allTestAnswerIBaseDao.findOne(" from AllTestAnswer where nid="+nid+" and allId="+allText.getAllId());
                if(one1!=null){
                    allTestBean.setType(1);
                    double grade = one1.getGrade();
                    allTestBean.setGrade((int) grade);
                    allTestBean.setTime(one1.getAnswerTime());
                    allTestBean.setWork(one1.getWork());
                }else{
                    allTestBean.setType(2);
                }
                beanList.add(allTestBean);
            }
        }
                Collections.sort(beanList, new Comparator<AllTestBean>(){

            /*
             * int compare(Student o1, Student o2) 返回一个基本类型的整型，
             * 返回负数表示：o1 小于o2，
             * 返回0 表示：o1和o2相等，
             * 返回正数表示：o1大于o2。
             */
            public int compare(AllTestBean o1, AllTestBean o2) {
                //按照学生的年龄进行升序排列
                if(o1.getTime()!=null&&o1.getTime().length()>0){
                    return 1;
                }
                return -1;
            }
        });
        System.out.println("排序后："+list);

        return ResultUtil.success("学生全文列表",beanList);
    }


    /**
     * @Author: 徐慷慨
     * @Description:学生全文答题
     * @Date: 14:04 2017/11/7
     */
    @Override
    @Transactional
    public Result allTestAnswer(AllTestAnswer allTestAnswer) throws Exception{
        AllTestAnswer one1 = allTestAnswerIBaseDao.findOne(" from AllTestAnswer where nid="+allTestAnswer.getNid()+" and allId="+allTestAnswer.getAllId());
        if(one1!=null){
            System.out.println("id:"+one1.getAnsId());
           allTestAnswer.setAnsId(one1.getAnsId());
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date());
            allTestAnswer.setAnswerTime(format);
            Session sessions = allTestAnswerIBaseDao.getSessions();
            sessions.evict(one1);
            boolean update = allTestAnswerIBaseDao.update(allTestAnswer);
           if(update){
               return ResultUtil.success("答题成功！");
           }
            return ResultUtil.error(2,"答题失败！");
        }else{
            SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(new Date());
            allTestAnswer.setAnswerTime(format);
            int save = allTestAnswerIBaseDao.save(allTestAnswer);
            if(save>0){
                return ResultUtil.success("答题成功！");
            }
            return ResultUtil.error(2,"答题失败！");
        }
    }


    /**
     * @Author: 徐慷慨
     * @Description:根据学生查询测试题
     * @Date: 19:49 2017/11/8
     */
    @Override
    @Transactional
    public Result selectTestByid(Integer nid) throws Exception {
        Nstudent one = ndao.getOne(Nstudent.class, nid);
        Oclass one2 = odao.getOne(Oclass.class, one.getOid());
        Tests one1 = testsIBaseDao.getOne(Tests.class, one2.getTestId());
        return ResultUtil.success("根据学生查询测试题",one1);
    }

    /**
     * @Author: 徐慷慨
     * @Description:答测试题
     * @Date: 19:51 2017/11/8
     */
    @Override
    @Transactional
    public Result answerTest(TestGrade testGrade){
        int save = testGradeIBaseDao.save(testGrade);
        if(save>0){
            int i = ndao.deleteWithHql("update Nstudent set isTest=1 where nid=" + testGrade.getNid());
            return ResultUtil.success("success");
        }
        return ResultUtil.error(2,"失败！");
    }

    /**
     * @Author: 徐慷慨
     * @Description: 学生答题翻分
     * @Date: 14:38 2017/11/9
     */
    @Override
    @Transactional
    public Result breakGrade(Integer nid,Integer score) throws Exception{
        int Workscore=score;
        int sysGrade=score;
        double coefficient=1;
        if(score!=0){
        // 学生自己答题
            Nstudent one1 = ndao.getOne(Nstudent.class, nid);
            Oclass one2 = odao.getOne(Oclass.class, one1.getOid());
            if(one1.getLevel()!=4){ //学生不是真实档
                //该学生的规则
                RuleLevel ruleLevel = ruleLevelIBaseDao.findOne("from RuleLevel where ruleId="+one2.getRuleId()+" and level="+one1.getLevel());
                String[] split = ruleLevel.getGradeScope().split("-");
                int lower=Integer.parseInt(split[0]);
                int high=Integer.parseInt(split[1]);
                if(lower<=score&&score<=high){
                    double workScore = score;
                    sysGrade=(int) workScore;
                    coefficient=1.0;
                }else{
                    double i=one1.getCoefficient();
                    if(score<lower){
                        for(;;){
                            i=i+0.1;
                            sysGrade=(int)(score*(i));
                            if(lower<=sysGrade&&sysGrade<=high){
                                coefficient=i;
                                break;
                            }
                        }
                    }else{
                        for(;;){
                            i=i-0.1;
                            sysGrade=(int)(score*(i));
                            if(lower<=sysGrade&&sysGrade<=high){
                                coefficient=i;
                                break;
                            }
                        }
                    }
                }
            }
        }
            Map<String,Object> map =new HashMap();
            map.put("Workscore",Workscore);
            map.put("sysGrade",sysGrade);
            map.put("coefficient",coefficient);
            return ResultUtil.success("学生答题返分",map);
    }

}
