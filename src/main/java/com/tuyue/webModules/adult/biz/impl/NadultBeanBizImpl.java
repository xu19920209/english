package com.tuyue.webModules.adult.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Nstudent;
import com.tuyue.pojo.WadultWorkDetails;
import com.tuyue.util.Page;
import com.tuyue.webModules.adult.bean.NadultBean;
import com.tuyue.webModules.adult.biz.INadultBeanBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/14.
 * @Modified By:
 */
@Service
@Transactional
public class NadultBeanBizImpl implements INadultBeanBiz {
    @Autowired
    private IBaseDao<Nstudent> nstudentIBaseDao;
    @Autowired
    private IBaseDao<WadultWorkDetails> wadultWorkDetailsIBaseDao;

    @Override
    public Page<NadultBean> list(Integer currentPage,Integer pageSize) throws Exception {
//        List<NstudentBiz> list = nstudentIBaseDao.findList("from Nstudent where isType=2");
        String hql="from Nstudent where isType=2";
        String chql="SELECT count(*) from Nstudent where isType=2";
        Page<Nstudent> page = nstudentIBaseDao.findPage(currentPage,pageSize,hql,chql);
        ArrayList<NadultBean> nadultBeans = new ArrayList<NadultBean>();
        if(page.getList().size()>0){
            for (Nstudent nstudent : page.getList()) {
                NadultBean nadultBean = new NadultBean();
                nadultBean.setNid(nstudent.getNid());
                nadultBean.setUsername(nstudent.getUsername());
                nadultBean.setPassword(nstudent.getPassword());
                nadultBean.setSex(nstudent.getSex());
                nadultBean.setCreateTime(nstudent.getCreateTime());
                long count = wadultWorkDetailsIBaseDao.findCount("SELECT count(*) FROM WadultWorkDetails where nid=" + nstudent.getNid());
                nadultBean.setNsum(count);
                nadultBeans.add(nadultBean);
            }
        }
        Page<NadultBean> nadultBeanPage = new Page<NadultBean>();
        nadultBeanPage.setList(nadultBeans);
        nadultBeanPage.setTotal(page.getTotal());
        nadultBeanPage.setallPage(page.getAllpage());
        nadultBeanPage.setCurrentPage(page.getCurrentPage());
        nadultBeanPage.setPageSize(page.getPageSize());
        return nadultBeanPage;
    }
}
