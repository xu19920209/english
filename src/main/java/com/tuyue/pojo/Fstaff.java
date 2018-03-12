package com.tuyue.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/6.
 */
@Entity
public class Fstaff {
    private int fid;
    private Integer eid;
    private Integer bid;
    private String name;
    private Integer sex;
    private String nation;
    private Integer marital;
    private Date entryTime;
    private Integer height;
    private Integer weight;
    private String bloodType;
    private String politicsStatus;
    private String nativePlace;
    private String huKou;
    private Integer drivingType;
    private String address;
    private String qq;
    private String tel;
    private String emergency;
    private String emergencyTel;
    private String emergencyType;
    private String idcardNo;
    private String email;
    private String school;
    private String education;
    private String certificate;
    private String hobby;
    private String initial;
    private Date initialTime;
    private String initialIdea;
    private String reexamine;
    private Date reexamineTime;
    private String reexamineIdea;
    private String username;
    private String password;
    private String weixin;
    private Integer isFlag;

    @Override
    public String toString() {
        return "Fstaff{" +
                "fid=" + fid +
                ", eid=" + eid +
                ", bid=" + bid +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", nation='" + nation + '\'' +
                ", marital=" + marital +
                ", entryTime=" + entryTime +
                ", height=" + height +
                ", weight=" + weight +
                ", bloodType='" + bloodType + '\'' +
                ", politicsStatus='" + politicsStatus + '\'' +
                ", nativePlace='" + nativePlace + '\'' +
                ", huKou='" + huKou + '\'' +
                ", drivingType=" + drivingType +
                ", address='" + address + '\'' +
                ", qq='" + qq + '\'' +
                ", tel='" + tel + '\'' +
                ", emergency='" + emergency + '\'' +
                ", emergencyTel='" + emergencyTel + '\'' +
                ", emergencyType='" + emergencyType + '\'' +
                ", idcardNo='" + idcardNo + '\'' +
                ", email='" + email + '\'' +
                ", school='" + school + '\'' +
                ", education='" + education + '\'' +
                ", certificate='" + certificate + '\'' +
                ", hobby='" + hobby + '\'' +
                ", initial='" + initial + '\'' +
                ", initialTime=" + initialTime +
                ", initialIdea='" + initialIdea + '\'' +
                ", reexamine='" + reexamine + '\'' +
                ", reexamineTime=" + reexamineTime +
                ", reexamineIdea='" + reexamineIdea + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", weixin='" + weixin + '\'' +
                ", isFlag=" + isFlag +
                '}';
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fid")
    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
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
    @Column(name = "bid")
    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
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
    @Column(name = "sex")
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
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
    @Column(name = "marital")
    public Integer getMarital() {
        return marital;
    }

    public void setMarital(Integer marital) {
        this.marital = marital;
    }

    @Basic
    @Column(name = "entryTime")
    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    @Basic
    @Column(name = "height")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Basic
    @Column(name = "weight")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "bloodType")
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Basic
    @Column(name = "politicsStatus")
    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
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
    @Column(name = "huKou")
    public String getHuKou() {
        return huKou;
    }

    public void setHuKou(String huKou) {
        this.huKou = huKou;
    }

    @Basic
    @Column(name = "drivingType")
    public Integer getDrivingType() {
        return drivingType;
    }

    public void setDrivingType(Integer drivingType) {
        this.drivingType = drivingType;
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
    @Column(name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Basic
    @Column(name = "emergency")
    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    @Basic
    @Column(name = "emergencyTel")
    public String getEmergencyTel() {
        return emergencyTel;
    }

    public void setEmergencyTel(String emergencyTel) {
        this.emergencyTel = emergencyTel;
    }

    @Basic
    @Column(name = "emergencyType")
    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    @Basic
    @Column(name = "idcardNo")
    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Basic
    @Column(name = "certificate")
    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    @Basic
    @Column(name = "hobby")
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Basic
    @Column(name = "initial")
    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    @Basic
    @Column(name = "initialTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(Date initialTime) {
        this.initialTime = initialTime;
    }

    @Basic
    @Column(name = "initialIdea")
    public String getInitialIdea() {
        return initialIdea;
    }

    public void setInitialIdea(String initialIdea) {
        this.initialIdea = initialIdea;
    }

    @Basic
    @Column(name = "reexamine")
    public String getReexamine() {
        return reexamine;
    }

    public void setReexamine(String reexamine) {
        this.reexamine = reexamine;
    }

    @Basic
    @Column(name = "reexamineTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getReexamineTime() {
        return reexamineTime;
    }

    public void setReexamineTime(Date reexamineTime) {
        this.reexamineTime = reexamineTime;
    }

    @Basic
    @Column(name = "reexamineIdea")
    public String getReexamineIdea() {
        return reexamineIdea;
    }

    public void setReexamineIdea(String reexamineIdea) {
        this.reexamineIdea = reexamineIdea;
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
    @Column(name = "weixin")
    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
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

        Fstaff fstaff = (Fstaff) o;

        if (fid != fstaff.fid) return false;
        if (eid != null ? !eid.equals(fstaff.eid) : fstaff.eid != null) return false;
        if (bid != null ? !bid.equals(fstaff.bid) : fstaff.bid != null) return false;
        if (name != null ? !name.equals(fstaff.name) : fstaff.name != null) return false;
        if (sex != null ? !sex.equals(fstaff.sex) : fstaff.sex != null) return false;
        if (nation != null ? !nation.equals(fstaff.nation) : fstaff.nation != null) return false;
        if (marital != null ? !marital.equals(fstaff.marital) : fstaff.marital != null) return false;
        if (entryTime != null ? !entryTime.equals(fstaff.entryTime) : fstaff.entryTime != null) return false;
        if (height != null ? !height.equals(fstaff.height) : fstaff.height != null) return false;
        if (weight != null ? !weight.equals(fstaff.weight) : fstaff.weight != null) return false;
        if (bloodType != null ? !bloodType.equals(fstaff.bloodType) : fstaff.bloodType != null) return false;
        if (politicsStatus != null ? !politicsStatus.equals(fstaff.politicsStatus) : fstaff.politicsStatus != null)
            return false;
        if (nativePlace != null ? !nativePlace.equals(fstaff.nativePlace) : fstaff.nativePlace != null) return false;
        if (huKou != null ? !huKou.equals(fstaff.huKou) : fstaff.huKou != null) return false;
        if (drivingType != null ? !drivingType.equals(fstaff.drivingType) : fstaff.drivingType != null) return false;
        if (address != null ? !address.equals(fstaff.address) : fstaff.address != null) return false;
        if (qq != null ? !qq.equals(fstaff.qq) : fstaff.qq != null) return false;
        if (tel != null ? !tel.equals(fstaff.tel) : fstaff.tel != null) return false;
        if (emergency != null ? !emergency.equals(fstaff.emergency) : fstaff.emergency != null) return false;
        if (emergencyTel != null ? !emergencyTel.equals(fstaff.emergencyTel) : fstaff.emergencyTel != null)
            return false;
        if (emergencyType != null ? !emergencyType.equals(fstaff.emergencyType) : fstaff.emergencyType != null)
            return false;
        if (idcardNo != null ? !idcardNo.equals(fstaff.idcardNo) : fstaff.idcardNo != null) return false;
        if (email != null ? !email.equals(fstaff.email) : fstaff.email != null) return false;
        if (school != null ? !school.equals(fstaff.school) : fstaff.school != null) return false;
        if (education != null ? !education.equals(fstaff.education) : fstaff.education != null) return false;
        if (certificate != null ? !certificate.equals(fstaff.certificate) : fstaff.certificate != null) return false;
        if (hobby != null ? !hobby.equals(fstaff.hobby) : fstaff.hobby != null) return false;
        if (initial != null ? !initial.equals(fstaff.initial) : fstaff.initial != null) return false;
        if (initialTime != null ? !initialTime.equals(fstaff.initialTime) : fstaff.initialTime != null) return false;
        if (initialIdea != null ? !initialIdea.equals(fstaff.initialIdea) : fstaff.initialIdea != null) return false;
        if (reexamine != null ? !reexamine.equals(fstaff.reexamine) : fstaff.reexamine != null) return false;
        if (reexamineTime != null ? !reexamineTime.equals(fstaff.reexamineTime) : fstaff.reexamineTime != null)
            return false;
        if (reexamineIdea != null ? !reexamineIdea.equals(fstaff.reexamineIdea) : fstaff.reexamineIdea != null)
            return false;
        if (username != null ? !username.equals(fstaff.username) : fstaff.username != null) return false;
        if (password != null ? !password.equals(fstaff.password) : fstaff.password != null) return false;
        if (weixin != null ? !weixin.equals(fstaff.weixin) : fstaff.weixin != null) return false;
        if (isFlag != null ? !isFlag.equals(fstaff.isFlag) : fstaff.isFlag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fid;
        result = 31 * result + (eid != null ? eid.hashCode() : 0);
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (nation != null ? nation.hashCode() : 0);
        result = 31 * result + (marital != null ? marital.hashCode() : 0);
        result = 31 * result + (entryTime != null ? entryTime.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (bloodType != null ? bloodType.hashCode() : 0);
        result = 31 * result + (politicsStatus != null ? politicsStatus.hashCode() : 0);
        result = 31 * result + (nativePlace != null ? nativePlace.hashCode() : 0);
        result = 31 * result + (huKou != null ? huKou.hashCode() : 0);
        result = 31 * result + (drivingType != null ? drivingType.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (emergency != null ? emergency.hashCode() : 0);
        result = 31 * result + (emergencyTel != null ? emergencyTel.hashCode() : 0);
        result = 31 * result + (emergencyType != null ? emergencyType.hashCode() : 0);
        result = 31 * result + (idcardNo != null ? idcardNo.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (certificate != null ? certificate.hashCode() : 0);
        result = 31 * result + (hobby != null ? hobby.hashCode() : 0);
        result = 31 * result + (initial != null ? initial.hashCode() : 0);
        result = 31 * result + (initialTime != null ? initialTime.hashCode() : 0);
        result = 31 * result + (initialIdea != null ? initialIdea.hashCode() : 0);
        result = 31 * result + (reexamine != null ? reexamine.hashCode() : 0);
        result = 31 * result + (reexamineTime != null ? reexamineTime.hashCode() : 0);
        result = 31 * result + (reexamineIdea != null ? reexamineIdea.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (weixin != null ? weixin.hashCode() : 0);
        result = 31 * result + (isFlag != null ? isFlag.hashCode() : 0);
        return result;
    }

}
