package com.tuyue.pojo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
@Entity
public class Ebranchschool {
    private int eid;
    private Integer fid;
    private String name;
    private String province;
    private String city;
    private String districts;
    private String address;
    private String fixationTel;
    private String principalName;
    private String principalTel;
    private String principalEmail;
    private String principalWeiXin;
    private String principalidNo;
    private String legalPersonName;
    private String legalPersonidNo;
    private String legalPersonFront;
    private String legalPersonVerso;
    private String schoolImg;
    private Integer schoolType;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "eid")
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "fid")
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "districts")
    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts;
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
    @Column(name = "fixationTel")
    public String getFixationTel() {
        return fixationTel;
    }

    public void setFixationTel(String fixationTel) {
        this.fixationTel = fixationTel;
    }

    @Basic
    @Column(name = "principalName")
    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    @Basic
    @Column(name = "principalTel")
    public String getPrincipalTel() {
        return principalTel;
    }

    public void setPrincipalTel(String principalTel) {
        this.principalTel = principalTel;
    }

    @Basic
    @Column(name = "principalEmail")
    public String getPrincipalEmail() {
        return principalEmail;
    }

    public void setPrincipalEmail(String principalEmail) {
        this.principalEmail = principalEmail;
    }

    @Basic
    @Column(name = "principalWeiXin")
    public String getPrincipalWeiXin() {
        return principalWeiXin;
    }

    public void setPrincipalWeiXin(String principalWeiXin) {
        this.principalWeiXin = principalWeiXin;
    }

    @Basic
    @Column(name = "principalidNo")
    public String getPrincipalidNo() {
        return principalidNo;
    }

    public void setPrincipalidNo(String principalidNo) {
        this.principalidNo = principalidNo;
    }

    @Basic
    @Column(name = "legalPersonName")
    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    @Basic
    @Column(name = "legalPersonidNo")
    public String getLegalPersonidNo() {
        return legalPersonidNo;
    }

    public void setLegalPersonidNo(String legalPersonidNo) {
        this.legalPersonidNo = legalPersonidNo;
    }

    @Basic
    @Column(name = "legalPersonFront")
    public String getLegalPersonFront() {
        return legalPersonFront;
    }

    public void setLegalPersonFront(String legalPersonFront) {
        this.legalPersonFront = legalPersonFront;
    }

    @Basic
    @Column(name = "legalPersonVerso")
    public String getLegalPersonVerso() {
        return legalPersonVerso;
    }

    public void setLegalPersonVerso(String legalPersonVerso) {
        this.legalPersonVerso = legalPersonVerso;
    }

    @Basic
    @Column(name = "schoolImg")
    public String getSchoolImg() {
        return schoolImg;
    }

    public void setSchoolImg(String schoolImg) {
        this.schoolImg = schoolImg;
    }

    @Basic
    @Column(name = "schoolType")
    public Integer getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Integer schoolType) {
        this.schoolType = schoolType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ebranchschool that = (Ebranchschool) o;

        if (eid != that.eid) return false;
        if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (province != null ? !province.equals(that.province) : that.province != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (districts != null ? !districts.equals(that.districts) : that.districts != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (fixationTel != null ? !fixationTel.equals(that.fixationTel) : that.fixationTel != null) return false;
        if (principalName != null ? !principalName.equals(that.principalName) : that.principalName != null)
            return false;
        if (principalTel != null ? !principalTel.equals(that.principalTel) : that.principalTel != null) return false;
        if (principalEmail != null ? !principalEmail.equals(that.principalEmail) : that.principalEmail != null)
            return false;
        if (principalWeiXin != null ? !principalWeiXin.equals(that.principalWeiXin) : that.principalWeiXin != null)
            return false;
        if (principalidNo != null ? !principalidNo.equals(that.principalidNo) : that.principalidNo != null)
            return false;
        if (legalPersonName != null ? !legalPersonName.equals(that.legalPersonName) : that.legalPersonName != null)
            return false;
        if (legalPersonidNo != null ? !legalPersonidNo.equals(that.legalPersonidNo) : that.legalPersonidNo != null)
            return false;
        if (legalPersonFront != null ? !legalPersonFront.equals(that.legalPersonFront) : that.legalPersonFront != null)
            return false;
        if (legalPersonVerso != null ? !legalPersonVerso.equals(that.legalPersonVerso) : that.legalPersonVerso != null)
            return false;
        if (schoolImg != null ? !schoolImg.equals(that.schoolImg) : that.schoolImg != null) return false;
        if (schoolType != null ? !schoolType.equals(that.schoolType) : that.schoolType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eid;
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (districts != null ? districts.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (fixationTel != null ? fixationTel.hashCode() : 0);
        result = 31 * result + (principalName != null ? principalName.hashCode() : 0);
        result = 31 * result + (principalTel != null ? principalTel.hashCode() : 0);
        result = 31 * result + (principalEmail != null ? principalEmail.hashCode() : 0);
        result = 31 * result + (principalWeiXin != null ? principalWeiXin.hashCode() : 0);
        result = 31 * result + (principalidNo != null ? principalidNo.hashCode() : 0);
        result = 31 * result + (legalPersonName != null ? legalPersonName.hashCode() : 0);
        result = 31 * result + (legalPersonidNo != null ? legalPersonidNo.hashCode() : 0);
        result = 31 * result + (legalPersonFront != null ? legalPersonFront.hashCode() : 0);
        result = 31 * result + (legalPersonVerso != null ? legalPersonVerso.hashCode() : 0);
        result = 31 * result + (schoolImg != null ? schoolImg.hashCode() : 0);
        result = 31 * result + (schoolType != null ? schoolType.hashCode() : 0);
        return result;
    }

    public Ebranchschool(Integer fid, int eid, String name, String province, String city, String districts, String address, String fixationTel, String principalName, String principalTel, String principalEmail, String principalWeiXin, String principalidNo, String legalPersonName, String legalPersonidNo, String legalPersonFront, String legalPersonVerso, String schoolImg, Integer schoolType) {
        this.fid = fid;
        this.eid = eid;
        this.name = name;
        this.province = province;
        this.city = city;
        this.districts = districts;
        this.address = address;
        this.fixationTel = fixationTel;
        this.principalName = principalName;
        this.principalTel = principalTel;
        this.principalEmail = principalEmail;
        this.principalWeiXin = principalWeiXin;
        this.principalidNo = principalidNo;
        this.legalPersonName = legalPersonName;
        this.legalPersonidNo = legalPersonidNo;
        this.legalPersonFront = legalPersonFront;
        this.legalPersonVerso = legalPersonVerso;
        this.schoolImg = schoolImg;
        this.schoolType = schoolType;
    }

    public Ebranchschool() {
    }

    @Override
    public String toString() {
        return "Ebranchschool{" +
                "eid=" + eid +
                ", fid=" + fid +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", districts='" + districts + '\'' +
                ", address='" + address + '\'' +
                ", fixationTel='" + fixationTel + '\'' +
                ", principalName='" + principalName + '\'' +
                ", principalTel='" + principalTel + '\'' +
                ", principalEmail='" + principalEmail + '\'' +
                ", principalWeiXin='" + principalWeiXin + '\'' +
                ", principalidNo='" + principalidNo + '\'' +
                ", legalPersonName='" + legalPersonName + '\'' +
                ", legalPersonidNo='" + legalPersonidNo + '\'' +
                ", legalPersonFront='" + legalPersonFront + '\'' +
                ", legalPersonVerso='" + legalPersonVerso + '\'' +
                ", schoolImg='" + schoolImg + '\'' +
                ", schoolType=" + schoolType +
                '}';
    }
}
