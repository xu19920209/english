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
    private Integer levelId;

    @Id
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

        Bhour bhour = (Bhour) object;

        if (bid != bhour.bid) return false;
        if (bname != null ? !bname.equals(bhour.bname) : bhour.bname != null) return false;
        if (levelId != null ? !levelId.equals(bhour.levelId) : bhour.levelId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid;
        result = 31 * result + (bname != null ? bname.hashCode() : 0);
        result = 31 * result + (levelId != null ? levelId.hashCode() : 0);
        return result;
    }
}
