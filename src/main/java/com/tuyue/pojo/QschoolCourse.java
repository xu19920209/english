package com.tuyue.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/9.
 */
@Entity
@Table(name = "qschool_course", schema = "aaenglish", catalog = "")
public class QschoolCourse {
    private String qid;
    private Integer aid;
    private Integer eid;
    private String levelId;
    private String path;

    @Id
    @Column(name = "qid")
    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    @Basic
    @Column(name = "aid")
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "eid")
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "level_id")
    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
