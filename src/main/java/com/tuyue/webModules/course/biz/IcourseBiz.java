package com.tuyue.webModules.course.biz;

import com.tuyue.pojo.Acourse;
import com.tuyue.pojo.Bhour;
import com.tuyue.pojo.CourseLevel;
import com.tuyue.pojo.Ctopic;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.webModules.course.bean.CourseDetilesBean;
import com.tuyue.webModules.course.bean.InCourseBean;

import java.sql.SQLException;
import java.util.List;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
public interface IcourseBiz {
    /**
     * @Author: 徐慷慨
     * @Description:添加课程
       * @param acourse
     * @Date: 9:30 2017/9/6
     */
    public Result insertCourse(Acourse acourse) throws Exception;
  /**
   * @Author: 徐慷慨
   * @Description:删除课程
     * @param aid
   * @Date: 9:32 2017/9/6
   */
    public Result deleteCourse(String aid);

    /**
     * @Author: 徐慷慨
     * @Description:课程列表
     * @Date: 9:32 2017/9/6
     */
    public Result courseList(String aname) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:修改课程
     * @param acourse
     * @Date: 9:30 2017/9/6
     */
    public Result updateCourse(Acourse acourse) throws Exception;

     //*******************************************课程级别模块***********************************************************

    /**
     * 添加课程级别
     * @param courseLevel
     * @return
     */
     public Result inCourseLevel(CourseLevel courseLevel);

    /**
     * 修改课程级别
     * @param courseLevel
     * @return
     * @throws Exception
     */
     public Result upCourseLevel(CourseLevel courseLevel) throws Exception;

    /**
     * 课程级别列表
     * @param courseLevel
     * @return
     * @throws Exception
     */
     public Result courseLevelList(CourseLevel courseLevel) throws Exception;

    /**
     * 删除课程级别
     * @param ids
     * @return
     * @throws Exception
     */
     public Result delCourseLevel(String ids) throws Exception;

    //************************************课时模块**********************************************************************


    /**
     * @Author: 徐慷慨
     * @Description:添加课时
     * @param bhour
     * @Date: 9:30 2017/9/6
     */
    public Result insertBhour(Bhour bhour) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:删除课时
     * @param bid
     * @Date: 9:32 2017/9/6
     */
    public Result deleteBhour(String bid);

    /**
     * @Author: 徐慷慨
     * @Description:课时列表
     * @Date: 9:32 2017/9/6
     */
    public Result bhourList(Integer aid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:修改课时
     * @param bhour
     * @Date: 9:30 2017/9/6
     */
    public Result updateBhour(Bhour bhour) throws Exception;


    //****************************************具体课程内容模块**********************************************************
        /**
         * @Author: 徐慷慨
         * @Description:点击增加时添加数据字典
         * @Date: 10:17 2017/9/6
         */
        public Result insertWord(String csentence) throws Exception;

        /**
         * @Author: 徐慷慨
         * @Description:添加课程详情
         * @param inCourseBean
         * @Date: 10:36 2017/9/6
         */
        public Result insertCtopic(InCourseBean inCourseBean);
        /**
         * @Author: 徐慷慨
         * @Description:根据课时查询课程详情
           * @param cbid
         * @Date: 10:39 2017/9/6
         */
        public Result ctopicList(Integer cbid) throws Exception;
        /**
         * @Author: 徐慷慨
         * @Description:删除课程详情
           * @param cid
         * @Date: 13:49 2017/9/7
         */
        public Result deleteCtopic(String cid);

    /**
     * @Author: 徐慷慨
     * @Description:修改课程详情
     * @param ctopic
     * @Date: 13:57 2017/9/7
     */
    public Result updateCtopic(Ctopic ctopic) throws Exception;

    //*********************************************课程管理模块列表*******************************************************************
    /**
     * @Author: 徐慷慨
     * @Description:课程管理列表
     * @Date: 17:21 2017/9/7
     */
    public Result courseManageList(String aname,Integer currentPage,Integer pageSiz) throws Exception;

    /**
     * 课程详情列表
     * @param leveId
     * @param name
     * @return
     */
    public Result courseDetaisList(int leveId ,String name,Integer currentPage,Integer pageSiz) throws Exception;


    /**
     * @Author: 徐慷慨
     * @Description:所有的课程/课时列表
     * @Date: 13:33 2017/9/9
     */
    public Result courseTreeAll() throws Exception;

}
