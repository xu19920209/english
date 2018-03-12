package com.tuyue.pojo;

import javax.persistence.*;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2018/3/1.
 */
@Entity
public class Message {
    private int messageId;
    private Integer nid;
    private String message;
    private String creatTime;

    @Id
    @GeneratedValue
    @Column(name = "messageId")
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "creatTime")
    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Message message1 = (Message) object;

        if (messageId != message1.messageId) return false;
        if (nid != null ? !nid.equals(message1.nid) : message1.nid != null) return false;
        if (message != null ? !message.equals(message1.message) : message1.message != null) return false;
        if (creatTime != null ? !creatTime.equals(message1.creatTime) : message1.creatTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (creatTime != null ? creatTime.hashCode() : 0);
        return result;
    }
}
