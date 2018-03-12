package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/11.
 */
@Entity
@Table(name = "course_comment", schema = "aaenglish", catalog = "")
public class CourseComment {
    private int id;
    private Integer courseId;
    private Integer nid;
    private Integer teachQuality;
    private Integer teachEnvironment;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "nid")
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    @Basic
    @Column(name = "teach_quality")
    public Integer getTeachQuality() {
        return teachQuality;
    }

    public void setTeachQuality(Integer teachQuality) {
        this.teachQuality = teachQuality;
    }

    @Basic
    @Column(name = "teach_environment")
    public Integer getTeachEnvironment() {
        return teachEnvironment;
    }

    public void setTeachEnvironment(Integer teachEnvironment) {
        this.teachEnvironment = teachEnvironment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseComment that = (CourseComment) o;

        if (id != that.id) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (nid != null ? !nid.equals(that.nid) : that.nid != null) return false;
        if (teachQuality != null ? !teachQuality.equals(that.teachQuality) : that.teachQuality != null) return false;
        if (teachEnvironment != null ? !teachEnvironment.equals(that.teachEnvironment) : that.teachEnvironment != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        result = 31 * result + (teachQuality != null ? teachQuality.hashCode() : 0);
        result = 31 * result + (teachEnvironment != null ? teachEnvironment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CourseComment{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", nid=" + nid +
                ", teachQuality=" + teachQuality +
                ", teachEnvironment=" + teachEnvironment +
                '}';
    }
}
