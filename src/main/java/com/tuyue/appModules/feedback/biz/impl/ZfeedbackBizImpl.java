package com.tuyue.appModules.feedback.biz.impl;

import com.tuyue.appModules.feedback.biz.IZfeedbackBiz;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Zfeedback;
import com.tuyue.util.Dateutil;
import com.tuyue.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static java.lang.System.out;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/15.
 * @Modified By:
 */
@Service
@Transactional
public class ZfeedbackBizImpl implements IZfeedbackBiz {
    @Autowired
    private IBaseDao<Zfeedback> zfeedbackIBaseDao;

    @Override
    public boolean add(Zfeedback zfeedback) {
        zfeedback.setFlag(2);
        zfeedback.setZtime(Dateutil.DateToTime(new Date()));
        int i = zfeedbackIBaseDao.save(zfeedback);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Page<Zfeedback> list(Integer currentPage, Integer pageSize) throws Exception {
        String hql = "from Zfeedback where flag=2 ORDER BY ztime DESC";
        List<Zfeedback> list = zfeedbackIBaseDao.findList(hql);
        for (Zfeedback zfeedback : list) {
            out.println(zfeedback);
        }
        String chql = "SELECT count(*) from Zfeedback where flag=2";
        Page<Zfeedback> zfeedbackPage = zfeedbackIBaseDao.findPage(currentPage, pageSize, hql, chql);
        return zfeedbackPage;
    }

    @Override
    public boolean del(Integer zid) throws Exception {
        Zfeedback zfeedback = zfeedbackIBaseDao.getOne(Zfeedback.class, zid);
        zfeedback.setFlag(1);
        boolean b = zfeedbackIBaseDao.update(zfeedback);
        return b;
    }
}
