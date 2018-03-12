package com.tuyue.appModules.courseGoods.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/12.
 */
public class CourseGoodsDetail {
    private String cover;
    private String num;
    private int finishState;
    private String goTime;
    private Integer courseState;

    public Integer getCourseState() {
        return courseState;
    }

    public void setCourseState(Integer courseState) {
        this.courseState = courseState;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public int getFinishState() {
        return finishState;
    }

    public void setFinishState(int finishState) {
        this.finishState = finishState;
    }

    public String getGoTime() {
        return goTime;
    }

    public void setGoTime(String goTime) {
        this.goTime = goTime;
    }
}
