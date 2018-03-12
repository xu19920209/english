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
    private String bid;
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
    @Column(name = "bid")
    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QschoolCourse that = (QschoolCourse) o;

        if (qid != null ? !qid.equals(that.qid) : that.qid != null) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (eid != null ? !eid.equals(that.eid) : that.eid != null) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = qid != null ? qid.hashCode() : 0;
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (eid != null ? eid.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
