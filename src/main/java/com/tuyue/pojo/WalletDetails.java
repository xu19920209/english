package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/2/23.
 */
@Entity
@Table(name = "wallet_details", schema = "aaenglish", catalog = "")
public class WalletDetails {
    private int walletDetailsId;
    private Integer walletId;
    private Double money;
    private Byte moneyStatus;
    private Byte detailsType;
    private Timestamp creatTime;

    @Id
    @GeneratedValue
    @Column(name = "wallet_details_id")
    public int getWalletDetailsId() {
        return walletDetailsId;
    }

    public void setWalletDetailsId(int walletDetailsId) {
        this.walletDetailsId = walletDetailsId;
    }

    @Basic
    @Column(name = "wallet_id")
    public Integer getWalletId() {
        return walletId;
    }

    public void setWalletId(Integer walletId) {
        this.walletId = walletId;
    }

    @Basic
    @Column(name = "money")
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Basic
    @Column(name = "money_status")
    public Byte getMoneyStatus() {
        return moneyStatus;
    }

    public void setMoneyStatus(Byte moneyStatus) {
        this.moneyStatus = moneyStatus;
    }

    @Basic
    @Column(name = "details_type")
    public Byte getDetailsType() {
        return detailsType;
    }

    public void setDetailsType(Byte detailsType) {
        this.detailsType = detailsType;
    }

    @Basic
    @Column(name = "creat_time")
    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        WalletDetails that = (WalletDetails) object;

        if (walletDetailsId != that.walletDetailsId) return false;
        if (walletId != null ? !walletId.equals(that.walletId) : that.walletId != null) return false;
        if (money != null ? !money.equals(that.money) : that.money != null) return false;
        if (moneyStatus != null ? !moneyStatus.equals(that.moneyStatus) : that.moneyStatus != null) return false;
        if (detailsType != null ? !detailsType.equals(that.detailsType) : that.detailsType != null) return false;
        if (creatTime != null ? !creatTime.equals(that.creatTime) : that.creatTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = walletDetailsId;
        result = 31 * result + (walletId != null ? walletId.hashCode() : 0);
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (moneyStatus != null ? moneyStatus.hashCode() : 0);
        result = 31 * result + (detailsType != null ? detailsType.hashCode() : 0);
        result = 31 * result + (creatTime != null ? creatTime.hashCode() : 0);
        return result;
    }
}
