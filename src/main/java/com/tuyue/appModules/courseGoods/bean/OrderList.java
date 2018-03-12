package com.tuyue.appModules.courseGoods.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/11.
 */
public class OrderList {
    private int courseOrderId;
    private String orderNo;
    private String cover;
    private String courseName;
    private Double coursePrice;
    private Double vipPrice;
    private int userFinshNum;
    private int classFinshNum;
    private int courseTime;
    private int courseId;
    private int flag;//1可以购买 2不可以购买

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserFinshNum() {
        return userFinshNum;
    }

    public void setUserFinshNum(int userFinshNum) {
        this.userFinshNum = userFinshNum;
    }

    public int getClassFinshNum() {
        return classFinshNum;
    }

    public void setClassFinshNum(int classFinshNum) {
        this.classFinshNum = classFinshNum;
    }

    public int getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(int courseTime) {
        this.courseTime = courseTime;
    }

    public int getCourseOrderId() {
        return courseOrderId;
    }

    public void setCourseOrderId(int courseOrderId) {
        this.courseOrderId = courseOrderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Double vipPrice) {
        this.vipPrice = vipPrice;
    }
}
