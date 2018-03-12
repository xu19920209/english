package com.tuyue.webModules.course.bean;

import com.tuyue.pojo.Ctopic;

import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
public class CourseDetilesBean {
    private Integer aid;
    private Integer levelId;
    private String aname;
    private String levelName;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public String toString() {
        return "CourseDetilesBean{" +
                "levelId=" + levelId +
                ", aid=" + aid +
                ", aname='" + aname + '\'' +
                ", levelName='" + levelName + '\'' +
                '}';
    }
}
