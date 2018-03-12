package com.tuyue.appModules.course.bean;

import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/15.
 */
public class ConsultListBean {
    private String aname;
    private String bname;
    private Integer replyStatus;//1回复2未回复
    private Timestamp consultTime;//请教时间
    private Integer tid;
    private Integer cid;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    public Timestamp getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(Timestamp consultTime) {
        this.consultTime = consultTime;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }
}
