package com.tuyue.pojo;

import javax.persistence.*;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/9.
 * @Modified By:
 */
@Entity
public class Replymodule {
    private int rid;
    private String comLanguage;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid", nullable = false)
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    @Basic
    @Column(name = "comLanguage", nullable = true, length = 50)
    public String getComLanguage() {
        return comLanguage;
    }

    public void setComLanguage(String comLanguage) {
        this.comLanguage = comLanguage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Replymodule that = (Replymodule) o;

        if (rid != that.rid) return false;
        if (comLanguage != null ? !comLanguage.equals(that.comLanguage) : that.comLanguage != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rid;
        result = 31 * result + (comLanguage != null ? comLanguage.hashCode() : 0);
        return result;
    }
}
