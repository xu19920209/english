package com.tuyue.webModules.work.bean;

import org.apache.shiro.util.Nameable;

import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/11.
 */
public class ConsultListBean {
    private Integer tid;
    private String studentName;//学生名称
    private String className;//班级名称
    private String aname;//课程名称
    private Timestamp consultTime;//提问时间
    private String name;//回复老师
    private Timestamp replyTime;//回复时间
    private Integer replyStatus;//回复状态
    private Double workScore;//作业得分
    private String studentTape;//学生录音
    private String  csentence;//作业内容
    private String replyWord;//回复文字
    private String replyVoice;//回复录音
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Timestamp getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(Timestamp consultTime) {
        this.consultTime = consultTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
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

    public String getCsentence() {
        return csentence;
    }

    public void setCsentence(String csentence) {
        this.csentence = csentence;
    }

    public String getReplyWord() {
        return replyWord;
    }

    public void setReplyWord(String replyWord) {
        this.replyWord = replyWord;
    }

    public String getReplyVoice() {
        return replyVoice;
    }

    public void setReplyVoice(String replyVoice) {
        this.replyVoice = replyVoice;
    }

    @Override
    public String toString() {
        return "ConsultListBean{" +
                "tid=" + tid +
                ", studentName='" + studentName + '\'' +
                ", className='" + className + '\'' +
                ", aname='" + aname + '\'' +
                ", consultTime=" + consultTime +
                ", name='" + name + '\'' +
                ", replyTime=" + replyTime +
                ", replyStatus=" + replyStatus +
                ", workScore=" + workScore +
                ", studentTape='" + studentTape + '\'' +
                ", csentence='" + csentence + '\'' +
                ", replyWord='" + replyWord + '\'' +
                ", replyVoice='" + replyVoice + '\'' +
                '}';
    }
}
