package com.tuyue.appModules.course.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
public class FinishBean {
    private Integer vid;
    private String csentence;//英语
    private String ctranslate;//汉语
    private String adultTape;;//语音
    private Double workScore;//成绩
    private String erro;//错误信息

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
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

    public Double getWorkScore() {
        return workScore;
    }

    public void setWorkScore(Double workScore) {
        this.workScore = workScore;
    }

    public String getAdultTape() {

        return adultTape;
    }

    public void setAdultTape(String adultTape) {
        this.adultTape = adultTape;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    @Override
    public String toString() {
        return "FinishBean{" +
                "vid=" + vid +
                ", csentence='" + csentence + '\'' +
                ", ctranslate='" + ctranslate + '\'' +
                ", adultTape='" + adultTape + '\'' +
                ", workScore=" + workScore +
                ", erro='" + erro + '\'' +
                '}';
    }
}
