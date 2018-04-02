package com.tuyue.webModules.work.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;

import com.tuyue.util.JPushUtil;
import com.tuyue.util.Md5Util;
import com.tuyue.util.Page;
import com.tuyue.util.Setting;
import com.tuyue.webModules.adult.bean.InsertWorBean;
import com.tuyue.webModules.work.bean.ConsultListBean;
import com.tuyue.webModules.work.bean.StudentWorkDetiles;
import com.tuyue.webModules.work.bean.StudentWorkList;
import com.tuyue.webModules.work.bean.WorkListBean;
import com.tuyue.webModules.work.biz.IworkBiz;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.out;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/11.
 */
@Service
@Transactional
public class workBizImpl implements IworkBiz {
    @Autowired
    private IBaseDao<Acourse> adao;
    @Autowired
    private IBaseDao<Bhour> bdao;
    @Autowired
    private IBaseDao<Ctopic> cdao;
    @Autowired
    private IBaseDao<QschoolCourse> qdao;
    @Autowired
    private IBaseDao<SclassWork> sdao;
    @Autowired
    private IBaseDao<TstuWorkFinish> tdao;
    @Autowired
    private IBaseDao<UteacherReply> udao;
    @Autowired
    private IBaseDao<Oclass> odao;
    @Autowired
    private IBaseDao<Ebranchschool> edao;
    @Autowired
    private IBaseDao<Nstudent> ndao;
    @Autowired
    private IBaseDao<Fstaff> fdao;
    @Autowired
    private IBaseDao<VadultWork>vdao;
    @Autowired
    private IBaseDao<AllText> allTextIBaseDao;
    @Autowired
    private IBaseDao<CourseLevel>courseLevelIBaseDao;

    /**
     * @Author: 徐慷慨
     * @Description: 给班级布置作业
     * @Date: 11:17 2017/9/11
     */
    @Override
    @Transactional
    public Result  insetClassWork(InsertWorBean insertWorBean) throws Exception {
        List<SclassWork> list = new ArrayList<SclassWork>();
        String[] split = insertWorBean.getCid().split(",");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (String id : split) {
            SclassWork sclassWork = new SclassWork();
            sclassWork.setAid(insertWorBean.getAid());
            sclassWork.setBid(insertWorBean.getBid());
            sclassWork.setCid(Integer.parseInt(id));
            sclassWork.setFid(insertWorBean.getFid());
            sclassWork.setLayoutTime(timestamp);
            sclassWork.setLevelId(insertWorBean.getLevelId());
            sclassWork.setOid(insertWorBean.getOid());
            sclassWork.setSid(UUID.randomUUID().toString().replaceAll("-", ""));
            list.add(sclassWork);
        }
        int i = sdao.batchSave(list);
        //添加全文测试题
        if (i > 0) {
            String cid="";
            for (String s : split) {
                 cid+=s+",";
            }
            cid=cid.substring(0,cid.length()-1);
            AllText allText = allTextIBaseDao.findOne(" from AllText where aid="+insertWorBean.getAid()+" and bid="+insertWorBean.getBid()+" and oid="+insertWorBean.getOid());
            List<Ctopic> cdaoList = cdao.findList(" from Ctopic where cid in ("+cid+") order by cid");
            String  english="";
            String  chinese="";
                for (Ctopic ctopic : cdaoList) {
                        english+=ctopic.getCsentence();
                        chinese+=ctopic.getCtranslate();
                }
                AllText allText1 =new AllText();
                allText1.setEnglish(english);
                allText1.setChinese(chinese);
                allText1.setAid(insertWorBean.getAid());
                allText1.setBid(insertWorBean.getBid());
                allText1.setOid(insertWorBean.getOid());
                allTextIBaseDao.save(allText1);
            List<Nstudent> list1 = ndao.findList(" FROM Nstudent  where oid=" + insertWorBean.getOid());
            HashMap<String, String> maps = new HashMap<String, String>();

            List<String> list2 =new ArrayList<String>();
            for (Nstudent nstudent : list1) {
                list2.add(Md5Util.toMD5(nstudent.getUsername()));
            }
            String[] string = new String[list2.size()];
            for (int i1 = 0; i1 < list2.size(); i1++) {
                string[i1] = list2.get(i1);
            }
            maps.put("type", String.valueOf(1));  //type  = 1 布置作业 type = 2  老师鼓励学生了
            JPushUtil.sendMsgToApp("你有新的作业了","老师布置作业了，快去完成","type",maps,string);
            JPushUtil.sendMsgToIos("你有新的作业了","老师布置作业了，快去完成","type",maps,string);
            return ResultUtil.success("班级布置作业！");
        } else {
            return ResultUtil.error(2, "班级布置失败！");
        }
    }




    /**
     * @Author: 徐慷慨
     * @Description:给成人布置作业
     * @Date: 15:48 2017/9/13
     */
    @Override
    public Result insetAdltWork(InsertWorBean insertWorBean) throws Exception{
        List<VadultWork> list =new ArrayList<VadultWork>();
        String[] split = insertWorBean.getCid().split(",");
        List<VadultWork> list1 = vdao.findList(" from VadultWork");
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
            vadultWork.setAid(insertWorBean.getAid());
            vadultWork.setBid(insertWorBean.getBid());
            vadultWork.setLevelId(insertWorBean.getLevelId());
            list.add(vadultWork);
        }
        int i = vdao.batchSave(list);
        if(i==list.size()){
            return ResultUtil.success("添加成功！");
        }
        return ResultUtil.error(2,"添加失败！");
    }

    /**
     * @Author: 徐慷慨
     * @Description:该学校下的课程列表
     * @Date: 11:19 2017/9/11
     */
    @Override
    public Result schoolCourseList(Integer eid) throws Exception {
        List<Acourse> list = adao.findList(" select a from Acourse a ,QschoolCourse q where a.aid=q.aid and q.eid=" + eid);
        Map map = new HashMap();
        map.put("list", list);
        return ResultUtil.success("该学校下的课程列表", map);

    }

    /**
     * @Author: 徐慷慨
     * @Description: 课程级别列表
     * @Date: 11:21 2017/9/11
     */
    @Override
    public Result schoolHourList(Integer aid, Integer eid) throws Exception {
        QschoolCourse one = qdao.findOne(" from QschoolCourse where aid=" + aid + " and eid=" + eid);
        String bid = "";
        if (one.getLevelId().endsWith(",")) {
            bid = one.getLevelId().substring(0, one.getLevelId().length() - 1);
        } else {
            bid = one.getLevelId();
        }

        List<CourseLevel> list = courseLevelIBaseDao.findList(" from CourseLevel where levelId in(" + bid + ")");
        Map map = new HashMap();
        map.put("list", list);
        return ResultUtil.success("课程级别列表", map);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 课程详情列表
     * @Date: 11:22 2017/9/11
     */
    @Override
    public Result courseDetilesList(Integer bid) throws Exception {
        List<Ctopic> list = cdao.findList("from Ctopic where bid=" + bid);
        return ResultUtil.success("课程详情列表", list);
    }

    /**
     * @param fid
     * @Author: 徐慷慨
     * @Description:班级列表
     * @Date: 14:31 2017/9/11
     */

    @Override
    public Result classList(Integer fid) throws Exception {
        Fstaff one = fdao.getOne(Fstaff.class, fid);
        List<Oclass> list =new ArrayList<>();
        if(one!=null){
            Ebranchschool one1 = edao.findOne(" from Ebranchschool where eid=" + one.getEid());
            if(one1!=null){
                 list = odao.findList(" from Oclass where eid="+one1.getEid());
            }
        }
        Map map = new HashMap();
        map.put("list", list);
        return ResultUtil.success("班级列表", map);
    }
    /**
     * @Author: 徐慷慨
     * @Description:作业列表
     * @Date: 8:45 2017/9/12
     */
    @Override
    public Result workList(Integer currentPage, Integer pageSize, Integer eid, Integer fid, String layoutTime, Integer aid , Integer bid, Integer oid1) throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map map =new HashMap();
        List<WorkListBean> list =new ArrayList<WorkListBean>();
        List<Oclass> list1 = odao.findList("from Oclass where eid=" + eid);
        if(list1==null||list1.size()<=0){
            map.put("list",list);
            return ResultUtil.success("作业列表",map);
        }
        String oid="";
        for (Oclass oclass : list1) {
            oid+=oclass.getOid()+",";
        }
        oid=oid.substring(0,oid.length()-1);
        if(oid1!=null){
            oid=oid1.toString();
        }
        StringBuffer hql= new StringBuffer("from SclassWork  where oid in ("+oid+") ");
        StringBuffer count= new StringBuffer("select count(*) from SclassWork where sid in ");
        if(aid!=null){
            hql.append(" and aid="+aid);
            count.append(" and aid="+aid);
        }
        if(bid!=null){
            hql.append(" and bid="+bid);
            count.append(" and bid="+bid);
        }
        if(layoutTime!=null){
            hql.append(" and layoutTime like'"+layoutTime+"%'");
            count.append(" and layoutTime like'"+layoutTime+"%'");
        }
        hql.append(" GROUP BY layoutTime");
        String s = hql.toString();
        count.append(" ( select sid "+s+")");
        Page<SclassWork> page = sdao.findPage(currentPage, pageSize, hql.toString(), count.toString());
       // for (SclassWork sclassWork : page.getList()) {
        for(int i=page.getList().size()-1;i>=0;i--){
            SclassWork sclassWork=page.getList().get(i);
            WorkListBean workListBean =new WorkListBean();
            workListBean.setLayoutTime(sclassWork.getLayoutTime());
            workListBean.setSid(sclassWork.getSid());
            if(sclassWork.getAid()!=null){
                Acourse one = adao.getOne(Acourse.class, sclassWork.getAid());
                if(one!=null){
                    workListBean.setAname(one.getAname()!=null?one.getAname():"");
                }else{
                    workListBean.setAname("");
                }
            }else{
                workListBean.setAname("");
            }

            Bhour one1 = bdao.getOne(Bhour.class, sclassWork.getBid());
            long count1 = sdao.findCount("select count(*) from SclassWork where bid="+sclassWork.getBid()+" and oid in(" + oid + ") and layoutTime='"+sclassWork.getLayoutTime()+"'");
            workListBean.setBname(one1.getBname()!=null?one1.getBname():"");
            workListBean.setNum((int) count1);
            workListBean.setLayoutTime(sclassWork.getLayoutTime());
            Fstaff one2 = fdao.getOne(Fstaff.class, sclassWork.getFid());
            workListBean.setName(one2.getName());
            Oclass one3 = odao.getOne(Oclass.class, sclassWork.getOid());
            workListBean.setClassName(one3.getClassName());
            workListBean.setOid(sclassWork.getOid());
            list.add(workListBean);
        }

        map.put("pageSize", page.getPageSize());
        map.put("total", page.getTotal());
        map.put("currentPage", page.getCurrentPage());
        map.put("allPage", page.getAllpage());
        map.put("list", list);
        return ResultUtil.success("作业列表",map);
    }

    /**
     * @param oid
     * @Author: 徐慷慨o
     * @Description:作业完成详情o
     * @Date: 10:27 2017/9/12
     */
    @Override
    public Result workDetiles(Integer oid, Integer currentPage, Integer pageSize, String layoutTime) throws Exception {
            Date date =new Date();
           date.setTime(Long.parseLong(layoutTime));
           SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        layoutTime = simpleDateFormat.format(date);
        StringBuffer hql =new StringBuffer(" from Nstudent where oid=" + oid);
        StringBuffer count =new StringBuffer("select count(*) from Nstudent where oid=" + oid);
        Page<Nstudent> page = ndao.findPage(currentPage, pageSize, hql.toString(), count.toString());
          List<StudentWorkList> lists =new ArrayList<StudentWorkList>();
        List<SclassWork> list1 = sdao.findList(" from SclassWork where layoutTime='" + layoutTime + "' and oid=" + oid);
        double all=list1.size()*100;
        for (Nstudent nstudent : page.getList()) {
            StudentWorkList studentWorkList =new StudentWorkList();
            studentWorkList.setStudentName(nstudent.getStudentName());
            studentWorkList.setNid(nstudent.getNid());
          //sdao.这块的完成率和的分先空着
            List<TstuWorkFinish> list = tdao.findList("select t from  TstuWorkFinish t ,SclassWork s where nid=" + nstudent.getNid()+" and t.sid=s.sid and s.layoutTime='"+layoutTime+"'");
            double num=0;
            for (TstuWorkFinish tstuWorkFinish : list) {
                num+=tstuWorkFinish.getWorkScore();
            }
                studentWorkList.setWorkScore((int)((num/all)*100));
                DecimalFormat format = new DecimalFormat("0.00");
                String s = format.format((float) list.size() / list1.size());
                studentWorkList.setPercentum(Double.valueOf(s)*100);
                lists.add(studentWorkList);
        }
        Map map =new HashMap();
        map.put("pageSize", page.getPageSize());
        map.put("total", page.getTotal());
        map.put("currentPage", page.getCurrentPage());
        map.put("allPage", page.getAllpage());
        map.put("list", lists);
        return ResultUtil.success("班级作业完成详情",map);
    }



    /**
     * @Author: 徐慷慨
     * @Description:学生作业详情
     * @Date: 18:04 2017/9/12
     */
    @Override
    public Result studentWorkDetiles(Integer nid, Integer currentPage, Integer pageSize, String layoutTime) throws Exception{
        Nstudent one = ndao.getOne(Nstudent.class, nid);
        Date date =new Date();
        date.setTime(Long.parseLong(layoutTime));
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        layoutTime = simpleDateFormat.format(date);
        //查询该班级下所有作业
        List<SclassWork> list = sdao.findList(" from SclassWork where oid=" + one.getOid()+" AND layoutTime ='"+layoutTime+"'");
        //查询此学生完成的作业
        List<TstuWorkFinish> list1 = tdao.findList(" select t from TstuWorkFinish t,SclassWork s where t.nid=" + nid+" and s.layoutTime ='"+layoutTime+"' and t.sid=s.sid ");
        List<StudentWorkDetiles> list2 = new ArrayList<StudentWorkDetiles>();
        List<String> listId =new ArrayList<String>();
        List<String> listall =new ArrayList<String>();
        for (SclassWork sclassWork : list) {
            listall.add(sclassWork.getSid());
        }
        for (TstuWorkFinish tstuWorkFinish : list1) {
            listId.add(tstuWorkFinish.getSid());
        }
        //去掉完成之后的
        listall.removeAll(listId);
        //完成的
        for(TstuWorkFinish tstuWorkFinish : list1){
            StudentWorkDetiles studentWorkDetiles =new StudentWorkDetiles();
            SclassWork one1 = sdao.findOne(" FROM SclassWork WHERE sid='" + tstuWorkFinish.getSid()+"'" );
            Ctopic one2 = cdao.getOne(Ctopic.class, one1.getCid());
            studentWorkDetiles.setCsentence(one2.getCsentence());
            studentWorkDetiles.setWorkScore(tstuWorkFinish.getWorkScore());
            studentWorkDetiles.setStudentTape(tstuWorkFinish.getStudentTape());
            list2.add(studentWorkDetiles);
        }
        for(String id: listall){
            StudentWorkDetiles studentWorkDetiles =new StudentWorkDetiles();
            if(id!=null||id!=""){
                SclassWork one1 = sdao.findOne(" from SclassWork where sid='" + id+"'");
                Ctopic one2 = cdao.getOne(Ctopic.class, one1.getCid());
                if(one2!=null){
                    studentWorkDetiles.setCsentence(one2.getCsentence()!=null?one2.getCsentence():"");
                    list2.add(studentWorkDetiles);
                }
            }
        }
        return ResultUtil.success("学生作业详情",list2);
    }



    //::::::::::::::::::::::::::::::::::::::回复作业模块:::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * @Author: 徐慷慨
     * @Description: 回复作业列表
     * @Date: 15:09 2017/9/11
     */
    @Override
    public Result consultList(Integer eid, Integer currentPage, Integer pageSize, Integer replyStatus, Integer fid, Integer oid, String studentName) throws Exception {
        List<ConsultListBean> bean = new ArrayList<ConsultListBean>();
        //查询所有学生
        StringBuffer hql = new StringBuffer(" from Nstudent where eid=" + eid);
        if (studentName != null) {
            hql.append(" and studentName like '%" + studentName + "%'");
        }
        if (oid != null) {
            hql.append(" and oid=" + oid);
        }
        List<Nstudent> list = ndao.findList(hql.toString());
        String nid = "";
        for (Nstudent nstudent : list) {
            nid += nstudent.getNid() + ",";
        }
        if (nid.length()>0) {
            nid = nid.substring(0, nid.length() - 1);
        }
        if (nid.length() <= 0) {
            Map map = new HashMap();
            map.put("list", bean);
            return ResultUtil.success(map);
        }
        //查询所有请教老师的数据
        String hql1 = " from TstuWorkFinish where nid in (" + nid + ") and isConsult=3 order by consultTime desc";
        String count = "select count(*) from TstuWorkFinish where nid in (" + nid + ") and isConsult=3";
        Page<TstuWorkFinish> page = tdao.findPage(currentPage, pageSize, hql1, count);
      if(page.getList().size()<=0){
          Map map = new HashMap();
          map.put("list", bean);
          return ResultUtil.success(map);
      }

        Set<String> set = new HashSet();
        String sid = "";
        //拿取所有的班级作业
        for (TstuWorkFinish tstuWorkFinish : page.getList()) {
            set.add(tstuWorkFinish.getSid());
        }
        for (String id : set) {
            sid += id + ",";
        }
        sid = sid.substring(0, sid.length() - 1);
        List<SclassWork> list1 = sdao.findList(" from SclassWork where sid in('" + sid + "')");
        Set<String> cidset = new HashSet();
        for (SclassWork sclassWork : list1) {
            cidset.add(sclassWork.getCid().toString());
        }
        String cid = "";
        for (String id : cidset) {
            cid += id + ",";
        }
        if(cid.length()>0){
            cid = cid.substring(0, cid.length() - 1);
        }else{
            cid="-1,-2";
        }


        List<Ctopic> cdaoList = cdao.findList(" from Ctopic where cid in (" + cid + ")");

        List<Acourse> adaoList = adao.findList(" from Acourse ");

        List<Bhour> bdaoList = bdao.findList("from Bhour");

        for (TstuWorkFinish tstuWorkFinish : page.getList()) {
            SclassWork one3 = sdao.findOne("from SclassWork where sid='"+tstuWorkFinish.getSid()+"'");
            Acourse one4 = adao.getOne(Acourse.class, one3.getAid());
            Bhour bdaoOne = bdao.getOne(Bhour.class, one3.getBid());
            Ctopic one5 = cdao.getOne(Ctopic.class, one3.getCid());
            ConsultListBean bean1 = new ConsultListBean();
            bean1.setAname(one4.getAname()+bdaoOne.getBname());
            bean1.setCsentence(one5.getCsentence());
            Nstudent one1 = ndao.getOne(Nstudent.class, tstuWorkFinish.getNid());
            Oclass one2 = odao.getOne(Oclass.class, one1.getOid());
            if(one2!=null){
                bean1.setClassName(one2.getClassName());
            }
            bean1.setStudentName(one1.getStudentName());
            bean1.setTid(tstuWorkFinish.getTid());
            bean1.setConsultTime(tstuWorkFinish.getConsultTime());
            bean1.setReplyStatus(tstuWorkFinish.getReplyStatus());
            bean1.setWorkScore(tstuWorkFinish.getWorkScore());
            bean1.setStudentTape(tstuWorkFinish.getStudentTape());
            UteacherReply udaoOne = udao.findOne("FROM UteacherReply WHERE tid="+tstuWorkFinish.getTid());
            if (udaoOne != null) {
                bean1.setReplyWord(udaoOne.getReplyWord());
                bean1.setReplyVoice(udaoOne.getReplyVoice());
                bean1.setReplyTime(udaoOne.getReplyTime());
                Fstaff one = fdao.getOne(Fstaff.class, udaoOne.getFid());
                if(one!=null){
                    bean1.setName(one.getName());
                }
            }
            SclassWork one = sdao.findOne(" from SclassWork where sid='" + tstuWorkFinish.getSid() + "'");
            bean.add(bean1);
        }
        Map map = new HashMap();
        map.put("pageSize", page.getPageSize());
        map.put("total", page.getTotal());
        map.put("currentPage", page.getCurrentPage());
        map.put("allPage", page.getAllpage());
        map.put("list", bean);
        return ResultUtil.success(map);
    }
    /**
     * @Author: 徐慷慨
     * @Description:回复作业
     * @Date: 8:42 2017/9/12
     */

    @Override
    public Result replyWork(UteacherReply uteacherReply, MultipartFile file) throws Exception {
        uteacherReply.setReplyTime(new Timestamp(System.currentTimeMillis()));
        if (file!=null) {
            String path = Setting.UPMP3PATH;// 文件路径
            long l = System.currentTimeMillis();
            String name = String.valueOf(l);// 文件名称
            name += ".mp3";
            File f = new File(path, name);
            if (!f.exists()) {
                f.mkdirs();
            }
            file.transferTo(f);
            String voice = "/wordVoice/" + name;
            uteacherReply.setReplyVoice(voice);
            out.println("+++++++++++++++++++录音不为空");
        }
        int save = udao.save(uteacherReply);
        if(save>0){
            TstuWorkFinish one = tdao.getOne(TstuWorkFinish.class, uteacherReply.getTid());
            one.setReplyStatus(1);
            tdao.update(one);
            return ResultUtil.success("回复作业成功！");
        }
        return ResultUtil.error(2,"回复作业失败！");
    }

}
