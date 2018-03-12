package com.tuyue.webModules.studentAndclass.biz;

import com.tuyue.pojo.Oclass;
import com.tuyue.util.Page;
import com.tuyue.webModules.studentAndclass.bean.ClassBean;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/7.
 * @Modified By:
 */
public interface IOclassBiz {
    /**
     * @Author: 王金海
     * @Description: 增加班级
      * @param oclass 班级对象
     * @Date: 15:03 2017/9/7
     */
    public boolean add(Oclass oclass);

    /**
     * @Author: 王金海
     * @Description:修改班级
      * @param oclass 班级对象
     * @Date: 15:23 2017/9/7
     */
    boolean updata(Oclass oclass) throws Exception;

    /**
     * 删除班级
     * @param oid 班级ID
     * @return
     * @throws Exception
     */
    int del(Integer oid) throws Exception;

    /**
     * 班级list
     * @param className 班级名称
     * @param classFid 班主任ID
     * @param teachFid 代课老师ID
     * @param eid 校区ID
     * @param page 页数
     * @param size 一页几个
     * @return
     */
    Page<ClassBean> list(String className, Integer classFid, Integer teachFid, Integer eid, Integer page, Integer size) throws Exception;

    /**
     * @Author: 王金海
     * @Description: 结课
      * @param oid 课程ID
     * @Date: 19:07 2017/9/11
     */
    boolean endclass(Integer oid) throws Exception;
}
