package com.tuyue.appModules.weChatGongZhong.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/2/23.
 */
public class UserCenterBean {
    private String imgUrl;
    private int userType;
    private int inviteNum;//邀请人数
    private String referrer;//推荐人
    private double sumMoney;//总收入
    private double canMoney;
    private double meMoney;
    private String ids;//可提取金额的id
    private int nid;
    private String username;
    private String studentName;
    private String phone;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getInviteNum() {
        return inviteNum;
    }

    public void setInviteNum(int inviteNum) {
        this.inviteNum = inviteNum;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public double getCanMoney() {
        return canMoney;
    }

    public void setCanMoney(double canMoney) {
        this.canMoney = canMoney;
    }

    public double getMeMoney() {
        return meMoney;
    }

    public void setMeMoney(double meMoney) {
        this.meMoney = meMoney;
    }
}
