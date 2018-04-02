package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/2/26.
 */
@Entity
@Table(name = "course_goods_order", schema = "aaenglish", catalog = "")
public class CourseGoodsOrder {
    private int courseOrderId;
    private Integer courseId;
    private Integer orderState;
    private Timestamp payTime;
    private Timestamp orderTime;
    private String orderNo;
    private Integer payWay;
    private Integer nid;
    private Double payMoney;
    private String details;
    private String address;
    private String name;
    private String phone;

    @Basic
    @Transient
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Id
    @Column(name = "courseOrderId")
    public int getCourseOrderId() {
        return courseOrderId;
    }

    public void setCourseOrderId(int courseOrderId) {
        this.courseOrderId = courseOrderId;
    }

    @Basic
    @Column(name = "courseId")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "orderState")
    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    @Basic
    @Column(name = "payTime")
    public Timestamp getPayTime() {
        return payTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    @Basic
    @Column(name = "orderTime")
    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "orderNo")
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "payWay")
    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
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
    @Column(name = "payMoney")
    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    @Basic
    @Column(name = "details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        CourseGoodsOrder that = (CourseGoodsOrder) object;

        if (courseOrderId != that.courseOrderId) return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (orderState != null ? !orderState.equals(that.orderState) : that.orderState != null) return false;
        if (payTime != null ? !payTime.equals(that.payTime) : that.payTime != null) return false;
        if (orderTime != null ? !orderTime.equals(that.orderTime) : that.orderTime != null) return false;
        if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) return false;
        if (payWay != null ? !payWay.equals(that.payWay) : that.payWay != null) return false;
        if (nid != null ? !nid.equals(that.nid) : that.nid != null) return false;
        if (payMoney != null ? !payMoney.equals(that.payMoney) : that.payMoney != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = courseOrderId;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (orderState != null ? orderState.hashCode() : 0);
        result = 31 * result + (payTime != null ? payTime.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (payWay != null ? payWay.hashCode() : 0);
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        result = 31 * result + (payMoney != null ? payMoney.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
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
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
