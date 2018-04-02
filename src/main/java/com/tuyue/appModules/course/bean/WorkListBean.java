package com.tuyue.appModules.course.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/14.
 */
public class WorkListBean {
    private Integer aid;
    private Integer bid;
    private Integer levelId;
    private String aname;
    private String bname;
    private String levelName;
    private Integer total;//作业总数
    private Integer finish;//完成的个数
    private Integer  grade;//得分
    private String  layoutTime;//布置时间
    private String cids;

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getCids() {
        return cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getLayoutTime() {
        return layoutTime;
    }

    public void setLayoutTime(String layoutTime) {
        this.layoutTime = layoutTime;
    }
}
