package com.tuyue.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/11.
 * @Modified By:
 */
@Entity
public class Pstudenthistory {
    private int pid;
    private Integer oid;

    private Date startTime;

    private Date endTime;
    private String goTime;
    private String className;
    private Integer classFid;
    private Integer teachFid;
    private Integer aid;
    private Integer nid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid", nullable = false)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "oid", nullable = true)
    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
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
    @Column(name = "className", nullable = true, length = 50)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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
    @Column(name = "aid", nullable = true)
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "nid", nullable = true)
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pstudenthistory that = (Pstudenthistory) o;

        if (pid != that.pid) return false;
        if (oid != null ? !oid.equals(that.oid) : that.oid != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (goTime != null ? !goTime.equals(that.goTime) : that.goTime != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (classFid != null ? !classFid.equals(that.classFid) : that.classFid != null) return false;
        if (teachFid != null ? !teachFid.equals(that.teachFid) : that.teachFid != null) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (nid != null ? !nid.equals(that.nid) : that.nid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (goTime != null ? goTime.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (classFid != null ? classFid.hashCode() : 0);
        result = 31 * result + (teachFid != null ? teachFid.hashCode() : 0);
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        return result;
    }
}
