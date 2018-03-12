package com.tuyue.webModules.adult.bean;

import com.tuyue.dao.IBaseDao;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/12.
 */
public class InsertWorBean {
    private Integer oid;
    private Integer fid;
    private String cid;
    private Integer aid;
    private Integer bid;
    private Integer levelId;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }
}
