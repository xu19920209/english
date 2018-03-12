package com.tuyue.webModules.work.biz;


import com.tuyue.pojo.UteacherReply;
import com.tuyue.result.Result;
import com.tuyue.webModules.adult.bean.InsertWorBean;
import org.springframework.web.multipart.MultipartFile;


/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/11.
 */
public interface IworkBiz {
    /**
     * @Author: 徐慷慨
     * @Description: 给班级布置作业
     * @Date: 11:17 2017/9/11
     */
    public Result insetClassWork(InsertWorBean insertWorBean) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:给成人布置作业
     * @Date: 15:48 2017/9/13
     */
    public Result  insetAdltWork(InsertWorBean insertWorBean) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:该学校下的课程列表
     * @Date: 11:19 2017/9/11
     */
    public Result schoolCourseList(Integer eid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description: 课时列表
     * @Date: 11:21 2017/9/11
     */
    public Result schoolHourList(Integer aid, Integer eid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description: 课程详情列表
     * @Date: 11:22 2017/9/11
     */
    public Result courseDetilesList(Integer bid) throws Exception;


    /**
     * @param fid
     * @Author: 徐慷慨
     * @Description:班级列表
     * @Date: 14:31 2017/9/11
     */

    public Result classList(Integer fid) throws Exception;


    /**
     * @Author: 徐慷慨
     * @Description:作业列表
     * @Date: 8:45 2017/9/12
     */
    public Result workList(Integer currentPage, Integer pageSize, Integer eid, Integer fid, String layoutTime, Integer aid, Integer bid,Integer oid) throws Exception;

    /**
     * @param oid
     * @Author: 徐慷慨
     * @Description:作业完成详情
     * @Date: 10:27 2017/9/12
     */
    public Result workDetiles(Integer oid, Integer currentPage, Integer pageSize,String layoutTime) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:学生作业详情
     * @Date: 18:04 2017/9/12
     */
    public Result studentWorkDetiles(Integer nid, Integer currentPage, Integer pageSize,String layoutTime) throws Exception;
    //::::::::::::::::::::::::::::::::::::::回复作业模块:::::::::::::::::::::::::::::::::::::::::::::::::::::::::

    /**
     * @Author: 徐慷慨
     * @Description: 回复作业列表
     * @Date: 15:09 2017/9/11
     */
    public Result consultList(Integer eid, Integer currentPage, Integer pageSize, Integer replyStatus, Integer fid, Integer oid, String studentName) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:回复作业
     * @Date: 8:42 2017/9/12
     */

    public Result replyWork(UteacherReply uteacherReply,MultipartFile file) throws Exception;

}
