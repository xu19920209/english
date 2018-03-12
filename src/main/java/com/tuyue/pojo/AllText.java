package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
@Entity
@Table(name = "all_text", schema = "aaenglish", catalog = "")
public class AllText {
    private int allId;
    private Integer bid;
    private String english;
    private String chinese;
    private Integer aid;
    private Integer oid;

    @Id
    @GeneratedValue
    @Column(name = "all_id")
    public int getAllId() {
        return allId;
    }

    public void setAllId(int allId) {
        this.allId = allId;
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
    @Column(name = "english")
    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    @Basic
    @Column(name = "chinese")
    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
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
    @Column(name = "oid")
    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllText allText = (AllText) o;

        if (allId != allText.allId) return false;
        if (bid != null ? !bid.equals(allText.bid) : allText.bid != null) return false;
        if (english != null ? !english.equals(allText.english) : allText.english != null) return false;
        if (chinese != null ? !chinese.equals(allText.chinese) : allText.chinese != null) return false;
        if (aid != null ? !aid.equals(allText.aid) : allText.aid != null) return false;
        if (oid != null ? !oid.equals(allText.oid) : allText.oid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = allId;
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (english != null ? english.hashCode() : 0);
        result = 31 * result + (chinese != null ? chinese.hashCode() : 0);
        result = 31 * result + (aid != null ? aid.hashCode() : 0);
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        return result;
    }
}
