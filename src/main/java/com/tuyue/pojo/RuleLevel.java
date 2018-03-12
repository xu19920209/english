package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
@Entity
@Table(name = "rule_level", schema = "aaenglish", catalog = "")
public class RuleLevel {
    private int ruLeId;
    private Integer ruleId;
    private Integer level;
    private Double num;
    private String gradeScope;

    @Id
    @GeneratedValue
    @Column(name = "ru_le_id")
    public int getRuLeId() {
        return ruLeId;
    }

    public void setRuLeId(int ruLeId) {
        this.ruLeId = ruLeId;
    }

    @Basic
    @Column(name = "rule_id")
    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "num")
    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    @Basic
    @Column(name = "grade_scope")
    public String getGradeScope() {
        return gradeScope;
    }

    public void setGradeScope(String gradeScope) {
        this.gradeScope = gradeScope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleLevel ruleLevel = (RuleLevel) o;

        if (ruLeId != ruleLevel.ruLeId) return false;
        if (ruleId != null ? !ruleId.equals(ruleLevel.ruleId) : ruleLevel.ruleId != null) return false;
        if (level != null ? !level.equals(ruleLevel.level) : ruleLevel.level != null) return false;
        if (num != null ? !num.equals(ruleLevel.num) : ruleLevel.num != null) return false;
        if (gradeScope != null ? !gradeScope.equals(ruleLevel.gradeScope) : ruleLevel.gradeScope != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ruLeId;
        result = 31 * result + (ruleId != null ? ruleId.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (gradeScope != null ? gradeScope.hashCode() : 0);
        return result;
    }
}
