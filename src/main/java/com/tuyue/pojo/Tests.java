package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
@Entity
public class Tests {
    private int testId;
    private String name;
    private String english;
    private String chinese;
    private Integer eid;
    private Timestamp upTime;

    @Id
    @GeneratedValue
    @Column(name = "test_id")
    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "eid")
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tests tests = (Tests) o;

        if (testId != tests.testId) return false;
        if (name != null ? !name.equals(tests.name) : tests.name != null) return false;
        if (english != null ? !english.equals(tests.english) : tests.english != null) return false;
        if (chinese != null ? !chinese.equals(tests.chinese) : tests.chinese != null) return false;
        if (eid != null ? !eid.equals(tests.eid) : tests.eid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (english != null ? english.hashCode() : 0);
        result = 31 * result + (chinese != null ? chinese.hashCode() : 0);
        result = 31 * result + (eid != null ? eid.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "up_time")
    public Timestamp getUpTime() {
        return upTime;
    }

    public void setUpTime(Timestamp upTime) {
        this.upTime = upTime;
    }
}
