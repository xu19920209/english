package com.tuyue.pojo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
@Entity
public class Grole {
    private int gid;
    private String role;
    private String comment;
    private Integer isFlag;
    private Integer eid;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "gid")
    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "isFlag")
    public Integer getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(Integer isFlag) {
        this.isFlag = isFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grole grole = (Grole) o;

        if (gid != grole.gid) return false;
        if (role != null ? !role.equals(grole.role) : grole.role != null) return false;
        if (comment != null ? !comment.equals(grole.comment) : grole.comment != null) return false;
        if (isFlag != null ? !isFlag.equals(grole.isFlag) : grole.isFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (isFlag != null ? isFlag.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "eid")
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Override
    public String toString() {
        return "Grole{" +
                "gid=" + gid +
                ", role='" + role + '\'' +
                ", comment='" + comment + '\'' +
                ", isFlag=" + isFlag +
                ", eid=" + eid +
                '}';
    }
}
