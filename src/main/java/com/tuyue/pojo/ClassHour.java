package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/7.
 */
@Entity
@Table(name = "class_hour", schema = "aaenglish", catalog = "")
public class ClassHour {
    private int classHourId;
    private Integer courseId;
    private String classHourName;
    private Timestamp goClassTime;

    @Id
    @GeneratedValue
    @Column(name = "classHourId")
    public int getClassHourId() {
        return classHourId;
    }

    public void setClassHourId(int classHourId) {
        this.classHourId = classHourId;
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
    @Column(name = "classHourName")
    public String getClassHourName() {
        return classHourName;
    }

    public void setClassHourName(String classHourName) {
        this.classHourName = classHourName;
    }

    @Basic
    @Column(name = "goClassTime")
    public Timestamp getGoClassTime() {
        return goClassTime;
    }

    public void setGoClassTime(Timestamp goClassTime) {
        this.goClassTime = goClassTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassHour classHour = (ClassHour) o;

        if (classHourId != classHour.classHourId) return false;
        if (courseId != null ? !courseId.equals(classHour.courseId) : classHour.courseId != null) return false;
        if (classHourName != null ? !classHourName.equals(classHour.classHourName) : classHour.classHourName != null)
            return false;
        if (goClassTime != null ? !goClassTime.equals(classHour.goClassTime) : classHour.goClassTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classHourId;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (classHourName != null ? classHourName.hashCode() : 0);
        result = 31 * result + (goClassTime != null ? goClassTime.hashCode() : 0);
        return result;
    }
}
