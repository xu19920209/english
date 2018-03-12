package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
@Entity
@Table(name = "test_grade", schema = "aaenglish", catalog = "")
public class TestGrade {
    private int teGrId;

    private Double grade;
    private Integer nid;
    private Integer oid;
    private Integer testId;
    private Integer sysGrade;
    private Double coefficient;

    @Id
    @GeneratedValue
    @Column(name = "te_gr_id")
    public int getTeGrId() {
        return teGrId;
    }

    public void setTeGrId(int teGrId) {
        this.teGrId = teGrId;
    }

    @Basic
    @Column(name = "grade")
    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
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

        TestGrade testGrade = (TestGrade) o;

        if (teGrId != testGrade.teGrId) return false;

        if (grade != null ? !grade.equals(testGrade.grade) : testGrade.grade != null) return false;
        if (nid != null ? !nid.equals(testGrade.nid) : testGrade.nid != null) return false;
        if (oid != null ? !oid.equals(testGrade.oid) : testGrade.oid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teGrId;

        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "test_id")
    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "sys_grade")
    public Integer getSysGrade() {
        return sysGrade;
    }

    public void setSysGrade(Integer sysGrade) {
        this.sysGrade = sysGrade;
    }

    @Basic
    @Column(name = "coefficient")
    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }
}
