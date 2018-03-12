package com.tuyue.webModules.studentAndclass.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/11.
 * @Modified By:
 */
public class StudentAndClassBean {

    private int nid;
    private Integer eid;
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
    @JSONField(format = "yyyy-MM-dd")
    private Date applyTime;
    private String username;
    private String password;
    private String courseName;
    private Integer oid; // 班级ID



    private ClassBean classBean;//当前班级
    private List<PstudenthistoryBean> pstudenthistoryBeans;// 历史上课班级

    @Override
    public String toString() {
        return "StudentAndClassBean{" + "nid=" + nid + ", eid=" + eid + ", studentName='" + studentName + '\'' + ", idNo='" + idNo + '\'' + ", sex=" + sex + ", nativePlace='" + nativePlace + '\'' + ", nation='" + nation + '\'' + ", schoolClass='" + schoolClass + '\'' + ", province='" + province + '\'' + ", city='" + city + '\'' + ", districts='" + districts + '\'' + ", address='" + address + '\'' + ", fatherName='" + fatherName + '\'' + ", fatherTel='" + fatherTel + '\'' + ", motherName='" + motherName + '\'' + ", motherTel='" + motherTel + '\'' + ", urgentName='" + urgentName + '\'' + ", urgentTel='" + urgentTel + '\'' + ", applyTime=" + applyTime + ", username='" + username + '\'' + ", password='" + password + '\'' + ", courseName='" + courseName + '\'' + ", oid=" + oid + ", classBean=" + classBean + ", pstudenthistoryBeans=" + pstudenthistoryBeans + '}';
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getFatherTel() {
        return fatherTel;
    }

    public void setFatherTel(String fatherTel) {
        this.fatherTel = fatherTel;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMotherTel() {
        return motherTel;
    }

    public void setMotherTel(String motherTel) {
        this.motherTel = motherTel;
    }

    public String getUrgentName() {
        return urgentName;
    }

    public void setUrgentName(String urgentName) {
        this.urgentName = urgentName;
    }

    public String getUrgentTel() {
        return urgentTel;
    }

    public void setUrgentTel(String urgentTel) {
        this.urgentTel = urgentTel;
    }
    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public ClassBean getClassBean() {
        return classBean;
    }

    public void setClassBean(ClassBean classBean) {
        this.classBean = classBean;
    }

    public List<PstudenthistoryBean> getPstudenthistoryBeans() {
        return pstudenthistoryBeans;
    }

    public void setPstudenthistoryBeans(List<PstudenthistoryBean> pstudenthistoryBeans) {
        this.pstudenthistoryBeans = pstudenthistoryBeans;
    }

    public StudentAndClassBean() {
    }

    public StudentAndClassBean(int nid, Integer eid, String studentName, String idNo, Integer sex, String nativePlace, String nation, String schoolClass, String province, String city, String districts, String address, String fatherName, String fatherTel, String motherName, String motherTel, String urgentName, String urgentTel, Date applyTime, String username, String password, String courseName, Integer oid, ClassBean classBean, List<PstudenthistoryBean> pstudenthistoryBeans) {
        this.nid = nid;
        this.eid = eid;
        this.studentName = studentName;
        this.idNo = idNo;
        this.sex = sex;
        this.nativePlace = nativePlace;
        this.nation = nation;
        this.schoolClass = schoolClass;
        this.province = province;
        this.city = city;
        this.districts = districts;
        this.address = address;
        this.fatherName = fatherName;
        this.fatherTel = fatherTel;
        this.motherName = motherName;
        this.motherTel = motherTel;
        this.urgentName = urgentName;
        this.urgentTel = urgentTel;
        this.applyTime = applyTime;
        this.username = username;
        this.password = password;
        this.courseName = courseName;
        this.oid = oid;
        this.classBean = classBean;
        this.pstudenthistoryBeans = pstudenthistoryBeans;
    }
}
