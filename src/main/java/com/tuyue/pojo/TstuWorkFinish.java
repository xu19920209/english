package com.tuyue.pojo;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 徐慷慨
 * 途悦信息
 * Created by dell on 2017/9/11.
 */
@Entity
@Table(name = "tstu_work_finish", schema = "aaenglish", catalog = "")
public class TstuWorkFinish {
    private int tid;
    private String sid;
    private String studentTape;
    private Integer isConsult;
    private Timestamp finishTime;
    private Integer nid;
    private Integer replyStatus;
    private Timestamp consultTime;
    private Double workScore;
    private String erro;
    private Integer sysGrade;
    private Double coefficient;
    private String startTime;
    private String endTime;

    @Override
    public TstuWorkFinish clone() throws CloneNotSupportedException {
        TstuWorkFinish tstuWorkFinish1 = new TstuWorkFinish();
        tstuWorkFinish1.setTid(getTid());
        tstuWorkFinish1.setEndTime(getEndTime());
        tstuWorkFinish1.setStartTime(getStartTime());
        tstuWorkFinish1.setSysGrade(getSysGrade());
        tstuWorkFinish1.setCoefficient(getCoefficient());
        tstuWorkFinish1.setIsConsult(getIsConsult());
        tstuWorkFinish1.setReplyStatus(getReplyStatus());
        tstuWorkFinish1.setNid(getNid());
        tstuWorkFinish1.setIsConsult(getIsConsult());
        tstuWorkFinish1.setReplyStatus(getReplyStatus());
        tstuWorkFinish1.setNid(getNid());
        tstuWorkFinish1.setSid(getSid());
        tstuWorkFinish1.setFinishTime(getFinishTime());
        tstuWorkFinish1.setConsultTime(getConsultTime());
        return tstuWorkFinish1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "sid")
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "studentTape")
    public String getStudentTape() {
        return studentTape;
    }

    public void setStudentTape(String studentTape) {
        this.studentTape = studentTape;
    }

    @Basic
    @Column(name = "isConsult")
    public Integer getIsConsult() {
        return isConsult;
    }

    public void setIsConsult(Integer isConsult) {
        this.isConsult = isConsult;
    }

    @Basic
    @Column(name = "finishTime")
    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    @Basic
    @Column(name = "nid")
    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TstuWorkFinish that = (TstuWorkFinish) o;

        if (tid != that.tid) return false;
        if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;
        if (studentTape != null ? !studentTape.equals(that.studentTape) : that.studentTape != null) return false;
        if (isConsult != null ? !isConsult.equals(that.isConsult) : that.isConsult != null) return false;
        if (finishTime != null ? !finishTime.equals(that.finishTime) : that.finishTime != null) return false;
        if (nid != null ? !nid.equals(that.nid) : that.nid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tid;
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (studentTape != null ? studentTape.hashCode() : 0);
        result = 31 * result + (isConsult != null ? isConsult.hashCode() : 0);
        result = 31 * result + (finishTime != null ? finishTime.hashCode() : 0);
        result = 31 * result + (nid != null ? nid.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "replyStatus")
    public Integer getReplyStatus() {
        return replyStatus;
    }

    public void setReplyStatus(Integer replyStatus) {
        this.replyStatus = replyStatus;
    }

    @Basic
    @Column(name = "consultTime")
    public Timestamp getConsultTime() {
        return consultTime;
    }

    public void setConsultTime(Timestamp consultTime) {
        this.consultTime = consultTime;
    }

    @Basic
    @Column(name = "workScore")
    public Double getWorkScore() {
        return workScore;
    }

    public void setWorkScore(Double workScore) {
        this.workScore = workScore;
    }

    @Basic
    @Column(name = "erro")
    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    @Override
    public String toString() {
        return "TstuWorkFinish{" +
                "tid=" + tid +
                ", sid='" + sid + '\'' +
                ", studentTape='" + studentTape + '\'' +
                ", isConsult=" + isConsult +
                ", finishTime=" + finishTime +
                ", nid=" + nid +
                ", replyStatus=" + replyStatus +
                ", consultTime=" + consultTime +
                ", workScore=" + workScore +
                ", erro='" + erro + '\'' +
                '}';
    }

    @Basic
    @Column(name = "sys_grade")
    public Integer getSysGrade() {
        return sysGrade;
    }

    public void setSysGrade(Integer sysGrade) {
        this.sysGrade = sysGrade;
    }

    @Basic
    @Column(name = "coefficient")
    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }

    @Basic
    @Column(name = "start_time")
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time")
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
