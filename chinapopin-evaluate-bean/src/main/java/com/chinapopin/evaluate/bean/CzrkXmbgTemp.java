package com.chinapopin.evaluate.bean;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "CZRK_XMBG_TEMP")
public class CzrkXmbgTemp implements Serializable {
    @Id
    @Column(name = "YWLSH")
    private String ywlsh;

    @Column(name = "SJXBSF")
    private String sjxbsf;

    @Column(name = "XM")
    private String xm;

    @Column(name = "GMSFHM")
    private String gmsfhm;

    @Column(name = "BGGZQNR")
    private String bggzqnr;

    @Column(name = "BGGZHNR")
    private String bggzhnr;

    @Column(name = "CZSJ")
    private String czsj;

    @Column(name = "BLSJ")
    private String blsj;

    @Column(name = "SLDWMC")
    private String sldwmc;

    @Column(name = "SLDWDM")
    private String sldwdm;

    @Column(name = "SLRXM")
    private String slrxm;

    @Column(name = "SSXQMC")
    private String ssxqmc;

    private static final long serialVersionUID = 1L;

    /**
     * @return YWLSH
     */
    public String getYwlsh() {
        return ywlsh;
    }

    /**
     * @param ywlsh
     */
    public void setYwlsh(String ywlsh) {
        this.ywlsh = ywlsh == null ? null : ywlsh.trim();
    }

    /**
     * @return SJXBSF
     */
    public String getSjxbsf() {
        return sjxbsf;
    }

    /**
     * @param sjxbsf
     */
    public void setSjxbsf(String sjxbsf) {
        this.sjxbsf = sjxbsf == null ? null : sjxbsf.trim();
    }

    /**
     * @return XM
     */
    public String getXm() {
        return xm;
    }

    /**
     * @param xm
     */
    public void setXm(String xm) {
        this.xm = xm == null ? null : xm.trim();
    }

    /**
     * @return GMSFHM
     */
    public String getGmsfhm() {
        return gmsfhm;
    }

    /**
     * @param gmsfhm
     */
    public void setGmsfhm(String gmsfhm) {
        this.gmsfhm = gmsfhm == null ? null : gmsfhm.trim();
    }

    /**
     * @return BGGZQNR
     */
    public String getBggzqnr() {
        return bggzqnr;
    }

    /**
     * @param bggzqnr
     */
    public void setBggzqnr(String bggzqnr) {
        this.bggzqnr = bggzqnr == null ? null : bggzqnr.trim();
    }

    /**
     * @return BGGZHNR
     */
    public String getBggzhnr() {
        return bggzhnr;
    }

    /**
     * @param bggzhnr
     */
    public void setBggzhnr(String bggzhnr) {
        this.bggzhnr = bggzhnr == null ? null : bggzhnr.trim();
    }

    /**
     * @return CZSJ
     */
    public String getCzsj() {
        return czsj;
    }

    /**
     * @param czsj
     */
    public void setCzsj(String czsj) {
        this.czsj = czsj == null ? null : czsj.trim();
    }

    /**
     * @return BLSJ
     */
    public String getBlsj() {
        return blsj;
    }

    /**
     * @param blsj
     */
    public void setBlsj(String blsj) {
        this.blsj = blsj == null ? null : blsj.trim();
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
     * @return SSXQMC
     */
    public String getSsxqmc() {
        return ssxqmc;
    }

    /**
     * @param ssxqmc
     */
    public void setSsxqmc(String ssxqmc) {
        this.ssxqmc = ssxqmc == null ? null : ssxqmc.trim();
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
        CzrkXmbgTemp other = (CzrkXmbgTemp) that;
        return (this.getYwlsh() == null ? other.getYwlsh() == null : this.getYwlsh().equals(other.getYwlsh()))
                && (this.getSjxbsf() == null ? other.getSjxbsf() == null : this.getSjxbsf().equals(other.getSjxbsf()))
                && (this.getXm() == null ? other.getXm() == null : this.getXm().equals(other.getXm()))
                && (this.getGmsfhm() == null ? other.getGmsfhm() == null : this.getGmsfhm().equals(other.getGmsfhm()))
                && (this.getBggzqnr() == null ? other.getBggzqnr() == null : this.getBggzqnr().equals(other.getBggzqnr()))
                && (this.getBggzhnr() == null ? other.getBggzhnr() == null : this.getBggzhnr().equals(other.getBggzhnr()))
                && (this.getCzsj() == null ? other.getCzsj() == null : this.getCzsj().equals(other.getCzsj()))
                && (this.getBlsj() == null ? other.getBlsj() == null : this.getBlsj().equals(other.getBlsj()))
                && (this.getSldwmc() == null ? other.getSldwmc() == null : this.getSldwmc().equals(other.getSldwmc()))
                && (this.getSldwdm() == null ? other.getSldwdm() == null : this.getSldwdm().equals(other.getSldwdm()))
                && (this.getSlrxm() == null ? other.getSlrxm() == null : this.getSlrxm().equals(other.getSlrxm()))
                && (this.getSsxqmc() == null ? other.getSsxqmc() == null : this.getSsxqmc().equals(other.getSsxqmc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getYwlsh() == null) ? 0 : getYwlsh().hashCode());
        result = prime * result + ((getSjxbsf() == null) ? 0 : getSjxbsf().hashCode());
        result = prime * result + ((getXm() == null) ? 0 : getXm().hashCode());
        result = prime * result + ((getGmsfhm() == null) ? 0 : getGmsfhm().hashCode());
        result = prime * result + ((getBggzqnr() == null) ? 0 : getBggzqnr().hashCode());
        result = prime * result + ((getBggzhnr() == null) ? 0 : getBggzhnr().hashCode());
        result = prime * result + ((getCzsj() == null) ? 0 : getCzsj().hashCode());
        result = prime * result + ((getBlsj() == null) ? 0 : getBlsj().hashCode());
        result = prime * result + ((getSldwmc() == null) ? 0 : getSldwmc().hashCode());
        result = prime * result + ((getSldwdm() == null) ? 0 : getSldwdm().hashCode());
        result = prime * result + ((getSlrxm() == null) ? 0 : getSlrxm().hashCode());
        result = prime * result + ((getSsxqmc() == null) ? 0 : getSsxqmc().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ywlsh=").append(ywlsh);
        sb.append(", sjxbsf=").append(sjxbsf);
        sb.append(", xm=").append(xm);
        sb.append(", gmsfhm=").append(gmsfhm);
        sb.append(", bggzqnr=").append(bggzqnr);
        sb.append(", bggzhnr=").append(bggzhnr);
        sb.append(", czsj=").append(czsj);
        sb.append(", blsj=").append(blsj);
        sb.append(", sldwmc=").append(sldwmc);
        sb.append(", sldwdm=").append(sldwdm);
        sb.append(", slrxm=").append(slrxm);
        sb.append(", ssxqmc=").append(ssxqmc);
        sb.append("]");
        return sb.toString();
    }
}