package com.tuyue.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/7.
 * @Modified By:
 */
@Entity
public class Oclass {
    private int oid;
    private Integer aid;
    private Integer classFid;
    private Integer teachFid;
    private String className;
    private Integer num;

    private Date startTime;
    private Date endTime;
    private String goTime;
    private Integer endflag;
    private Integer eid;
    private Integer ruleId;
    private Integer testId;

    public void setStartTime(java.sql.Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(java.sql.Date endTime) {
        this.endTime = endTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid", nullable = false)
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "aid", nullable = true)
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "classFid", nullable = true)
    public Integer getClassFid() {
        return classFid;
    }

    public void setClassFid(Integer classFid) {
        this.classFid = classFid;
    }

    @Basic
    @Column(name = "teachFid", nullable = true)
    public Integer getTeachFid() {
        return teachFid;
    }

    public void setTeachFid(Integer teachFid) {
        this.teachFid = teachFid;
    }

    @Basic
    @Column(name = "className", nullable = true, length = 50)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "num", nullable = true)
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Basic
    @Column(name = "startTime", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "goTime", nullable = true, length = 50)
    public String getGoTime() {
        return goTime;
    }

    public void setGoTime(String goTime) {
        this.goTime = goTime;
    }

    @Basic
    @Column(name = "endflag", nullable = true)
    public Integer getEndflag() {
        return endflag;
    }

    public void setEndflag(Integer endflag) {
        this.endflag = endflag;
    }

    @Basic
    @Column(name = "eid", nullable = true)
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Oclass oclass = (Oclass) o;

        if (oid != oclass.oid) return false;
        if (aid != null ? !aid.equals(oclass.aid) : oclass.aid != null) return false;
        if (classFid != null ? !classFid.equals(oclass.classFid) : oclass.classFid != null) return false;
        if (teachFid != null ? !teachFid.equals(oclass.teachFid) : oclass.teachFid != null) return false;
        if (className != null ? !className.equals(oclass.className) : oclass.className != null) return false;
        if (num != null ? !num.equals(oclass.num) : oclass.num != null) return false;
        if (startTime != null ? !startTime.equals(oclass.startTime) : oclass.startTime != null) return false;
        if (endTime != null ? !endTime.equals(oclass.endTime) : oclass.endTime != null) return false;
        if (goTime != null ? !goTime.equals(oclass.goTime) : oclass.goTime != null) return false;
        if (endflag != null ? !endflag.equals(oclass.endflag) : oclass.endflag != null) return false;
        if (eid != null ? !eid.equals(oclass.eid) : oclass.eid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = oid;
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (classFid != null ? classFid.hashCode() : 0);
        result = 31 * result + (teachFid != null ? teachFid.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (goTime != null ? goTime.hashCode() : 0);
        result = 31 * result + (endflag != null ? endflag.hashCode() : 0);
        result = 31 * result + (eid != null ? eid.hashCode() : 0);
        return result;
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
    @Column(name = "test_id")
    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }
}
