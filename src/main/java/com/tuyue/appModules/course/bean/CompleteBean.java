package com.tuyue.appModules.course.bean;

/**
 * @Author: 王金海
 * @Description: 成绩单作业完成详情
 * @Date: Created by Administrator on 2017/9/15.
 * @Modified By:
 */
public class CompleteBean {
    private String csentence;//英语
    private String adultTape; //读音
    private Double workScore; // 得分

    public String getCsentence() {
        return csentence;
    }

    public void setCsentence(String csentence) {
        this.csentence = csentence;
    }

    public String getAdultTape() {
        return adultTape;
    }

    public void setAdultTape(String adultTape) {
        this.adultTape = adultTape;
    }

    public Double getWorkScore() {
        return workScore;
    }

    public void setWorkScore(Double workScore) {
        this.workScore = workScore;
    }
}
