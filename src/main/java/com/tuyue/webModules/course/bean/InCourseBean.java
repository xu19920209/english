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
    private List<Ctopic>ctopics;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public List<Ctopic> getCtopics() {
        return ctopics;
    }

    public void setCtopics(List<Ctopic> ctopics) {
        this.ctopics = ctopics;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}
