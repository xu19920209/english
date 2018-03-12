package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
@Entity
public class Rule {
    private int ruleId;
    private Integer eid;
    private String ruleName;

    @Id
    @GeneratedValue
    @Column(name = "rule_id")
    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    @Basic
    @Column(name = "eid")
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "rule_name")
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (ruleId != rule.ruleId) return false;
        if (eid != null ? !eid.equals(rule.eid) : rule.eid != null) return false;
        if (ruleName != null ? !ruleName.equals(rule.ruleName) : rule.ruleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ruleId;
        result = 31 * result + (eid != null ? eid.hashCode() : 0);
        result = 31 * result + (ruleName != null ? ruleName.hashCode() : 0);
        return result;
    }
}
