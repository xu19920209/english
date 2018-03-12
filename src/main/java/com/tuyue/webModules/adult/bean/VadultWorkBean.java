package com.tuyue.webModules.adult.bean;

import com.tuyue.pojo.Ctopic;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/14.
 * @Modified By:
 */
public class VadultWorkBean {
    private Integer aid;
    private String aname;
    private Integer bid;
    private String bname;
    private Timestamp layoutTime;
    private String vids;
    private List<Ctopic> ctopics;


    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Timestamp getLayoutTime() {
        return layoutTime;
    }

    public void setLayoutTime(Timestamp layoutTime) {
        this.layoutTime = layoutTime;
    }

    public String getVids() {
        return vids;
    }

    public void setVids(String vids) {
        this.vids = vids;
    }

    public List<Ctopic> getCtopics() {
        return ctopics;
    }

    public void setCtopics(List<Ctopic> ctopics) {
        this.ctopics = ctopics;
    }
}
