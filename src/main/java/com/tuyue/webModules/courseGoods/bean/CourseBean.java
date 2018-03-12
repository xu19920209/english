package com.tuyue.webModules.courseGoods.bean;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/10/19.
 */
public class CourseBean {
    private int courseId;
    private String courseName;
    private Double coursePrice;
    private Integer courseTime;
    private String courseIntroduce;
    private String cover;
    private Double vipPrice;
    private Integer isAdded;
    private String itemNo;
    private String details;
    private Timestamp inputTime;

    private Integer peopleNum;//班级容纳人数
    private String teacherName;//代课老师
    private String[] goClassWeek;//上课周次
    private String[] goClassTime;//上课时间
    private String linkTel;//联系电话
    private Timestamp endClassTime;//结课时间
    private Timestamp startClassTime;//开课时间
    private Integer isArrang;//是否排课
    private Integer isAppointment;
    private Integer eid;//学校id
    private Double commission;


    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String[] getGoClassTime() {
        return goClassTime;
    }

    public void setGoClassTime(String[] goClassTime) {
        this.goClassTime = goClassTime;
    }

    public String[] getGoClassWeek() {
        return goClassWeek;
    }

    public void setGoClassWeek(String[] goClassWeek) {
        this.goClassWeek = goClassWeek;
    }


    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    public Timestamp getEndClassTime() {
        return endClassTime;
    }

    public void setEndClassTime(Timestamp endClassTime) {
        this.endClassTime = endClassTime;
    }

    public Timestamp getStartClassTime() {
        return startClassTime;
    }

    public void setStartClassTime(Timestamp startClassTime) {
        this.startClassTime = startClassTime;
    }

    public Integer getIsArrang() {
        return isArrang;
    }

    public void setIsArrang(Integer isArrang) {
        this.isArrang = isArrang;
    }

    public Integer getIsAppointment() {
        return isAppointment;
    }

    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

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

    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Integer getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Integer courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseIntroduce() {
        return courseIntroduce;
    }

    public void setCourseIntroduce(String courseIntroduce) {
        this.courseIntroduce = courseIntroduce;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Double vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Integer getIsAdded() {
        return isAdded;
    }

    public void setIsAdded(Integer isAdded) {
        this.isAdded = isAdded;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }


    public Timestamp getInputTime() {
        return inputTime;
    }

    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "CourseBean{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", coursePrice=" + coursePrice +
                ", courseTime=" + courseTime +
                ", courseIntroduce='" + courseIntroduce + '\'' +
                ", cover='" + cover + '\'' +
                ", vipPrice=" + vipPrice +
                ", isAdded=" + isAdded +
                ", itemNo='" + itemNo + '\'' +
                ", details='" + details + '\'' +
                ", inputTime=" + inputTime +
                ", peopleNum=" + peopleNum +
                ", teacherName='" + teacherName + '\'' +
                ", goClassWeek=" + Arrays.toString(goClassWeek) +
                ", goClassTime=" + Arrays.toString(goClassTime) +
                ", linkTel='" + linkTel + '\'' +
                ", endClassTime=" + endClassTime +
                ", startClassTime=" + startClassTime +
                ", isArrang=" + isArrang +
                ", isAppointment=" + isAppointment +
                ", eid=" + eid +
                ", commission=" + commission +
                '}';
    }
}
