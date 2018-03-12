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

    public Acourse(int aid, String aname) {
        this.aid = aid;
        this.aname = aname;
    }

    public Acourse() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Acourse acourse = (Acourse) o;

        if (aid != acourse.aid) return false;
        if (aname != null ? !aname.equals(acourse.aname) : acourse.aname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aid;
        result = 31 * result + (aname != null ? aname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Acourse{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                '}';
    }

    @Basic
    @Column(name = "creat_time")
    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }
}
