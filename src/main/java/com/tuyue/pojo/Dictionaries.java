package com.tuyue.pojo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 徐慷慨
 * 途悦信息http://192.168.8.22:8090/AAEnglish/Download/words?words=boy,is
 * Created by dell on 2017/9/5.
 */
@Entity
public class Dictionaries {
    private int did;
    private String dword;
    private String dadd;
    private Integer dflag;


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "did")
    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    @Basic
    @Column(name = "dword")
    public String getDword() {
        return dword;
    }

    public void setDword(String dword) {
        this.dword = dword;
    }

    @Basic
    @Column(name = "dadd")
    public String getDadd() {
        return dadd;
    }

    public void setDadd(String dadd) {
        this.dadd = dadd;
    }

    @Basic
    @Column(name = "dflag")
    public Integer getDflag() {
        return dflag;
    }

    public void setDflag(Integer dflag) {
        this.dflag = dflag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dictionaries that = (Dictionaries) o;

        if (did != that.did) return false;
        if (dword != null ? !dword.equals(that.dword) : that.dword != null) return false;
        if (dadd != null ? !dadd.equals(that.dadd) : that.dadd != null) return false;
        if (dflag != null ? !dflag.equals(that.dflag) : that.dflag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = did;
        result = 31 * result + (dword != null ? dword.hashCode() : 0);
        result = 31 * result + (dadd != null ? dadd.hashCode() : 0);
        result = 31 * result + (dflag != null ? dflag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dictionaries{" +
                "did=" + did +
                ", dword='" + dword + '\'' +
                ", dadd='" + dadd + '\'' +
                ", dflag=" + dflag +
                '}';
    }
}
