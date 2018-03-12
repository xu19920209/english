package com.tuyue.webModules.studentAndclass.controller;

import com.tuyue.pojo.Nstudent;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.studentAndclass.bean.StudentAndClassBean;
import com.tuyue.webModules.studentAndclass.biz.INstudentBiz;
import com.tuyue.webModules.upload.controller.UploadController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 王金海
 * @Description: 学生管理
 * @Date: Created by Administrator on 2017/9/11.
 * @Modified By:
 */
@Controller
@RequestMapping("Nstudent")
public class StudentController {
    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    private INstudentBiz nstudentBiz;
    /**
     * @Author: 王金海
     * @Description: 增加修改学生
      * @param nstudent 学生对象
     * @Date: 11:32 2017/9/11
     */
    @RequestMapping("addandupdata")
    @ResponseBody
    public Result addandupdata(@RequestBody Nstudent nstudent) throws Exception {

        logger.error("增加修改学生");
        if (nstudent==null) {
            return ResultUtil.error(2,"参数不能为空");
        }
        if (nstudent.getNid()>0){
            Nstudent nstudent1= nstudentBiz.updata(nstudent);
            if (nstudent1!=null) {
                return ResultUtil.success(nstudent1);
            }
            return ResultUtil.error(2,"修改失败");
        }
        boolean b= nstudentBiz.add(nstudent);
        if (b){
            return ResultUtil.success("增加成功");
        }
        return ResultUtil.error(2,"增加失败");
    }

    /**
     * @Author: 王金海
     * @Description: 学生列表
     * @Date: 17:06 2017/9/13
     */
    @RequestMapping("list")
    @ResponseBody
    public Result list(String studentName,Integer oid,Integer eid,Integer currentPage,Integer pageSize) throws Exception {
        logger.error("学生列表");
       if(studentName!=null){
           studentName = new String(studentName.getBytes("iso8859-1"),"utf-8");
       }

        if (eid==null) {
            return ResultUtil.error(2,"eid不能为空");
        }
        if(currentPage==null||currentPage<=0){
            currentPage=1;
        }
        if(pageSize==null||pageSize<=0){
            pageSize=10;
        }
        Page<StudentAndClassBean> nstudentPage =nstudentBiz.list(studentName,oid,eid,currentPage,pageSize);
        return ResultUtil.success(nstudentPage);
    }
    /**
     * @Author: 王金海
     * @Description: 删除学生
     * @Date: 17:06 2017/9/13
     */
    @RequestMapping("del")
    @ResponseBody
    public Result del(Integer nid) throws Exception {
        logger.error("删除学生");
        if (nid==null) {
            return ResultUtil.error(2,"学生id不能为空");
        }
        boolean b = nstudentBiz.del(nid);
        if (b) {
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.error(2,"删除失败");
    }

}
