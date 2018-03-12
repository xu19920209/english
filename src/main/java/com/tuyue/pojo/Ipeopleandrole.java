package com.tuyue.pojo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
@Entity
public class Ipeopleandrole {
    private int iid;
    private Integer fid;
    private Integer gid;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "iid")
    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
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
    @Column(name = "gid")
    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ipeopleandrole that = (Ipeopleandrole) o;

        if (iid != that.iid) return false;
        if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;
        if (gid != null ? !gid.equals(that.gid) : that.gid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iid;
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        result = 31 * result + (gid != null ? gid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ipeopleandrole{" +
                "iid=" + iid +
                ", fid=" + fid +
                ", gid=" + gid +
                '}';
    }
}
