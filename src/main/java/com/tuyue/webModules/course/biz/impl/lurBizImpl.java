package com.tuyue.webModules.course.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Ctopic;
import com.tuyue.webModules.course.biz.IlurBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */
@Service
@Transactional
public class lurBizImpl implements IlurBiz {
    @Autowired
    private IBaseDao<Ctopic> ctopicIBaseDao;
    @Autowired
    private IBaseDao<Deprecated > deprecatedIBaseDao;
    @Override
    public boolean daoru() throws Exception {
        List<Ctopic> ctopicList = ctopicIBaseDao.findList("from Ctopic");
        for (Ctopic ctopic : ctopicList) {
            String csentence = ctopic.getCsentence();

        }

        return false;
    }
}
