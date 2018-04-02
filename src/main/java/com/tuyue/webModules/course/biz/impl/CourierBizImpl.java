package com.tuyue.webModules.course.biz.impl;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;
import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.*;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import com.tuyue.util.Tools;
import com.tuyue.webModules.course.bean.CourseDetilesBean;
import com.tuyue.webModules.course.bean.CourseTreeBean;
import com.tuyue.webModules.course.bean.InCourseBean;
import com.tuyue.webModules.course.biz.IcourseBiz;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.validation.Validator;
import java.sql.SQLException;
import java.util.*;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
@Service
@Transactional
public class CourierBizImpl implements IcourseBiz {
    @Autowired
    private IBaseDao<Acourse> adao;
    @Autowired
    private IBaseDao<Bhour> bdao;
    @Autowired
    private IBaseDao<Ctopic> cdao;
    @Autowired
    private IBaseDao<Dictionaries> ddao;
    @Autowired
    private IBaseDao<CourseLevel> courseLevelIBaseDao;


    /**
     * @param acourse
     * @Author: 徐慷慨
     * @Description:添加课程
     * @Date: 9:30 2017/9/6
     */
    @Override
    public Result insertCourse(Acourse acourse) throws Exception {

        Acourse adaoOne = adao.findOne(" from Acourse where aname='" + acourse.getAname() + "' and isDel=2");
        if (adaoOne != null) {
            return ResultUtil.error(2, "课程已存在！");
        }
        Acourse acourse1 = new Acourse();
        acourse1.setAname(acourse.getAname());
        acourse1.setIsDel(2);
        int i = adao.save(acourse1);
        Acourse one = adao.getOne(Acourse.class, i);
        if (i > 0) {
            return ResultUtil.success("添加课程成功！", one);
        } else {
            return ResultUtil.error(2, "添加课程失败！");
        }
    }

    /**
     * @param aid
     * @Author: 徐慷慨
     * @Description:删除课程
     * @Date: 9:32 2017/9/6
     */
    @Override
    public Result deleteCourse(String aid) {
        if (aid.endsWith(",")) {
            aid = aid.substring(0, aid.length() - 1);
        }
        try {
            List<CourseLevel> list = courseLevelIBaseDao.findList(" from CourseLevel where aid in (" + aid + ") and isDel=2");
            if (list != null&&list.size()>0) {
                return ResultUtil.error(2, "该课程下面有课程级别，请先删除课程级别");
            } else {
                int i = adao.deleteWithHql("update  Acourse set isDel=1 where aid in (" + aid + ")");
                return ResultUtil.success("删除成功！");
            }
        } catch (Exception e) {
            return ResultUtil.error(2, "删除失败！");
        }

    }

    /**
     * @Author: 徐慷慨
     * @Description:课程列表
     * @Date: 9:32 2017/9/6
     */
    @Override
    public Result courseList(String aname) throws Exception {
        String hql = "";
        if (aname != null) {
            hql = "from Acourse where aname like('%" + aname + "%') and isDel=2";
        } else {
            hql = "from Acourse where isDel=2";
        }
        List<Acourse> list = adao.findList(hql);

        return ResultUtil.success("课程列表", list);
    }

    /**
     * @param acourse
     * @Author: 徐慷慨
     * @Description:修改课程
     * @Date: 9:30 2017/9/6
     */
    @Override
    public Result updateCourse(Acourse acourse) throws Exception {
        Acourse adaoOne = adao.findOne(" from Acourse where aname='" + acourse.getAname() + "'");
        if (adaoOne != null) {
            return ResultUtil.error(2, "课程已存在！");
        }
        Acourse one = adao.getOne(Acourse.class, acourse.getAid());
        if (one != null) {
            Session sessions = adao.getSessions();
            try {
                sessions.evict(one);
//                sessions.evict(adaoOne);
            } catch (Exception e) {
                e.printStackTrace();
            }
            acourse.setCreatTime(one.getCreatTime());
            acourse.setIsDel(2);
            boolean update = adao.update(acourse);
            if (update) {
                return ResultUtil.success("修改成功！");
            } else {
                return ResultUtil.error(2, "修改失败！");
            }
        } else {
            return ResultUtil.error(2, "参数错误！");
        }
    }


    //*******************************************课程结构模块***********************************************************
    @Override
    public Result inCourseLevel(CourseLevel courseLevel) {
        courseLevel.setIsDel(2);
        int save = courseLevelIBaseDao.save(courseLevel);
        if (save > 0) {
            return ResultUtil.success("添加成功");
        }
        return ResultUtil.error(2, "添加失败");
    }

    @Override
    public Result upCourseLevel(CourseLevel courseLevel) throws Exception {
        courseLevel.setIsDel(2);
        boolean update = courseLevelIBaseDao.update(courseLevel);
        if (update) {
            return ResultUtil.success("修改成功");
        }
        return ResultUtil.error(2, "修改失败");
    }

    @Override
    public Result courseLevelList(CourseLevel courseLevel) throws Exception {
        StringBuffer stringBuffer = new StringBuffer("from CourseLevel where isDel=2");
        if (courseLevel.getAid() != null) {
            stringBuffer.append(" and aid=" + courseLevel.getAid());
        }
        if (courseLevel.getLevelName() != null) {
            stringBuffer.append(" and levelName='" + courseLevel.getLevelName() + "'");
        }
        List<CourseLevel> list = courseLevelIBaseDao.findList(stringBuffer.toString());
        return ResultUtil.success("查询成功", list);
    }

    @Override
    public Result delCourseLevel(String ids) throws Exception {
        if (ids.endsWith(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        List<Bhour> bdaoList = bdao.findList("from Bhour where levelId in (" + ids + ") and isDel=2");
        if (bdaoList != null&&bdaoList.size()>0) {
            return ResultUtil.error(2, "改课程级别下有课时，请先删除课时");
        }
        int i = courseLevelIBaseDao.deleteWithHql("update  CourseLevel set isDel=1 where levelId in (" + ids + ")");
        if (i > 0) {
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.error(2, "删除失败");
    }
    //************************************课时模块**********************************************************************


    /**
     * @param bhour
     * @Author: 徐慷慨
     * @Description:添加课时
     * @Date: 9:30 2017/9/6
     */
    @Override
    public Result insertBhour(Bhour bhour) throws Exception {
        bhour.setIsDel(2);
        Bhour bdaoOne = bdao.findOne("from Bhour where levelId=" + bhour.getLevelId() + " and bname='" + bhour.getBname() + "' and isDel=2");
        if(bdaoOne==null){
            int i = bdao.save(bhour);
            if (i > 0) {
                Bhour bdaoOne1 = bdao.findOne("from Bhour where levelId=" + bhour.getLevelId() + " and bname='" + bhour.getBname() + "' and isDel=2");
                return ResultUtil.success("添加课时成功！",bdaoOne1);
            } else {
                return ResultUtil.error(2, "添加课时失败！");
            }
        }else{
            return ResultUtil.success("添加课时成功！",bdaoOne);
        }
    }

    /**
     * @param bid
     * @Author: 徐慷慨
     * @Description:删除课时
     * @Date: 9:32 2017/9/6
     */
    @Override
    public Result deleteBhour(String bid) {
        if (bid.endsWith(",")) {
            bid = bid.substring(0, bid.length() - 1);
        }
        try {
            List<Ctopic> list = cdao.findList(" from Ctopic where bid  in (" + bid + ") and isDel=2");
            if (list.size() > 0) {
                return ResultUtil.error(2, "该课时下面有课程详情，不能删除！");
            } else {
                int i = adao.deleteWithHql("update  Bhour set isDel=1 where bid in (" + bid + ")");
                return ResultUtil.success("删除成功！");
            }
        } catch (Exception e) {
            return ResultUtil.error(2, "删除失败！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:课时列表
     * @Date: 9:32 2017/9/6
     */
    @Override
    public Result bhourList(Integer aid) throws Exception {
        List<Bhour> list = bdao.findList("from Bhour where levelId=" + aid+" and isDel=2");
        return ResultUtil.success("课时列表", list);
    }

    /**
     * @param bhour
     * @Author: 徐慷慨
     * @Description:修改课时
     * @Date: 9:30 2017/9/6
     */
    @Override
    public Result updateBhour(Bhour bhour) throws Exception {
        Bhour one = bdao.getOne(Bhour.class, bhour.getBid());
        if (one != null) {
            bdao.getSessions().evict(one);
            bhour.setIsDel(2);
            boolean update = bdao.update(bhour);
            if (update) {
                return ResultUtil.success("修改成功！");
            } else {
                return ResultUtil.error(2, "修改失败！");
            }
        } else {
            return ResultUtil.error(2, "参数错误！");
        }
    }


    //****************************************具体课程内容模块**********************************************************

    /**
     * @Author: 徐慷慨
     * @Description:点击增加时添加数据字典
     * @Date: 10:17 2017/9/6
     */
    @Override
    public Result insertWord(String csentence) throws Exception {
        List<String> set = Tools.zhuhuan(csentence);
        List<Dictionaries> list = ddao.findList("select LOWER(dword) from Dictionaries");
        set.removeAll(list);
        int i = 0;
        for (String word : set) {
            Dictionaries dictionaries = new Dictionaries();
            dictionaries.setDword(word);
            dictionaries.setDflag(1);
            ddao.save(dictionaries);
            i++;
        }
        if (i == set.size()) {
            return ResultUtil.success("点击增加时添加数据字典成功！");
        } else {
            return ResultUtil.error(2, "点击增加时添加数据字典失败！");
        }
    }

    /**
     * @param inCourseBean
     * @Author: 徐慷慨
     * @Description:添加课程详情
     * @Date: 10:36 2017/9/6
     */
    @Override
    public Result insertCtopic(InCourseBean inCourseBean) {
        int i = 0;
        for (Ctopic ctopic : inCourseBean.getList()) {
            ctopic.setBid(inCourseBean.getBid());
            ctopic.setIsDel(2);
            cdao.save(ctopic);
            i++;
        }
        if (i == inCourseBean.getList().size()) {
            return ResultUtil.success("添加课程详情成功！");
        } else {
            return ResultUtil.error(2, "添加课程详情失败！");
        }
    }

    /**
     * @param cbid
     * @Author: 徐慷慨
     * @Description:根据课时查询课程详情
     * @Date: 10:39 2017/9/6
     */
    @Override
    public Result ctopicList(Integer cbid) throws Exception {
        List<Ctopic> list = cdao.findList("from Ctopic where bid=" + cbid+" and isDel=2");
        return ResultUtil.success("根据课时查询课程详情", list);
    }

    /**
     * @param cid
     * @Author: 徐慷慨
     * @Description:删除课程详情
     * @Date: 13:49 2017/9/7
     */
    @Override
    public Result deleteCtopic(String cid) {
        if (cid.endsWith(",")) {
            cid = cid.substring(0, cid.length() - 1);
        }
        try {
            int i = cdao.deleteWithHql("update  Ctopic set isDel=1 where cid in (" + cid + ")");
            return ResultUtil.success("删除成功！");
        } catch (Exception e) {
            return ResultUtil.error(2, "删除失败！");
        }
    }

    /**
     * @param ctopic
     * @Author: 徐慷慨
     * @Description:修改课程详情
     * @Date: 13:57 2017/9/7
     */
    @Override
    public Result updateCtopic(Ctopic ctopic) throws Exception {
        Ctopic one = cdao.getOne(Ctopic.class, ctopic.getCid());
        if (one != null) {
            List<String> set = Tools.zhuhuan(ctopic.getCsentence());
            List<Dictionaries> list = ddao.findList("select LOWER(dword) from Dictionaries");
            set.removeAll(list);
            int i = 0;
            for (String word : set) {
                Dictionaries dictionaries = new Dictionaries();
                dictionaries.setDword(word);
                dictionaries.setDflag(1);
                ddao.save(dictionaries);
                i++;
            }
            if (i == set.size()) {
                Session sessions = cdao.getSessions();
                sessions.evict(one);
                ctopic.setIsDel(2);
                boolean update = cdao.update(ctopic);
                if (update) {
                    return ResultUtil.success("修改成功！");
                } else {
                    return ResultUtil.error(2, "修改失败！");
                }
            } else {
                return ResultUtil.error(2, "添加数据字典失败！");
            }


        } else {
            return ResultUtil.error(2, "参数错误！");
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:课程管理列表
     * @Date: 17:21 2017/9/7
     */
    @Override
    public Result courseManageList(String aname, Integer currentPage, Integer pageSiz) throws Exception {
        StringBuffer hql = new StringBuffer("select b from Acourse a ,CourseLevel b where a.aid=b.aid and a.isDel=2 and b.isDel=2");
        StringBuffer count = new StringBuffer("select count(b) from Acourse a ,CourseLevel b where a.aid=b.aid and a.isDel=2 and b.isDel=2");
        if (aname != null) {
            hql.append(" and a.aname like '%" + aname + "%'");
            count.append(" and a.aname like '%" + aname + "%'");
        }
        Page<CourseLevel> page = courseLevelIBaseDao.findPage(currentPage, pageSiz, hql.toString(), count.toString());
        List<CourseDetilesBean> list = new ArrayList<CourseDetilesBean>();
        if(page!=null&&page.getList()!=null&&page.getList().size()>0){
            for (CourseLevel bhour : page.getList()) {
                Acourse one = adao.getOne(Acourse.class, bhour.getAid());
                CourseDetilesBean courseDetilesBean = new CourseDetilesBean();
                courseDetilesBean.setAid(one.getAid());
                courseDetilesBean.setAname(one.getAname());
                courseDetilesBean.setLevelId(bhour.getLevelId());
                courseDetilesBean.setLevelName(bhour.getLevelName());
                list.add(courseDetilesBean);
            }
        }
        Map map = new HashMap();
        map.put("total", page.getTotal());
        map.put("allPage", page.getAllpage());
        map.put("currentPage", page.getCurrentPage());
        map.put("pageSize", page.getPageSize());
        map.put("list", list);
        return ResultUtil.success("课程管理列表", map);
    }


    /**
     * 课程详情列表
     * @param leveId
     * @param name
     * @return
     */
    @Override
    public Result courseDetaisList(int leveId , String name,Integer currentPage,Integer pageSiz) throws Exception {
        CourseLevel one = courseLevelIBaseDao.getOne(CourseLevel.class, leveId);
        String sql="from Bhour where levelId="+leveId+" and isDel=2";
        String count="select count(*) from Bhour where levelId="+leveId;
        if(name!=null){
            sql="from Bhour where levelId="+leveId+"and isDel=2 and bname like '%"+name+"%'";
            count="select count(*) from Bhour where levelId="+leveId+" and isDel=2 and bname like '%"+name+"%'";;
        }
        Page<Bhour> page = bdao.findPage(currentPage, pageSiz, sql, count);
        List<InCourseBean> list = new ArrayList<InCourseBean>();
        if(page!=null&&page.getList()!=null){
            for (Bhour bhour : page.getList()) {
                List<Ctopic> cdaoList = cdao.findList("from Ctopic where bid=" + bhour.getBid());
                InCourseBean inCourseBean=new InCourseBean();
                inCourseBean.setLevelName(one.getLevelName());
                inCourseBean.setBid(bhour.getBid());
                inCourseBean.setBname(bhour.getBname());
                inCourseBean.setList(cdaoList);
                list.add(inCourseBean);
            }
        }
        Map map = new HashMap();
        map.put("total", page.getTotal());
        map.put("allPage", page.getAllpage());
        map.put("currentPage", page.getCurrentPage());
        map.put("pageSize", page.getPageSize());
        map.put("list", list);
        return ResultUtil.success("课程管理列表", map);
    }










    /**
     * @Author: 徐慷慨
     * @Description:所有的课程/课时列表
     * @Date: 13:33 2017/9/9
     */
    @Override
    public Result courseTreeAll() throws Exception {
        List<CourseTreeBean> list = new ArrayList<CourseTreeBean>();
        List<Acourse> list1 = adao.findList(" from Acourse where isDel=2");
        for (Acourse acourse : list1) {
            CourseTreeBean courseTreeBean = new CourseTreeBean();
            courseTreeBean.setId(acourse.getAid());
            courseTreeBean.setName(acourse.getAname());
            courseTreeBean.setPath(String.valueOf(acourse.getAid()) + ";" + "0");
            List<CourseLevel> list2 = courseLevelIBaseDao.findList(" from CourseLevel where aid=" + acourse.getAid()+" and isDel=2");
            List<CourseTreeBean.course> list3 = new ArrayList<CourseTreeBean.course>();
            if(list2!=null&&list2.size()>0){
                for (CourseLevel bhour : list2) {
                    CourseTreeBean.course course = new CourseTreeBean.course();
                    course.setId(bhour.getLevelId());
                    course.setName(bhour.getLevelName());
                    course.setPath(String.valueOf(bhour.getLevelId()) + ";" + String.valueOf(bhour.getAid()));
                    list3.add(course);
                }
            }
            courseTreeBean.setChildren(list3);
            list.add(courseTreeBean);
        }
        Map map = new HashMap();
        map.put("list", list);
        return ResultUtil.success("所有的课程/课时列表", map);
    }

}
