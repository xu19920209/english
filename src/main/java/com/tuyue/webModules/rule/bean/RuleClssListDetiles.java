package com.tuyue.webModules.rule.bean;

import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/8.
 */
public class RuleClssListDetiles {
    private String ruleName;
    private int oid;
    private String className;
    private int num;
    private RuleLelveBean ruleLelveBean;
    private List<levelNum> levelNums;
   public static class levelNum{
       private String time;
       private int oneNum;
       private int twoNum;
       private int threeNum;
       private int realNum;

       @Override
       public levelNum clone() throws CloneNotSupportedException {
           levelNum levelNum = new levelNum();
           levelNum.setTime(time);
           levelNum.setRealNum(realNum);
           levelNum.setThreeNum(threeNum);
           levelNum.setTwoNum(twoNum);
           levelNum.setOneNum(oneNum);
           return levelNum;
       }

       public String getTime() {
           return time;
       }

       public void setTime(String time) {
           this.time = time;
       }

       public int getOneNum() {
           return oneNum;
       }

       public void setOneNum(int oneNum) {
           this.oneNum = oneNum;
       }

       public int getTwoNum() {
           return twoNum;
       }

       public void setTwoNum(int twoNum) {
           this.twoNum = twoNum;
       }

       public int getThreeNum() {
           return threeNum;
       }

       public void setThreeNum(int threeNum) {
           this.threeNum = threeNum;
       }

       public int getRealNum() {
           return realNum;
       }

       public void setRealNum(int realNum) {
           this.realNum = realNum;
       }
   }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public RuleLelveBean getRuleLelveBean() {
        return ruleLelveBean;
    }

    public void setRuleLelveBean(RuleLelveBean ruleLelveBean) {
        this.ruleLelveBean = ruleLelveBean;
    }

    public List<levelNum> getLevelNums() {
        return levelNums;
    }

    public void setLevelNums(List<levelNum> levelNums) {
        this.levelNums = levelNums;
    }
}
