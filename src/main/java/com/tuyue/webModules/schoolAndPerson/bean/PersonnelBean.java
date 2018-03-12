package com.tuyue.webModules.schoolAndPerson.bean;

import com.tuyue.pojo.Keduundergo;
import com.tuyue.pojo.Lworkresume;
import com.tuyue.pojo.Mfamilystatus;

import java.sql.Date;
import java.util.List;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/7.
 */
public class PersonnelBean {
    private int fid;
    private int gid;
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
    private String role;
    List<Keduundergo> eduList;
    List<Lworkresume> workList;
    List<Mfamilystatus> familList;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getMarital() {
        return marital;
    }

    public void setMarital(Integer marital) {
        this.marital = marital;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getHuKou() {
        return huKou;
    }

    public void setHuKou(String huKou) {
        this.huKou = huKou;
    }

    public Integer getDrivingType() {
        return drivingType;
    }

    public void setDrivingType(Integer drivingType) {
        this.drivingType = drivingType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getEmergencyTel() {
        return emergencyTel;
    }

    public void setEmergencyTel(String emergencyTel) {
        this.emergencyTel = emergencyTel;
    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public Date getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(Date initialTime) {
        this.initialTime = initialTime;
    }

    public String getInitialIdea() {
        return initialIdea;
    }

    public void setInitialIdea(String initialIdea) {
        this.initialIdea = initialIdea;
    }

    public String getReexamine() {
        return reexamine;
    }

    public void setReexamine(String reexamine) {
        this.reexamine = reexamine;
    }

    public Date getReexamineTime() {
        return reexamineTime;
    }

    public void setReexamineTime(Date reexamineTime) {
        this.reexamineTime = reexamineTime;
    }

    public String getReexamineIdea() {
        return reexamineIdea;
    }

    public void setReexamineIdea(String reexamineIdea) {
        this.reexamineIdea = reexamineIdea;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public Integer getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(Integer isFlag) {
        this.isFlag = isFlag;
    }

    public List<Keduundergo> getEduList() {
        return eduList;
    }

    public void setEduList(List<Keduundergo> eduList) {
        this.eduList = eduList;
    }

    public List<Lworkresume> getWorkList() {
        return workList;
    }

    public void setWorkList(List<Lworkresume> workList) {
        this.workList = workList;
    }

    public List<Mfamilystatus> getFamilList() {
        return familList;
    }

    public void setFamilList(List<Mfamilystatus> familList) {
        this.familList = familList;
    }

    public PersonnelBean() {
    }

    @Override
    public String toString() {
        return "PersonnelBean{" +
                "fid=" + fid +
                ", gid=" + gid +
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
                ", role='" + role + '\'' +
                ", eduList=" + eduList +
                ", workList=" + workList +
                ", familList=" + familList +
                '}';
    }

}
