package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
@Entity
@Table(name = "point_like", schema = "aaenglish", catalog = "")
public class PointLike {
    private int poId;
    private Integer nid;
    private Timestamp pointTime;
    private Integer byNid;

    @Id
    @GeneratedValue
    @Column(name = "po_id")
    public int getPoId() {
        return poId;
    }

    public void setPoId(int poId) {
        this.poId = poId;
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
    @Column(name = "point_time")
    public Timestamp getPointTime() {
        return pointTime;
    }

    public void setPointTime(Timestamp pointTime) {
        this.pointTime = pointTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PointLike pointLike = (PointLike) o;

        if (poId != pointLike.poId) return false;
        if (nid != null ? !nid.equals(pointLike.nid) : pointLike.nid != null) return false;
        if (pointTime != null ? !pointTime.equals(pointLike.pointTime) : pointLike.pointTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = poId;
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        result = 31 * result + (pointTime != null ? pointTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "by_nid")
    public Integer getByNid() {
        return byNid;
    }

    public void setByNid(Integer byNid) {
        this.byNid = byNid;
    }
}
