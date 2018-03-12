package com.tuyue.webModules.rule.controller;

import com.tuyue.result.Result;
import com.tuyue.result.ResultUtil;
import com.tuyue.webModules.rule.bean.RuleBean;
import com.tuyue.webModules.rule.biz.IruleBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
@Controller
@ResponseBody
@RequestMapping("web/rule")
public class RuleController {
    @Autowired
    private IruleBiz biz;

    /**
     * @Author: 徐慷慨
     * @Description:创建规则
     * @Date: 14:34 2017/11/7
     */
    @RequestMapping("createRule.do")
    public Result createRule(@RequestBody RuleBean ruleBean) throws Exception {
        if(ruleBean.getRuleId()>0){
            return biz.upRule(ruleBean);
        }else{
            return biz.createRule(ruleBean);
        }
    }

    /**
     * @Author: 徐慷慨
     * @Description:删除规则
     * @Date: 14:35 2017/11/7
     */
    @RequestMapping("deleteRule.do")
    public Result deleteRule(Integer ruleId) throws Exception {
        return biz.deleteRule(ruleId);
    }

    /**
     * @Author: 徐慷慨
     * @Description:规则列表
     * @Date: 14:36 2017/11/7
     */
    @RequestMapping("ruleList.do")
    public Result ruleList(Integer eid) throws Exception {
        return biz.ruleList(eid);
    }



    /**
     * @Author: 徐慷慨
     * @Description:修改规则
     * @Date: 14:36 2017/11/7
     */
    @RequestMapping("upRule.do")
    public Result upRule(@RequestBody RuleBean ruleBean) throws Exception{
        if(ruleBean.getRuleId()>0){
            return biz.upRule(ruleBean);
        }else{
            return biz.createRule(ruleBean);
        }

    }

    /**
     * @Author: 徐慷慨
     * @Description:修改班级规则
     * @Date: 14:39 2017/11/7
     */
    @RequestMapping("upRuleClass.do")
    public Result upRuleClass(Integer oid,Integer ruleId) throws Exception{
        return biz.upRuleClass(oid,ruleId);
    }
    /**
     * @Author: 徐慷慨
     * @Description:根据id查看规则
     * @Date: 18:11 2017/11/7
     */
    @RequestMapping("ruleById.do")
    public Result ruleById(Integer ruleId) throws Exception{
       return biz.ruleById(ruleId);
    }


    /**
     * @Author: 徐慷慨
     * @Description: 班级规则列表详细列表
     * @Date: 14:19 2017/11/8
     */
    @RequestMapping("ruleClssListDetiles.do")
    public Result ruleClssListDetiles(Integer currentPage,Integer pageSize,Integer eid) throws Exception{
        return biz.ruleClssListDetiles(currentPage,pageSize,eid);
    }

    /**
     * @Author: 徐慷慨
     * @Description:查询每个班级每个档次的详细信息
     * date 1:今天 2昨天 3 前天
     * level 1 第一 2 第二档
     * @Date: 14:56 2017/11/8
     */
    @RequestMapping("classRuledetiles.do")
    public Result classRuledetiles(Integer oid,Integer date ,Integer level) throws Exception{
        return biz.classRuledetiles(oid,date,level);
    }
    /**
     * @Author: 徐慷慨
     * @Description:班级规则列表
     * @Date: 11:59 2017/11/8
     */
    @RequestMapping("ruleClssList.do")
    public Result ruleClssList(Integer currentPage, Integer pageSiz,Integer eid) throws Exception{
        return biz.ruleClssList(currentPage,pageSiz,eid);
    }
}
