package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/10/18.
 */
@Entity
@Table(name = "a_course_good", schema = "aaenglish", catalog = "")
public class ACourseGood {
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
    private Double commission;
    private Integer peopleNum;// 班级人数
    private String teacherName;//代课老师名字
    private String goClassWeek;//上课周次
    private String goClassTime;//上课时间
    private String linkTel;//联系电话
    private Timestamp endClassTime;
    private Timestamp startClassTime;
    private Integer isArrang;
    private Integer isAppointment;
    private Integer eid;//学校id

    private Integer teachQuality;//教学质量
    private Integer teachEnvironment;//教学环境
    private String address;

    @Transient
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private Integer flag;

    @Transient
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Transient
    public Integer getTeachQuality() {
        return teachQuality;
    }

    public void setTeachQuality(Integer teachQuality) {
        this.teachQuality = teachQuality;
    }

    @Transient
    public Integer getTeachEnvironment() {
        return teachEnvironment;
    }

    public void setTeachEnvironment(Integer teachEnvironment) {
        this.teachEnvironment = teachEnvironment;
    }

    @Id
    @GeneratedValue
    @Column(name = "courseId")
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "courseName")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "coursePrice")
    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }

    @Basic
    @Column(name = "courseTime")
    public Integer getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Integer courseTime) {
        this.courseTime = courseTime;
    }

    @Basic
    @Column(name = "courseIntroduce")
    public String getCourseIntroduce() {
        return courseIntroduce;
    }

    public void setCourseIntroduce(String courseIntroduce) {
        this.courseIntroduce = courseIntroduce;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "vipPrice")
    public Double getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Double vipPrice) {
        this.vipPrice = vipPrice;
    }

    @Basic
    @Column(name = "isAdded")
    public Integer getIsAdded() {
        return isAdded;
    }

    public void setIsAdded(Integer isAdded) {
        this.isAdded = isAdded;
    }

    @Basic
    @Column(name = "ItemNo")
    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Basic
    @Column(name = "inputTime")
    public Timestamp getInputTime() {
        return inputTime;
    }

    public void setInputTime(Timestamp inputTime) {
        this.inputTime = inputTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ACourseGood that = (ACourseGood) o;

        if (courseId != that.courseId) return false;
        if (courseName != null ? !courseName.equals(that.courseName) : that.courseName != null) return false;
        if (coursePrice != null ? !coursePrice.equals(that.coursePrice) : that.coursePrice != null) return false;
        if (courseTime != null ? !courseTime.equals(that.courseTime) : that.courseTime != null) return false;
        if (courseIntroduce != null ? !courseIntroduce.equals(that.courseIntroduce) : that.courseIntroduce != null)
            return false;
        if (cover != null ? !cover.equals(that.cover) : that.cover != null) return false;
        if (vipPrice != null ? !vipPrice.equals(that.vipPrice) : that.vipPrice != null) return false;
        if (isAdded != null ? !isAdded.equals(that.isAdded) : that.isAdded != null) return false;
        if (itemNo != null ? !itemNo.equals(that.itemNo) : that.itemNo != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (inputTime != null ? !inputTime.equals(that.inputTime) : that.inputTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = courseId;
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (coursePrice != null ? coursePrice.hashCode() : 0);
        result = 31 * result + (courseTime != null ? courseTime.hashCode() : 0);
        result = 31 * result + (courseIntroduce != null ? courseIntroduce.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + (vipPrice != null ? vipPrice.hashCode() : 0);
        result = 31 * result + (isAdded != null ? isAdded.hashCode() : 0);
        result = 31 * result + (itemNo != null ? itemNo.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (inputTime != null ? inputTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "commission")
    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    @Basic
    @Column(name = "peopleNum")
    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Basic
    @Column(name = "teacherName")
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Basic
    @Column(name = "goClassWeek")
    public String getGoClassWeek() {
        return goClassWeek;
    }

    public void setGoClassWeek(String goClassWeek) {
        this.goClassWeek = goClassWeek;
    }

    @Basic
    @Column(name = "goClassTime")
    public String getGoClassTime() {
        return goClassTime;
    }

    public void setGoClassTime(String goClassTime) {
        this.goClassTime = goClassTime;
    }

    @Basic
    @Column(name = "linkTel")
    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }

    @Basic
    @Column(name = "endClassTime")
    public Timestamp getEndClassTime() {
        return endClassTime;
    }

    public void setEndClassTime(Timestamp endClassTime) {
        this.endClassTime = endClassTime;
    }

    @Basic
    @Column(name = "startClassTime")
    public Timestamp getStartClassTime() {
        return startClassTime;
    }

    public void setStartClassTime(Timestamp startClassTime) {
        this.startClassTime = startClassTime;
    }

    @Basic
    @Column(name = "isArrang")
    public Integer getIsArrang() {
        return isArrang;
    }

    public void setIsArrang(Integer isArrang) {
        this.isArrang = isArrang;
    }

    @Basic
    @Column(name = "isAppointment")
    public Integer getIsAppointment() {
        return isAppointment;
    }

    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
    }

    @Basic
    @Column(name = "eid")
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }
}
