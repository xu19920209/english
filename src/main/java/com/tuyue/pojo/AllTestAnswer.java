package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
@Entity
@Table(name = "all_test_answer", schema = "aaenglish", catalog = "")
public class AllTestAnswer {
    private int ansId;
    private Integer nid;
    private String answerTime;
    private Double grade;
    private String startTime;
    private String endTime;
    private Integer allId;
    private String work;

    @Id
    @GeneratedValue
    @Column(name = "ans_id")
    public int getAnsId() {
        return ansId;
    }

    public void setAnsId(int ansId) {
        this.ansId = ansId;
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
    @Column(name = "answer_time")
    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
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
    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllTestAnswer that = (AllTestAnswer) o;

        if (ansId != that.ansId) return false;

        if (nid != null ? !nid.equals(that.nid) : that.nid != null) return false;
        if (answerTime != null ? !answerTime.equals(that.answerTime) : that.answerTime != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ansId;

        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        result = 31 * result + (answerTime != null ? answerTime.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "all_id")
    public Integer getAllId() {
        return allId;
    }

    public void setAllId(Integer allId) {
        this.allId = allId;
    }

    @Basic
    @Column(name = "work")
    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
}
