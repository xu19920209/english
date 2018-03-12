package com.tuyue.webModules.courseGoods.bean;

import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/8.
 */
public class ClassChart {
    private int courseTime;
    private String goClassWeek;
    private String startClassTime;
    private String goClassTime;

    public int getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(int courseTime) {
        this.courseTime = courseTime;
    }

    public String getGoClassWeek() {
        return goClassWeek;
    }

    public void setGoClassWeek(String goClassWeek) {
        this.goClassWeek = goClassWeek;
    }

    public String getStartClassTime() {
        return startClassTime;
    }

    public void setStartClassTime(String startClassTime) {
        this.startClassTime = startClassTime;
    }

    public String getGoClassTime() {
        return goClassTime;
    }

    public void setGoClassTime(String goClassTime) {
        this.goClassTime = goClassTime;
    }
}
