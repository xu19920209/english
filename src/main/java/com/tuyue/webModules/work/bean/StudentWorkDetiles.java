package com.tuyue.webModules.work.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/12.
 */
public class StudentWorkDetiles {
    private String csentence;
    private Double workScore;
    private String studentTape;

    public String getCsentence() {
        return csentence;
    }

    public void setCsentence(String csentence) {
        this.csentence = csentence;
    }

    public Double getWorkScore() {
        return workScore;
    }

    public void setWorkScore(Double workScore) {
        this.workScore = workScore;
    }

    public String getStudentTape() {
        return studentTape;
    }

    public void setStudentTape(String studentTape) {
        this.studentTape = studentTape;
    }

    @Override
    public String toString() {
        return "StudentWorkDetiles{" +
                "csentence='" + csentence + '\'' +
                ", workScore=" + workScore +
                ", studentTape='" + studentTape + '\'' +
                '}';
    }
}
