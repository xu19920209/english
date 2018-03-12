package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
@Entity
@Table(name = "wadult_work_details", schema = "aaenglish", catalog = "")
public class WadultWorkDetails {
    private int wid;
    private Integer vid;
    private String adultTape;
    private Double workScore;
    private Integer nid;
    private String erro;
    private Timestamp finishTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wid")
    public int getWid() {
        return wid;
    }

    public void setWid(int wid) {
        this.wid = wid;
    }

    @Basic
    @Column(name = "vid")
    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    @Basic
    @Column(name = "adultTape")
    public String getAdultTape() {
        return adultTape;
    }

    public void setAdultTape(String adultTape) {
        this.adultTape = adultTape;
    }

    @Basic
    @Column(name = "workScore")
    public Double getWorkScore() {
        return workScore;
    }

    public void setWorkScore(Double workScore) {
        this.workScore = workScore;
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
    @Column(name = "erro")
    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    @Basic
    @Column(name = "finishTime")
    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WadultWorkDetails that = (WadultWorkDetails) o;

        if (wid != that.wid) return false;
        if (vid != null ? !vid.equals(that.vid) : that.vid != null) return false;
        if (adultTape != null ? !adultTape.equals(that.adultTape) : that.adultTape != null) return false;
        if (workScore != null ? !workScore.equals(that.workScore) : that.workScore != null) return false;
        if (nid != null ? !nid.equals(that.nid) : that.nid != null) return false;
        if (erro != null ? !erro.equals(that.erro) : that.erro != null) return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wid;
        result = 31 * result + (vid != null ? vid.hashCode() : 0);
        result = 31 * result + (adultTape != null ? adultTape.hashCode() : 0);
        result = 31 * result + (workScore != null ? workScore.hashCode() : 0);
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        result = 31 * result + (erro != null ? erro.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        return result;
    }
}
