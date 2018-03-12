package com.tuyue.appModules.course.bean;

import java.util.List;

/**
 * @Author: 王金海
 * @Description: 成人成绩单
 * @Date: Created by Administrator on 2017/9/15.
 * @Modified By:
 */
public class AdultCompleteBean {
    private String aname;//课程名称
    private String bname;//课时名称
    private double sumworkScore;// 总分
    private int sumctopic;// 总题数
    private int finishsum;// 完成题数
    private String encourage;//鼓励语句

    public String getEncourage() {
        return encourage;
    }

    public void setEncourage(String encourage) {
        this.encourage = encourage;
    }

    private List<CompleteBean> completeBeans;

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

    public double getSumworkScore() {
        return sumworkScore;
    }

    public void setSumworkScore(double sumworkScore) {
        this.sumworkScore = sumworkScore;
    }

    public int getSumctopic() {
        return sumctopic;
    }

    public void setSumctopic(int sumctopic) {
        this.sumctopic = sumctopic;
    }

    public int getFinishsum() {
        return finishsum;
    }

    public void setFinishsum(int finishsum) {
        this.finishsum = finishsum;
    }

    public List<CompleteBean> getCompleteBeans() {
        return completeBeans;
    }

    public void setCompleteBeans(List<CompleteBean> completeBeans) {
        this.completeBeans = completeBeans;
    }
}
