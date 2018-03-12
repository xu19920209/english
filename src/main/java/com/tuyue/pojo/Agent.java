package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/12/7.
 */
@Entity
public class Agent {

    private int agentId;
    private String agentName;
    private String agentTel;
    private String bankNumber;
    private String openBank;
    private String branchBank;
    private String province;
    private String city;
    private String region;
    private Integer nid;
    private Byte agentStatus;
    private String creatTime;
    private String tuijainName;
    private String studentName;
    @Transient
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Transient
    public String getTuijainName() {
        return tuijainName;
    }

    public void setTuijainName(String tuijainName) {
        this.tuijainName = tuijainName;
    }

    @Id
    @GeneratedValue
    @Column(name = "agentId")
    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    @Basic
    @Column(name = "agentName")
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    @Basic
    @Column(name = "agentTel")
    public String getAgentTel() {
        return agentTel;
    }

    public void setAgentTel(String agentTel) {
        this.agentTel = agentTel;
    }

    @Basic
    @Column(name = "bankNumber")
    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    @Basic
    @Column(name = "openBank")
    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    @Basic
    @Column(name = "branchBank")
    public String getBranchBank() {
        return branchBank;
    }

    public void setBranchBank(String branchBank) {
        this.branchBank = branchBank;
    }

    @Basic
    @Column(name = "province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Basic
    @Column(name = "nid")
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    @Basic
    @Column(name = "agent_status")
    public Byte getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(Byte agentStatus) {
        this.agentStatus = agentStatus;
    }

    @Basic
    @Column(name = "creat_time")
    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }


}
