package com.tuyue.webModules.login.biz.impl;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Ebranchschool;
import com.tuyue.pojo.Fstaff;
import com.tuyue.pojo.Grole;
import com.tuyue.pojo.Ipeopleandrole;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.webModules.login.bean.StaffLoginBean;
import com.tuyue.webModules.login.biz.ISumLoginBiz;
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
 * @Date: Created by Administrator on 2017/9/8.
 * @Modified By:
 */
@Service
@Transactional
public class SumLoginBizImpl implements ISumLoginBiz {
    @Autowired
    private IBaseDao<Fstaff> fstaffIBaseDao;
    @Autowired
    private IBaseDao<Ebranchschool> ebranchschoolIBaseDao;
    @Autowired
    private IBaseDao<Ipeopleandrole> ipeopleandroleIBaseDao;
    @Autowired
    private IBaseDao<Grole> groleIBaseDao;

    private final static Logger logger = LoggerFactory.getLogger(SumLoginBizImpl.class);
    @Override
    public Result login( String name, String password) throws Exception {
        Fstaff one = fstaffIBaseDao.findOne("from Fstaff where username='" + name+"'");
        logger.error("name = "+name+" password = "+password +" one = "+one);
        if (one == null) {
            return ResultUtil.error(2, "用户名不存在");
        }
        if (one.getPassword().equals(password)) {
            ArrayList<Grole> groles = new ArrayList<Grole>();
            //根据员工ID查询校区信息
            Ebranchschool ebranchschool = ebranchschoolIBaseDao.getOne(Ebranchschool.class, one.getEid());
            //根据员工ID查询员工对应的员工角色中间表list
            List<Ipeopleandrole> ipeopleandroleList = ipeopleandroleIBaseDao.findList("from Ipeopleandrole where fid=" + one.getFid());
            for (Ipeopleandrole ipeopleandrole : ipeopleandroleList) {
                //根据中间表角色ID查询对应的角色对象
                Grole grole = groleIBaseDao.getOne(Grole.class, ipeopleandrole.getGid());
                groles.add(grole);
            }
            StaffLoginBean loginBean = new StaffLoginBean();
            loginBean.setFid(one.getFid());
            loginBean.setFname(one.getName());
            loginBean.setEid(ebranchschool != null ? ebranchschool.getEid() : 0);
            loginBean.setEname(ebranchschool != null ? ebranchschool.getName() : "");
            loginBean.setGroles(groles);
            return ResultUtil.success(loginBean);
        }
        return ResultUtil.error(2, "密码错误");
    }
}
