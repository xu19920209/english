package com.tuyue.webModules.rule.bean;

import com.tuyue.pojo.RuleLevel;

import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
public class RuleBean {
    private int ruleId;
    private Integer eid;
    private String ruleName;

    List<RuleLevel> list;

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public List<RuleLevel> getList() {
        return list;
    }

    public void setList(List<RuleLevel> list) {
        this.list = list;
    }
}
