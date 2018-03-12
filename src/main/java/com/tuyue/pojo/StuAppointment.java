package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/7.
 */
@Entity
@Table(name = "stu_appointment", schema = "aaenglish", catalog = "")
public class StuAppointment {
    private int appointmentId;
    private Integer courseId;
    private Integer classHourId;
    private Integer nid;
    private Timestamp appointmentTime;

    @Id
    @GeneratedValue
    @Column(name = "appointmentId")
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Basic
    @Column(name = "courseId")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
    @Column(name = "nid")
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StuAppointment that = (StuAppointment) o;

        if (appointmentId != that.appointmentId) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (classHourId != null ? !classHourId.equals(that.classHourId) : that.classHourId != null) return false;
        if (nid != null ? !nid.equals(that.nid) : that.nid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = appointmentId;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (classHourId != null ? classHourId.hashCode() : 0);
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "appointmentTime")
    public Timestamp getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Timestamp appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}
