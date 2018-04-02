package com.tuyue.webModules.course.controller;

import com.tuyue.pojo.Acourse;
import com.tuyue.pojo.Bhour;
import com.tuyue.pojo.CourseLevel;
import com.tuyue.pojo.Ctopic;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Tools;
import com.tuyue.webModules.course.bean.CourseDetilesBean;
import com.tuyue.webModules.course.bean.InCourseBean;
import com.tuyue.webModules.course.biz.IcourseBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
@Controller
@ResponseBody
@RequestMapping("course")
public class CourseController {
    private final static Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private IcourseBiz biz;

    /**
     * @param aid
     * @Author: 徐慷慨
     * @Description:删除课程
     * @Date: 9:32 2017/9/6
     */
    @RequestMapping("deleteCourse.do")
    public Result deleteCourse(String aid) {
        logger.error("删除课程");
        if(aid==null){
            return ResultUtil.error(2,"aid不能为空");
        }
        return biz.deleteCourse(aid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:课程列表
     * @Date: 9:32 2017/9/6
     */
    @RequestMapping("courseList.do")
    public Result courseList(String aname) throws Exception {
        logger.error("课程列表");
        if(aname!=null){
            aname = new String(aname.getBytes("iso8859-1"),"utf-8");
        }

        return biz.courseList(aname);
    }

    /**
     * @param acourse
     * @Author: 徐慷慨
     * @Description:修改课程/添加课程
     * @Date: 9:30 2017/9/6
     */
    @RequestMapping("updateCourse.do")
    public Result updateCourse(@RequestBody Acourse acourse) throws Exception {
        logger.error("修改课程/添加课程");
        if(acourse.getAname()==null){
            return ResultUtil.error(2,"参数不能为空！");
        }
        if (acourse.getAid() > 0) {
            return biz.updateCourse(acourse);
        } else {
            return biz.insertCourse(acourse);
        }

    }


    //************************************课时模块**********************************************************************

    /**
     * @param bid
     * @Author: 徐慷慨
     * @Description:删除课时
     * @Date: 9:32 2017/9/6
     */
    @RequestMapping("deleteBhour.do")
    public Result deleteBhour(String bid) {
        logger.error("删除课时");
        if (Tools.isEmpty(bid)) {
            return ResultUtil.error(2, "bid不能为空");
        }
        return biz.deleteBhour(bid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:课时列表
     * @Date: 9:32 2017/9/6
     */
    @RequestMapping("hourList.do")
    public Result bhourList(@RequestParam(required = true) Integer levelId) throws Exception {
        logger.error("课时列表");
        return biz.bhourList(levelId);
    }

    /**
     * @param bhour
     * @Author: 徐慷慨
     * @Description:修改课时
     * @Date: 9:30 2017/9/6
     */
    @RequestMapping("updateBhour.do")
    public Result updateBhour(@RequestBody Bhour bhour) throws Exception {
        logger.error("修改课时");
        if(bhour.getLevelId()==null||bhour.getLevelId()<=0){
            return ResultUtil.error(2,"levelId不能为空！");
        }
        if(bhour.getBid()==0&&bhour.getBname()==null){
            return ResultUtil.error(2,"参数不能为空！");
        }
        if (bhour.getBid() > 0) {
            return biz.updateBhour(bhour);
        } else {
            return biz.insertBhour(bhour);
        }

    }
    //*******************************************课程级别模块***********************************************************

    /**
     * 添加课程级别
     * @param courseLevel
     * @return
     */
    @RequestMapping("inCourseLevel")
    public Result inCourseLevel(@RequestBody CourseLevel courseLevel){
        if(courseLevel.getAid()==null){
            return ResultUtil.error(2,"课程id不能为空");
        }
        return biz.inCourseLevel(courseLevel);
    }

    /**
     * 修改课程级别
     * @param courseLevel
     * @return
     * @throws Exception
     */
    @RequestMapping("upCourseLevel")
    public Result upCourseLevel(@RequestBody CourseLevel courseLevel) throws Exception{
        return biz.upCourseLevel(courseLevel);
    }

    /**
     * 课程级别列表
     * @param courseLevel
     * @return
     * @throws Exception
     */
    @RequestMapping("courseLevelList")
    public Result courseLevelList(CourseLevel courseLevel) throws Exception{
        return biz.courseLevelList(courseLevel);
    }

    /**
     * 删除课程级别
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("delCourseLevel")
    public Result delCourseLevel(@RequestParam(required = true) String ids) throws Exception{
        return biz.delCourseLevel(ids);

    }

    //****************************************具体课程内容模块**********************************************************

    /**
     * @Author: 徐慷慨
     * @Description:点击增加时添加数据字典
     * @Date: 10:17 2017/9/6
     */
    @RequestMapping("insertWord.do")
    public Result insertWord(String csentence) throws Exception {
        logger.error("点击增加时添加数据字典");
        if(csentence==null){
            return ResultUtil.error(2,"请传入参数！");
        }
        return biz.insertWord(csentence);
    }

    /**
     * @param inCourseBean
     * @Author: 徐慷慨
     * @Description:添加课程详情
     * @Date: 10:36 2017/9/6
     */
    @RequestMapping("insertCtopic.do")
    public Result insertCtopic(@RequestBody InCourseBean inCourseBean) {
        logger.error("添加课程详情");
        System.out.println(inCourseBean.toString());
        return biz.insertCtopic(inCourseBean);
    }

    /**
     * @param bid
     * @Author: 徐慷慨
     * @Description:根据课时查询课程详情
     * @Date: 10:39 2017/9/6
     */
    @RequestMapping("ctopicList.do")
    public Result ctopicList(Integer bid) throws Exception {
        logger.error("根据课时查询课程详情");
        if(bid==null){
            return ResultUtil.error(2,"参数（bid）不能为空！");
        }else{
            return biz.ctopicList(bid);
        }
    }

    /**
     * @param cid
     * @Author: 徐慷慨
     * @Description:删除课程详情
     * @Date: 13:49 2017/9/7
     */
    @RequestMapping("deleteCtopic.do")
    public Result deleteCtopic(String cid) {
        logger.error("删除课程详情");
        if (cid == null) {
            return ResultUtil.error(2, "请传入参数！");
        } else {
            return biz.deleteCtopic(cid);
        }
    }
    /**
     * @Author: 徐慷慨
     * @Description:修改课程详情
       * @param ctopic
     * @Date: 13:57 2017/9/7
     */
    @RequestMapping("updateCtopic.do")
    public Result updateCtopic(@RequestBody Ctopic ctopic) throws Exception {
        logger.error("修改课程详情");
        return biz.updateCtopic(ctopic);
    }


    //*********************************************课程管理模块列表*******************************************************************
    /**
     * @Author: 徐慷慨
     * @Description:课程管理列表
     * @Date: 17:21 2017/9/7
     */
    @RequestMapping("courseManageList.do")
    public Result courseManageList(String aname,@RequestParam(defaultValue ="1") Integer currentPage,@RequestParam(defaultValue ="10")Integer pageSize) throws Exception{
//        logger.error("课程管理列表");
//        if(aname!=null){
//            aname = new String(aname.getBytes("iso8859-1"),"utf-8");
//        }
        return biz.courseManageList(aname,currentPage,pageSize);
    }

    /**
     * 课程详情列表
     * @param levelId
     * @param name
     * @return
     */
    @RequestMapping("courseDetailsList")
    public Result courseDetaisList(@RequestParam(required = true) int levelId ,String name,@RequestParam(defaultValue ="1") Integer currentPage,@RequestParam(defaultValue ="10")Integer pageSiz) throws Exception{
        return biz.courseDetaisList(levelId,name,currentPage,pageSiz);
    }
    /**
     * @Author: 徐慷慨
     * @Description: 所有的课程/课时列表
     * @Date: 13:33 2017/9/9
     */
    @RequestMapping("courseTreeAll.do")
    public Result courseTreeAll() throws Exception{
        logger.error("所有的课程/课时列表");
        return biz.courseTreeAll();
    }

}
