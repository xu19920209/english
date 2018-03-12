package com.tuyue.webModules.courseGoods.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/7.
 */
public class PkMangerList {
    private int courseId;
    private String courseName;
    private String schoolName;
    private String address;
    private int courseTime;
    private int peopleNum;
    private int payPeopleNum;
    private int isArrang;//是否排课
    private int isAppointment;//是否开放预约

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(int courseTime) {
        this.courseTime = courseTime;
    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getPayPeopleNum() {
        return payPeopleNum;
    }

    public void setPayPeopleNum(int payPeopleNum) {
        this.payPeopleNum = payPeopleNum;
    }

    public int getIsArrang() {
        return isArrang;
    }

    public void setIsArrang(int isArrang) {
        this.isArrang = isArrang;
    }

    public int getIsAppointment() {
        return isAppointment;
    }

    public void setIsAppointment(int isAppointment) {
        this.isAppointment = isAppointment;
    }
}
