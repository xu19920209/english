package com.tuyue.webModules.work.bean;

import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/12.
 */
public class WorkListBean {
    private String sid;
    private String className;
    private String name;
    private String aname;
    private String bname;
    private Timestamp layoutTime;
    private int oid;
    private int num;
    public String getSid() {
        return sid;
    }

    public void setSid(String tid) {
        this.sid = tid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Timestamp getLayoutTime() {
        return layoutTime;
    }

    public void setLayoutTime(Timestamp layoutTime) {
        this.layoutTime = layoutTime;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public WorkListBean() {
    }


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "WorkListBean{" +
                "sid='" + sid + '\'' +
                ", className='" + className + '\'' +
                ", name='" + name + '\'' +
                ", aname='" + aname + '\'' +
                ", bname='" + bname + '\'' +
                ", layoutTime='" + layoutTime + '\'' +
                ", oid=" + oid +
                ", num=" + num +
                '}';
    }
}
