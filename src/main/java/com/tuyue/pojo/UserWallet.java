package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/3/5.
 */
@Entity
@Table(name = "user_wallet", schema = "aaenglish", catalog = "")
public class UserWallet {
    private int walletId;
    private Double money;
    private Integer nid;

    @Id
    @GeneratedValue
    @Column(name = "wallet_id")
    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
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
    @Column(name = "nid")
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        UserWallet that = (UserWallet) object;

        if (walletId != that.walletId) return false;
        if (money != null ? !money.equals(that.money) : that.money != null) return false;
        if (nid != null ? !nid.equals(that.nid) : that.nid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = walletId;
        result = 31 * result + (money != null ? money.hashCode() : 0);
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        return result;
    }
}
