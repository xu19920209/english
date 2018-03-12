package com.tuyue.webModules.studentAndclass.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/11.
 * @Modified By:
 */
public class PstudenthistoryBean {
    private int pid;
    private Integer oid;
    @JSONField(format = "yyyy-MM-dd")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd")
    private Date endTime;
    private String goTime;
    private String className;
    private Integer classFid;
    private Integer teachFid;
    private Integer aid;
    private Integer nid;

    private String classFname;
    private String teachFname;

    @Override
    public String toString() {
        return "PstudenthistoryBean{" + "pid=" + pid + ", oid=" + oid + ", startTime=" + startTime + ", endTime=" + endTime + ", goTime='" + goTime + '\'' + ", className='" + className + '\'' + ", classFid=" + classFid + ", teachFid=" + teachFid + ", aid=" + aid + ", nid=" + nid + ", classFname='" + classFname + '\'' + ", teachFname='" + teachFname + '\'' + '}';
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
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

    public PstudenthistoryBean() {
    }

    public PstudenthistoryBean(int pid, Integer oid, Date startTime, Date endTime, String goTime, String className, Integer classFid, Integer teachFid, Integer aid, Integer nid, String classFname, String teachFname) {
        this.pid = pid;
        this.oid = oid;
        this.startTime = startTime;
        this.endTime = endTime;
        this.goTime = goTime;
        this.className = className;
        this.classFid = classFid;
        this.teachFid = teachFid;
        this.aid = aid;
        this.nid = nid;
        this.classFname = classFname;
        this.teachFname = teachFname;
    }
}
