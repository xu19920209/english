package com.tuyue.webModules.studentAndclass.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/11.
 * @Modified By:
 */
public class ClassBean {
    private int oid;
    private Integer aid;
    private Integer classFid;
    private Integer teachFid;
    private String className;
    private Integer num;
    @JSONField(format = "yyyy-MM-dd")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd")
    private Date endTime;
    private String goTime;
    private Integer endflag;
    private Integer eid;
    private String classFname;
    private String teachFname;
    private List<PstudenthistoryBean> pstudenthistoryBeans;
    private Integer ruleId;
    private Integer testId;

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "ClassBean{" + "oid=" + oid + ", aid=" + aid + ", classFid=" + classFid + ", teachFid=" + teachFid + ", className='" + className + '\'' + ", num=" + num + ", startTime=" + startTime + ", endTime=" + endTime + ", goTime='" + goTime + '\'' + ", endflag=" + endflag + ", eid=" + eid + ", classFname='" + classFname + '\'' + ", teachFname='" + teachFname + '\'' + ", pstudenthistoryBeans=" + pstudenthistoryBeans + '}';
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getClassFid() {
        return classFid;
    }

    public void setClassFid(Integer classFid) {
        this.classFid = classFid;
    }

    public Integer getTeachFid() {
        return teachFid;
    }

    public void setTeachFid(Integer teachFid) {
        this.teachFid = teachFid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getGoTime() {
        return goTime;
    }

    public void setGoTime(String goTime) {
        this.goTime = goTime;
    }

    public Integer getEndflag() {
        return endflag;
    }

    public void setEndflag(Integer endflag) {
        this.endflag = endflag;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getClassFname() {
        return classFname;
    }

    public void setClassFname(String classFname) {
        this.classFname = classFname;
    }

    public String getTeachFname() {
        return teachFname;
    }

    public void setTeachFname(String teachFname) {
        this.teachFname = teachFname;
    }

    public List<PstudenthistoryBean> getPstudenthistoryBeans() {
        return pstudenthistoryBeans;
    }

    public void setPstudenthistoryBeans(List<PstudenthistoryBean> pstudenthistoryBeans) {
        this.pstudenthistoryBeans = pstudenthistoryBeans;
    }

    public ClassBean() {
    }

    public ClassBean(int oid, Integer aid, Integer classFid, Integer teachFid, String className, Integer num, Date startTime, java.util.Date endTime, String goTime, Integer endflag, Integer eid, String classFname, String teachFname, List<PstudenthistoryBean> pstudenthistoryBeans) {
        this.oid = oid;
        this.aid = aid;
        this.classFid = classFid;
        this.teachFid = teachFid;
        this.className = className;
        this.num = num;
        this.startTime = startTime;
        this.endTime = endTime;
        this.goTime = goTime;
        this.endflag = endflag;
        this.eid = eid;
        this.classFname = classFname;
        this.teachFname = teachFname;
        this.pstudenthistoryBeans = pstudenthistoryBeans;
    }
}
