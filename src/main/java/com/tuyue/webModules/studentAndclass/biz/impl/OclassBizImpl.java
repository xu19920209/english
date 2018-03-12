package com.tuyue.webModules.studentAndclass.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.exception.MyException;
import com.tuyue.pojo.Fstaff;
import com.tuyue.pojo.Nstudent;
import com.tuyue.pojo.Oclass;
import com.tuyue.pojo.Pstudenthistory;
import com.tuyue.result.ResultEnum;
import com.tuyue.util.ObjectCopyUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.studentAndclass.bean.ClassBean;
import com.tuyue.webModules.studentAndclass.bean.PstudenthistoryBean;
import com.tuyue.webModules.studentAndclass.biz.IOclassBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/7.
 * @Modified By:
 */
@Service
@Transactional
public class OclassBizImpl implements IOclassBiz {
    private final static Logger logger = LoggerFactory.getLogger(OclassBizImpl.class);

    @Autowired
    private IBaseDao<Oclass> oclassIBaseDao;
    @Autowired
    private IBaseDao<Nstudent> nstudentIBaseDao;

    @Autowired
    private IBaseDao<Fstaff> fstaffIBaseDao;
    @Autowired
    private IBaseDao<Pstudenthistory> pstudenthistoryIBaseDao;

    @Override
    public boolean add(Oclass oclass) {
        oclass.setEndflag(1);
        int i = oclassIBaseDao.save(oclass);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updata(Oclass oclass) throws Exception {
        boolean update = oclassIBaseDao.update(oclass);
        return update;
    }

    @Override
    public int del(Integer oid) throws Exception {
        List<Nstudent> list = nstudentIBaseDao.findList("from Nstudent where oid=" + oid);
        //判断班级下是否有学生
        if (list.size() > 0) {
            return 2;
        }
        Oclass one = oclassIBaseDao.getOne(Oclass.class, oid);
        if (one == null) {
            throw new MyException(ResultEnum.UNKONW_ERROR);
        } else {
            boolean b = oclassIBaseDao.delete(one);

            if (b) {
                return 1;
            }
        }
        return 3;
    }

    @Override
    public Page<ClassBean> list(String className, Integer classFid, Integer teachFid, Integer eid, Integer page, Integer size) throws Exception {
        String hql = "from Oclass where eid=" + eid;

        StringBuffer sb = new StringBuffer(hql);
        if (className!=null) {
            sb.append(" and className like '%" + className+"%'");
        }
        if (classFid!=null) {
            sb.append(" and classFid=" + classFid);
        }
        if (teachFid!=null) {
            sb.append(" and teachFid=" + teachFid);
        }
        String chql = "SELECT COUNT(*) FROM Oclass where eid=" + eid;
        StringBuffer csb = new StringBuffer(chql);
        if (className!=null) {
            csb.append(" and className like '%" + className+"%'");
        }
        if (classFid!=null) {
            csb.append(" and classFid=" + classFid);
        }
        if (teachFid!=null) {
            csb.append(" and teachFid=" + teachFid);
        }
        Page<Oclass> oclassPage = oclassIBaseDao.findPage(page, size, sb.toString(), csb.toString());
        ArrayList<ClassBean> classBeans = new ArrayList<ClassBean>();
   if(oclassPage!=null&&oclassPage.getList()!=null){
        for (Oclass oclass : oclassPage.getList()) {
            long count = nstudentIBaseDao.findCount("SELECT COUNT(*) FROM Nstudent where oid=" + oclass.getOid());
            ClassBean classBean = ObjectCopyUtil.copy(oclass, Oclass.class,ClassBean.class);
            classBean.setRuleId(oclass.getRuleId());
            classBean.setTestId(oclass.getTestId());
            classBean.setNum((int)count);
            if (oclass.getClassFid()!=null) {
                Fstaff fstaff = fstaffIBaseDao.getOne(Fstaff.class, oclass.getClassFid());
                if (fstaff!=null) {
                    classBean.setClassFname(fstaff.getName());
                }
            }
            if (oclass.getTeachFid()!=null) {
                Fstaff fstaff = fstaffIBaseDao.getOne(Fstaff.class, oclass.getTeachFid());
                if (fstaff!=null) {
                    classBean.setTeachFname(fstaff.getName());
                }
            }
            List<Pstudenthistory> pstudenthistories = pstudenthistoryIBaseDao.findList("from Pstudenthistory where oid="+oclass.getOid());
            List<PstudenthistoryBean> pstudenthistoryBeans = new ArrayList<PstudenthistoryBean>();
            for (Pstudenthistory pstudenthistory : pstudenthistories) {
                PstudenthistoryBean pstudenthistoryBean = ObjectCopyUtil.copy(pstudenthistory, Pstudenthistory.class, PstudenthistoryBean.class);
                if (pstudenthistory.getClassFid()!=null) {
                    Fstaff fstaff = fstaffIBaseDao.getOne(Fstaff.class, pstudenthistory.getClassFid());
                    if (fstaff!=null) {
                        pstudenthistoryBean.setClassFname(fstaff.getName());
                    }
                };
                if (pstudenthistory.getTeachFid()!=null) {
                    Fstaff fstaff = fstaffIBaseDao.getOne(Fstaff.class, pstudenthistory.getTeachFid());
                    if (fstaff!=null) {
                        pstudenthistoryBean.setTeachFname(fstaff.getName());
                    }
                }
                pstudenthistoryBeans.add(pstudenthistoryBean);
            }

            classBean.setPstudenthistoryBeans(pstudenthistoryBeans);
            classBeans.add(classBean);
        }
}
        Page<ClassBean> page1 = new Page<ClassBean>();
        page1.setList(classBeans);
        page1.setallPage(oclassPage.getAllpage());
        page1.setPageSize(oclassPage.getPageSize());
        page1.setTotal(oclassPage.getTotal());
        page1.setCurrentPage(oclassPage.getCurrentPage());
        return page1;
    }

    @Override
    public boolean endclass(Integer oid) throws Exception {
        Oclass oclass = oclassIBaseDao.getOne(Oclass.class, oid);
        if (oclass!=null) {
            List<Nstudent> nstudentList = nstudentIBaseDao.findList("from Nstudent where oid=" + oid);
            ArrayList<Pstudenthistory> pstudenthistories = new ArrayList<Pstudenthistory>();
            for (Nstudent nstudent : nstudentList) {
                Pstudenthistory pstudenthistory = new Pstudenthistory();
                pstudenthistory.setOid(oid);
                pstudenthistory.setStartTime(oclass.getStartTime());
                pstudenthistory.setEndTime(oclass.getEndTime());
                pstudenthistory.setGoTime(oclass.getGoTime());
                pstudenthistory.setClassName(oclass.getClassName());
                pstudenthistory.setClassFid(oclass.getClassFid());
                pstudenthistory.setTeachFid(oclass.getTeachFid());
                pstudenthistory.setAid(oclass.getAid());
                pstudenthistory.setNid(nstudent.getNid());
                pstudenthistories.add(pstudenthistory);
            }
            int i = pstudenthistoryIBaseDao.batchSave(pstudenthistories);
                if (i==nstudentList.size()){
                    Oclass oclass1 = new Oclass();
                    oclass1.setOid(oid);
                    oclass1.setEndflag(2);
                    oclass1.setClassName(oclass.getClassName());
                    oclass1.setAid(oclass.getAid());
                    oclass1.setEid(oclass.getEid());
                    boolean delete = oclassIBaseDao.delete(oclass);
                    if (delete) {
                        int save = oclassIBaseDao.save(oclass1);
                        if (save>0) {
                            return true;
                        }
                        logger.error("删除班级后增加班级失败");
                        return false;
                    }
                    logger.error("删除班级失败");
                    return false;
                }

            logger.error("增加学生历史记录失败");
            return false;
        }
        return false;
    }


}
