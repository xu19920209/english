package com.tuyue.pojo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
@Entity
public class Bhour {
    private int bid;
    private String bname;
    private Integer aid;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "bid")
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "bname")
    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    @Basic
    @Column(name = "aid")
    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bhour bhour = (Bhour) o;

        if (bid != bhour.bid) return false;
        if (bname != null ? !bname.equals(bhour.bname) : bhour.bname != null) return false;
        if (aid != null ? !aid.equals(bhour.aid) : bhour.aid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid;
        result = 31 * result + (bname != null ? bname.hashCode() : 0);
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bhour{" +
                "bid=" + bid +
                ", bname='" + bname + '\'' +
                ", aid=" + aid +
                '}';
    }
}
