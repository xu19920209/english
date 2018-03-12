package com.tuyue.webModules.studentAndclass.biz.impl;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Fstaff;
import com.tuyue.pojo.Nstudent;
import com.tuyue.pojo.Oclass;
import com.tuyue.pojo.Pstudenthistory;
import com.tuyue.util.ObjectCopyUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.studentAndclass.bean.ClassBean;
import com.tuyue.webModules.studentAndclass.bean.PstudenthistoryBean;
import com.tuyue.webModules.studentAndclass.bean.StudentAndClassBean;
import com.tuyue.webModules.studentAndclass.biz.INstudentBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/11.
 * @Modified By:
 */
@Service
@Transactional
public class NstudentBizImpl implements INstudentBiz {
    @Autowired
    private IBaseDao<Nstudent> nstudentIBaseDao;
    @Autowired
    private IBaseDao<Oclass> oclassIBaseDao;

    @Autowired
    private IBaseDao<Pstudenthistory> pstudenthistoryIBaseDao;

    @Autowired
    private IBaseDao<Fstaff> fstaffIBaseDao;
    @Override
    public Nstudent updata(Nstudent nstudent) throws Exception {
        nstudent.setIsType(1);

        boolean b = nstudentIBaseDao.update(nstudent);
        if (b){
            return nstudent;
        }
        return null;
    }

    @Override
    public boolean add(Nstudent nstudent) {
        nstudent.setIsType(1);
        nstudent.setCreateTime(new Timestamp(System.currentTimeMillis()));
        int i = nstudentIBaseDao.save(nstudent);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public Page<StudentAndClassBean> list(String studentName, Integer oid , Integer eid, Integer currentPage, Integer pageSize) throws Exception {
        String hql = "from Nstudent Where eid=" + eid;
        StringBuffer sb = new StringBuffer(hql);
        if (studentName != null) {
            sb.append(" and studentName like '%" + studentName + "%'");
        }
        if (oid != null) {
            sb.append(" and oid=" + oid);
        }
        String chql = "SELECT COUNT(*) FROM Nstudent where eid=" + eid;
        StringBuffer csb = new StringBuffer(chql);
        if (studentName != null) {
            csb.append(" and studentName like '%" + studentName + "%'");
        }
        if (oid != null) {
            csb.append(" and oid=" + oid);
        }
        Page<Nstudent> nstudentPage = nstudentIBaseDao.findPage(currentPage, pageSize, sb.toString(), csb.toString());
        Page<StudentAndClassBean> studentAndClassBeanPage = new Page<StudentAndClassBean>();
        ArrayList<StudentAndClassBean> studentAndClassBeans = new ArrayList<StudentAndClassBean>();
        if (nstudentPage != null && nstudentPage.getList()!=null && nstudentPage.getList().size()>0) {
        for (Nstudent nstudent : nstudentPage.getList()) {
            StudentAndClassBean studentAndClassBean = ObjectCopyUtil.copy(nstudent, Nstudent.class, StudentAndClassBean.class);
            long count = nstudentIBaseDao.findCount("SELECT COUNT(*) FROM Nstudent where oid=" + oid);
            ClassBean classBean = new ClassBean();
            classBean.setNum((int) count);
            if (nstudent.getOid() != null) {
                Oclass oclass = oclassIBaseDao.getOne(Oclass.class, nstudent.getOid());
                if (oclass != null) {
                    classBean = ObjectCopyUtil.copy(oclass, Oclass.class, ClassBean.class);
                    if (oclass.getClassFid() != null) {
                        Fstaff fstaff = fstaffIBaseDao.getOne(Fstaff.class, oclass.getClassFid());
                        if (fstaff != null) {
                            classBean.setClassFname(fstaff.getName());
                        }
                    }
                    if (oclass.getTeachFid() != null) {
                        Fstaff fstaff = fstaffIBaseDao.getOne(Fstaff.class, oclass.getTeachFid());
                        if (fstaff != null) {
                            classBean.setTeachFname(fstaff.getName());
                        }
                    }
                }
            }
            ArrayList<PstudenthistoryBean> pstudenthistoryBeans = new ArrayList<PstudenthistoryBean>();
            List<Pstudenthistory> pstudenthistorys = pstudenthistoryIBaseDao.findList("from Pstudenthistory where nid=" + nstudent.getNid());
            for (Pstudenthistory pstudenthistory : pstudenthistorys) {
                PstudenthistoryBean pstudenthistoryBean = ObjectCopyUtil.copy(pstudenthistory, Pstudenthistory.class, PstudenthistoryBean.class);
                if (pstudenthistory.getClassFid() != null) {
                    Fstaff fstaff = fstaffIBaseDao.getOne(Fstaff.class, pstudenthistory.getClassFid());
                    if (fstaff != null) {
                        pstudenthistoryBean.setClassFname(fstaff.getName());
                    }
                }
                if (pstudenthistory.getTeachFid() != null) {
                    Fstaff fstaff = fstaffIBaseDao.getOne(Fstaff.class, pstudenthistory.getTeachFid());
                    if (fstaff != null) {
                        pstudenthistoryBean.setTeachFname(fstaff.getName());
                    }
                }
                pstudenthistoryBeans.add(pstudenthistoryBean);
            }
            studentAndClassBean.setClassBean(classBean);
            studentAndClassBean.setPstudenthistoryBeans(pstudenthistoryBeans);
            studentAndClassBean.setApplyTime(nstudent.getApplyTime());
            studentAndClassBeans.add(studentAndClassBean);
        }
        studentAndClassBeanPage.setList(studentAndClassBeans);
        studentAndClassBeanPage.setallPage(nstudentPage.getAllpage());
        studentAndClassBeanPage.setPageSize(nstudentPage.getPageSize());
        studentAndClassBeanPage.setTotal(nstudentPage.getTotal());
        studentAndClassBeanPage.setCurrentPage(nstudentPage.getCurrentPage());
        }
        return studentAndClassBeanPage;
    }

    @Override
    public boolean del(Integer nid) throws Exception {
        Nstudent nstudent = nstudentIBaseDao.getOne(Nstudent.class, nid);
        boolean b = nstudentIBaseDao.delete(nstudent);
        return b;
    }
}
