package com.tuyue.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/8.
 */
@Entity
public class Jroleandresource {
    private Integer gid;
    private Integer hid;
    private String jid;

    @Basic
    @Column(name = "gid")
    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Basic
    @Column(name = "hid")
    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    @Id
    @Column(name = "jid")
    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jroleandresource that = (Jroleandresource) o;

        if (gid != null ? !gid.equals(that.gid) : that.gid != null) return false;
        if (hid != null ? !hid.equals(that.hid) : that.hid != null) return false;
        if (jid != null ? !jid.equals(that.jid) : that.jid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = gid != null ? gid.hashCode() : 0;
        result = 31 * result + (hid != null ? hid.hashCode() : 0);
        result = 31 * result + (jid != null ? jid.hashCode() : 0);
        return result;
    }
}
