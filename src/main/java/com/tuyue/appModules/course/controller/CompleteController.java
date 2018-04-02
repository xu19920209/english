package com.tuyue.appModules.course.controller;

import com.tuyue.appModules.course.bean.AdultCompleteBean;
import com.tuyue.appModules.course.biz.IAdultCompleteBiz;
import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 王金海
 * @Description: 作业历史得分详情
 * @Date: Created by Administrator on 2017/9/15.
 * @Modified By:
 */
@Controller
@ResponseBody
@RequestMapping("Complete")
public class CompleteController {

    @Autowired
    private IAdultCompleteBiz adultCompleteBiz;

    /**
     * @Author: 王金海
     * @Description:
      * @param cids 课程详情ID以逗号隔开的
     * @Date: 11:40 2017/9/15
     */
    @RequestMapping("AdultCompletelist")
    public Result AdultCompletelist(Integer aid,Integer bid, String cids,Integer nid) throws Exception {
        if (aid==null) {
            return ResultUtil.error(2,"aid不能为空");
        }
        if (bid==null) {
            return ResultUtil.error(2,"bid不能为空");
        }
        if (cids==null) {
            return ResultUtil.error(2,"cids不能为空");
        }
        if (nid==null) {
            return ResultUtil.error(2,"nid不能为空");
        }
        AdultCompleteBean bean = adultCompleteBiz.list(aid, bid, cids, nid);
        return ResultUtil.success(bean);
    }


    /**
     * @Author: 王金海
     * @Description: 学生成绩单
     * @param cids 课程详情ID以逗号隔开的
     * @Date: 11:40 2017/9/15
     */
    @RequestMapping("StudentCompletelist")
    public Result StudentCompletelist(Integer aid, Integer bid, String cids, String layoutTime,Integer nid) throws Exception {
        if (aid==null) {
            return ResultUtil.error(2,"aid不能为空");
        }
        if (bid==null) {
            return ResultUtil.error(2,"bid不能为空");
        }
        if (cids==null) {
            return ResultUtil.error(2,"cids不能为空");
        }
        if (layoutTime==null) {
            return ResultUtil.error(2,"layoutTime不能为空");
        }
        AdultCompleteBean bean = adultCompleteBiz.Studentlist(aid, bid, cids, layoutTime,nid);
        return ResultUtil.success(bean);
    }
}
