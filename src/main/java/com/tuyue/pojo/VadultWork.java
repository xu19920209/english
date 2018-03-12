package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/13.
 */
@Entity
@Table(name = "vadult_work", schema = "aaenglish", catalog = "")
public class VadultWork {
    private int vid;
    private Integer aid;
    private Integer bid;
    private Integer cid;
    private Timestamp layoutTime;
    private Integer vflag;
    private Integer levelId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vid")
    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
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
    @Column(name = "cid")
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VadultWork that = (VadultWork) o;

        if (vid != that.vid) return false;
        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (cid != null ? !cid.equals(that.cid) : that.cid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = vid;
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        return result;
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
    @Column(name = "vflag")
    public Integer getVflag() {
        return vflag;
    }

    public void setVflag(Integer vflag) {
        this.vflag = vflag;
    }

    @Basic
    @Column(name = "level_id")
    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
}
