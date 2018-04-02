package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/3/12.
 */
@Entity
@Table(name = "course_level", schema = "aaenglish", catalog = "")
public class CourseLevel {
    private int levelId;
    private String levelName;
    private Integer aid;
    private Integer isDel;

    @Id
    @Column(name = "level_id")
    @GeneratedValue
    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    @Basic
    @Column(name = "level_name")
    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Basic
    @Column(name = "aid")
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        CourseLevel that = (CourseLevel) object;

        if (levelId != that.levelId) return false;
        if (levelName != null ? !levelName.equals(that.levelName) : that.levelName != null) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = levelId;
        result = 31 * result + (levelName != null ? levelName.hashCode() : 0);
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "is_del")
    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
