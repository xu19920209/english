package com.tuyue.pojo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
@Entity
public class Ctopic {
    private int cid;
    private String csentence;
    private String ctranslate;
    private Integer bid;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "cid")
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "csentence")
    public String getCsentence() {
        return csentence;
    }

    public void setCsentence(String csentence) {
        this.csentence = csentence;
    }

    @Basic
    @Column(name = "ctranslate")
    public String getCtranslate() {
        return ctranslate;
    }

    public void setCtranslate(String ctranslate) {
        this.ctranslate = ctranslate;
    }

    @Basic
    @Column(name = "bid")
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ctopic ctopic = (Ctopic) o;

        if (cid != ctopic.cid) return false;
        if (csentence != null ? !csentence.equals(ctopic.csentence) : ctopic.csentence != null) return false;
        if (ctranslate != null ? !ctranslate.equals(ctopic.ctranslate) : ctopic.ctranslate != null) return false;
        if (bid != null ? !bid.equals(ctopic.bid) : ctopic.bid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid;
        result = 31 * result + (csentence != null ? csentence.hashCode() : 0);
        result = 31 * result + (ctranslate != null ? ctranslate.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ctopic{" +
                "cid=" + cid +
                ", csentence='" + csentence + '\'' +
                ", ctranslate='" + ctranslate + '\'' +
                ", bid=" + bid +
                '}';
    }
}
