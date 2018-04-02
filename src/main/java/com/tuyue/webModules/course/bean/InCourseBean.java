package com.tuyue.webModules.course.bean;

import com.tuyue.pojo.Ctopic;

import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/3/12.
 */
public class InCourseBean {
    private String bname;
    private int bid;
    private String levelName;

    private List<Ctopic>list;

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public List<Ctopic> getList() {
        return list;
    }

    public void setList(List<Ctopic> list) {
        this.list = list;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    @Override
    public String toString() {
        return "InCourseBean{" +
                "bname='" + bname + '\'' +
                ", bid=" + bid +
                ", levelName='" + levelName + '\'' +
                ", list=" + list +
                '}';
    }
}
