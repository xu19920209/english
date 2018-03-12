package com.tuyue.webModules.courseGoods.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/7.
 */
public class CourseHourDetailsList {
  private int currentHour;
  private int peopleNum;
  private int goClassNum;
  private String goClassTime;

    public int getCurrentHour() {
        return currentHour;
    }

    public void setCurrentHour(int currentHour) {
        this.currentHour = currentHour;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getGoClassNum() {
        return goClassNum;
    }

    public void setGoClassNum(int goClassNum) {
        this.goClassNum = goClassNum;
    }

    public String getGoClassTime() {
        return goClassTime;
    }

    public void setGoClassTime(String goClassTime) {
        this.goClassTime = goClassTime;
    }
}
