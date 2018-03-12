package com.tuyue.webModules.work.controller;

import com.tuyue.dao.IBaseDao;
import com.tuyue.pojo.Nstudent;
import com.tuyue.pojo.UteacherReply;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.JPushUtil;
import com.tuyue.util.Md5Util;
import com.tuyue.webModules.adult.bean.InsertWorBean;
import com.tuyue.webModules.work.biz.IworkBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/11.
 */
@Controller
@ResponseBody
@RequestMapping("web/work")
public class WorkController {
    private final static Logger logger = LoggerFactory.getLogger(WorkController.class);
    @Autowired
    private IworkBiz biz;
    @Autowired
    private IBaseDao<Nstudent> nstudentIBaseDao;

    /**
     * @Author: 徐慷慨
     * @Description: 给班级布置作业
     * @Date: 11:17 2017/9/11
     */
    @RequestMapping("insetClassWork.do")
    public Result insetClassWork(@RequestBody InsertWorBean insertWorBean) throws Exception {
        if (insertWorBean == null) {
            return ResultUtil.error(2, "参数不能为空！");
        }
        return biz.insetClassWork(insertWorBean);
    }

    /**
     * @Author: 徐慷慨
     * @Description:该学校下的课程列表
     * @Date: 11:19 2017/9/11
     */
    @RequestMapping("courseList.do")
    public Result schoolCourseList(Integer eid) throws Exception {
        logger.error("该学校下的课程列表");
        if (eid == null) {
            return ResultUtil.error(2, "参数不能为空！");
        }
        return biz.schoolCourseList(eid);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 课时列表
     * @Date: 11:21 2017/9/11
     */
    @RequestMapping("hourList.do")
    public Result schoolHourList(Integer aid, Integer eid) throws Exception {
        logger.error("课时列表");
        if (aid == null || eid == null) {
            return ResultUtil.error(2, "参数不能为空");
        }
        return biz.schoolHourList(aid, eid);
    }

    /**
     * @Author: 徐慷慨
     * @Description: 课程详情列表
     * @Date: 11:22 2017/9/11
     */
    @RequestMapping("courseDetilesList.do")
    public Result courseDetilesList(Integer bid) throws Exception {
        logger.error("课程详情列表");
        if (bid == null) {
            return ResultUtil.error(2, "参数不能为空");
        }
        return biz.courseDetilesList(bid);
    }


    /**
     * @param fid
     * @Author: 徐慷慨
     * @Description:班级列表
     * @Date: 14:31 2017/9/11
     */
    @RequestMapping("classList.do")
    public Result classList(Integer fid) throws Exception {
        logger.error("班级列表");
        return biz.classList(fid);
    }


    /**
     * @Author: 徐慷慨
     * @Description: 回复作业列表
     * @Date: 15:09 2017/9/11
     */
    @RequestMapping("consultList.do")
    public Result consultList(Integer eid, Integer currentPage, Integer pageSize, Integer replyStatus, Integer fid, Integer oid, String studentName) throws Exception {
        logger.error("回复作业列表");
//        if (studentName != null) {
//            studentName = new String(studentName.getBytes("iso8859-1"), "utf-8");
//        }
        return biz.consultList(eid, currentPage, pageSize, replyStatus, fid, oid, studentName);
    }

    /**
     * @Author: 徐慷慨
     * @Description:作业列表
     * @Date: 8:45 2017/9/12
     */
    @RequestMapping("workList.do")
    public Result workList(Integer currentPage, Integer pageSize, Integer eid, Integer fid, String layoutTime, Integer aid, Integer bid, Integer oid) throws Exception {
        logger.error("作业列表");
        return biz.workList(currentPage, pageSize, eid, fid, layoutTime, aid, bid, oid);
    }

    /**
     * @param oid
     * @Author: 徐慷慨
     * @Description:作业完成详情
     * @Date: 10:27 2017/9/12
     */
    @RequestMapping("workDetiles.do")
    public Result workDetiles(Integer oid, Integer currentPage, Integer pageSize, String layoutTime) throws Exception {
        logger.error("作业完成详情");
        return biz.workDetiles(oid, currentPage, pageSize, layoutTime);
    }

    /**
     * @Author: 徐慷慨
     * @Description:学生作业详情
     * @Date: 18:04 2017/9/12
     */
    @RequestMapping("studentWorkDetiles.do")
    public Result studentWorkDetiles(Integer nid, Integer currentPage, Integer pageSize, String layoutTime) throws Exception {
        logger.error("学生作业详情");
        return biz.studentWorkDetiles(nid, currentPage, pageSize, layoutTime);
    }

    /**
     * @Author: 徐慷慨
     * @Description:回复作业
     * @Date: 8:42 2017/9/12
     */
    @RequestMapping("replyWork.do")
    public Result replyWork(@RequestParam(value = "file", required = false) MultipartFile file, Integer tid
            , Integer fid, String replyWord) throws Exception {
        logger.error("回复作业");
        UteacherReply uteacherReply = new UteacherReply();
        uteacherReply.setFid(fid);
        uteacherReply.setTid(tid);
        if (tid == null) {
            return ResultUtil.error(2, "tid不能为空");
        }
        if (fid == null) {
            return ResultUtil.error(2, "fid不能为空");
        }
        if (replyWord != null) {
            uteacherReply.setReplyWord(replyWord);
        }


        return biz.replyWork(uteacherReply, file);
    }

    /**
     * @Author: 徐慷慨
     * @Description:给成人布置作业
     * @Date: 15:48 2017/9/13
     */
    @RequestMapping("insetAdltWork.do")
    public Result insetAdltWork(@RequestBody InsertWorBean insertWorBean) throws Exception {
        logger.error("给成人布置作业");
        if (insertWorBean == null) {
            return ResultUtil.error(2, "参数不能为空！");
        }
        return biz.insetAdltWork(insertWorBean);
    }

    /**
     * @Author: 徐慷慨
     * @Description:鼓励学生
     * @Date: 17:02 2017/11/14
     */
    @RequestMapping("encourage.do")
    public void encourage(Integer nid, String msg) throws Exception {
        Nstudent one = nstudentIBaseDao.getOne(Nstudent.class, nid);
        HashMap<String, String> maps = new HashMap<String, String>();
        maps.put("type", String.valueOf(2));  //type  = 1 布置作业 type = 2  老师鼓励学生了
        JPushUtil.sendMsgToApp("你有新消息了",msg,"type",maps,Md5Util.toMD5(one.getUsername()));
    }
}
