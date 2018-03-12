package com.tuyue.webModules.adult.controller;

import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.util.Page;
import com.tuyue.webModules.adult.bean.NadultBean;
import com.tuyue.webModules.adult.biz.INadultBeanBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("Adult")
public class AdultController {
    @Autowired
    private INadultBeanBiz nadultBeanBiz;

    /**
     * @Author: 王金海
     * @Description: 成人列表
     * @Date: 15:01 2017/9/14
     */
    @RequestMapping("list")
    public Result list(Integer currentPage,Integer pageSize) throws Exception {
        Page<NadultBean> list = nadultBeanBiz.list( currentPage,pageSize);
        return ResultUtil.success(list);
    }
}
