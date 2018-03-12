package com.tuyue.webModules.courseGoods.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/8.
 */
public class AgentBean {
    private int agentId;
    private String agentName;//代理人名称
    private String agentTel;//代理人电话
    private String bankNumber;//银行账号
    private String openBank;//开户行
    private String branchBank;//开户支行
    private String province;//省
    private String city;//市
    private String region;//区
    private double noPayMoney;//未支付金额
    private double payMoney;//已支付金额
    private String ids;//待打款id


    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentTel() {
        return agentTel;
    }

    public void setAgentTel(String agentTel) {
        this.agentTel = agentTel;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getBranchBank() {
        return branchBank;
    }

    public void setBranchBank(String branchBank) {
        this.branchBank = branchBank;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getNoPayMoney() {
        return noPayMoney;
    }

    public void setNoPayMoney(double noPayMoney) {
        this.noPayMoney = noPayMoney;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
