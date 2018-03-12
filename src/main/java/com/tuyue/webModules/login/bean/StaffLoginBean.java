package com.tuyue.webModules.login.bean;

import com.tuyue.pojo.Grole;

import java.util.List;

/**
 * @Author: 王金海
 * @Description: 学生登陆
 * @Date: Created by Administrator on 2017/9/8.
 * @Modified By:
 */
public class StaffLoginBean {
    private int fid; //员工ID
    private String fname;//员工姓名
    private List<Grole> groles;//角色集合
    private int eid;//学校ID
    private String ename;// 校区名称

    public StaffLoginBean() {
    }

    public StaffLoginBean(int fid, String fname, List<Grole> groles, int eid, String ename) {
        this.fid = fid;
        this.fname = fname;
        this.groles = groles;
        this.eid = eid;
        this.ename = ename;
    }

    @Override
    public String toString() {
        return "StaffLoginBean{" +
                "fid=" + fid +
                ", fname='" + fname + '\'' +
                ", groles=" + groles +
                ", eid=" + eid +
                ", ename='" + ename + '\'' +
                '}';
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public List<Grole> getGroles() {
        return groles;
    }

    public void setGroles(List<Grole> groles) {
        this.groles = groles;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}
