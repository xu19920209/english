package com.tuyue.webModules.parameter.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Replymodule;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.parameter.biz.IReplymoduleBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/9.
 * @Modified By:
 */
@Service
@Transactional
public class ReplymoduleBizImpl implements IReplymoduleBiz {
    @Autowired
    private IBaseDao<Replymodule> replymoduleIBaseDao;
    @Override
    public Result add(Replymodule replymodule) throws Exception {
        int i = replymoduleIBaseDao.save(replymodule);
        if (i>0) {
            Replymodule one = replymoduleIBaseDao.getOne(Replymodule.class, i);
            return ResultUtil.success("添加回复常用语添加成功！",one);
        }
        return ResultUtil.error(2,"添加回复常用语添加失败！");
    }

    @Override
    public boolean del(int rid) throws Exception {
        Replymodule replymodule = replymoduleIBaseDao.getOne(Replymodule.class, rid);
        boolean b = replymoduleIBaseDao.delete(replymodule);
        return b;
    }

    @Override
    public Page<Replymodule> list(Integer page,Integer size) throws Exception {
        String hql="from Replymodule";
        String chql="select count (*) from Replymodule";
        Page<Replymodule> replymodulePage = replymoduleIBaseDao.findPage(page, size, hql, chql);
        return replymodulePage;
    }

    @Override
    public Result updata(Replymodule replymodule) throws Exception {
        if(replymodule==null){
            return ResultUtil.error(2,"参数错误！");
        }
        boolean b = replymoduleIBaseDao.update(replymodule);
        if(b){
            return ResultUtil.success(replymodule);
        }
            return ResultUtil.error(2,"修改失败！");
    }
}
