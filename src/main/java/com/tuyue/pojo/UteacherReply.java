package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/11.
 */
@Entity
@Table(name = "uteacher_reply", schema = "aaenglish", catalog = "")
public class UteacherReply {
    private int uid;
    private Integer tid;
    private Integer fid;
    private String replyWord;
    private String replyVoice;
    private Timestamp replyTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "tid")
    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
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
    @Column(name = "replyWord")
    public String getReplyWord() {
        return replyWord;
    }

    public void setReplyWord(String replyWord) {
        this.replyWord = replyWord;
    }

    @Basic
    @Column(name = "replyVoice")
    public String getReplyVoice() {
        return replyVoice;
    }

    public void setReplyVoice(String replyVoice) {
        this.replyVoice = replyVoice;
    }

    @Basic
    @Column(name = "replyTime")
    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UteacherReply that = (UteacherReply) o;

        if (uid != that.uid) return false;
        if (tid != null ? !tid.equals(that.tid) : that.tid != null) return false;
        if (fid != null ? !fid.equals(that.fid) : that.fid != null) return false;
        if (replyWord != null ? !replyWord.equals(that.replyWord) : that.replyWord != null) return false;
        if (replyVoice != null ? !replyVoice.equals(that.replyVoice) : that.replyVoice != null) return false;
        if (replyTime != null ? !replyTime.equals(that.replyTime) : that.replyTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (tid != null ? tid.hashCode() : 0);
        result = 31 * result + (fid != null ? fid.hashCode() : 0);
        result = 31 * result + (replyWord != null ? replyWord.hashCode() : 0);
        result = 31 * result + (replyVoice != null ? replyVoice.hashCode() : 0);
        result = 31 * result + (replyTime != null ? replyTime.hashCode() : 0);
        return result;
    }
}
