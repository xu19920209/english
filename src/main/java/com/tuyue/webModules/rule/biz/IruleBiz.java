package com.tuyue.webModules.rule.biz;

import com.tuyue.appModules.download.controller.DownloadController;
import com.tuyue.result.Result;
import com.tuyue.webModules.rule.bean.RuleBean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
public interface IruleBiz {
    /**
     * @Author: 徐慷慨
     * @Description:创建规则
     * @Date: 14:34 2017/11/7
     */
    public Result createRule(RuleBean ruleBean) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:删除规则
     * @Date: 14:35 2017/11/7
     */
    public Result deleteRule(Integer ruleId) throws Exception;
    /**
     * @Author: 徐慷慨
     * @Description:规则列表
     * @Date: 14:36 2017/11/7
     */
    public Result ruleList(Integer eid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:修改规则
     * @Date: 14:36 2017/11/7
     */
    public Result upRule(RuleBean ruleBean) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:修改班级规则
     * @Date: 14:39 2017/11/7
     */
    public Result upRuleClass(Integer oid,Integer ruleId) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:根据id查看规则
     * @Date: 18:11 2017/11/7
     */
    public Result ruleById(Integer ruleId) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:班级规则列表
     * @Date: 11:59 2017/11/8
     */
    public Result ruleClssList(Integer currentPage,Integer pageSize,Integer eid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description: 班级规则列表详细列表
     * @Date: 14:19 2017/11/8
     */

    public Result ruleClssListDetiles(Integer currentPage,Integer pageSize,Integer eid) throws Exception;

    /**
     * @Author: 徐慷慨
     * @Description:查询每个班级每个档次的详细信息
     * date 1:今天 2昨天 3 前天
     * level 1 第一 2 第二档
     * @Date: 14:56 2017/11/8
     */
    public Result classRuledetiles(Integer oid,Integer date ,Integer level) throws Exception;
}
