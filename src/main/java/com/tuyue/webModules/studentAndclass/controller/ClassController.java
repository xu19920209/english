package com.tuyue.webModules.studentAndclass.controller;

import com.tuyue.pojo.Oclass;
import com.tuyue.result.Result;
import com.tuyue.result.ResultEnum;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.studentAndclass.biz.IOclassBiz;
import com.tuyue.webModules.studentAndclass.bean.ClassBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/7.
 * @Modified By:
 */
@Controller
@RequestMapping("Oclass")
public class ClassController {
    private final static Logger logger = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private IOclassBiz oclassBiz;

    /**
     * @Author: 王金海
     * @Description: 增加班级
      * @param oclass 班级对象
     * @Date: 15:18 2017/9/7
     */
    @RequestMapping("add")
    @ResponseBody
    public Result<Oclass> add(@RequestBody Oclass oclass){
        logger.error("增加班级");
        if (oclass==null) {
            return ResultUtil.error(2,"对象不能为空");
        }

        oclass.setEndflag(1);
        boolean b = oclassBiz.add(oclass);
        if (b) {
            return ResultUtil.success("增加成功");
        }
        return ResultUtil.error(2,"增加失败");
    }

    /**
     * @Author: 王金海
     * @Description:增加修改班级
      * @param oclass 班级对象
     * @Date: 15:32 2017/9/7
     */
    @RequestMapping("addandupdata")
    @ResponseBody
    public Result addandupdata(@RequestBody Oclass oclass) throws Exception {
        logger.error("增加和修改班级");
        if (oclass.getEid()==null) {
            return ResultUtil.error(2,"eid不能为空");
        }
        if (oclass==null) {
            return ResultUtil.error(2,"参数不能为空");
        }
        if (oclass.getOid()>0) {
            boolean b = oclassBiz.updata(oclass);
            if (b) {
                return ResultUtil.success("修改成功");
            }
            return ResultUtil.error(2,"修改失败");
        }
        oclass.setEndflag(1);
        boolean b = oclassBiz.add(oclass);
        if (b) {
            return ResultUtil.success("增加成功");
        }
        return ResultUtil.error(2,"增加失败");

    }
    /**
     * @Author: 王金海
     * @Description:删除班级
      * @param oid 班级ID
     * @Date: 15:34 2017/9/7
     */
    @RequestMapping("del")
    @ResponseBody
    public Result del(Integer oid) throws Exception {
        logger.error("删除班级 ");
        if (oid==null) {
            return ResultUtil.error(2,"参数不能为空");
        }
        int i = oclassBiz.del(oid);
        switch (i) {
            case 1:
                return ResultUtil.success("删除成功",1);
            case 2:
                return ResultUtil.error(2,"班级下有学生，不能删除班级");
            case 3:
                return ResultUtil.error(2,"删除失败");
            default:
                return ResultUtil.error(ResultEnum.UNKONW_ERROR);
        }
    }
    /**
     * @Author: 王金海
     * @Description:班级list
     * @param className 班级名称
     * @param classFid 班主任ID
     * @param teachFid 代课老师ID
     * @param eid 校区ID
     * @param currentPage 页数
     * @param pageSize 一页几个
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Result list(String className,Integer classFid,Integer teachFid,Integer eid,Integer currentPage,Integer pageSize) throws Exception {
        logger.error("班级list");
        logger.error("班级名称："+className+"老师id"+teachFid+"班主任"+classFid);
//        if (className!=null) {
//            className = new String(className.getBytes("iso8859-1"), "utf-8");
//        }
        if (eid==null) {
            return ResultUtil.error(2,"校区ID不能为空");
        }
if(currentPage==null||currentPage<=0){
    currentPage=1;
}
        if(pageSize==null||pageSize<=0){
            pageSize=10;
        }
        Page<ClassBean> list = oclassBiz.list(className, classFid, teachFid, eid, currentPage, pageSize);

        return ResultUtil.success(list);
    }

    /**
     * @Author: 王金海
     * @Description: 结课
      * @param oid 课程ID
     * @Date: 19:02 2017/9/11
     */
    @RequestMapping("endclass")
    @ResponseBody
    public Result endclass(Integer oid) throws Exception {
        logger.error("结课");
        if (oid==null) {
                return ResultUtil.error(2,"班级oid不能为空");
        }
        boolean b=oclassBiz.endclass(oid);
        if (b){
            return ResultUtil.success("结课成功");
        }
        return ResultUtil.error(2,"结课失败");
    }
}
