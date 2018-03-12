package com.tuyue.webModules.studentAndclass.biz;

import com.tuyue.pojo.Nstudent;
import com.tuyue.util.Page;
import com.tuyue.webModules.studentAndclass.bean.StudentAndClassBean;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/7.
 * @Modified By:
 */
public interface INstudentBiz {
    /**
     * @Author: 王金海
     * @Description: 修改学生信息
     * @Date: 13:48 2017/9/11
     */
    Nstudent updata(Nstudent nstudent) throws Exception;

    /**
     * @Author: 王金海
     * @Description：增加学生信息
     * @Date: 13:49 2017/9/11
     */
    boolean add(Nstudent nstudent);

    /**
     * @Author: 王金海
     * @Description: 学生列表
      * @param studentName 学生姓名
      * @param eid 校区ID
      * @param oid 班级ID
     * @Date: 9:08 2017/9/12
     */
    Page<StudentAndClassBean> list(String studentName, Integer oid, Integer eid, Integer currentPage, Integer pageSize) throws Exception;

    boolean del(Integer nid) throws Exception;
}
