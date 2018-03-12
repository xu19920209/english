package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/7.
 */
@Entity
@Table(name = "stu_go_class", schema = "aaenglish", catalog = "")
public class StuGoClass {
    private int stuGoClassId;
    private Integer classHourId;
    private Integer isSign;
    private Integer nid;
    private Integer isAppointment;

    @Id
    @GeneratedValue
    @Column(name = "stuGoClassId")
    public int getStuGoClassId() {
        return stuGoClassId;
    }

    public void setStuGoClassId(int stuGoClassId) {
        this.stuGoClassId = stuGoClassId;
    }

    @Basic
    @Column(name = "classHourId")
    public Integer getClassHourId() {
        return classHourId;
    }

    public void setClassHourId(Integer classHourId) {
        this.classHourId = classHourId;
    }

    @Basic
    @Column(name = "isSign")
    public Integer getIsSign() {
        return isSign;
    }

    public void setIsSign(Integer isSign) {
        this.isSign = isSign;
    }

    @Basic
    @Column(name = "nid")
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    @Basic
    @Column(name = "isAppointment")
    public Integer getIsAppointment() {
        return isAppointment;
    }

    public void setIsAppointment(Integer isAppointment) {
        this.isAppointment = isAppointment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StuGoClass that = (StuGoClass) o;

        if (stuGoClassId != that.stuGoClassId) return false;
        if (classHourId != null ? !classHourId.equals(that.classHourId) : that.classHourId != null) return false;
        if (isSign != null ? !isSign.equals(that.isSign) : that.isSign != null) return false;
        if (nid != null ? !nid.equals(that.nid) : that.nid != null) return false;
        if (isAppointment != null ? !isAppointment.equals(that.isAppointment) : that.isAppointment != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stuGoClassId;
        result = 31 * result + (classHourId != null ? classHourId.hashCode() : 0);
        result = 31 * result + (isSign != null ? isSign.hashCode() : 0);
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        result = 31 * result + (isAppointment != null ? isAppointment.hashCode() : 0);
        return result;
    }
}
