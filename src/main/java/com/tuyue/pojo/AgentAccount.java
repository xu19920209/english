package com.tuyue.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/2/26.
 */
@Entity
@Table(name = "agent_account", schema = "aaenglish", catalog = "")
public class AgentAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer courseOrderId;
    private Double orderMoney;
    private Integer moneyState;
    private Integer nid;
    private int accountId;

    @Basic
    @Column(name = "courseOrderId")
    public Integer getCourseOrderId() {
        return courseOrderId;
    }

    public void setCourseOrderId(Integer courseOrderId) {
        this.courseOrderId = courseOrderId;
    }

    @Basic
    @Column(name = "orderMoney")
    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    @Basic
    @Column(name = "moneyState")
    public Integer getMoneyState() {
        return moneyState;
    }

    public void setMoneyState(Integer moneyState) {
        this.moneyState = moneyState;
    }

    @Basic
    @Column(name = "nid")
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    @Id
    @GeneratedValue
    @Column(name = "accountId")
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "AgentAccount{" +
                "courseOrderId=" + courseOrderId +
                ", orderMoney=" + orderMoney +
                ", moneyState=" + moneyState +
                ", nid=" + nid +
                ", accountId=" + accountId +
                '}';
    }
}
