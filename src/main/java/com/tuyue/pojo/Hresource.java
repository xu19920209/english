package com.tuyue.pojo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
@Entity
public class Hresource {
    private int hid;
    private String resource;
    private String address;
    private Integer rank;
    private Integer sort;
    private Integer isFlag;
    private Integer parentId;
    private String icon;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "hid")
    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    @Basic
    @Column(name = "resource")
    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "rank")
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Basic
    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Basic
    @Column(name = "isFlag")
    public Integer getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(Integer isFlag) {
        this.isFlag = isFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hresource hresource = (Hresource) o;

        if (hid != hresource.hid) return false;
        if (resource != null ? !resource.equals(hresource.resource) : hresource.resource != null) return false;
        if (address != null ? !address.equals(hresource.address) : hresource.address != null) return false;
        if (rank != null ? !rank.equals(hresource.rank) : hresource.rank != null) return false;
        if (sort != null ? !sort.equals(hresource.sort) : hresource.sort != null) return false;
        if (isFlag != null ? !isFlag.equals(hresource.isFlag) : hresource.isFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hid;
        result = 31 * result + (resource != null ? resource.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        result = 31 * result + (isFlag != null ? isFlag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hresource{" +
                "hid=" + hid +
                ", resource='" + resource + '\'' +
                ", address='" + address + '\'' +
                ", rank=" + rank +
                ", sort=" + sort +
                ", isFlag=" + isFlag +
                '}';
    }

    @Basic
    @Column(name = "parentId")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
