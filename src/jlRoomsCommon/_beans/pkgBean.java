/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jlRoomsCommon._beans;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;
import javax.faces.bean.ManagedBean;
/**
 *
 * @author lmeans
 */
@ManagedBean(name = "pkgBean")
public class pkgBean implements Serializable{
private int custPkgId,pkgId,custId,flagInd,sponsorId,cnt=1,cntKids,cntSr ;
    private String pkgDesc;
    private double pkgAmt,pkgFee;
    private Date modDate,pkgDate;
    private Time modTime;
    public pkgBean(){
        
    }
    /**
     * @return the custPkgId
     */
    public int getCustPkgId() {
        return custPkgId;
    }

    /**
     * @param custPkgId the custPkgId to set
     */
    public void setCustPkgId(int custPkgId) {
        this.custPkgId = custPkgId;
    }

    /**
     * @return the pkgId
     */
    public int getPkgId() {
        return pkgId;
    }

    /**
     * @param pkgId the pkgId to set
     */
    public void setPkgId(int pkgId) {
        this.pkgId = pkgId;
    }

    /**
     * @return the custId
     */
    public int getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * @return the flagInd
     */
    public int getFlagInd() {
        return flagInd;
    }

    /**
     * @param flagInd the flagInd to set
     */
    public void setFlagInd(int flagInd) {
        this.flagInd = flagInd;
    }

    /**
     * @return the sponsorId
     */
    public int getSponsorId() {
        return sponsorId;
    }

    /**
     * @param sponsorId the sponsorId to set
     */
    public void setSponsorId(int sponsorId) {
        this.sponsorId = sponsorId;
    }

    /**
     * @return the pkgDesc
     */
    public String getPkgDesc() {
        return pkgDesc;
    }

    /**
     * @param pkgDesc the pkgDesc to set
     */
    public void setPkgDesc(String pkgDesc) {
        this.pkgDesc = pkgDesc;
    }

    /**
     * @return the pkgAmt
     */
    public double getPkgAmt() {
        return pkgAmt;
    }

    /**
     * @param pkgAmt the pkgAmt to set
     */
    public void setPkgAmt(double pkgAmt) {
        this.pkgAmt = pkgAmt;
    }

    /**
     * @return the pkgFee
     */
    public double getPkgFee() {
        return pkgFee;
    }

    /**
     * @param pkgFee the pkgFee to set
     */
    public void setPkgFee(double pkgFee) {
        this.pkgFee = pkgFee;
    }

    /**
     * @return the modDate
     */
    public Date getModDate() {
        return modDate;
    }

    /**
     * @param modDate the modDate to set
     */
    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    /**
     * @return the pkgDate
     */
    public Date getPkgDate() {
        return pkgDate;
    }

    /**
     * @param pkgDate the pkgDate to set
     */
    public void setPkgDate(Date pkgDate) {
        this.pkgDate = pkgDate;
    }

    /**
     * @return the modTime
     */
    public Time getModTime() {
        return modTime;
    }

    /**
     * @param modTime the modTime to set
     */
    public void setModTime(Time modTime) {
        this.modTime = modTime;
    }

    /**
     * @return the cnt
     */
    public int getCnt() {
        return cnt;
    }

    /**
     * @param cnt the cnt to set
     */
    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    /**
     * @return the cntKids
     */
    public int getCntKids() {
        return cntKids;
    }

    /**
     * @param cntKids the cntKids to set
     */
    public void setCntKids(int cntKids) {
        this.cntKids = cntKids;
    }

    /**
     * @return the cntSr
     */
    public int getCntSr() {
        return cntSr;
    }

    /**
     * @param cntSr the cntSr to set
     */
    public void setCntSr(int cntSr) {
        this.cntSr = cntSr;
    }
}
