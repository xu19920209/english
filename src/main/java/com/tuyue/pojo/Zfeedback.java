package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @Author: 王金海
 * @Description:
 * @Date: Created by Administrator on 2017/9/15.
 * @Modified By:
 */
@Entity
public class Zfeedback {
    private int id;
    private String phone;
    private String email;
    private String img1;
    private String img2;
    private String content;
    private Timestamp ztime;
    private Integer flag;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "img1", nullable = true, length = 100)
    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    @Basic
    @Column(name = "img2", nullable = true, length = 100)
    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zfeedback zfeedback = (Zfeedback) o;

        if (id != zfeedback.id) return false;
        if (phone != null ? !phone.equals(zfeedback.phone) : zfeedback.phone != null) return false;
        if (email != null ? !email.equals(zfeedback.email) : zfeedback.email != null) return false;
        if (img1 != null ? !img1.equals(zfeedback.img1) : zfeedback.img1 != null) return false;
        if (img2 != null ? !img2.equals(zfeedback.img2) : zfeedback.img2 != null) return false;
        if (content != null ? !content.equals(zfeedback.content) : zfeedback.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (img1 != null ? img1.hashCode() : 0);
        result = 31 * result + (img2 != null ? img2.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "ztime", nullable = true)
    public Timestamp getZtime() {
        return ztime;
    }

    public void setZtime(Timestamp ztime) {
        this.ztime = ztime;
    }

    @Basic
    @Column(name = "flag", nullable = true)
    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Zfeedback{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", img1='" + img1 + '\'' +
                ", img2='" + img2 + '\'' +
                ", content='" + content + '\'' +
                ", ztime=" + ztime +
                ", flag=" + flag +
                '}';
    }
}
