package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/21.
 */
@Entity
public class Encourage {
    private int id;
    private String encourage;
    private Integer type;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "encourage")
    public String getEncourage() {
        return encourage;
    }

    public void setEncourage(String encourage) {
        this.encourage = encourage;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Encourage encourage1 = (Encourage) o;

        if (id != encourage1.id) return false;
        if (encourage != null ? !encourage.equals(encourage1.encourage) : encourage1.encourage != null) return false;
        if (type != null ? !type.equals(encourage1.type) : encourage1.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (encourage != null ? encourage.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
