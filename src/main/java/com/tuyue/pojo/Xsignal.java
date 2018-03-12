package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
@Entity
public class Xsignal {
    private int xid;
    private Integer nid;
    private Integer count;
    private String time;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "xid")
    public int getXid() {
        return xid;
    }

    public void setXid(int xid) {
        this.xid = xid;
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
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Xsignal xsignal = (Xsignal) o;

        if (xid != xsignal.xid) return false;
        if (nid != null ? !nid.equals(xsignal.nid) : xsignal.nid != null) return false;
        if (count != null ? !count.equals(xsignal.count) : xsignal.count != null) return false;
        if (time != null ? !time.equals(xsignal.time) : xsignal.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = xid;
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
