package com.tuyue.webModules.common.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Encourage;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/21.
 */
@Service
@Transactional
public class EncourageBiz implements com.tuyue.webModules.common.biz.EncourageBiz{
    @Autowired
    private IBaseDao<Encourage> encourageIBaseDao;
    /**
     * @Author: 徐慷慨
     * @Description:添加鼓励
     * @Date: 9:56 2017/11/21
     */
    @Override
    public Result inEncourage(Encourage encourage) {
        int save = encourageIBaseDao.save(encourage);
        if(save>0){
            return ResultUtil.success("添加成功！");
        }
        return ResultUtil.error(2,"添加失败");
    }
    /**
     * @Author: 徐慷慨
     * @Description:修改鼓励语
     * @Date: 9:58 2017/11/21
     */
    @Override
    public Result upEncourage(Encourage encourage) throws Exception {
        boolean update = encourageIBaseDao.update((Encourage) encourage);
        if(update){
            return ResultUtil.success("修改成功！");
        }
        return ResultUtil.error(2,"修改失败");
    }

    /**
     * @Author: 徐慷慨
     * @Description:删除鼓励语
     * @Date: 9:59 2017/11/21
     */
    public Result delEncourage(String ids){
        int i = encourageIBaseDao.deleteWithHql("delete from Encourage where id in (" + ids + ")");
        if(i>0){
            return ResultUtil.success("删除成功！");
        }else{
            return ResultUtil.error(2,"删除失败");
        }
    }
    /**
     * @Author: 徐慷慨
     * @Description:鼓励语列表
     * @Date: 9:59 2017/11/21
     */
    @Override
    public Result encourageList(Integer currentPage , Integer pageSize, Integer type) throws Exception {
        StringBuffer HQL=new StringBuffer("from Encourage   order by type,id");
        StringBuffer COUNT=new StringBuffer("select count(*) from Encourage  order by type,id");
        if(type>0){
             HQL.append(" type="+type);
             COUNT.append(" type="+type);
        }
       Page<Encourage> page = encourageIBaseDao.findPage(currentPage, pageSize, HQL.toString(), COUNT.toString());
        //List<Encourage> list = encourageIBaseDao.findList("from Encourage");
        return ResultUtil.success("鼓励语列表",page);
    }
}
