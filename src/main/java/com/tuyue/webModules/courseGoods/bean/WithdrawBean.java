package com.tuyue.webModules.courseGoods.bean;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/2/23.
 */
public class WithdrawBean {
    private String userName;
    private String phone;
    private String bankNumber;
    private String openBank;
    private double money;
    private String time;
    private int agentStatus;
    private String branchBank;
    private String address;
    private int walletDetailsId;
    private int moneyStatus;

    public int getMoneyStatus() {
        return moneyStatus;
    }

    public void setMoneyStatus(int moneyStatus) {
        this.moneyStatus = moneyStatus;
    }

    public String getBranchBank() {
        return branchBank;
    }

    public void setBranchBank(String branchBank) {
        this.branchBank = branchBank;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWalletDetailsId() {
        return walletDetailsId;
    }

    public void setWalletDetailsId(int walletDetailsId) {
        this.walletDetailsId = walletDetailsId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(int agentStatus) {
        this.agentStatus = agentStatus;
    }
}
