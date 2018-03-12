package com.tuyue.pojo;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/11/7.
 */
@DynamicInsert
@Entity
public class Nstudent {
    private int nid;
    private Integer eid;
    private String courseName;
    private String studentName;
    private String idNo;
    private Integer sex;
    private String nativePlace;
    private String nation;
    private String schoolClass;
    private String province;
    private String city;
    private String districts;
    private String address;
    private String fatherName;
    private String fatherTel;
    private String motherName;
    private String motherTel;
    private String urgentName;
    private String urgentTel;
    private Date applyTime;
    private String username;
    private String password;
    private Integer oid;
    private Integer isType;
    private String imgUrl;
    private Integer integral;
    private Timestamp createTime;
    private int level = 4;
    private int isTest = 1;
    private Double coefficient = 1.0;
    private String alias;
    private String openId;
    private String referrerPhone;
    private String parentIds;

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Transient  //忽略当前字段映射
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Id
    @GeneratedValue
    @Column(name = "nid")
    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    @Basic
    @Column(name = "eid")
    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "courseName")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Basic
    @Column(name = "studentName")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Basic
    @Column(name = "idNo")
    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    @Basic
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "nativePlace")
    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    @Basic
    @Column(name = "nation")
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    @Basic
    @Column(name = "schoolClass")
    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
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
    @Column(name = "fatherName")
    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Basic
    @Column(name = "fatherTel")
    public String getFatherTel() {
        return fatherTel;
    }

    public void setFatherTel(String fatherTel) {
        this.fatherTel = fatherTel;
    }

    @Basic
    @Column(name = "motherName")
    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    @Basic
    @Column(name = "motherTel")
    public String getMotherTel() {
        return motherTel;
    }

    public void setMotherTel(String motherTel) {
        this.motherTel = motherTel;
    }

    @Basic
    @Column(name = "urgentName")
    public String getUrgentName() {
        return urgentName;
    }

    public void setUrgentName(String urgentName) {
        this.urgentName = urgentName;
    }

    @Basic
    @Column(name = "urgentTel")
    public String getUrgentTel() {
        return urgentTel;
    }

    public void setUrgentTel(String urgentTel) {
        this.urgentTel = urgentTel;
    }

    @Basic
    @Column(name = "applyTime")
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "oid")
    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "isType")
    public Integer getIsType() {
        return isType;
    }

    public void setIsType(Integer isType) {
        this.isType = isType;
    }

    @Basic
    @Column(name = "imgUrl")
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Basic
    @Column(name = "integral")
    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "level", nullable = false, columnDefinition = "Integer default 4")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "is_test", nullable = false, columnDefinition = "Integer default 2")
    public int getIsTest() {
        return isTest;
    }

    public void setIsTest(int isTest) {
        this.isTest = isTest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nstudent nstudent = (Nstudent) o;

        if (nid != nstudent.nid) return false;
        if (level != nstudent.level) return false;
        if (isTest != nstudent.isTest) return false;
        if (eid != null ? !eid.equals(nstudent.eid) : nstudent.eid != null) return false;
        if (courseName != null ? !courseName.equals(nstudent.courseName) : nstudent.courseName != null) return false;
        if (studentName != null ? !studentName.equals(nstudent.studentName) : nstudent.studentName != null)
            return false;
        if (idNo != null ? !idNo.equals(nstudent.idNo) : nstudent.idNo != null) return false;
        if (sex != null ? !sex.equals(nstudent.sex) : nstudent.sex != null) return false;
        if (nativePlace != null ? !nativePlace.equals(nstudent.nativePlace) : nstudent.nativePlace != null)
            return false;
        if (nation != null ? !nation.equals(nstudent.nation) : nstudent.nation != null) return false;
        if (schoolClass != null ? !schoolClass.equals(nstudent.schoolClass) : nstudent.schoolClass != null)
            return false;
        if (province != null ? !province.equals(nstudent.province) : nstudent.province != null) return false;
        if (city != null ? !city.equals(nstudent.city) : nstudent.city != null) return false;
        if (districts != null ? !districts.equals(nstudent.districts) : nstudent.districts != null) return false;
        if (address != null ? !address.equals(nstudent.address) : nstudent.address != null) return false;
        if (fatherName != null ? !fatherName.equals(nstudent.fatherName) : nstudent.fatherName != null) return false;
        if (fatherTel != null ? !fatherTel.equals(nstudent.fatherTel) : nstudent.fatherTel != null) return false;
        if (motherName != null ? !motherName.equals(nstudent.motherName) : nstudent.motherName != null) return false;
        if (motherTel != null ? !motherTel.equals(nstudent.motherTel) : nstudent.motherTel != null) return false;
        if (urgentName != null ? !urgentName.equals(nstudent.urgentName) : nstudent.urgentName != null) return false;
        if (urgentTel != null ? !urgentTel.equals(nstudent.urgentTel) : nstudent.urgentTel != null) return false;
        if (applyTime != null ? !applyTime.equals(nstudent.applyTime) : nstudent.applyTime != null) return false;
        if (username != null ? !username.equals(nstudent.username) : nstudent.username != null) return false;
        if (password != null ? !password.equals(nstudent.password) : nstudent.password != null) return false;
        if (oid != null ? !oid.equals(nstudent.oid) : nstudent.oid != null) return false;
        if (isType != null ? !isType.equals(nstudent.isType) : nstudent.isType != null) return false;
        if (imgUrl != null ? !imgUrl.equals(nstudent.imgUrl) : nstudent.imgUrl != null) return false;
        if (integral != null ? !integral.equals(nstudent.integral) : nstudent.integral != null) return false;
        if (createTime != null ? !createTime.equals(nstudent.createTime) : nstudent.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nid;
        result = 31 * result + (eid != null ? eid.hashCode() : 0);
        result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
        result = 31 * result + (studentName != null ? studentName.hashCode() : 0);
        result = 31 * result + (idNo != null ? idNo.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (nativePlace != null ? nativePlace.hashCode() : 0);
        result = 31 * result + (nation != null ? nation.hashCode() : 0);
        result = 31 * result + (schoolClass != null ? schoolClass.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (districts != null ? districts.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (fatherName != null ? fatherName.hashCode() : 0);
        result = 31 * result + (fatherTel != null ? fatherTel.hashCode() : 0);
        result = 31 * result + (motherName != null ? motherName.hashCode() : 0);
        result = 31 * result + (motherTel != null ? motherTel.hashCode() : 0);
        result = 31 * result + (urgentName != null ? urgentName.hashCode() : 0);
        result = 31 * result + (urgentTel != null ? urgentTel.hashCode() : 0);
        result = 31 * result + (applyTime != null ? applyTime.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (oid != null ? oid.hashCode() : 0);
        result = 31 * result + (isType != null ? isType.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        result = 31 * result + (integral != null ? integral.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + isTest;
        return result;
    }

    @Basic
    @Column(name = "coefficient")
    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public String toString() {
        return "Nstudent{" +
                "nid=" + nid +
                ", eid=" + eid +
                ", courseName='" + courseName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", idNo='" + idNo + '\'' +
                ", sex=" + sex +
                ", nativePlace='" + nativePlace + '\'' +
                ", nation='" + nation + '\'' +
                ", schoolClass='" + schoolClass + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", districts='" + districts + '\'' +
                ", address='" + address + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", fatherTel='" + fatherTel + '\'' +
                ", motherName='" + motherName + '\'' +
                ", motherTel='" + motherTel + '\'' +
                ", urgentName='" + urgentName + '\'' +
                ", urgentTel='" + urgentTel + '\'' +
                ", applyTime=" + applyTime +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", oid=" + oid +
                ", isType=" + isType +
                ", imgUrl='" + imgUrl + '\'' +
                ", integral=" + integral +
                ", createTime=" + createTime +
                ", level=" + level +
                ", isTest=" + isTest +
                ", coefficient=" + coefficient +
                ", alias='" + alias + '\'' +
                ", openId='" + openId + '\'' +
                '}';
    }

    @Basic
    @Column(name = "openId")
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Basic
    @Column(name = "referrer_phone")
    public String getReferrerPhone() {
        return referrerPhone;
    }

    public void setReferrerPhone(String referrerPhone) {
        this.referrerPhone = referrerPhone;
    }

    @Basic
    @Column(name = "parent_ids")
    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }
}
