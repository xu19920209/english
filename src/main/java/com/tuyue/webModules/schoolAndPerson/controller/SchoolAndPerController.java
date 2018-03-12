package com.tuyue.webModules.schoolAndPerson.controller;

import com.tuyue.pojo.Ebranchschool;
import com.tuyue.pojo.Grole;
import com.tuyue.pojo.Hresource;
import com.tuyue.pojo.Jroleandresource;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.webModules.schoolAndPerson.bean.InsertSchoolBean;
import com.tuyue.webModules.schoolAndPerson.bean.PersonZiYuanBean;
import com.tuyue.webModules.schoolAndPerson.bean.PersonnelBean;
import com.tuyue.webModules.schoolAndPerson.biz.IschoolBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/5.
 */
@Controller
@ResponseBody
@RequestMapping("main")
public class SchoolAndPerController {
    private final static Logger logger = LoggerFactory.getLogger(SchoolAndPerController.class);

    @Autowired
    private IschoolBiz biz;


    /**
     * @param insertSchoolBean
     * @Author: 徐慷慨
     * @Description:修改校区/ 添加校区
     * @Date: 15:50 2017/9/5
     */
    @RequestMapping("updateSchool.do")
    public Result updateSchool(@RequestBody InsertSchoolBean insertSchoolBean) throws Exception {
        logger.error("修改校区/ 添加校区");

        Ebranchschool ebranchschool = new Ebranchschool(insertSchoolBean.getFid(),insertSchoolBean.getEid(), insertSchoolBean.getName(), insertSchoolBean.getProvince(), insertSchoolBean.getCity(), insertSchoolBean.getDistricts(), insertSchoolBean.getAddress(), insertSchoolBean.getFixationTel(), insertSchoolBean.getPrincipalName(), insertSchoolBean.getPrincipalTel(), insertSchoolBean.getPrincipalEmail(), insertSchoolBean.getPrincipalWeiXin(), insertSchoolBean.getPrincipalidNo(), insertSchoolBean.getLegalPersonName(), insertSchoolBean.getLegalPersonidNo(), insertSchoolBean.getLegalPersonFront(), insertSchoolBean.getLegalPersonVerso(), insertSchoolBean.getSchoolImg(),insertSchoolBean.getSchoolType());

        if (ebranchschool.getEid() > 0) {
            return biz.updateSchool(ebranchschool);
        } else {
            if(biz.mateUsername(insertSchoolBean.getUsername())){
                return biz.insertSchool(ebranchschool, insertSchoolBean.getUsername());
            }else{
                return ResultUtil.error(2,"账号已被使用");
            }
        }

    }

    /**
     * @Author: 徐慷慨
     * @Description:删除校区
     * @Date: 10:17 2017/9/5
     */
    @RequestMapping("deleteSchool.do")
    public Result deleteSchool(String eid) throws Exception {
        logger.error("删除校区");
        if(eid==null){
            return ResultUtil.error(2,"参数(eid)不能为空");
        }
        return biz.deleteSchool(eid);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 根据id查找学校信息
     * @Date: 10:31 2017/9/5
     */
    @RequestMapping("selectSchoolById.do")
    public Result selectSchoolById(Integer eid) throws Exception {
        logger.error("根据id查找学校信息");
        if(eid==null){
            return ResultUtil.error(2,"EID不能为空！");
        }
        return biz.selectSchoolById(eid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:分学校列表
     * @Date: 18:30 2017/9/5
     */
    @RequestMapping("schoolList.do")
    public Result schoolList(String name, Integer currentPage, Integer pageSize) throws Exception {
        logger.error("分学校列表");
        if (currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        if(name!=null){
            //new System(name.getBytes())
            name = new String(name.getBytes("iso8859-1"),"utf-8");
        }
        return biz.schoolList(name, currentPage, pageSize);
    }
    /**
     * @Author: 徐慷慨
     * @Description:判断账号是否已被使用
     * @Date: 8:46 2017/9/7
     */
@RequestMapping("mateUsername.do")
    public Result mateUsername(String username) throws Exception{
    logger.error("判断账号是否已被使用");
        boolean b = biz.mateUsername(username);
        if(b){
            return ResultUtil.success("可以使用！");
        }else{
            return ResultUtil.error(2,"不可以使用");
        }
    }



    /**
     * @Author: 徐慷慨
     * @Description: 给分校分配课程
     * @param path
     * @Date: 14:48 2017/9/9
     */
    @RequestMapping("allotCourse.do")
    public Result allotCourse(String path,Integer eid) throws Exception{
        logger.error("给分校分配课程");
        if(path==null||eid==null){
            return ResultUtil.error(2,"参数不能为空！");
        }
        return biz.allotCourse(path,eid);
    }







    //::::::::::::::::::::::::::::::::::::::::::员工:::::::::::::::::::::::::::::::::::::::::::::::::::::::::



    /**
     * @Author: 徐慷慨
     * @Description:根据id查员工信息
     * @Date: 11:47 2017/9/5
     */
    @RequestMapping("selectPersionById.do")
    public Result selectPersionById(Integer fid) throws Exception {
        logger.error("根据id查员工信息");
        if(fid==null){
            return ResultUtil.error(2,"FID不能为空！");
        }else{
            return biz.selectPersionById(fid);
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:删除员工
     * @Date: 10:17 2017/9/5
     */
    @RequestMapping("deletePersion.do")
    public Result deletePersion(String fid) {
        logger.error("删除员工");
        if(fid==null){
        return ResultUtil.error(2,"参数不能为空！");
        }else{
            return biz.deletePersion(fid);
        }

    }

    /**
     * @Author: 徐慷慨
     * @Description:修改员工信息/添加员工信息
     * @Date: 13:36 2017/9/5
     */
    @RequestMapping("updatePersion.do")
    public Result updatePersion(@RequestBody PersonnelBean personnelBean) throws Exception {
        logger.error("修改员工信息/添加员工信息");
        if(personnelBean.getFid()>0){
            return biz.updatePersion(personnelBean);
        }else{
            return biz.insertPerson(personnelBean);
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:给员工添加角色
     * @Date: 14:30 2017/9/5
     */
    @RequestMapping("insertPerRole.do")
    public Result insertPerRole(Integer fid, String gid) throws Exception {
        logger.error("给员工添加角色");
        return biz.insertPerRole(fid, gid);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 查询员工下的资源
     * @Date: 15:18 2017/9/5
     */
    @RequestMapping("perResourceList.do")
    public Result perResourceList(String gid) throws Exception {
        logger.error("查询员工下的资源");
        if(gid==null){
            return ResultUtil.error(2,"你还没有角色权限！");
        }else{
            if(gid.endsWith(",")){
                gid=gid.substring(0,gid.length()-1);
            }
            List<PersonZiYuanBean> list = biz.perResourceList(gid);
            Map map =new HashMap();
            map.put("list",list);
            return ResultUtil.success("查询员工下的资源",map);
        }

    }

    /**
     * @param eid
     * @Author: 徐慷慨
     * @Description:查询某个学校下所有员工
     * @Date: 18:34 2017/9/5
     */
    @RequestMapping("perBySchoolList.do")
    public Result perBySchoolList(Integer eid,Integer gid,String name,Integer currentPage,Integer pageSize) throws Exception {
        if(eid==null){
            return ResultUtil.error(2,"学校id不能为空！");
        }
        if (currentPage == null) {
            currentPage = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
//        if(name!=null){
//            name = new String(name.getBytes("iso8859-1"),"utf-8");
//        }
        return biz.perBySchoolList(eid,gid,name,currentPage,pageSize);
    }

    //::::::::::::::::::::::::::::::::::::::::::::角色::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


    /**
     * @Author: 徐慷慨
     * @Description:修改角色/添加角色
     * @Date: 14:04 2017/9/5
     */
    @RequestMapping("updateRole.do")
    public Result updateRole(@RequestBody  Grole grole) throws Exception {
        logger.error("修改角色/添加角色");
        if(grole.getGid()>0){
            return biz.updateRole(grole);
        }else{
            return biz.insertRole(grole);
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:删除角色
     * @Date: 14:05 2017/9/5
     */
    @RequestMapping("deleteRole.do")
    public Result deleteRole(String gid) throws Exception {
        logger.error("删除角色");
        if(gid==null){
            return  ResultUtil.error(2,"参数不能为空！");
        }
        return biz.deleteRole(gid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:角色列表
     * @Date: 14:06 2017/9/5
     */
    @RequestMapping("rolerList.do")
    public Result rolerList(String role,Integer eid,Integer currenPage,Integer pageSize) throws Exception {
        if(currenPage==null||currenPage<=0){
            currenPage=1;
        }
        if(pageSize==null||pageSize<=0){
            pageSize=10;
        }
        logger.error("角色列表");
        return biz.rolerList(eid,currenPage,pageSize,role);
    }

    /**
     * @Author: 徐慷慨
     * @Description:给角色添加资源
     * @Date: 14:42 2017/9/5
     */
    @RequestMapping("inRoleResour.do")
    public Result inRoleResour(Integer gid, String hid) throws Exception {
        logger.error("给角色添加资源");
        if(gid==null||hid==null){
            return ResultUtil.error(2,"参数不能为空！");
        }
        return biz.inRoleResour(gid, hid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:修改角色资源
     * @Date: 14:43 2017/9/5
     */
    @RequestMapping("upRoleResour.do")
    public Result upRoleResour(Jroleandresource jroleandresource) throws Exception {
        logger.error("修改角色资源");
        return biz.upRoleResour(jroleandresource);
    }


    //::::::::::::::::::::::::::::::::::::::::::::::::资源管理::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


    /**
     * @Author: 徐慷慨
     * @Description:添加资源管理
     * @Date: 14:01 2017/9/5
     */
    @RequestMapping("insertResource.do")
    public Result insertResource(Hresource hresource) {
        logger.error("添加资源管理");
        return biz.insertResource(hresource);
    }


    /**
     * @Author: 徐慷慨
     * @Description:修改资源管理
     * @Date: 14:04 2017/9/5
     */
    @RequestMapping("updateResource.do")
    public Result updateResource(@RequestBody Hresource hresource) throws Exception {
        logger.error("修改资源管理");
        return biz.updateResource(hresource);
    }

    /**
     * @Author: 徐慷慨
     * @Description:删除资源管理
     * @Date: 14:05 2017/9/5
     */
    @RequestMapping("deleteResource.do")
    public Result deleteResource(String hid) {
        logger.error("删除资源管理");
        return biz.deleteResource(hid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:资源管理列表
     * @Date: 14:06 2017/9/5
     */
    @RequestMapping("resourceList.do")
    public Result resourceList(Integer eid) throws Exception {
        logger.error("资源管理列表");
        if(eid==null){
            return ResultUtil.error(2,"参数不能为空！");
        }
        return biz.resourceList(eid);
    }
}
