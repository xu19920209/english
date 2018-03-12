package com.tuyue.webModules.work.bean;

import com.tuyue.pojo.Ctopic;
import com.tuyue.pojo.TstuWorkFinish;

import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/12.
 */
public class StudentWorkList {
    private Integer nid;
    private  String studentName;
    private double workScore;
    private double percentum;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getWorkScore() {
        return workScore;
    }

    public void setWorkScore(double workScore) {
        this.workScore = workScore;
    }

    public double getPercentum() {
        return percentum;
    }

    public void setPercentum(double percentum) {
        this.percentum = percentum;
    }



    @Override
    public String toString() {
        return "StudentWorkList{" +
                "nid=" + nid +
                ", studentName='" + studentName + '\'' +
                ", workScore=" + workScore +
                ", percentum=" + percentum +
                '}';
    }
}
