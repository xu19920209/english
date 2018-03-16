package com.tuyue.pojo;

import org.hibernate.id.GUIDGenerator;
import sun.nio.cs.Surrogate;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/12.
 */
@Entity
@Table(name = "sclass_work", schema = "aaenglish", catalog = "")
public class SclassWork {

    private String sid;
    private Integer oid;
    private Integer cid;
    private Timestamp layoutTime;
    private Integer fid;
    private Integer aid;
    private Integer bid;
    private java.sql.Date startTime;
    private Integer levelId;

    @Id
    @Column(name = "sid")
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "oid")
    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "cid")
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "layoutTime")
    public Timestamp getLayoutTime() {
        return layoutTime;
    }

    public void setLayoutTime(Timestamp layoutTime) {
        this.layoutTime = layoutTime;
    }

    @Basic
    @Column(name = "fid")
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
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
    @Column(name = "bid")
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "startTime")
    public java.sql.Date getStartTime() {
        return startTime;
    }

    public void setStartTime(java.sql.Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "level_id")
    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        SclassWork that = (SclassWork) object;

        if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;
        if (oid != null ? !oid.equals(that.oid) : that.oid != null) return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;
        if (layoutTime != null ? !layoutTime.equals(that.layoutTime) : that.layoutTime != null) return false;
        if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (levelId != null ? !levelId.equals(that.levelId) : that.levelId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid != null ? sid.hashCode() : 0;
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (layoutTime != null ? layoutTime.hashCode() : 0);
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (levelId != null ? levelId.hashCode() : 0);
        return result;
    }
}
