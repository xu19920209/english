package com.tuyue.pojo;

import javax.persistence.*;

import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
@Entity
public class Acourse {

    private int aid;
    private String aname;
    private Timestamp creatTime;
    private Integer isDel;

    @Id
    @GeneratedValue
    @Column(name = "aid")
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Basic
    @Column(name = "aname")
    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    @Basic
    @Column(name = "creat_time")
    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Acourse acourse = (Acourse) object;

        if (aid != acourse.aid) return false;
        if (aname != null ? !aname.equals(acourse.aname) : acourse.aname != null) return false;
        if (creatTime != null ? !creatTime.equals(acourse.creatTime) : acourse.creatTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aid;
        result = 31 * result + (aname != null ? aname.hashCode() : 0);
        result = 31 * result + (creatTime != null ? creatTime.hashCode() : 0);
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
