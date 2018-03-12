package com.tuyue.webModules.schoolAndPerson.bean;

import com.tuyue.pojo.Grole;

import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/8.
 */
public class RolerAndResouceBean {
    private int gid;
    private String role;
    private String comment;
    private Integer isFlag;
    private Integer eid;
    private String hid;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(Integer isFlag) {
        this.isFlag = isFlag;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public RolerAndResouceBean(int gid, String role, String comment, Integer isFlag, Integer eid, String hid) {
        this.gid = gid;
        this.role = role;
        this.comment = comment;
        this.isFlag = isFlag;
        this.eid = eid;
        this.hid = hid;
    }

    public RolerAndResouceBean() {
    }
}
