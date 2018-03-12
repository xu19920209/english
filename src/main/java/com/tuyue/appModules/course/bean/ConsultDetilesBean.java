package com.tuyue.appModules.course.bean;

import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/15.
 */
public class ConsultDetilesBean {
    private String csentence;//英语
    private String ctranslate;//汉语
    private String studentTape;//学生语音
    private String replyWord;//回复文字
    private String replyVoice;//回复语音
    private Timestamp consultTime;
    private String aname;
    private String bname;
    private Integer sysGrade;
    private Double coefficient;

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getSysGrade() {

        return sysGrade;
    }

    public void setSysGrade(Integer sysGrade) {
        this.sysGrade = sysGrade;
    }

    private String className;

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

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getCsentence() {
        return csentence;
    }

    public void setCsentence(String csentence) {
        this.csentence = csentence;
    }

    public String getCtranslate() {
        return ctranslate;
    }

    public void setCtranslate(String ctranslate) {
        this.ctranslate = ctranslate;
    }

    public String getStudentTape() {
        return studentTape;
    }

    public void setStudentTape(String studentTape) {
        this.studentTape = studentTape;
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

    public Timestamp getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(Timestamp consultTime) {
        this.consultTime = consultTime;
    }
}
