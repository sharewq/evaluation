package com.chinapopin.evaluate.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "XT_FWPJXX_TEMP")
public class XtFwpjxxTemp implements Serializable {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SLYWLXBH")
    private String slywlxbh;

    @Column(name = "SLYWLSH")
    private String slywlsh;

    @Column(name = "SLDWDM")
    private String sldwdm;

    @Column(name = "SLDWMC")
    private String sldwmc;

    @Column(name = "SLRBH")
    private Integer slrbh;

    @Column(name = "SLRXM")
    private String slrxm;

    @Column(name = "YWSSJZ")
    private String ywssjz;

    @Column(name = "SQRXM")
    private String sqrxm;

    @Column(name = "SQRLXDH")
    private String sqrlxdh;

    @Column(name = "FWPJJG")
    private Short fwpjjg;

    @Column(name = "FWPJSJ")
    private String fwpjsj;

    @Column(name = "CJSJ")
    private String cjsj;

    @Column(name = "RESERVE1")
    private String reserve1;

    @Column(name = "RESERVE2")
    private String reserve2;

    private static final long serialVersionUID = 1L;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return SLYWLXBH
     */
    public String getSlywlxbh() {
        return slywlxbh;
    }

    /**
     * @param slywlxbh
     */
    public void setSlywlxbh(String slywlxbh) {
        this.slywlxbh = slywlxbh == null ? null : slywlxbh.trim();
    }

    /**
     * @return SLYWLSH
     */
    public String getSlywlsh() {
        return slywlsh;
    }

    /**
     * @param slywlsh
     */
    public void setSlywlsh(String slywlsh) {
        this.slywlsh = slywlsh == null ? null : slywlsh.trim();
    }

    /**
     * @return SLDWDM
     */
    public String getSldwdm() {
        return sldwdm;
    }

    /**
     * @param sldwdm
     */
    public void setSldwdm(String sldwdm) {
        this.sldwdm = sldwdm == null ? null : sldwdm.trim();
    }

    /**
     * @return SLDWMC
     */
    public String getSldwmc() {
        return sldwmc;
    }

    /**
     * @param sldwmc
     */
    public void setSldwmc(String sldwmc) {
        this.sldwmc = sldwmc == null ? null : sldwmc.trim();
    }

    /**
     * @return SLRBH
     */
    public Integer getSlrbh() {
        return slrbh;
    }

    /**
     * @param slrbh
     */
    public void setSlrbh(Integer slrbh) {
        this.slrbh = slrbh;
    }

    /**
     * @return SLRXM
     */
    public String getSlrxm() {
        return slrxm;
    }

    /**
     * @param slrxm
     */
    public void setSlrxm(String slrxm) {
        this.slrxm = slrxm == null ? null : slrxm.trim();
    }

    /**
     * @return YWSSJZ
     */
    public String getYwssjz() {
        return ywssjz;
    }

    /**
     * @param ywssjz
     */
    public void setYwssjz(String ywssjz) {
        this.ywssjz = ywssjz == null ? null : ywssjz.trim();
    }

    /**
     * @return SQRXM
     */
    public String getSqrxm() {
        return sqrxm;
    }

    /**
     * @param sqrxm
     */
    public void setSqrxm(String sqrxm) {
        this.sqrxm = sqrxm == null ? null : sqrxm.trim();
    }

    /**
     * @return SQRLXDH
     */
    public String getSqrlxdh() {
        return sqrlxdh;
    }

    /**
     * @param sqrlxdh
     */
    public void setSqrlxdh(String sqrlxdh) {
        this.sqrlxdh = sqrlxdh == null ? null : sqrlxdh.trim();
    }

    /**
     * @return FWPJJG
     */
    public Short getFwpjjg() {
        return fwpjjg;
    }

    /**
     * @param fwpjjg
     */
    public void setFwpjjg(Short fwpjjg) {
        this.fwpjjg = fwpjjg;
    }

    /**
     * @return FWPJSJ
     */
    public String getFwpjsj() {
        return fwpjsj;
    }

    /**
     * @param fwpjsj
     */
    public void setFwpjsj(String fwpjsj) {
        this.fwpjsj = fwpjsj == null ? null : fwpjsj.trim();
    }

    /**
     * @return CJSJ
     */
    public String getCjsj() {
        return cjsj;
    }

    /**
     * @param cjsj
     */
    public void setCjsj(String cjsj) {
        this.cjsj = cjsj == null ? null : cjsj.trim();
    }

    /**
     * @return RESERVE1
     */
    public String getReserve1() {
        return reserve1;
    }

    /**
     * @param reserve1
     */
    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    /**
     * @return RESERVE2
     */
    public String getReserve2() {
        return reserve2;
    }

    /**
     * @param reserve2
     */
    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        XtFwpjxxTemp other = (XtFwpjxxTemp) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSlywlxbh() == null ? other.getSlywlxbh() == null : this.getSlywlxbh().equals(other.getSlywlxbh()))
            && (this.getSlywlsh() == null ? other.getSlywlsh() == null : this.getSlywlsh().equals(other.getSlywlsh()))
            && (this.getSldwdm() == null ? other.getSldwdm() == null : this.getSldwdm().equals(other.getSldwdm()))
            && (this.getSldwmc() == null ? other.getSldwmc() == null : this.getSldwmc().equals(other.getSldwmc()))
            && (this.getSlrbh() == null ? other.getSlrbh() == null : this.getSlrbh().equals(other.getSlrbh()))
            && (this.getSlrxm() == null ? other.getSlrxm() == null : this.getSlrxm().equals(other.getSlrxm()))
            && (this.getYwssjz() == null ? other.getYwssjz() == null : this.getYwssjz().equals(other.getYwssjz()))
            && (this.getSqrxm() == null ? other.getSqrxm() == null : this.getSqrxm().equals(other.getSqrxm()))
            && (this.getSqrlxdh() == null ? other.getSqrlxdh() == null : this.getSqrlxdh().equals(other.getSqrlxdh()))
            && (this.getFwpjjg() == null ? other.getFwpjjg() == null : this.getFwpjjg().equals(other.getFwpjjg()))
            && (this.getFwpjsj() == null ? other.getFwpjsj() == null : this.getFwpjsj().equals(other.getFwpjsj()))
            && (this.getCjsj() == null ? other.getCjsj() == null : this.getCjsj().equals(other.getCjsj()))
            && (this.getReserve1() == null ? other.getReserve1() == null : this.getReserve1().equals(other.getReserve1()))
            && (this.getReserve2() == null ? other.getReserve2() == null : this.getReserve2().equals(other.getReserve2()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSlywlxbh() == null) ? 0 : getSlywlxbh().hashCode());
        result = prime * result + ((getSlywlsh() == null) ? 0 : getSlywlsh().hashCode());
        result = prime * result + ((getSldwdm() == null) ? 0 : getSldwdm().hashCode());
        result = prime * result + ((getSldwmc() == null) ? 0 : getSldwmc().hashCode());
        result = prime * result + ((getSlrbh() == null) ? 0 : getSlrbh().hashCode());
        result = prime * result + ((getSlrxm() == null) ? 0 : getSlrxm().hashCode());
        result = prime * result + ((getYwssjz() == null) ? 0 : getYwssjz().hashCode());
        result = prime * result + ((getSqrxm() == null) ? 0 : getSqrxm().hashCode());
        result = prime * result + ((getSqrlxdh() == null) ? 0 : getSqrlxdh().hashCode());
        result = prime * result + ((getFwpjjg() == null) ? 0 : getFwpjjg().hashCode());
        result = prime * result + ((getFwpjsj() == null) ? 0 : getFwpjsj().hashCode());
        result = prime * result + ((getCjsj() == null) ? 0 : getCjsj().hashCode());
        result = prime * result + ((getReserve1() == null) ? 0 : getReserve1().hashCode());
        result = prime * result + ((getReserve2() == null) ? 0 : getReserve2().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", slywlxbh=").append(slywlxbh);
        sb.append(", slywlsh=").append(slywlsh);
        sb.append(", sldwdm=").append(sldwdm);
        sb.append(", sldwmc=").append(sldwmc);
        sb.append(", slrbh=").append(slrbh);
        sb.append(", slrxm=").append(slrxm);
        sb.append(", ywssjz=").append(ywssjz);
        sb.append(", sqrxm=").append(sqrxm);
        sb.append(", sqrlxdh=").append(sqrlxdh);
        sb.append(", fwpjjg=").append(fwpjjg);
        sb.append(", fwpjsj=").append(fwpjsj);
        sb.append(", cjsj=").append(cjsj);
        sb.append(", reserve1=").append(reserve1);
        sb.append(", reserve2=").append(reserve2);
        sb.append("]");
        return sb.toString();
    }
}