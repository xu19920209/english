package com.tuyue.webModules.adult.controller;

import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.adult.bean.InsertWorBean;
import com.tuyue.webModules.adult.bean.VadultWorkBean;
import com.tuyue.webModules.adult.biz.IAdultWorkBiz;
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
 * @Date: Created by Administrator on 2017/9/14.
 * @Modified By:
 */
@Controller
@ResponseBody
@RequestMapping("AdultWork")
public class AdultWorkController {
    private final static Logger logger = LoggerFactory.getLogger(AdultWorkController.class);
    @Autowired
    private IAdultWorkBiz adultWorkBiz;

    /**
     * @Author: 徐慷慨
     * @Description:给成人布置作业
     * @Date: 15:48 2017/9/13
     */
    @RequestMapping("insetAdltWork")
    public Result insetAdltWork(@RequestBody InsertWorBean insertWorBean) throws Exception{
        logger.error("给成人布置作业");
        if(insertWorBean==null){
            return ResultUtil.error(2,"参数不能为空！");
        }
        return  adultWorkBiz.insetAdltWork(insertWorBean);
    }

    @RequestMapping("del")
    public Result del(String vids) throws Exception {
        boolean b=adultWorkBiz.del(vids) ;
        if (b) {
            return ResultUtil.success("删除成功");
        }
        return ResultUtil.error(2,"删除失败");
    }

    @RequestMapping("list")
    public Result list(Integer currentPage,Integer pageSize) throws Exception {
        Page<VadultWorkBean> list = adultWorkBiz.list(currentPage, pageSize);
        return ResultUtil.success(list);
    }

}
