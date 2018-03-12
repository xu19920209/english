package com.tuyue.appModules.course.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
public class StuFinshBean {
    private String sid;
    private String csentence;//英语
    private String ctranslate;//汉语
    private String studentTape;//语音
    private Double workScore;//成绩
    private String erro;//错误信息
    private Integer sysGrade;
    private Double coefficient;
    public Integer getSysGrade() {
        return sysGrade;
    }

    public void setSysGrade(Integer sysGrade) {
        this.sysGrade = sysGrade;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    public Double getWorkScore() {
        return workScore;
    }

    public void setWorkScore(Double workScore) {
        this.workScore = workScore;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
